package dev.LzGuimaraes.ProjectManager.dto.User;

public record UserResponseDTO(
    Long id,
    String username,
    String email,
    String role
) {}
