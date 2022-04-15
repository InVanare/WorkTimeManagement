package pl.coderslab.wtm.dao.entity;

import java.time.LocalDateTime;

public class Presence {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long userId;
    private Long organizationId;

    public Presence(Long id, LocalDateTime start, LocalDateTime end, Long userId, Long organizationId) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.userId = userId;
        this.organizationId = organizationId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
