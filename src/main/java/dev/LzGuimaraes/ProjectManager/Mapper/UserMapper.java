package dev.LzGuimaraes.ProjectManager.Mapper;

import dev.LzGuimaraes.ProjectManager.dto.User.UserResponseDTO;
import dev.LzGuimaraes.ProjectManager.model.User;

public class UserMapper {
   
    public static UserResponseDTO toDTO(User user) {
        
        return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );
    }

    public static User toEntity(UserResponseDTO userDTO) {
        
        User user = new User();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setRole(userDTO.role());
        return user;
    }
}
