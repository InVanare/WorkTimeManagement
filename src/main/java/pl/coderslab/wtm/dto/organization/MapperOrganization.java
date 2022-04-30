package pl.coderslab.wtm.dto.organization;

import pl.coderslab.wtm.repository.entity.Organization;

public class MapperOrganization {

    private Organization organization = new Organization();

    public OrganizationDto toDto(Organization organization) {
        return new OrganizationDto(organization.getId(),
                organization.getName(),
                organization.getUsers(),
                organization.getMaxUser(),
                organization.getCountUser(),
                organization.getTeamList());
    }

    public Organization toOrganization(OrganizationCreationDto organizationDTO) {
        organization.setName(organizationDTO.getName());
        organization.setActive(organizationDTO.getActive());
        organization.setOwner(organizationDTO.getOwner());
        organization.setUsers(organizationDTO.getUsers());
        organization.setMaxUser(organizationDTO.getMaxUser());
        organization.setCountUser(organizationDTO.getCountUser());
        organization.setCreated(organizationDTO.getCreated());

        return organization;
    }

    public Organization toOrganization(Organization organizationMap) {
        organization.setId(organizationMap.getId());
        organization.setName(organizationMap.getName());
        organization.setActive(organizationMap.getActive());
        organization.setOwner(organizationMap.getOwner());
        organization.setUsers(organizationMap.getUsers());
        organization.setMaxUser(organizationMap.getMaxUser());
        organization.setCountUser(organizationMap.getCountUser());
        organization.setCreated(organizationMap.getCreated());
        return organization;
    }
}
