package pl.coderslab.wtm.dto.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class UserCreationDto {

    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d]{5,20}$")
    private String name;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,20}$")
    private String pass;

    @Email
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
