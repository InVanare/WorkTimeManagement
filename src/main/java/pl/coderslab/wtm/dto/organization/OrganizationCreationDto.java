package pl.coderslab.wtm.dto.organization;

import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class OrganizationCreationDto {

    private String name;
    private Boolean isActive;
    private User owner;
    private List<User> users;
    private Integer maxUser;
    private Integer countUser;
    private List<Team> teamList;
    private LocalDateTime created;

    public OrganizationCreationDto(String name, Long owner, List<User> users, Integer maxUser, List<Team> teamList) {
        this.name = name;
        this.isActive = true;
        //this.owner = owner;
        //this.users = users;
        this.maxUser = maxUser;
        this.countUser = 1;
        //this.teamList = teamList;
        this.created = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public User getOwner() {
        return owner;
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

    public LocalDateTime getCreated() {
        return created;
    }
}
