package pl.coderslab.wtm.dao.entity;

import java.time.LocalDateTime;

public class Organization {

    private Long id;
    private String name;
    private Boolean isActive;
    private Long ownerId;
    private Integer maxUser;
    private Integer countUser;
    private LocalDateTime created;

    public Organization(Long id, String name, Boolean isActive, Long ownerId, Integer maxUser, Integer countUser, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.ownerId = ownerId;
        this.maxUser = maxUser;
        this.countUser = countUser;
        this.created = created;
    }

    public Organization() {
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(Integer maxUser) {
        this.maxUser = maxUser;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public void setCountUser(Integer countUser) {
        this.countUser = countUser;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
