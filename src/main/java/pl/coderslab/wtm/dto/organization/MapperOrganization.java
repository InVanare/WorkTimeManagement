package pl.coderslab.wtm.dto.organization;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.user.MapperUser;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;

import java.util.stream.Collectors;

public class MapperOrganization {

    private Organization organization = new Organization();

    public OrganizationDto toDto(Organization organization) {
        return new OrganizationDto(organization.getId(),
                organization.getName(),
                organization.getOwner().getUsername(),
                organization.getUsers().stream().map(User::getUsername).collect(Collectors.toList()),
                organization.getCountUser(),
                organization.getTeamList());
    }

    public Organization toOrganization(OrganizationCreationDto organizationDTO) {
        organization.setName(organizationDTO.getName());
        organization.setActive(organizationDTO.getActive());
        organization.setOwner(organizationDTO.getOwner());
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
        organization.setCountUser(organizationMap.getCountUser());
        organization.setCreated(organizationMap.getCreated());
        return organization;
    }
}
