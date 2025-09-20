package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import br.com.cacadoresti.saga.sagh.model.enums.AffiliationStatus;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(
    name = "tab_student",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "un_enrollment_tab_student",
            columnNames = {"enrollment"}
        ),
        @UniqueConstraint(
                name = "un_institutional_email_tab_student",
                columnNames = {"institutional_email"}
        )
    }
)
@PrimaryKeyJoinColumn(name = "affiliation_id")
public class Student extends Affiliation {

    @Pattern(regexp = "^\\d{10}$", message = "The enrollment code must contain exactly 10 numeric digits")
    @Column(name = "enrollment", nullable = false)
    private String enrollment;

    @Column(name = "institutional_email", nullable = false)
    private String institutionalEmail;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Group> groups;

    public Student(User user, LocalDate startingDate, LocalDate endingDate, AffiliationStatus status,
                   String enrollment, String institutionalEmail) {
        this.setUser(user);
        this.setStartingDate(startingDate);
        this.setEndingDate(endingDate);
        this.setStatus(status);
        this.setCreatedAt(LocalDateTime.now());

        this.enrollment = enrollment;
        this.institutionalEmail = institutionalEmail;
    }
}
