package pl.coderslab.wtm.dto.user;

import pl.coderslab.wtm.repository.entity.Organization;

import java.time.LocalDateTime;

public class UserDto {
    private Long id;
    private String name;
    private String mail;
    private LocalDateTime lastLogin;
    private Organization organization;
    private Integer teamCount;

    public UserDto(Long id, String name, String mail, LocalDateTime lastLogin, Organization organization, Integer teamCount) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.lastLogin = lastLogin;
        this.organization = organization;
        this.teamCount = teamCount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Integer getTeamCount() {
        return teamCount;
    }
}
