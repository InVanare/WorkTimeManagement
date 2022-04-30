package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dto.user.UserUpdateDto;
import pl.coderslab.wtm.repository.UserRepository;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.dto.Mapper;
import pl.coderslab.wtm.dto.user.UserDto;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService {

    private UserRepository userRepository;
    private Mapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(this::toDto);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<UserDto> update(UserUpdateDto userUpdate) {
        Optional<User> user = userRepository
                .findById(userUpdate.getId())
                .map(u -> {
                    u.setPass(userUpdate.getPass());
                    u.setMail(userUpdate.getMail());
                    u.setActive(userUpdate.getIsActive());
                    System.out.println("test2");
                    return u;
                });

        if (user.isEmpty()) {
            return Optional.empty();
        }
        User userMapped = user.map(this::toUser).get();
        userRepository.save(userMapped);
        return Optional.ofNullable(toDto(userMapped));
    }

    //TODO: w przyszłości do usunięcia
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new User(1L, "inva", "afdsfsdg", "test@gmail.com", true, LocalDateTime.now(), LocalDateTime.now().plusDays(2), null, 2));
        save(new User(2L, "test", "afdsfsdga123", "test123@gmail.com", true, LocalDateTime.now(), LocalDateTime.now().plusDays(3), null, 20));

    }

    private UserDto toDto(User user) {
        return mapper.toDto(user);
    }

    private User toUser(User user) {
        return mapper.toUser(user);
    }
}
