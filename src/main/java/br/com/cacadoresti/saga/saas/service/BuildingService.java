package br.com.cacadoresti.saga.saas.service;

import org.springframework.stereotype.Service;

import br.com.cacadoresti.saga.saas.model.entity.Building;
import br.com.cacadoresti.saga.saas.repository.BuildingRepository;

@Service
public class BuildingService {
    private BuildingRepository repository;
    public BuildingService(BuildingRepository buildingRepository){
        this.repository = buildingRepository;
    }

    //CREATE
    public Building createBuilding(Building building){
        return repository.save(building);
    }

    //READ BY ID
    //READ ALL
    //UPDATE
    //DELETE
}
