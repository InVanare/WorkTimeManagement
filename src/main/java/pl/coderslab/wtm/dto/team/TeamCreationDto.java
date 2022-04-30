package pl.coderslab.wtm.dto.team;

import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class TeamCreationDto {

    private String name;
    private User owner;
    private Organization organization;
    private List<User> members;
    private Boolean isActive;
    private LocalDateTime created;

    public TeamCreationDto(String name, User owner, Organization organization, List<User> members, Boolean isActive, LocalDateTime created) {
        this.name = name;
        this.owner = owner;
        this.organization = organization;
        this.members = members;
        this.isActive = true;
        this.created = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public Organization getOrganization() {
        return organization;
    }

    public List<User> getMembers() {
        return members;
    }

    public Boolean getActive() {
        return isActive;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "TeamCreationDto{" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", organization=" + organization +
                ", members=" + members +
                ", isActive=" + isActive +
                ", created=" + created +
                '}';
    }
}
