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
import pl.coderslab.wtm.utility.EnumUpdate;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final UserService userService;
    private final Mapper mapper;

    @Autowired
    public UserApi(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.of(userService.findById(id));
    }

    @GetMapping("/get/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.of(userService.findByUsername(username));
    }

    @GetMapping("/get/my")
    public ResponseEntity<UserDto> getUserByMe() {
        return ResponseEntity.of(userService.getUserByMe());
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserCreationDto userCreation) {
        return ResponseEntity.of(userService.register(userCreation));
    }

    @PutMapping("/update/password")
    public ResponseEntity<UserDto> updateUserPassword(@RequestBody UserUpdateDto pass) {
        return ResponseEntity.of(userService.update(pass, EnumUpdate.PASS));
    }
    @PutMapping("/update/mail")
    public ResponseEntity<UserDto> updateUserMail(@RequestBody UserUpdateDto mail) {
        return ResponseEntity.of(userService.update(mail, EnumUpdate.MAIL));
    }
    @PutMapping("/update/active")
    public ResponseEntity<UserDto> updateUserActive(@RequestBody UserUpdateDto active) {
        return ResponseEntity.of(userService.update(active, EnumUpdate.ACTIVE));
    }
}
