package pl.coderslab.wtm.dto.organization;

import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;

import java.util.List;

public class OrganizationDto {

    private Long id;
    private String name;
    private String owner;
    private List<String> users;
    private Integer countUser;
    private List<String> teamList;

    public OrganizationDto(Long id, String name, String owner, List<String> users, Integer countUser, List<String> teamList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.users = users;
        this.countUser = countUser;
        this.teamList = teamList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public List<String> getUsers() {
        return users;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public List<String> getTeamList() {
        return teamList;
    }
}