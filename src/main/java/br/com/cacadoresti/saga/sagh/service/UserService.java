package br.com.cacadoresti.saga.sagh.service;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cacadoresti.saga.sagh.exception.user.UserUUIDNotFoundException;
import br.com.cacadoresti.saga.sagh.model.dto.user.UserRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.user.UserResponseDTO;
import br.com.cacadoresti.saga.sagh.model.entity.User;
import br.com.cacadoresti.saga.sagh.model.entity.UserRole;
import br.com.cacadoresti.saga.sagh.repository.UserRepository;
import br.com.cacadoresti.saga.sagh.repository.UserRoleRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//TODO: Add @Transactional annotation on methods
public class UserService {
    private final UserRepository repository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder
    ) {
        this.repository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //CREATE
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO createUserDTO) {
        return this.toUserResponseDTO(
            repository.save(this.toUser(createUserDTO))
        );
    }

    //READ ALL
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll().stream()
                                   .map(this::toUserResponseDTO)
                                   .collect(Collectors.toList());
    }

    //READ BY ID
    public UserResponseDTO getUserById(UUID id) {
        return this.toUserResponseDTO(
            repository.findById(id).orElseThrow(
                () -> new UserUUIDNotFoundException(
                    String.format("User with UUID '%s' not found", id)
                )
            )
        );
    }

    //UPDATE
    public UserResponseDTO updateUser(UUID id, UserRequestDTO userUpdateDTO) {
        User savedUser = repository.findById(id).orElseThrow(
            () -> new UserUUIDNotFoundException(
                String.format("User with UUID '%s' not found", id)
            )
        );
        
        savedUser.setCpf(userUpdateDTO.cpf());
        savedUser.setName(userUpdateDTO.name());
        savedUser.setSurname(userUpdateDTO.surname());
        savedUser.setBirthdate(userUpdateDTO.birthdate());
        savedUser.setEmail(userUpdateDTO.email());
        savedUser.setUsername(userUpdateDTO.username());
        savedUser.setPassword(passwordEncoder.encode(userUpdateDTO.password()));
        savedUser.setRoles(
            //TODO: Implement custom exception
            userUpdateDTO.roles().stream()
                    .map(role -> userRoleRepository.findById(role.ordinal()).orElseThrow(
                            () -> new IllegalArgumentException("Invalid role assignment")
                    ))
                    .collect(Collectors.toSet())
        );

        return this.toUserResponseDTO(repository.save(savedUser));
    }
    
    //DELETE
    public void deleteUser(UUID id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new UserUUIDNotFoundException(
                String.format("User with UUID '%s' not found", id)
            );
        }
    };


    private UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getCpf(),
            user.getName(),
            user.getSurname(),
            user.getBirthdate(),
            user.getEmail(),
            user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toSet()),
            user.getCreatedAt()
        );
    }

    private User toUser(UserRequestDTO userRequestDTO) {
        return new User(
            userRequestDTO.cpf(),
            userRequestDTO.name(),
            userRequestDTO.surname(),
            userRequestDTO.birthdate(),
            userRequestDTO.email(),
            userRequestDTO.username(),
            passwordEncoder.encode(userRequestDTO.password()),
            userRequestDTO.roles().stream().map(UserRole::new).collect(Collectors.toSet())
        );
    }
}
