package pl.coderslab.wtm.dto.presence;

import pl.coderslab.wtm.repository.entity.User;

import java.time.LocalDateTime;

public class PresenceDto {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private User user;

    public PresenceDto(Long id, LocalDateTime start, LocalDateTime end, User user) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.user = user;
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

    public User getUser() {
        return user;
    }
}
