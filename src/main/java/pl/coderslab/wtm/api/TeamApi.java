package pl.coderslab.wtm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.organization.OrganizationCreationDto;
import pl.coderslab.wtm.dto.organization.OrganizationDto;
import pl.coderslab.wtm.dto.organization.OrganizationUpdateDto;
import pl.coderslab.wtm.dto.team.TeamCreationDto;
import pl.coderslab.wtm.dto.team.TeamDto;
import pl.coderslab.wtm.dto.team.TeamUpdateDto;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.service.OrganizationService;
import pl.coderslab.wtm.service.TeamService;

@RestController
@RequestMapping("/api/team")
public class TeamApi {

    private TeamService teamService;
    private Mapper mapper;

    @Autowired
    public TeamApi(TeamService teamService, Mapper mapper) {
        this.teamService = teamService;
        this.mapper = mapper;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable Long id) {
        return ResponseEntity.of(teamService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<TeamDto> addTeam(@RequestBody TeamCreationDto teamCreation) {
        System.out.println(teamCreation);
        Team team = mapper.toTeam(teamCreation);
        teamService.save(team);
        return ResponseEntity.ok(mapper.toDto(team));
    }

    @PutMapping("/update")
    public ResponseEntity<TeamDto> updateTeam(@RequestBody TeamUpdateDto team) {
        return ResponseEntity.of(teamService.update(team));
    }
}
