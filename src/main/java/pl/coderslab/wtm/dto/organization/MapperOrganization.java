package pl.coderslab.wtm.dto.organization;

import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;

import java.util.stream.Collectors;

public class MapperOrganization {

    public OrganizationDto toDto(Organization organization) {
        return new OrganizationDto(organization.getId(),
                organization.getName(),
                organization.getOwner().getUsername(),
                organization.getUsers().stream().map(User::getUsername).collect(Collectors.toList()),
                organization.getCountUser(),
                organization.getTeamList().stream().map(Team::getName).collect(Collectors.toList()));
    }

    public Organization toOrganization(OrganizationCreationDto organizationDTO) {
        Organization organization = new Organization();
        organization.setName(organizationDTO.getName());
        organization.setActive(organizationDTO.getActive());
        organization.setOwner(organizationDTO.getOwner());
        organization.setCountUser(organizationDTO.getCountUser());
        organization.setCreated(organizationDTO.getCreated());

        return organization;
    }

    public Organization toOrganization(Organization organizationMap) {
        Organization organization = new Organization();
        organization.setId(organizationMap.getId());
        organization.setName(organizationMap.getName());
        organization.setActive(organizationMap.getActive());
        organization.setOwner(organizationMap.getOwner());
        organization.setUsers(organizationMap.getUsers());
        organization.setCountUser(organizationMap.getCountUser());
        organization.setCreated(organizationMap.getCreated());
        return organization;
    }
}
