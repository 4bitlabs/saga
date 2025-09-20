package br.com.cacadoresti.saga.sagh.service;

import org.springframework.stereotype.Service;

import br.com.cacadoresti.saga.sagh.exception.affiliation.AffiliationUUIDNotFoundException;
import br.com.cacadoresti.saga.sagh.exception.student.StudentEnrollmentCodeNotFoundException;
import br.com.cacadoresti.saga.sagh.exception.user.UserUUIDNotFoundException;
import br.com.cacadoresti.saga.sagh.model.dto.student.StudentRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.student.StudentResponseDTO;
import br.com.cacadoresti.saga.sagh.model.entity.Student;
import br.com.cacadoresti.saga.sagh.model.entity.User;
import br.com.cacadoresti.saga.sagh.repository.StudentRepository;
import br.com.cacadoresti.saga.sagh.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//TODO: Add @Transactional annotation on methods
public class StudentService {
    private final StudentRepository repository;
    private final UserRepository userRepository;
    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.repository = studentRepository;
        this.userRepository = userRepository;
    }

    //CREATE
    public StudentResponseDTO createStudent(StudentRequestDTO createStudentDTO) {
        return this.toStudentResponseDTO(
            repository.save(this.toStudent(createStudentDTO))
        );
    }

    //READ ALL
    public List<StudentResponseDTO> getAllStudents() {
        return repository.findAll().stream()
                                   .map(this::toStudentResponseDTO)
                                   .collect(Collectors.toList());
    }

    //READ BY AFFILIATION ID
    public StudentResponseDTO getStudentByAffiliationId(UUID affiliationId) {
        return this.toStudentResponseDTO(
            repository.findById(affiliationId).orElseThrow(
                () -> new AffiliationUUIDNotFoundException(
                    String.format("Student with affiliation id '%s' not found", affiliationId)
                )
            )
        );
    }

    //READ BY ENROLLMENT CODE
    public StudentResponseDTO getStudentByEnrollmentCode(String enrollmentCode) {
        return this.toStudentResponseDTO(
            repository.findByEnrollment(enrollmentCode).orElseThrow(
                () -> new StudentEnrollmentCodeNotFoundException(
                    String.format("Student with enrollment code '%s' not found", enrollmentCode)
                )
            )
        );
    }

    //UPDATE BY AFFILIATION ID
    public StudentResponseDTO updateStudentByAffiliationId(UUID affiliationId, StudentRequestDTO updateStudentDTO) {
        Student savedStudent = repository.findById(affiliationId).orElseThrow(
            () -> new AffiliationUUIDNotFoundException(
                String.format("Student with affiliation id '%s' not found", affiliationId)
            )
        );

        User savedUser = userRepository.findById(updateStudentDTO.userId()).orElseThrow(
            () -> new UserUUIDNotFoundException(
                String.format("User with id '%s' not found", updateStudentDTO.userId())
            )
        );

        savedStudent.setUser(savedUser);
        savedStudent.setStartingDate(updateStudentDTO.startingDate());
        savedStudent.setEndingDate(updateStudentDTO.endingDate());
        savedStudent.setStatus(updateStudentDTO.status());
        savedStudent.setEnrollment(updateStudentDTO.enrollment());
        savedStudent.setInstitutionalEmail(updateStudentDTO.institutionalEmail());

        return this.toStudentResponseDTO(repository.save(savedStudent));
    }

    //UPDATE BY ENROLLMENT CODE
    public StudentResponseDTO updateStudentByEnrollmentCode(String enrollmentCode, StudentRequestDTO updateStudentDTO) {
        Student savedStudent = repository.findByEnrollment(enrollmentCode).orElseThrow(
            () -> new StudentEnrollmentCodeNotFoundException(
                String.format("Student with enrollment code '%s' not found", enrollmentCode)
            )
        );

        User savedUser = userRepository.findById(updateStudentDTO.userId()).orElseThrow(
                () -> new UserUUIDNotFoundException(
                        String.format("User with id '%s' not found", updateStudentDTO.userId())
                )
        );

        savedStudent.setUser(savedUser);
        savedStudent.setStartingDate(updateStudentDTO.startingDate());
        savedStudent.setEndingDate(updateStudentDTO.endingDate());
        savedStudent.setStatus(updateStudentDTO.status());
        savedStudent.setEnrollment(updateStudentDTO.enrollment());
        savedStudent.setInstitutionalEmail(updateStudentDTO.institutionalEmail());

        return this.toStudentResponseDTO(repository.save(savedStudent));
    }

    //DELETE BY AFFILIATION ID
    public void deleteStudentByAffiliationId(UUID affiliationId) {
        if(repository.existsById(affiliationId)) {
            repository.deleteById(affiliationId);
        } else {
            throw new AffiliationUUIDNotFoundException(
                String.format("Student with affiliation id '%s' not found", affiliationId)
            );
        }
    }

    //ENTITY TO RESPONSE DTO
    protected StudentResponseDTO toStudentResponseDTO(Student student) {
        return new StudentResponseDTO(
            student.getUser().getId(),
            student.getUser().getName(),
            student.getStatus(),
            student.getEnrollment(),
            student.getInstitutionalEmail(),
            student.getCreatedAt()
        );
    }

    //REQUEST DTO TO ENTITY
    private Student toStudent(StudentRequestDTO studentRequestDTO) {
        User studentUser = userRepository.findById(studentRequestDTO.userId()).orElseThrow(
            () -> new UserUUIDNotFoundException(
                String.format("User with UUID '%s' not found", studentRequestDTO.userId())
            )
        );

        return new Student(
            studentUser,
            studentRequestDTO.startingDate(),
            studentRequestDTO.endingDate(),
            studentRequestDTO.status(),
            studentRequestDTO.enrollment(),
            studentRequestDTO.institutionalEmail()
        );
    }
}
