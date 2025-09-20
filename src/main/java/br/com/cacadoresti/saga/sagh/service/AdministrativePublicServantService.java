package br.com.cacadoresti.saga.sagh.service;

import org.springframework.stereotype.Service;

import br.com.cacadoresti.saga.sagh.model.dto.administrative_public_servant.AdministrativePublicServantResponseDTO;
import br.com.cacadoresti.saga.sagh.model.entity.AdministrativePublicServant;
import br.com.cacadoresti.saga.sagh.repository.AdministrativePublicServantRepository;

@Service
public class AdministrativePublicServantService {
    private final AdministrativePublicServantRepository repository;
    public AdministrativePublicServantService(AdministrativePublicServantRepository administrativePublicServantRepository) {
        this.repository = administrativePublicServantRepository;
    }

    public AdministrativePublicServant createAdministrativePublicServant(AdministrativePublicServant administrative) {
        return repository.save(administrative);
    }

    //ENTITY TO RESPONSE DTO
    protected AdministrativePublicServantResponseDTO toAdministrativePublicServantResponseDTO(AdministrativePublicServant administrativePublicServant) {
        return new AdministrativePublicServantResponseDTO(
                administrativePublicServant.getUser().getId(),
                administrativePublicServant.getUser().getName()
        );
    }
}
