package br.com.cacadoresti.saga.saas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cacadoresti.saga.saas.model.entity.Building;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Building save(Building building);
    Optional<Building> findById(Long id);
    List<Building> findAll();
    void deleteById(Long id);
}
