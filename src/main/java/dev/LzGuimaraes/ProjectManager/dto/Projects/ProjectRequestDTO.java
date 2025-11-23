package dev.LzGuimaraes.ProjectManager.dto.Projects;

import dev.LzGuimaraes.ProjectManager.Enum.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjectRequestDTO(
    @NotBlank(message = "Name is mandatory")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    String name,
    @NotBlank(message = "Description is mandatory")
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 3, max = 50, message = "Description must be between 3 and 50 characters")
    String description,
    @NotNull(message = "Status is mandatory")
    Status status,
    Long userId
) {}
