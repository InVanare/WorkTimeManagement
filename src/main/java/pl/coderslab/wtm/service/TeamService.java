package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.team.TeamDto;
import pl.coderslab.wtm.dto.team.TeamUpdateDto;
import pl.coderslab.wtm.repository.TeamRepository;
import pl.coderslab.wtm.repository.entity.Team;

import java.util.Optional;

@Service
public class TeamService {

    private TeamRepository teamRepository;
    private Mapper mapper;

    @Autowired
    public TeamService(TeamRepository teamRepository, Mapper mapper) {
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    public Optional<TeamDto> findById(Long id) {
        return teamRepository.findById(id).map(this::toDto);
    }

    public Team save(Team team) {
        return teamRepository.save(team);
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
        Team teamMapped = team.map(this::toTeam).get();
        teamRepository.save(teamMapped);
        return Optional.ofNullable(toDto(teamMapped));
    }

    private TeamDto toDto(Team team) {
        return mapper.toDto(team);
    }

    private Team toTeam(Team team) {
        return mapper.toTeam(team);
    }
}
