package dev.LzGuimaraes.ProjectManager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.LzGuimaraes.ProjectManager.Enum.Role;
import dev.LzGuimaraes.ProjectManager.Exceptions.ResourceNotFoundException;
import dev.LzGuimaraes.ProjectManager.Repository.UserRepository;
import dev.LzGuimaraes.ProjectManager.dto.User.UserRequestDTO;
import dev.LzGuimaraes.ProjectManager.dto.User.UserResponseDTO;
import dev.LzGuimaraes.ProjectManager.model.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {

        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new IllegalArgumentException("Username já existe: " + dto.username());
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        if (userRepository.count() == 0) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        User savedUser = userRepository.save(user);
        return toDTO(savedUser);
    }

    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(this::toDTO);
    }

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));

        if (dto.username() != null && !dto.username().isBlank()) {
            var existingUser = userRepository.findByUsername(dto.username());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
                 throw new IllegalArgumentException("Username já em uso por outro usuário.");
            }
            user.setUsername(dto.username());
        }

        if (dto.email() != null && !dto.email().isBlank()) {
            user.setEmail(dto.email());
        }
        
        User updatedUser = userRepository.save(user);
        return toDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado para exclusão: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );
    }
}