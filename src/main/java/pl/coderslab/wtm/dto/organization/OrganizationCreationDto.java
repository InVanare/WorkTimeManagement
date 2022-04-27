package pl.coderslab.wtm.dto.organization;

import java.time.LocalDateTime;

public class OrganizationCreationDto {

    private String name;
    private Boolean isActive;
    private Long ownerId;
    private Integer maxUser;
    private Integer countUser;
    private LocalDateTime created;

    public OrganizationCreationDto(String name, Boolean isActive, Long ownerId, Integer maxUser, Integer countUser, LocalDateTime created) {
        this.name = name;
        this.isActive = isActive;
        this.ownerId = ownerId;
        this.maxUser = maxUser;
        this.countUser = countUser;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Integer getMaxUser() {
        return maxUser;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
