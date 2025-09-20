package br.com.cacadoresti.saga.sagh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cacadoresti.saga.sagh.model.entity.Group;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {

}
