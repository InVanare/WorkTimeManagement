package pl.coderslab.wtm.dto.organization;

import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;

import java.util.List;

public class OrganizationDto {

    private Long id;
    private String name;
    private List<User> users;
    private Integer maxUser;
    private Integer countUser;
    private List<Team> teamList;

    public OrganizationDto(Long id, String name, List<User> users, Integer maxUser, Integer countUser, List<Team> teamList) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.maxUser = maxUser;
        this.countUser = countUser;
        this.teamList = teamList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public Integer getMaxUser() {
        return maxUser;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public List<Team> getTeamList() {
        return teamList;
    }
}