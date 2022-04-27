package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.organization.OrganizationDto;
import pl.coderslab.wtm.dto.organization.OrganizationUpdateDto;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.dto.user.UserUpdateDto;
import pl.coderslab.wtm.repository.OrganizationRepository;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;

import java.util.Optional;

@Service
public class OrganizationService {

    private OrganizationRepository organizationRepository;
    private Mapper mapper;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, Mapper mapper) {
        this.organizationRepository = organizationRepository;
        this.mapper = mapper;
    }

    public OrganizationDto findById(Long id) {
        return organizationRepository.findById(id).map(this::toDto).orElseThrow(() -> new RuntimeException("Organization not found"));
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public OrganizationDto update(OrganizationUpdateDto organizationUpdate) {
        Optional<Organization> organization = organizationRepository.findById(organizationUpdate.getId());
        Organization organizationMapped = organization.map(this::toOrganization).orElseThrow(() -> new RuntimeException("Organization not found"));
        organizationMapped.setName(organizationUpdate.getName());
        organizationMapped.setActive(organizationUpdate.getActive());
        organizationRepository.save(organizationMapped);
        return toDto(organizationMapped);
    }

    private OrganizationDto toDto(Organization organization) {
        return mapper.toDto(organization);
    }

/*    private Organization toOrganization(Organization organization) {
        return mapper.toOrganization(organization);
    }*/

}
