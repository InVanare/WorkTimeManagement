package pl.coderslab.wtm.dto.team;

import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;

import java.util.List;

public class TeamDto {

    private Long id;
    private String name;
    private Organization organization;
    private List<User> members;

    public TeamDto(Long id, String name, Organization organization, List<User> members) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public List<User> getMembers() {
        return members;
    }
}
