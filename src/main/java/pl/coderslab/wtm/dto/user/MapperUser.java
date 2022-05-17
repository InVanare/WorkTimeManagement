package pl.coderslab.wtm.dto.user;

import pl.coderslab.wtm.repository.entity.User;

public class MapperUser {

    private User user = new User();

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getMail(),
                user.getLastLogin(),
                user.getOrganization(),
                user.getTeamCount());
    }

    public User toUser(UserCreationDto userDTO) {
        user.setUsername(userDTO.getName());
        user.setPassword(userDTO.getPass());
        user.setMail(userDTO.getMail());
        user.setActive(userDTO.getActive());
        user.setCreated(userDTO.getCreated());
        user.setLastLogin(userDTO.getLastLogin());
        user.setTeamCount(userDTO.getTeamCount());
        user.setRoles(userDTO.getRole());
        return user;
    }

    public User toUser(User userMap) {
        user.setId(userMap.getId());
        user.setUsername(userMap.getUsername());
        user.setPassword(userMap.getPassword());
        user.setMail(userMap.getMail());
        user.setActive(userMap.getActive());
        user.setCreated(userMap.getCreated());
        user.setLastLogin(userMap.getLastLogin());
        user.setOrganization(userMap.getOrganization());
        user.setTeamCount(userMap.getTeamCount());
        return user;
    }

}
