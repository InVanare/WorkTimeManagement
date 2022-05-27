package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.organization.OrganizationCreationDto;
import pl.coderslab.wtm.dto.organization.OrganizationDto;
import pl.coderslab.wtm.dto.organization.OrganizationUpdateDto;
import pl.coderslab.wtm.repository.OrganizationRepository;
import pl.coderslab.wtm.repository.UserRepository;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.utility.SecurityContext;
import pl.coderslab.wtm.utility.Validation;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final Validation validation;
    private final SecurityContext securityContext;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, UserRepository userRepository, Mapper mapper, Validation validation, SecurityContext securityContext) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validation = validation;
        this.securityContext = securityContext;
    }

    public Optional<OrganizationDto> findById(Long id) {
        for (GrantedAuthority single : securityContext.getAuthorities()) {
            if (single.getAuthority().equals("ROLE_ADMIN")) {
                return organizationRepository.findById(id).map(mapper::toDto);
            }
        }
        return Optional.empty();
    }

    public Optional<OrganizationDto> findByName(String name) {
        Optional<Organization> organization = organizationRepository.findByName(name);
        String username = securityContext.getName();

        if (organization.isEmpty()) {
            return Optional.empty();
        }
        Organization organizationMapped = organization.get();

        if (username.equals(organizationMapped.getOwner().getUsername()) || isMember(organizationMapped.getUsers(), username)) {
            return organization.map(mapper::toDto);
        }
        return Optional.empty();
    }

    public Optional<Organization> findByOwnerAndIsActive(String name, Boolean bool) {
        return organizationRepository.findByOwnerAndIsActive(userRepository.findByUsername(name).orElseThrow(RuntimeException::new), bool);
    }

    public Optional<OrganizationDto> getOrganizationByMe() {
        return findByOwnerAndIsActive(securityContext.getName(), true).map(mapper::toDto);
    }

    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    public Optional<OrganizationDto> creatOrganization(OrganizationCreationDto organizationCreation) {
        String username = securityContext.getName();
        if (findByOwnerAndIsActive(username, true).isEmpty()) {
            if (organizationRepository.findByName(organizationCreation.getName()).isEmpty()){
                User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
                organizationCreation.setOwner(user);
                Organization organization = mapper.toOrganization(organizationCreation);
                organization.setUsers(List.of(user));
                save(organization);
                user.setOrganization(organization);
                userRepository.save(user);
                return Optional.ofNullable(mapper.toDto(organization));
            }
        }
        return Optional.empty();
    }

    public Optional<OrganizationDto> update(OrganizationUpdateDto organizationUpdate) {
        Optional<Organization> organizationByName = organizationRepository.findByName(organizationUpdate.getName());
        if (!organizationByName.isEmpty()) {
            return Optional.empty();
        }
        Optional<Organization> organizationByOwner = findByOwnerAndIsActive(securityContext.getName(), true);

        if (organizationByOwner.isEmpty()) {
            return Optional.empty();
        }

        Organization organizationMappedByOwner = organizationByOwner.get();

        organizationMappedByOwner.setActive(organizationUpdate.getActive());
        organizationMappedByOwner.setName(organizationUpdate.getName());
        save(organizationMappedByOwner);
        return Optional.ofNullable(mapper.toDto(organizationMappedByOwner));
    }

    public Optional<OrganizationDto> addUser(String name) {
        Optional<User> owner = userRepository.findByUsername(securityContext.getName());
        if (owner.isEmpty()) {
            return Optional.empty();
        }

        Optional<User> userToAdd = userRepository.findByUsername(name);
        if (userToAdd.isEmpty()) {
            return Optional.empty();
        }
        if (userToAdd.orElseThrow(RuntimeException::new).getOrganization() != null) {
            return Optional.empty();
        }

        Optional<Organization> organization = organizationRepository.findByOwnerAndIsActive(owner.get(), true);
        if (organization.isEmpty()) {
            return Optional.empty();
        }

        Organization organizationMapped = organization.orElseThrow(RuntimeException::new);
        List<User> userList = organizationMapped.getUsers();
        User user = userToAdd.orElseThrow(RuntimeException::new);
        userList.add(user);
        organizationMapped.setUsers(userList);
        organizationMapped.setCountUser(organizationMapped.getCountUser()+1);
        organizationRepository.save(organizationMapped);
        user.setOrganization(organizationMapped);
        userRepository.save(user);
        return Optional.ofNullable(mapper.toDto(organizationMapped));
    }

    public Optional<OrganizationDto> removeUser(String name) {
        Optional<User> owner = userRepository.findByUsername(securityContext.getName());
        if (owner.isEmpty()) {
            return Optional.empty();
        }

        Optional<User> userToRemove = userRepository.findByUsername(name);
        if (userToRemove.isEmpty()) {
            return Optional.empty();
        }
        User user = userToRemove.orElseThrow(RuntimeException::new);
        User ownerMapped = owner.orElseThrow(RuntimeException::new);
        if (user.getOrganization() == null) {
            return Optional.empty();
        }
        if (user.getOrganization().getId() != ownerMapped.getOrganization().getId()) {
            return Optional.empty();
        }

        Optional<Organization> organization = organizationRepository.findByOwnerAndIsActive(ownerMapped, true);
        if (organization.isEmpty()) {
            return Optional.empty();
        }

        Organization organizationMapped = organization.orElseThrow(RuntimeException::new);
        List<User> userList = organizationMapped.getUsers();
        userList.remove(user);
        organizationMapped.setUsers(userList);
        organizationMapped.setCountUser(organizationMapped.getCountUser()-1);
        organizationRepository.save(organizationMapped);
        user.setOrganization(null);
        userRepository.save(user);

        return Optional.ofNullable(mapper.toDto(organizationMapped));
    }

    private Boolean isMember(List<User> userList, String username) {
        for (User singleUser : userList) {
            if (singleUser.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
