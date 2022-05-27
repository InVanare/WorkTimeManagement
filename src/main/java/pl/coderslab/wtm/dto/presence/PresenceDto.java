package pl.coderslab.wtm.dto.presence;

import pl.coderslab.wtm.repository.entity.User;

import java.time.LocalDateTime;

public class PresenceDto {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String user;
    private String organization;
    private Boolean finished;

    public PresenceDto(Long id, LocalDateTime start, LocalDateTime end, String user, String organization, Boolean finished) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.user = user;
        this.organization = organization;
        this.finished = finished;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getUser() {
        return user;
    }

    public String getOrganization() {
        return organization;
    }

    public Boolean getFinished() {
        return finished;
    }
}
