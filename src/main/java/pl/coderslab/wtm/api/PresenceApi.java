package pl.coderslab.wtm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.presence.PresenceCreationDto;
import pl.coderslab.wtm.dto.presence.PresenceDto;
import pl.coderslab.wtm.dto.presence.PresenceUpdateDto;
import pl.coderslab.wtm.repository.entity.Presence;
import pl.coderslab.wtm.service.PresenceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/presence")
public class PresenceApi {

    private final PresenceService presenceService;
    private final Mapper mapper;

    @Autowired
    public PresenceApi(PresenceService presenceService, Mapper mapper) {
        this.presenceService = presenceService;
        this.mapper = mapper;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<PresenceDto> getPresence(@PathVariable Long id) {
        return ResponseEntity.of(presenceService.findById(id));
    }

    @GetMapping("/get/user/{name}")
    public ResponseEntity<List<PresenceDto>> getPresenceFromUser(@PathVariable String name) {
        return ResponseEntity.ok(presenceService.findByUser(name));
    }

    @GetMapping("/start")
    public ResponseEntity<PresenceDto> startPresence() {
        return ResponseEntity.of(presenceService.startPresence());
    }

    @GetMapping("/finish")
    public ResponseEntity<PresenceDto> endPresence() {
        return ResponseEntity.of(presenceService.endPresence());
    }
}
