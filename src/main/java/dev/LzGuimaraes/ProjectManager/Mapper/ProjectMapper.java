package dev.LzGuimaraes.ProjectManager.Mapper;

import org.springframework.stereotype.Component;

import dev.LzGuimaraes.ProjectManager.dto.Projects.ProjectRequestDTO;
import dev.LzGuimaraes.ProjectManager.dto.Projects.ProjectResponseDTO;
import dev.LzGuimaraes.ProjectManager.dto.User.UserResumoDTO;
import dev.LzGuimaraes.ProjectManager.model.Project;

@Component
public class ProjectMapper {

    public ProjectResponseDTO toDTO(Project project) {
        
        UserResumoDTO userDTO = null;
        if (project.getUser() != null) {
            userDTO = new UserResumoDTO(
                project.getUser().getId(),
                project.getUser().getUsername()
            );
        }

        return new ProjectResponseDTO(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getStatus(),
            userDTO
        );
    }

    public Project toEntity(ProjectResponseDTO projectDTO) {

        Project project = new Project();
        project.setName(projectDTO.name());
        project.setDescription(projectDTO.description());
        project.setStatus(projectDTO.status());
        
        return project;
    }

    public Project toEntity(ProjectRequestDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.name());
        project.setDescription(projectDTO.description());
        project.setStatus(projectDTO.status()); 
        return project;
    }
}
