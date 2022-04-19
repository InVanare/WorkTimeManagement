package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.dao.UserRepository;
import pl.coderslab.wtm.dao.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new User(1L, "inva", "afdsfsdg", "test@gmail.com", true, LocalDateTime.now(), LocalDateTime.now().plusDays(2), null, 2));
    }
}
