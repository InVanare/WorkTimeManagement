package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.user.UserCreationDto;
import pl.coderslab.wtm.dto.user.UserUpdateDto;
import pl.coderslab.wtm.repository.RoleRepository;
import pl.coderslab.wtm.repository.UserRepository;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.utility.EnumUpdate;
import pl.coderslab.wtm.utility.Validation;

import javax.persistence.EntityExistsException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Mapper mapper;
    private final Validation validation;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, Mapper mapper, Validation validation) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.validation = validation;
    }

    public Optional<UserDto> findById(Long id) {

        for (GrantedAuthority single : securityGetAuthorities()) {
            if (single.getAuthority().equals("ROLE_ADMIN")) {
                return userRepository.findById(id).map(mapper::toDto);
            }
        }
        return Optional.empty();
    }

    public Optional<UserDto> findByUsername(String username) {
        if (securityGetName().equals(username)) {
            return userRepository.findByUsername(username).map(mapper::toDto);
        }
        return Optional.empty();
    }

    public Optional<UserDto> getUserByMe() {
        return userRepository.findByUsername(securityGetName()).map(mapper::toDto);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<UserDto> register(UserCreationDto userCreation) {
        userCreation.setRole(List.of(roleRepository.findByName("ROLE_USER").orElseThrow(RuntimeException::new)));
        if (userRepository.findByUsernameOrMail(userCreation.getName(), userCreation.getMail()).isEmpty()) {
            User user = mapper.toUser(userCreation);
            save(user);
            return Optional.ofNullable(mapper.toDto(user));
        }
        return Optional.empty();
    }

    public Optional<UserDto> update(UserUpdateDto userUpdate, EnumUpdate enumUpdate) {
        System.out.println(userUpdate.getPass());
        Optional<User> user = userRepository.findByUsername(securityGetName());

        if (user.isEmpty()) {
            return Optional.empty();
        }
        User userMapped = user.get();

        switch (enumUpdate) {
            case PASS:
                if (validation.validateUserUpdate(userUpdate.getPass(), enumUpdate)) {
                    userMapped.setPassword(userUpdate.getPass());
                    userRepository.save(userMapped);
                }
                break;
            case MAIL:
                if (validation.validateUserUpdate(userUpdate.getMail(), enumUpdate)) {
                    userMapped.setMail(userUpdate.getMail());
                    userRepository.save(userMapped);
                }
                break;
            case ACTIVE:
                try {
                    if (validation.validateUserUpdate(userUpdate.getActive(), enumUpdate)) {
                        userMapped.setActive(userUpdate.getActive());
                        userRepository.save(userMapped);
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return Optional.ofNullable(mapper.toDto(userMapped));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityExistsException("User " + username + " doesn't exist"));
    }

    public String securityGetName() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    public List<GrantedAuthority> securityGetAuthorities() {
        return (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
