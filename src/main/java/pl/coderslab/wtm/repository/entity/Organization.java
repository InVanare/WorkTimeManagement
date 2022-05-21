package pl.coderslab.wtm.repository.entity;

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
    @OneToOne
    private User owner;
    @OneToMany(mappedBy = "organization")
    private List<User> users;
    private Integer countUser;
    @OneToMany(mappedBy = "organization")
    private List<Team> teamList;
    private LocalDateTime created;

    public Organization(Long id, String name, Boolean isActive, User owner, List<User> users, Integer countUser, List<Team> teamList, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.owner = owner;
        this.users = users;
        this.countUser = countUser;
        this.teamList = teamList;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public void setCountUser(Integer countUser) {
        this.countUser = countUser;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
