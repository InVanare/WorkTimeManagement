package pl.coderslab.wtm.dto.team;

import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

public class TeamCreationDto {

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d]{3,20}$")
    private String name;
    private Boolean isActive;
    private LocalDateTime created;

    public TeamCreationDto(String name) {
        this.name = name;
        this.isActive = true;
        this.created = LocalDateTime.now();
    }

    public TeamCreationDto() {
        this.isActive = true;
        this.created = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public LocalDateTime getCreated() {
        return created;
    }

}
