package br.com.cacadoresti.saga.sagh.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import br.com.cacadoresti.saga.sagh.exception.department.DepartmentUUIDNotFoundException;
import br.com.cacadoresti.saga.sagh.model.dto.department.DepartmentRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.department.DepartmentResponseDTO;
import br.com.cacadoresti.saga.sagh.model.entity.Department;
import br.com.cacadoresti.saga.sagh.repository.DepartmentRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//TODO: Add @Transactional annotation on methods
public class DepartmentService {
    private final DepartmentRepository repository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.repository = departmentRepository;
    }

    //CREATE
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentCreateDTO) {
        return this.toDepartmentResponseDTO(
            repository.save(this.toDepartment(departmentCreateDTO))
        );
    }

    //READ ALL
    public List<DepartmentResponseDTO> getAllDepartments() {
        return repository.findAll().stream().map(this::toDepartmentResponseDTO).collect(Collectors.toList());
    }

    //READ BY ID
    public DepartmentResponseDTO getDepartmentById(UUID id) {
        return this.toDepartmentResponseDTO(
            repository.findById(id).orElseThrow(
                () -> new DepartmentUUIDNotFoundException(
                    String.format("Department with id '%s' not found", id)
                )
            )
        );
    }

    //UPDATE
    public DepartmentResponseDTO updateDepartment(UUID id, DepartmentRequestDTO departmentUpdateDTO) {
        Department savedDepartment = repository.findById(id).orElseThrow(
            () -> new DepartmentUUIDNotFoundException(
                String.format("Department with id '%s' not found", id)
            )
        );

        savedDepartment.setCode(departmentUpdateDTO.code());
        savedDepartment.setName(departmentUpdateDTO.name());

        return this.toDepartmentResponseDTO(
            repository.save(savedDepartment)
        );
    }

    //DELETE
    public void deleteDepartment(UUID id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new DepartmentUUIDNotFoundException(
                String.format("Department with id '%s' not found", id)
            );
        }
    }

    //ENTITY TO RESPONSE DTO
    private DepartmentResponseDTO toDepartmentResponseDTO(Department department) {
        return new DepartmentResponseDTO(
          department.getCode(),
          department.getName()
        );
    }
    
    //REQUEST DTO TO ENTITY
    private Department toDepartment(DepartmentRequestDTO departmentRequestDTO) {
        return new Department(
            departmentRequestDTO.code(),
            departmentRequestDTO.name()
        );
    }
}
