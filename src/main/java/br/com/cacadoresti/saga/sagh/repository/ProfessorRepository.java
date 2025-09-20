package br.com.cacadoresti.saga.sagh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cacadoresti.saga.sagh.model.entity.Professor;

import java.util.Optional;
import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
    Optional<Professor> findByUserId(UUID id);
    Optional<Professor> findBySiape(String siape);
}
