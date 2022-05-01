package pl.coderslab.wtm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.presence.PresenceCreationDto;
import pl.coderslab.wtm.dto.presence.PresenceDto;
import pl.coderslab.wtm.dto.presence.PresenceUpdateDto;
import pl.coderslab.wtm.dto.team.TeamCreationDto;
import pl.coderslab.wtm.dto.team.TeamDto;
import pl.coderslab.wtm.dto.team.TeamUpdateDto;
import pl.coderslab.wtm.repository.entity.Presence;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.service.PresenceService;
import pl.coderslab.wtm.service.TeamService;

@RestController
@RequestMapping("/api/presence")
public class PresenceApi {

    private PresenceService presenceService;
    private Mapper mapper;

    @Autowired
    public PresenceApi(PresenceService presenceService, Mapper mapper) {
        this.presenceService = presenceService;
        this.mapper = mapper;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PresenceDto> getPresence(@PathVariable Long id) {
        return ResponseEntity.of(presenceService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<PresenceDto> addPresence(@RequestBody PresenceCreationDto presenceCreation) {
        Presence presence = mapper.toPresence(presenceCreation);
        presenceService.save(presence);
        return ResponseEntity.ok(mapper.toDto(presence));
    }

    @PutMapping("/update")
    public ResponseEntity<PresenceDto> updatePresence(@RequestBody PresenceUpdateDto presence) {
        return ResponseEntity.of(presenceService.update(presence));
    }
}
