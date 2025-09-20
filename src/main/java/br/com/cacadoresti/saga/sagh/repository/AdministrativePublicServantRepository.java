package br.com.cacadoresti.saga.sagh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cacadoresti.saga.sagh.model.entity.AdministrativePublicServant;

import java.util.Optional;
import java.util.UUID;

public interface AdministrativePublicServantRepository extends JpaRepository<AdministrativePublicServant, UUID> {

    Optional<AdministrativePublicServant> findByUserId(UUID userId);
}
