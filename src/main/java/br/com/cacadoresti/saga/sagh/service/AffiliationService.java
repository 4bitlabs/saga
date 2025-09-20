package br.com.cacadoresti.saga.sagh.service;

import org.springframework.stereotype.Service;

import br.com.cacadoresti.saga.sagh.interfaces.ConcreteAffiliationResponseDTO;
import br.com.cacadoresti.saga.sagh.model.dto.affiliation.AffiliationResponseDTO;
import br.com.cacadoresti.saga.sagh.model.entity.AdministrativePublicServant;
import br.com.cacadoresti.saga.sagh.model.entity.Affiliation;
import br.com.cacadoresti.saga.sagh.model.entity.Professor;
import br.com.cacadoresti.saga.sagh.model.entity.Student;
import br.com.cacadoresti.saga.sagh.repository.AffiliationRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AffiliationService {
    private final AffiliationRepository repository;
    private final StudentService studentService;
    private final ProfessorService professorService;
    private final AdministrativePublicServantService administrativePublicServantService;
    public AffiliationService(AffiliationRepository affiliationRepository,
                              StudentService studentService,
                              ProfessorService professorService,
                              AdministrativePublicServantService administrativePublicServantService) {
        this.repository = affiliationRepository;
        this.studentService = studentService;
        this.professorService = professorService;
        this.administrativePublicServantService = administrativePublicServantService;
    }

    //GET ALL
    public List<AffiliationResponseDTO> getAllAffiliations() {
        return repository.findAll().stream()
                                   .map(this::toAffiliationResponseDTO)
                                   .collect(Collectors.toList());
    }

    //GET ALL BY USER ID
    public List<AffiliationResponseDTO> getAffiliationsByUserId(UUID id) {
        return this.repository.findAllByUserId(id).stream()
                                                   .map(this::toAffiliationResponseDTO)
                                                   .collect(Collectors.toList());
    }

    //ENTITY TO RESPONSE DTO
    private AffiliationResponseDTO toAffiliationResponseDTO(Affiliation affiliation) {
        ConcreteAffiliationResponseDTO concreteAffiliation = switch(affiliation) {
            case Student student -> studentService.toStudentResponseDTO((Student) affiliation);
            case Professor professor -> professorService.toProfessorResponseDTO((Professor) affiliation);
            case AdministrativePublicServant administrativePublicServant -> administrativePublicServantService.toAdministrativePublicServantResponseDTO((AdministrativePublicServant) affiliation);
            default -> null;
        };

        return new AffiliationResponseDTO(
            affiliation.getId(),
            affiliation.getUser().getId(),
            affiliation.getStartingDate(),
            affiliation.getEndingDate(),
            affiliation.getClass().getSimpleName(),
            affiliation.getStatus(),
            concreteAffiliation,
            affiliation.getCreatedAt()
        );
    }
}
