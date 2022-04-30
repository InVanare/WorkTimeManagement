package pl.coderslab.wtm.dto.team;

import pl.coderslab.wtm.dto.user.UserCreationDto;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;

public class MapperTeam {

    private Team team = new Team();

    public TeamDto toDto(Team team) {
        return new TeamDto(team.getId(),
                team.getName(),
                team.getOrganization(),
                team.getMembers());
    }

    public Team toTeam(TeamCreationDto teamDTO) {
        team.setName(teamDTO.getName());
        team.setOwner(teamDTO.getOwner());
        team.setOrganization(teamDTO.getOrganization());
        team.setMembers(teamDTO.getMembers());
        team.setActive(teamDTO.getActive());
        team.setCreated(teamDTO.getCreated());
        return team;
    }

    public Team toTeam(Team teamMap) {
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
