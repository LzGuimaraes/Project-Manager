package dev.LzGuimaraes.ProjectManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface User extends JpaRepository<User, Long> {
    User findByNome(String nome);
}
