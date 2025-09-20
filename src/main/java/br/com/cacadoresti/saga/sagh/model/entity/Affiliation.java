package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.cacadoresti.saga.sagh.model.enums.AffiliationStatus;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tab_affiliation")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Affiliation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "starting_date", nullable = false)
    private LocalDate startingDate;

    @Column(name = "ending_date")
    private LocalDate endingDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AffiliationStatus status;

    private LocalDateTime createdAt;
}
