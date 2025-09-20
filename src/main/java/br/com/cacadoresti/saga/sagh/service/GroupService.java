package br.com.cacadoresti.saga.sagh.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.cacadoresti.saga.sagh.exception.group.GroupUUIDNotFoundException;
import br.com.cacadoresti.saga.sagh.exception.professor.ProfessorSiapeCodeNotFoundException;
import br.com.cacadoresti.saga.sagh.exception.student.StudentEnrollmentCodeNotFoundException;
import br.com.cacadoresti.saga.sagh.exception.subject.SubjectUUIDNotFoundException;
import br.com.cacadoresti.saga.sagh.model.dto.group.GroupRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.group.GroupResponseDTO;
import br.com.cacadoresti.saga.sagh.model.entity.AcademicPeriod;
import br.com.cacadoresti.saga.sagh.model.entity.Group;
import br.com.cacadoresti.saga.sagh.model.entity.Professor;
import br.com.cacadoresti.saga.sagh.model.entity.Student;
import br.com.cacadoresti.saga.sagh.model.entity.Subject;
import br.com.cacadoresti.saga.sagh.repository.GroupRepository;
import br.com.cacadoresti.saga.sagh.repository.ProfessorRepository;
import br.com.cacadoresti.saga.sagh.repository.StudentRepository;
import br.com.cacadoresti.saga.sagh.repository.SubjectRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//TODO: Add @Transactional annotation on methods
public class GroupService {
    private final GroupRepository repository;
    private final SubjectRepository subjectRepository;
    private final ProfessorService professorService;
    private final StudentService studentService;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    public GroupService(GroupRepository groupRepository, SubjectRepository subjectRepository, ProfessorService professorService, StudentService studentService, ProfessorRepository professorRepository, StudentRepository studentRepository) {
        this.repository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.professorService = professorService;
        this.studentService = studentService;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    //CREATE
    @Transactional
    public GroupResponseDTO createGroup(GroupRequestDTO createGroupDTO) {
        return this.toGroupResponseDTO(
            repository.save(this.toGroup(createGroupDTO))
        );
    }

    //READ ALL
    public List<GroupResponseDTO> getAllGroups() {
        return repository.findAll().stream()
                                   .map(this::toGroupResponseDTO)
                                   .collect(Collectors.toList());
    }

    //READ BY ID
    public GroupResponseDTO getGroupById(UUID id) {
        return this.toGroupResponseDTO(
            repository.findById(id).orElseThrow(
                () -> new GroupUUIDNotFoundException(
                    String.format("Group with UUID '%s' not found", id)
                )
            )
        );
    }

    //UPDATE
    public GroupResponseDTO updateGroup(UUID id, GroupRequestDTO groupUpdateDTO) {
        Group savedGroup = repository.findById(id).orElseThrow(
            () -> new GroupUUIDNotFoundException(
                String.format("Group with UUID '%s' not found", id)
            )
        );

        Subject groupSubject = this.subjectRepository.findById(groupUpdateDTO.subjectId()).orElseThrow(
            () -> new SubjectUUIDNotFoundException(
                    String.format("Subject with UUID '%s' not found", groupUpdateDTO.subjectId())
            )
        );
        
        savedGroup.setCode(groupUpdateDTO.code());
        savedGroup.setSubject(groupSubject);
        savedGroup.setStatus(groupUpdateDTO.status());

        return this.toGroupResponseDTO(repository.save(savedGroup));
    }
    
    //DELETE
    public void deleteGroup(UUID id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new GroupUUIDNotFoundException(
                String.format("Group with UUID '%s' not found", id)
            );
        }
    }

    //ADDS A PROFESSOR TO GROUP
    @Transactional
    public GroupResponseDTO addsProfessorToGroup(UUID id, String siapeCode) {
        Group group = this.repository.findById(id).orElseThrow(
            () -> new GroupUUIDNotFoundException(
                String.format("Group with UUID '%s' not found", id)
            )
        );

        Professor professor = professorRepository.findBySiape(siapeCode).orElseThrow(
            () -> new ProfessorSiapeCodeNotFoundException(
                String.format("Professor with siape code '%s' not found", siapeCode)
            )
        );

        group.getProfessors().add(professor);

        return this.toGroupResponseDTO(repository.save(group));
    }

    //REMOVES A PROFESSOR FROM GROUP
    @Transactional
    public GroupResponseDTO removesProfessorFromGroup(UUID id, String siapeCode) {
        Group group = this.repository.findById(id).orElseThrow(
            () -> new GroupUUIDNotFoundException(
                String.format("Group with UUID '%s' not found", id)
            )
        );

        Professor professor = professorRepository.findBySiape(siapeCode).orElseThrow(
            () -> new ProfessorSiapeCodeNotFoundException(
                String.format("Professor with siape code '%s' not found", siapeCode)
            )
        );

        group.getProfessors().remove(professor);

        return this.toGroupResponseDTO(repository.save(group));
    }

    //ADDS PROFESSORS TO GROUP
    @Transactional
    public GroupResponseDTO addsProfessorsToGroup(UUID id, List<String> professorsSiapeCodes) {
        Group group = this.repository.findById(id).orElseThrow(
            () -> new GroupUUIDNotFoundException(
                String.format("Group with UUID '%s' not found", id)
            )
        );

        new HashSet<>(professorsSiapeCodes).forEach(
            professorSiapeCode -> {
                Professor professor = professorRepository.findBySiape(professorSiapeCode).orElseThrow(
                    () -> new ProfessorSiapeCodeNotFoundException(
                        String.format("Professor with siape code '%s' not found", professorSiapeCode)
                    )
                );

                group.getProfessors().add(professor);
            }
        );

        return this.toGroupResponseDTO(repository.save(group));
    }

    //ADDS A STUDENT TO GROUP
    @Transactional
    public GroupResponseDTO addsStudentToGroup(UUID id, String enrollmentCode) {
        Group group = this.repository.findById(id).orElseThrow(
            () -> new GroupUUIDNotFoundException(
                String.format("Group with UUID '%s' not found", id)
            )
        );

        Student student = studentRepository.findByEnrollment(enrollmentCode).orElseThrow(
            () -> new StudentEnrollmentCodeNotFoundException(
                String.format("Student with enrollment code '%s' not found", enrollmentCode)
            )
        );

        group.getStudents().add(student);

        return this.toGroupResponseDTO(repository.save(group));
    }

    //REMOVES A STUDENT FROM GROUP
    @Transactional
    public GroupResponseDTO removesStudentFromGroup(UUID id, String enrollmentCode) {
        Group group = this.repository.findById(id).orElseThrow(
            () -> new GroupUUIDNotFoundException(
                String.format("Group with UUID '%s' not found", id)
            )
        );

        Student student = studentRepository.findByEnrollment(enrollmentCode).orElseThrow(
            () -> new StudentEnrollmentCodeNotFoundException(
                String.format("Student with enrollment code '%s' not found", enrollmentCode)
            )
        );

        group.getStudents().remove(student);

        return this.toGroupResponseDTO(repository.save(group));
    }

    //ADDS STUDENTS TO GROUP
    @Transactional
    public GroupResponseDTO addsStudentsToGroup(UUID id, List<String> studentsEnrollmentCodes) {
        Group group = this.repository.findById(id).orElseThrow(
            () -> new GroupUUIDNotFoundException(
                String.format("Group with UUID '%s' not found", id)
            )
        );

        new HashSet<>(studentsEnrollmentCodes).forEach(
            enrollmentCode -> {
                Student student = studentRepository.findByEnrollment(enrollmentCode).orElseThrow(
                    () -> new StudentEnrollmentCodeNotFoundException(
                        String.format("Student with enrollment code '%s' not found", enrollmentCode)
                    )
                );

                group.getStudents().add(student);
            }
        );

        return this.toGroupResponseDTO(repository.save(group));
    }

    //ENTITY TO RESPONSE DTO
    private GroupResponseDTO toGroupResponseDTO(Group group) {
        return new GroupResponseDTO(
            group.getId(),
            group.getCode(),
            group.getSubject().getId(),
            group.getAcademicPeriod().toString(),
            group.getStatus(),
            group.getProfessors() != null ? group.getProfessors().stream().map(professorService::toProfessorResponseDTO).collect(Collectors.toSet()) : Collections.emptySet(),
            group.getStudents() != null ? group.getStudents().stream().map(studentService::toStudentResponseDTO).collect(Collectors.toSet()) : Collections.emptySet(),
            group.getCreatedAt()
        );
    }

    //REQUEST DTO TO ENTITY
    private Group toGroup(GroupRequestDTO groupRequestDTO) {
        Subject groupSubject = this.subjectRepository.findById(groupRequestDTO.subjectId()).orElseThrow(
            () -> new SubjectUUIDNotFoundException(
                String.format("Subject with UUID '%s' not found", groupRequestDTO.subjectId())
            )
        );

        return new Group(
            groupRequestDTO.code(),
            groupSubject,
            new AcademicPeriod(groupRequestDTO.year(), groupRequestDTO.semester()),
            groupRequestDTO.status()
        );
    }
}
