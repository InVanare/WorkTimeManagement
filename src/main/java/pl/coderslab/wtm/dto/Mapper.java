package pl.coderslab.wtm.dto;

import org.springframework.stereotype.Component;
import pl.coderslab.wtm.dto.organization.OrganizationCreationDto;
import pl.coderslab.wtm.dto.organization.OrganizationDto;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.dto.user.UserCreationDto;

@Component
public class Mapper {

    private User user = new User();
    private Organization organization = new Organization();

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getMail(),
                user.getLastLogin(),
                user.getOrganization(),
                user.getTeamCount());
    }

    public User toUser(UserCreationDto userDTO) {
        user.setName(userDTO.getName());
        user.setPass(userDTO.getPass());
        user.setMail(userDTO.getMail());
        user.setActive(userDTO.getActive());
        user.setCreated(userDTO.getCreated());
        user.setLastLogin(userDTO.getLastLogin());
        user.setTeamCount(userDTO.getTeamCount());
        return user;
    }

    public User toUser(User userMap) {
        user.setId(userMap.getId());
        user.setName(userMap.getName());
        user.setPass(userMap.getPass());
        user.setMail(userMap.getMail());
        user.setActive(userMap.getActive());
        user.setCreated(userMap.getCreated());
        user.setLastLogin(userMap.getLastLogin());
        user.setOrganization(userMap.getOrganization());
        user.setTeamCount(userMap.getTeamCount());
        return user;
    }

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