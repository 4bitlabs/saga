package br.com.cacadoresti.saga.saas.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import br.com.cacadoresti.saga.saas.model.enums.AcademicRoomStatus;
import br.com.cacadoresti.saga.sagh.model.entity.AcademicLessonReservation;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tab_classroom")
public class Classroom extends InternalRoom {
    private String code;
    private AcademicRoomStatus status;

    @OneToMany(mappedBy = "classroom")
    Set<AcademicLessonReservation> academicLessonReservations = new HashSet<>();

    public Classroom(Building building, String code, AcademicRoomStatus status) {
        this.setBuilding(building);

        this.code = code;
        this.status = status;
    }
}
