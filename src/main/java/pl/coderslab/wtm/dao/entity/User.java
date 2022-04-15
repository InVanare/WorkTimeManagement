package pl.coderslab.wtm.dao.entity;

import java.time.LocalDateTime;

public class User {

    private Long id;
    private String name;
    private String password;
    private String mail;
    private Boolean isActive;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private Organization organization;
    private Integer teamCount;

    public User(Long id, String name, String password, String mail, Boolean isActive, LocalDateTime created, LocalDateTime lastLogin, Organization organization, Integer teamCount) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.isActive = isActive;
        this.created = created;
        this.lastLogin = lastLogin;
        this.organization = organization;
        this.teamCount = teamCount;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Integer getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(Integer teamCount) {
        this.teamCount = teamCount;
    }
}
