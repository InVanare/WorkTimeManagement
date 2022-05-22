package pl.coderslab.wtm.dto.team;

import pl.coderslab.wtm.dto.user.UserCreationDto;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;

import java.util.stream.Collectors;

public class MapperTeam {

    public TeamDto toDto(Team team) {
        return new TeamDto(team.getId(),
                team.getName(),
                team.getOrganization().getName(),
                team.getMembers().stream().map(User::getUsername).collect(Collectors.toList()));
    }

    public Team toTeam(TeamCreationDto teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        team.setActive(teamDTO.getActive());
        team.setCreated(teamDTO.getCreated());
        return team;
    }

    public Team toTeam(Team teamMap) {
        Team team = new Team();
        team.setId(teamMap.getId());
        team.setName(teamMap.getName());
        team.setOwner(teamMap.getOwner());
        team.setOrganization(teamMap.getOrganization());
        team.setMembers(teamMap.getMembers());
        team.setActive(teamMap.getActive());
        team.setCreated(teamMap.getCreated());
        return team;
    }


}
