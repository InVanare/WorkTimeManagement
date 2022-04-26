package pl.coderslab.wtm.api;

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
    public UserDto getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/add")
    public UserDto addUser(@RequestBody UserCreationDto userCreationDTO) {
        User user = mapper.toUser(userCreationDTO);
        userService.save(user);
        return mapper.toDto(user);
    }

    @PutMapping("/update")
    public UserDto updateUser(@RequestBody UserUpdateDto user) {
        return userService.update(user);
    }
}
