package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.user.UserUpdateDto;
import pl.coderslab.wtm.repository.UserRepository;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.utility.EnumUpdate;
import pl.coderslab.wtm.utility.Validation;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final Mapper mapper;
    private final Validation validation;

    @Autowired
    public UserService(UserRepository userRepository, Mapper mapper, Validation validation) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.validation = validation;
    }

    public Optional<UserDto> findById(Long id) {
        //authentication = SecurityContextHolder.getContext().getAuthentication();
        //if (authentication.)
        return userRepository.findById(id).map(mapper::toDto);
    }

    public Optional<UserDto> findByUsername(String username) {
        if (getName().equals(username)) {
            return userRepository.findByUsername(username).map(mapper::toDto);
        }
        return Optional.empty();
    }


    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<UserDto> update(UserUpdateDto userUpdate, EnumUpdate enumUpdate) {
        Optional<User> user = userRepository.findByUsername(getName());

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

    public String getName() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
