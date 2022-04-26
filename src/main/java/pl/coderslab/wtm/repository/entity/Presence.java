package pl.coderslab.wtm.repository.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "presences")
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    @OneToOne
    private User user;
    @OneToOne
    private Organization organization;

    public Presence(Long id, LocalDateTime start, LocalDateTime end, User user, Organization organization) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.user = user;
        this.organization = organization;
    }

    public Presence() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
