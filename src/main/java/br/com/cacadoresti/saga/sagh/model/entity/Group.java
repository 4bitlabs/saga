package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import br.com.cacadoresti.saga.sagh.model.enums.GroupStatus;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tab_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String code;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    @ManyToMany
    @JoinTable(name = "tab_group_has_professor")
    private Set<Professor> professors;

    @ManyToMany
    @JoinTable(name = "tab_group_has_student")
    private Set<Student> students;

    @OneToMany(mappedBy = "group")
    private Set<AcademicLessonReservation> schedules;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "year", column = @Column(name = "academic_year")),
        @AttributeOverride(name = "semester", column = @Column(name = "academic_semester"))
    })
    private AcademicPeriod academicPeriod;

    @Enumerated(EnumType.STRING)
    private GroupStatus status;

    private LocalDateTime createdAt;

    public Group(String code, Subject subject, AcademicPeriod academicPeriod, GroupStatus status) {
        this.code = code;
        this.subject = subject;
        this.academicPeriod = academicPeriod;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }
}
