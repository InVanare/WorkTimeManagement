package pl.coderslab.wtm.dto.organization;

import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

public class OrganizationCreationDto {

    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z\\d]{2,20}$")
    private String name;
    private Boolean isActive;
    private User owner;
    private Integer countUser;
    private LocalDateTime created;

    public OrganizationCreationDto(String name) {
        this.name = name;
        this.isActive = true;
        this.countUser = 1;
        this.created = LocalDateTime.now();
    }

    public OrganizationCreationDto() {
        this.isActive = true;
        this.countUser = 1;
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

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
