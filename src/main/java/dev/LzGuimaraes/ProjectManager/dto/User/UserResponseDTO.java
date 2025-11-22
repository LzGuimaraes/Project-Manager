package dev.LzGuimaraes.ProjectManager.dto.User;

import dev.LzGuimaraes.ProjectManager.Enum.Role;

public record UserResponseDTO(
    Long id,
    String username,
    String email,
    Role role
) {}
