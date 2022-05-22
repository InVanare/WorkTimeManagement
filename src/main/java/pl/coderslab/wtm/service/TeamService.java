package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.team.TeamCreationDto;
import pl.coderslab.wtm.dto.team.TeamDto;
import pl.coderslab.wtm.dto.team.TeamUpdateDto;
import pl.coderslab.wtm.repository.OrganizationRepository;
import pl.coderslab.wtm.repository.TeamRepository;
import pl.coderslab.wtm.repository.UserRepository;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.utility.SecurityContext;
import pl.coderslab.wtm.utility.Validation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final Validation validation;
    private final SecurityContext securityContext;

    @Autowired
    public TeamService(TeamRepository teamRepository, OrganizationRepository organizationRepository, UserRepository userRepository, Mapper mapper, Validation validation, SecurityContext securityContext) {
        this.teamRepository = teamRepository;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validation = validation;
        this.securityContext = securityContext;
    }

    public Optional<TeamDto> findById(Long id) {
        for (GrantedAuthority single : securityContext.getAuthorities()) {
            if (single.getAuthority().equals("ROLE_ADMIN")) {
                return teamRepository.findById(id).map(mapper::toDto);
            }
        }
        return Optional.empty();
    }

    public Optional<TeamDto> findByName(String name) {
        Optional<Team> team = teamRepository.findByName(name);
        String username = securityContext.getName();

        if (team.isEmpty()) {
            return Optional.empty();
        }
        Team teamMapped = team.get();
        if (username.equals(teamMapped.getOwner().getUsername()) || isMember(teamMapped.getMembers(), username)) {
            return team.map(mapper::toDto);
        }
        return Optional.empty();
    }

    public List<TeamDto> getTeamByMe() {
        String username = securityContext.getName();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return List.of();
        }
        Optional<Organization> organization = Optional.ofNullable(user.get().getOrganization());
        if (organization.isEmpty()) {
            return List.of();
        }
        List<Team> teamList = organization.get().getTeamList();

        return teamList.stream().filter(t -> t.getActive()).map(mapper::toDto).collect(Collectors.toList());
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Optional<TeamDto> create(TeamCreationDto teamCreation) {
        User user = userRepository.findByUsername(securityContext.getName()).orElseThrow(RuntimeException::new);
        Optional<Organization> organization = organizationRepository.findByOwnerAndIsActive(user, true);
        List<Optional<Team>> teams = teamRepository.findAllByOwner(user);

        if (organization.isEmpty()) {
            return Optional.empty();
        }

        if (!teams.isEmpty()) {
            for (Optional<Team> singleTeam : teams) {
                if (singleTeam.get().getName().equals(teamCreation.getName())) {
                    return Optional.empty();
                }
            }
        }

        Team teamMapped = mapper.toTeam(teamCreation);
        teamMapped.setOwner(user);
        teamMapped.setOrganization(organization.get());
        teamMapped.setMembers(List.of(user));
        save(teamMapped);
        return Optional.ofNullable(mapper.toDto(teamMapped));
    }

    public Optional<TeamDto> update(TeamUpdateDto teamUpdate) {
        Optional<Team> team = teamRepository
                .findById(teamUpdate.getId())
                .map(u -> {
                    u.setName(teamUpdate.getName());
                    u.setActive(teamUpdate.getActive());
                    return u;
                });

        if (team.isEmpty()) {
            return Optional.empty();
        }
        Team teamMapped = team.map(mapper::toTeam).get();
        teamRepository.save(teamMapped);
        return Optional.ofNullable(mapper.toDto(teamMapped));
    }

    private Boolean isMember(List<User> userList, String username) {
        for (User singleUser : userList) {
            if (singleUser.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
