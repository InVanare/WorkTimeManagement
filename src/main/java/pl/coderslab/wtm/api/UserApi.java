package pl.coderslab.wtm.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.wtm.dao.entity.User;
import pl.coderslab.wtm.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public Optional<User> getUser() {
        return userService.findById(1L);
        //Organization organization = new Organization(1L, "org", true, List.of(user), 10, 0, LocalDateTime.now());

    }
}
