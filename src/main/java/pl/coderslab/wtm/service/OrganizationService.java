package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.organization.OrganizationDto;
import pl.coderslab.wtm.dto.organization.OrganizationUpdateDto;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.dto.user.UserUpdateDto;
import pl.coderslab.wtm.repository.OrganizationRepository;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;

import java.time.LocalDateTime;
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

    public Optional<OrganizationDto> findById(Long id) {
        return organizationRepository.findById(id).map(this::toDto);
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Optional<OrganizationDto> update(OrganizationUpdateDto organizationUpdate) {
        Optional<Organization> organization = organizationRepository
                .findById(organizationUpdate.getId())
                .map(org -> {
                    org.setName(organizationUpdate.getName());
                    org.setActive(organizationUpdate.getActive());
                    return org;
                });

        if (organization.isEmpty()) {
            return Optional.empty();
        }

        Organization organizationMapped = organization.map(this::toOrganization).get();
        organizationRepository.save(organizationMapped);
        return Optional.ofNullable(toDto(organizationMapped));
    }

    private OrganizationDto toDto(Organization organization) {
        return mapper.toDto(organization);
    }

    private Organization toOrganization(Organization organization) {
        return mapper.toOrganization(organization);
    }

}
