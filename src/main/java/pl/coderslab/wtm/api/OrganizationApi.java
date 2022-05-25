package pl.coderslab.wtm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.organization.OrganizationCreationDto;
import pl.coderslab.wtm.dto.organization.OrganizationDto;
import pl.coderslab.wtm.dto.organization.OrganizationUpdateDto;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.service.OrganizationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/organization")
public class OrganizationApi {

    private final OrganizationService organizationService;
    private final Mapper mapper;

    @Autowired
    public OrganizationApi(OrganizationService organizationService, Mapper mapper) {
        this.organizationService = organizationService;
        this.mapper = mapper;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable Long id) {
        return ResponseEntity.of(organizationService.findById(id));
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<OrganizationDto> getOrganizationByName(@PathVariable String name) {
        return ResponseEntity.of(organizationService.findByName(name));
    }

    @GetMapping("/get/my")
    public ResponseEntity<OrganizationDto> getOrganizationByMe() {
        return ResponseEntity.of(organizationService.getOrganizationByMe());
    }

    @GetMapping("/add/user/{name}")
    public ResponseEntity<OrganizationDto> addUserToOrganization(@PathVariable String name) {
        return ResponseEntity.of(organizationService.addUser(name));
    }

    @PostMapping("/create")
    public ResponseEntity<OrganizationDto> addOrganization(@Valid @RequestBody OrganizationCreationDto organizationCreation) {
        return ResponseEntity.of(organizationService.creatOrganization(organizationCreation));
    }

    @PutMapping("/update")
    public ResponseEntity<OrganizationDto> updateOrganization(@Valid @RequestBody OrganizationUpdateDto organization) {
        return ResponseEntity.of(organizationService.update(organization));
    }
}
