package pl.coderslab.wtm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.team.TeamCreationDto;
import pl.coderslab.wtm.dto.team.TeamDto;
import pl.coderslab.wtm.dto.team.TeamUpdateDto;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.service.TeamService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamApi {

    private final TeamService teamService;
    private final Mapper mapper;

    @Autowired
    public TeamApi(TeamService teamService, Mapper mapper) {
        this.teamService = teamService;
        this.mapper = mapper;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable Long id) {
        return ResponseEntity.of(teamService.findById(id));
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<TeamDto> getTeamByName(@PathVariable String name) {
        return ResponseEntity.of(teamService.findByName(name));
    }

    @GetMapping("/get/my")
    public ResponseEntity<List<TeamDto>> getTeamByMe() {
        return ResponseEntity.ok(teamService.getTeamByMe());
    }

    @GetMapping("/add/user/{username}/team/{teamname}")
    public ResponseEntity<TeamDto> addUserToTeam(@PathVariable String username,@PathVariable String teamname) {
        return ResponseEntity.of(teamService.addUser(username, teamname));
    }
    @PostMapping("/create")
    public ResponseEntity<TeamDto> addTeam(@Valid @RequestBody TeamCreationDto teamCreation) {
        return ResponseEntity.of(teamService.create(teamCreation));
    }

    @PutMapping("/update")
    public ResponseEntity<TeamDto> updateTeam(@Valid @RequestBody TeamUpdateDto team) {
        return ResponseEntity.of(teamService.update(team));
    }
}
