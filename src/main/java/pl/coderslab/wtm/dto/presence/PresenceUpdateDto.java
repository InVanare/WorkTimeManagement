package pl.coderslab.wtm.dto.presence;

import java.time.LocalDateTime;

public class PresenceUpdateDto {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;

    public PresenceUpdateDto(Long id, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.start = start;
        this.end = end;
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
}
