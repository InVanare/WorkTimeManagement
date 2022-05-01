package pl.coderslab.wtm.dto.presence;

import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;

import java.time.LocalDateTime;

public class PresenceCreationDto {

    private LocalDateTime start;
    private LocalDateTime end;
    private User user;
    private Organization organization;

    public PresenceCreationDto() {
        this.start = LocalDateTime.now();
        this.end = LocalDateTime.now();
        this.user = user;
        this.organization = organization;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public User getUser() {
        return user;
    }

    public Organization getOrganization() {
        return organization;
    }
}
