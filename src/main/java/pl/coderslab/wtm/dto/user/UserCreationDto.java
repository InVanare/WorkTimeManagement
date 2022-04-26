package pl.coderslab.wtm.dto.user;

import java.time.LocalDateTime;

public class UserCreationDto {

    private String name;
    private String pass;
    private String mail;
    private Boolean isActive;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private Integer teamCount;

    public UserCreationDto(String name, String pass, String mail) {
        LocalDateTime time = LocalDateTime.now();
        this.name = name;
        this.pass = pass;
        this.mail = mail;
        this.isActive = true;
        this.created = time;
        this.lastLogin = time;
        this.teamCount = 0;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getMail() {
        return mail;
    }

    public Boolean getActive() {
        return isActive;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public Integer getTeamCount() {
        return teamCount;
    }
}
