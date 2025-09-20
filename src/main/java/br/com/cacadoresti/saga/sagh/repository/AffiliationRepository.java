package br.com.cacadoresti.saga.sagh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cacadoresti.saga.sagh.model.entity.Affiliation;

import java.util.List;
import java.util.UUID;

public interface AffiliationRepository extends JpaRepository<Affiliation, UUID> {
    List<Affiliation> findAllByUserId(UUID userId);
}
