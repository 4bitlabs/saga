package br.com.cacadoresti.saga.sagh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cacadoresti.saga.sagh.model.entity.Department;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
