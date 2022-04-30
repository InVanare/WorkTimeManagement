package pl.coderslab.wtm.api;

import org.springframework.http.ResponseEntity;
import pl.coderslab.wtm.dto.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.wtm.dto.user.UserUpdateDto;
import pl.coderslab.wtm.repository.entity.User;
import pl.coderslab.wtm.dto.user.UserDto;
import pl.coderslab.wtm.dto.user.UserCreationDto;
import pl.coderslab.wtm.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private UserService userService;
    private Mapper mapper;

    @Autowired
    public UserApi(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.of(userService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserCreationDto userCreation) {
        User user = mapper.toUser(userCreation);
        System.out.println(userCreation.getName());
        System.out.println(userCreation.getPass());
        System.out.println(userCreation.getMail());
        userService.save(user);
        return ResponseEntity.ok(mapper.toDto(user));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateDto user) {
        return ResponseEntity.of(userService.update(user));
    }
}
