package dev.LzGuimaraes.ProjectManager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.LzGuimaraes.ProjectManager.Enum.Status;
import dev.LzGuimaraes.ProjectManager.Exceptions.ResourceNotFoundException;
import dev.LzGuimaraes.ProjectManager.Mapper.ProjectMapper;
import dev.LzGuimaraes.ProjectManager.Repository.ProjectRepository;
import dev.LzGuimaraes.ProjectManager.Repository.UserRepository;
import dev.LzGuimaraes.ProjectManager.dto.Projects.ProjectRequestDTO;
import dev.LzGuimaraes.ProjectManager.dto.Projects.ProjectResponseDTO;
import dev.LzGuimaraes.ProjectManager.model.Project;
import dev.LzGuimaraes.ProjectManager.model.User;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectMapper = projectMapper;
    }
    
    //Retorna todos os dados de projetos com paginação
    public Page<ProjectResponseDTO> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(projectMapper::toDTO);
    }
    
    //Retorna todos os dados de projetos de um usuário específico com paginação
    public Page<ProjectResponseDTO> getProjectsByUser(Long userId, Pageable pageable) {
    userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + userId));

    Page<ProjectResponseDTO> projects = projectRepository.findByUserId(userId, pageable)
            .map(projectMapper::toDTO);

    if (projects.isEmpty()) {
        throw new ResourceNotFoundException("Nenhum projeto encontrado para o usuário " + userId);
        }
        
        return projects;
    }
    
    //Retorna todos os dados de projetos de um usuário específico pelo nome de usuário com paginação
    public Page<ProjectResponseDTO> getProjectsByUsername(String username, Pageable pageable) {
    userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + username));
    
    Page<ProjectResponseDTO> projects = projectRepository.findByUserUsername(username, pageable)
            .map(projectMapper::toDTO);
    if (projects.isEmpty()) {
        throw new ResourceNotFoundException("Nenhum projeto encontrado para o usuário " + username);
        }
        return projects;
    }
    
    public ProjectResponseDTO getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .map(projectMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado: " + projectId));
    }

    //Cria um novo projeto associado a um usuário existente
    public ProjectResponseDTO createProject(ProjectRequestDTO dto) {
        User user = userRepository.findById(dto.userId())
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + dto.userId()));

        Project project = projectMapper.toEntity(dto);

        project.setUser(user);
        project.setStatus(Status.IN_PROGRESS); 
        
        Project saved = projectRepository.save(project);

        return projectMapper.toDTO(saved);
    }
    //Atualiza os dados de um projeto existente
    public ProjectResponseDTO updateProject(Long projectId, ProjectRequestDTO dto) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado: " + projectId));

        if (dto.name() != null && !dto.name().isBlank()) {
            project.setName(dto.name());
        }

        if (dto.description() != null && !dto.description().isBlank()) {
            project.setDescription(dto.description());
        }
        if (dto.status() != null) {
            project.setStatus(dto.status());
        }

        Project updatedProject = projectRepository.save(project);

        return projectMapper.toDTO(updatedProject);
    }

    //Deleta um projeto existente
    public void deleteProject(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Projeto não encontrado para exclusão: " + projectId);
        }
        projectRepository.deleteById(projectId);
    }
}
