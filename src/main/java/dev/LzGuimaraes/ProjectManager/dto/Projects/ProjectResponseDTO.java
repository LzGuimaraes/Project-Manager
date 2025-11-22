package dev.LzGuimaraes.ProjectManager.dto.Projects;

import dev.LzGuimaraes.ProjectManager.Enum.Status;
import dev.LzGuimaraes.ProjectManager.dto.User.UserResumoDTO;

public record ProjectResponseDTO(
    Long id,
    String name,
    String description,
    Status status,
    UserResumoDTO user

) {}
