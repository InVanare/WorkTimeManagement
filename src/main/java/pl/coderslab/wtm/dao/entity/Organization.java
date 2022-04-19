package pl.coderslab.wtm.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private Boolean isActive;
    @OneToMany(mappedBy = "organization")
    private List<User> owner;
    private Integer maxUser;
    private Integer countUser;
    private LocalDateTime created;

    public Organization(Long id, String name, Boolean isActive, List<User> owner, Integer maxUser, Integer countUser, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.owner = owner;
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

    public List<User> getOwner() {
        return owner;
    }

    public void setOwner(List<User> owner) {
        this.owner = owner;
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
