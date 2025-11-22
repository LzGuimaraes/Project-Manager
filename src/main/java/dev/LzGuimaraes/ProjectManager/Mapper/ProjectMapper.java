package dev.LzGuimaraes.ProjectManager.Mapper;

import dev.LzGuimaraes.ProjectManager.dto.Projects.ProjectResponseDTO;
import dev.LzGuimaraes.ProjectManager.dto.User.UserResumoDTO;
import dev.LzGuimaraes.ProjectManager.model.Project;

public class ProjectMapper {

    public static ProjectResponseDTO toDTO(Project project) {

        UserResumoDTO userDTO = new UserResumoDTO(
            project.getUser().getId(),
            project.getUser().getUsername()
        );

        return new ProjectResponseDTO(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getStatus(),
            userDTO
        );
    }

    public static Project toEntity(ProjectResponseDTO projectDTO) {

        Project project = new Project();
        project.setName(projectDTO.name());
        project.setDescription(projectDTO.description());
        project.setStatus(projectDTO.status());
        
        return project;
    }   
}
