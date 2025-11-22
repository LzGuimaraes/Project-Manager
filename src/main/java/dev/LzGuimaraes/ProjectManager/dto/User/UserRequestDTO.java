package dev.LzGuimaraes.ProjectManager.dto.User;

import javax.management.relation.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
    @NotBlank(message = "Username is mandatory")
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    String username,
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email,
    Role role
) {}
