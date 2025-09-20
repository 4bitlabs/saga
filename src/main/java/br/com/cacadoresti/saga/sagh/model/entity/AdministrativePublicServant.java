package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

import br.com.cacadoresti.saga.sagh.model.enums.AffiliationStatus;
import br.com.cacadoresti.saga.sagh.model.enums.EducationLevel;

@NoArgsConstructor
@Entity
@Table(name = "tab_administrative_public_servant")
public class AdministrativePublicServant extends PublicServant {
    public AdministrativePublicServant(User user, LocalDate startingDate, LocalDate endingDate, AffiliationStatus status,
                                       String siape, EducationLevel education, Department department) {
        this.setUser(user);
        this.setStartingDate(startingDate);
        this.setEndingDate(endingDate);
        this.setStatus(status);

        this.setSiape(siape);
        this.setEducation(education);
        this.setDepartment(department);
    }
}
