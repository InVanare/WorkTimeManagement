package pl.coderslab.wtm.dto;

import org.springframework.stereotype.Component;
import pl.coderslab.wtm.dto.organization.MapperOrganization;
import pl.coderslab.wtm.dto.organization.OrganizationCreationDto;
import pl.coderslab.wtm.dto.organization.OrganizationDto;
import pl.coderslab.wtm.dto.team.MapperTeam;
import pl.coderslab.wtm.dto.team.TeamCreationDto;
import pl.coderslab.wtm.dto.team.TeamDto;
import pl.coderslab.wtm.dto.user.MapperUser;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.dto.user.UserCreationDto;

@Component
public class Mapper {

    private MapperUser mapperUser;
    private MapperOrganization mapperOrganization;
    private MapperTeam mapperTeam;

    public Mapper() {
        this.mapperUser = new MapperUser();
        this.mapperOrganization = new MapperOrganization();
        this.mapperTeam = new MapperTeam();
    }

    public UserDto toDto(User user) {
        return mapperUser.toDto(user);
    }

    public User toUser(UserCreationDto userDTO) {
        return mapperUser.toUser(userDTO);
    }

    public User toUser(User userMap) {
        return mapperUser.toUser(userMap);
    }

    public OrganizationDto toDto(Organization organization) {
        return mapperOrganization.toDto(organization);
    }

    public Organization toOrganization(OrganizationCreationDto organizationDTO) {
        return mapperOrganization.toOrganization(organizationDTO);
    }

    public Organization toOrganization(Organization organizationMap) {
        return mapperOrganization.toOrganization(organizationMap);
    }

    public TeamDto toDto(Team team) {
        return mapperTeam.toDto(team);
    }

    public Team toTeam(TeamCreationDto teamDTO) {
        return mapperTeam.toTeam(teamDTO);
    }

    public Team toTeam(Team teamMap) {
        return mapperTeam.toTeam(teamMap);
    }
}