package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import br.com.cacadoresti.saga.sagh.model.enums.EducationLevel;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(
    name = "tab_public_servant",
    uniqueConstraints = @UniqueConstraint(
        name = "un_siape_tab_public_servant",
        columnNames = {"siape"}
    )
)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "affiliation_id")
public abstract class PublicServant extends Affiliation {

    @Pattern(message = "SIAPE code must contain exactly 7 numeric digits", regexp = "^\\d{7}$")
    @Column(name = "siape", nullable = false)
    private String siape;

    @Column(name = "education_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private EducationLevel education;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
        name = "tab_public_servant_has_role",
        joinColumns = @JoinColumn(name = "public_servant_id"),
        inverseJoinColumns = @JoinColumn(name = "public_servant_role_id")
    )
    private Set<PublicServantRole> roles = new HashSet<>();
}
