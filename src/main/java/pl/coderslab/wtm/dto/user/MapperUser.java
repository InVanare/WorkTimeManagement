package pl.coderslab.wtm.dto.user;

import pl.coderslab.wtm.repository.entity.User;

public class MapperUser {

    private User user = new User();

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getMail(),
                user.getLastLogin(),
                user.getOrganization(),
                user.getTeamCount());
    }

    public User toUser(UserCreationDto userDTO) {
        user.setName(userDTO.getName());
        user.setPass(userDTO.getPass());
        user.setMail(userDTO.getMail());
        user.setActive(userDTO.getActive());
        user.setCreated(userDTO.getCreated());
        user.setLastLogin(userDTO.getLastLogin());
        user.setTeamCount(userDTO.getTeamCount());
        return user;
    }

    public User toUser(User userMap) {
        user.setId(userMap.getId());
        user.setName(userMap.getName());
        user.setPass(userMap.getPass());
        user.setMail(userMap.getMail());
        user.setActive(userMap.getActive());
        user.setCreated(userMap.getCreated());
        user.setLastLogin(userMap.getLastLogin());
        user.setOrganization(userMap.getOrganization());
        user.setTeamCount(userMap.getTeamCount());
        return user;
    }

}
