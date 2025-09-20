package br.com.cacadoresti.saga.sagh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cacadoresti.saga.sagh.model.entity.Subject;

import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {

}
