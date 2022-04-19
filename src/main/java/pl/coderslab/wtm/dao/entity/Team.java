package pl.coderslab.wtm.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private Long ownerId;
    private Long organizationId;
    private Boolean isActive;
    private LocalDateTime created;

    public Team(Long id, String name, Long ownerId, Long organizationId, Boolean isActive, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.organizationId = organizationId;
        this.isActive = isActive;
        this.created = created;
    }

    public Team() {
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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
}
