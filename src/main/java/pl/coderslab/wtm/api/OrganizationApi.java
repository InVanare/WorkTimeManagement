package pl.coderslab.wtm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.service.OrganizationService;

import java.util.Optional;

@RestController
@RequestMapping("/api/organization")
public class OrganizationApi {

    private OrganizationService organizationService;

    @Autowired
    public OrganizationApi(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/get/{id}")
    public Optional<Organization> getOrganization(@PathVariable Long id) {
        return organizationService.findById(id);
    }

    @GetMapping("/get/all")
    public Iterable<Organization> getAllOrganization() {
        return organizationService.findAll();
    }

    @PostMapping("/add")
    public Organization addOrganization(@RequestBody Organization organization) {
        return organizationService.save(organization);
    }

    @PutMapping("/update")
    public Organization updateOrganization(@RequestBody Organization organization) {
        return organizationService.save(organization);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrganization(@PathVariable Long id) {
        organizationService.deleteById(id);
    }
}
