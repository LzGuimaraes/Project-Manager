package dev.LzGuimaraes.ProjectManager.dto.Projects;

public record ProjectResponseDTO(
    Long id,
    String name,
    String description,
    String status,
    UserResumoDTO user

) {}
