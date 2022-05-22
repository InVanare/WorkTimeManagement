package pl.coderslab.wtm.dto.team;

import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;

import java.util.List;

public class TeamDto {

    private Long id;
    private String name;
    private String organization;
    private List<String> members;

    public TeamDto(Long id, String name, String organization, List<String> members) {
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

    public String getOrganization() {
        return organization;
    }

    public List<String> getMembers() {
        return members;
    }
}
