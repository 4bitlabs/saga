package br.com.cacadoresti.saga.sagh.service;

import org.springframework.stereotype.Service;

import br.com.cacadoresti.saga.sagh.exception.affiliation.AffiliationUUIDNotFoundException;
import br.com.cacadoresti.saga.sagh.exception.professor.ProfessorSiapeCodeNotFoundException;
import br.com.cacadoresti.saga.sagh.exception.user.UserUUIDNotFoundException;
import br.com.cacadoresti.saga.sagh.model.dto.professor.ProfessorRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.professor.ProfessorResponseDTO;
import br.com.cacadoresti.saga.sagh.model.entity.Professor;
import br.com.cacadoresti.saga.sagh.model.entity.User;
import br.com.cacadoresti.saga.sagh.repository.DepartmentRepository;
import br.com.cacadoresti.saga.sagh.repository.ProfessorRepository;
import br.com.cacadoresti.saga.sagh.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//TODO: Add @Transactional annotation on methods
public class ProfessorService {
    private final ProfessorRepository repository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    public ProfessorService(ProfessorRepository professorRepository, UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.repository = professorRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    //CREATE
    public ProfessorResponseDTO createProfessor(ProfessorRequestDTO createProfessorDTO) {
        return this.toProfessorResponseDTO(
            repository.save(this.toProfessor(createProfessorDTO))
        );
    }

    //READ ALL
    public List<ProfessorResponseDTO> getAllProfessors() {
        return repository.findAll().stream()
                                   .map(this::toProfessorResponseDTO)
                                   .collect(Collectors.toList());
    }

    //READ BY ID
    public ProfessorResponseDTO getProfessorByAffiliationId(UUID affiliationId) {
        return this.toProfessorResponseDTO(
            repository.findById(affiliationId).orElseThrow(
                () -> new AffiliationUUIDNotFoundException(
                    String.format("Professor with affiliation id '%s' not found", affiliationId)
                )
            )
        );
    }

    //READ BY SIAPE CODE
    public ProfessorResponseDTO getProfessorBySiapeCode(String siapeCode) {
        return this.toProfessorResponseDTO(
                repository.findBySiape(siapeCode).orElseThrow(
                        () -> new ProfessorSiapeCodeNotFoundException(
                                String.format("Professor with affiliation id '%s' not found", siapeCode)
                        )
                )
        );
    }

    //UPDATE BY AFFILIATION ID
    public ProfessorResponseDTO updateProfessorByAffiliationId(UUID affiliationId, ProfessorRequestDTO professorUpdateDTO) {
        Professor savedProfessor = repository.findById(affiliationId).orElseThrow(
                () -> new AffiliationUUIDNotFoundException(
                    String.format("Professor with affiliation id '%s' not found", affiliationId)
                )
        );

        User savedUser = userRepository.findById(professorUpdateDTO.userId()).orElseThrow(
            () -> new UserUUIDNotFoundException(
                String.format("User with id '%s' not found", professorUpdateDTO.userId())
            )
        );

//        //TODO: Implement custom exception
//        Department savedDepartment = departmentRepository.findById(professorUpdateDTO.departmentId()).orElseThrow(
//                () -> new RuntimeException("Department not found")
//        );

        savedProfessor.setUser(savedUser);
        savedProfessor.setStartingDate(professorUpdateDTO.startingDate());
        savedProfessor.setEndingDate(professorUpdateDTO.endingDate());
        savedProfessor.setStatus(professorUpdateDTO.status());
        savedProfessor.setSiape(professorUpdateDTO.siape());
        savedProfessor.setEducation(professorUpdateDTO.education());
//        savedProfessor.setDepartment(savedDepartment);
        savedProfessor.setInstitutionalEmail(professorUpdateDTO.institutionalEmail());

        return this.toProfessorResponseDTO(repository.save(savedProfessor));
    }

    //UPDATE BY SIAPE CODE
    public ProfessorResponseDTO updateProfessorBySiapeCode(String siapeCode, ProfessorRequestDTO professorUpdateDTO) {
        Professor savedProfessor = repository.findBySiape(siapeCode).orElseThrow(
                () -> new ProfessorSiapeCodeNotFoundException(
                        String.format("Professor with siape code '%s' not found", siapeCode)
                )
        );

        User savedUser = userRepository.findById(professorUpdateDTO.userId()).orElseThrow(
                () -> new UserUUIDNotFoundException(
                        String.format("User with id '%s' not found", professorUpdateDTO.userId())
                )
        );

        //        //TODO: Implement custom exception
        //        Department savedDepartment = departmentRepository.findById(professorUpdateDTO.departmentId()).orElseThrow(
        //                () -> new RuntimeException("Department not found")
        //        );

        savedProfessor.setUser(savedUser);
        savedProfessor.setStartingDate(professorUpdateDTO.startingDate());
        savedProfessor.setEndingDate(professorUpdateDTO.endingDate());
        savedProfessor.setStatus(professorUpdateDTO.status());
        savedProfessor.setSiape(professorUpdateDTO.siape());
        savedProfessor.setEducation(professorUpdateDTO.education());
        //        savedProfessor.setDepartment(savedDepartment);
        savedProfessor.setInstitutionalEmail(professorUpdateDTO.institutionalEmail());

        return this.toProfessorResponseDTO(repository.save(savedProfessor));
    }

    //DELETE BY AFFILIATION ID
    public void deleteProfessorByAffiliationId(UUID affiliationId) {
        if(repository.existsById(affiliationId)) {
            repository.deleteById(affiliationId);
        } else {
            throw new AffiliationUUIDNotFoundException(
                String.format("Professor with affiliation id '%s' not found", affiliationId)
            );
        }
    }

    //ENTITY TO RESPONSE DTO
    protected ProfessorResponseDTO toProfessorResponseDTO(Professor professor) {
        return new ProfessorResponseDTO(
            professor.getUser().getId(),
            professor.getSiape(),
            professor.getUser().getName(),
            professor.getUser().getSurname(),
            professor.getStatus(),
            professor.getEducation(),
            professor.getInstitutionalEmail(),
            professor.getCreatedAt()
        );
    }

//    //REQUEST DTO TO ENTITY
//    private Professor toProfessor(ProfessorRequestDTO professorRequestDTO) {
//        User professorUser = userRepository.findById(professorRequestDTO.userId()).orElseThrow(
//            () -> new UserUUIDNotFoundException(
//                String.format("User with UUID '%s' not found", professorRequestDTO.userId())
//            )
//        );
//
//        Department professorDepartment = departmentRepository.findById(professorRequestDTO.departmentId()).orElseThrow(
//                () -> new DepartmentUUIDNotFoundException(
//                        String.format("Department with UUID '%s' not found", professorRequestDTO.departmentId())
//                )
//        );
//
//        return new Professor(
//            professorUser,
//            professorRequestDTO.startingDate(),
//            professorRequestDTO.endingDate(),
//            professorRequestDTO.status(),
//            professorRequestDTO.siape(),
//            professorRequestDTO.education(),
//            //TODO: Check it out if department is obligatory
//            professorDepartment,
//            professorRequestDTO.institutionalEmail()
//        );
//    }

    //REQUEST DTO TO ENTITY
    private Professor toProfessor(ProfessorRequestDTO professorRequestDTO) {
        User professorUser = userRepository.findById(professorRequestDTO.userId()).orElseThrow(
                () -> new UserUUIDNotFoundException(
                        String.format("User with UUID '%s' not found", professorRequestDTO.userId())
                )
        );

        return new Professor(
                professorUser,
                professorRequestDTO.startingDate(),
                professorRequestDTO.endingDate(),
                professorRequestDTO.status(),
                professorRequestDTO.siape(),
                professorRequestDTO.education(),
                professorRequestDTO.institutionalEmail()
        );
    }
}