package dev.LzGuimaraes.ProjectManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Project extends JpaRepository<Project, Long> {

}
