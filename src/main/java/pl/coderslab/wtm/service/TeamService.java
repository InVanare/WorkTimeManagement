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
        List<Optional<Team>> teams = teamRepository.findByName(name);
        String username = securityContext.getName();

        if (teams.isEmpty()) {
            return Optional.empty();
        }


        //Team teamMapped = teams.stream().filter(team -> team.get().getOwner().equals(name)).findFirst().orElseThrow(RuntimeException::new).get();
        for (Optional<Team> singleTeam : teams) {
            Team team = singleTeam.orElseThrow(RuntimeException::new);
            if (username.equals(team.getOwner().getUsername()) || isMember(team.getMembers(), username)) {
                return Optional.ofNullable(mapper.toDto(team));
            }
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
        List<Optional<Team>> teams = teamRepository.findAllByOwnerAndIsActive(user, true);

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
        user.setTeamCount(user.getTeamCount()+1);
        userRepository.save(user);
        return Optional.ofNullable(mapper.toDto(teamMapped));
    }

    public Optional<TeamDto> update(TeamUpdateDto teamUpdate) {
        System.out.println(teamUpdate.getNameTeam());
        System.out.println(teamUpdate.getNameToUpdate());
        System.out.println(teamUpdate.getActive());
        Optional<User> user = userRepository.findByUsername(securityContext.getName());
        List<Optional<Team>> team = teamRepository.findAllByOwnerAndIsActive(user.orElseThrow(RuntimeException::new), true);
        if (team.isEmpty()) {
            return Optional.empty();
        }

        Team teamMapped;
        for (Optional<Team> singleTeam : team) {
            if (singleTeam.get().getName().equals(teamUpdate.getNameTeam())) {
                for (Optional<Team> singleTeam1 : team) {
                    if (singleTeam1.get().getName().equals(teamUpdate.getNameToUpdate())) {
                        return Optional.empty();
                    }
                }
                teamMapped = singleTeam.get();
                teamMapped.setName(teamUpdate.getNameToUpdate());
                teamMapped.setActive(teamUpdate.getActive());
                teamRepository.save(teamMapped);
                return Optional.ofNullable(mapper.toDto(teamMapped));
            }
        }

        return Optional.empty();
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
