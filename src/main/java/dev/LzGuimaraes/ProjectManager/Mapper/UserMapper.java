package dev.LzGuimaraes.ProjectManager.Mapper;

import org.springframework.stereotype.Component;

import dev.LzGuimaraes.ProjectManager.dto.User.UserRequestDTO;
import dev.LzGuimaraes.ProjectManager.dto.User.UserResponseDTO;
import dev.LzGuimaraes.ProjectManager.model.User;

@Component
public class UserMapper {
   
    public UserResponseDTO toDTO(User user) {
        
        return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );
    }

    public User toEntity(UserRequestDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        return user;
    }
}
