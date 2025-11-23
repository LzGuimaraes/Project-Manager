package dev.LzGuimaraes.ProjectManager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.LzGuimaraes.ProjectManager.dto.Projects.ProjectRequestDTO;
import dev.LzGuimaraes.ProjectManager.dto.Projects.ProjectResponseDTO;
import dev.LzGuimaraes.ProjectManager.service.ProjectService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    // LISTAR TODOS OS PROJETOS
    @GetMapping("/all")
    public ResponseEntity<Page<ProjectResponseDTO>> getProjectService() {
        Page<ProjectResponseDTO> projects = projectService.getAllProjects(Pageable.unpaged());
        return ResponseEntity.ok(projects);
    }
    // LISTAR PROJETOS POR USUÁRIO
    @GetMapping("/all/{id}")
    public ResponseEntity<Page<ProjectResponseDTO>> getProjectsByUser(@PathVariable Long userId, Pageable pageable) {
        Page<ProjectResponseDTO> projects = projectService.getProjectsByUser(userId, pageable);
        return ResponseEntity.ok(projects);
    }
    // LISTAR PROJETOS POR USERNAME
    @GetMapping("/by-username/{username}")
    public ResponseEntity<Page<ProjectResponseDTO>> getProjectsByUsername(String username, Pageable pageable) {
        Page<ProjectResponseDTO> projects = projectService.getProjectsByUsername(username, pageable);
        return ResponseEntity.ok(projects);
    }
    // CRIAR PROJETO
    @PostMapping("/create")
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO dto) {
        ProjectResponseDTO createProject = projectService.createProject(dto);
        return new ResponseEntity<>(createProject, HttpStatus.CREATED);
    }
    // ATUALIZAR PROJETO
    @PutMapping("/update/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectRequestDTO dto) {
        ProjectResponseDTO updatedProject = projectService.updateProject(id, dto);
        return ResponseEntity.ok(updatedProject);
    }
    // DELETAR PROJETO
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

}   
