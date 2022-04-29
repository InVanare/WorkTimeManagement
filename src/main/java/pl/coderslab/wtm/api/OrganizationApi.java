package pl.coderslab.wtm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.organization.OrganizationCreationDto;
import pl.coderslab.wtm.dto.organization.OrganizationDto;
import pl.coderslab.wtm.dto.organization.OrganizationUpdateDto;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.service.OrganizationService;

import java.util.Optional;

@RestController
@RequestMapping("/api/organization")
public class OrganizationApi {

    private OrganizationService organizationService;
    private Mapper mapper;

    @Autowired
    public OrganizationApi(OrganizationService organizationService, Mapper mapper) {
        this.organizationService = organizationService;
        this.mapper = mapper;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable Long id) {
        return ResponseEntity.of(organizationService.findById(id));
    }

    @PostMapping("/add")
    public OrganizationDto addOrganization(@RequestBody OrganizationCreationDto organizationCreation) {
        Organization organization = mapper.toOrganization(organizationCreation);
        organizationService.save(organization);
        return mapper.toDto(organization);
    }

    @PutMapping("/update")
    public ResponseEntity<OrganizationDto> updateOrganization(@RequestBody OrganizationUpdateDto organization) {
        return ResponseEntity.of(organizationService.update(organization));
    }
}
