package br.com.cacadoresti.saga.sagh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cacadoresti.saga.sagh.model.entity.Student;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByEnrollment(String enrollment);

    Optional<Student> findByUserId(UUID userId);
}
