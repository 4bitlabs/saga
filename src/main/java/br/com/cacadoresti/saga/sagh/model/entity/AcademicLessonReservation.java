package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

import br.com.cacadoresti.saga.saas.model.entity.Classroom;
import br.com.cacadoresti.saga.sagh.interfaces.Reservation;
import br.com.cacadoresti.saga.sagh.model.enums.WeekDay;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tab_lesson_reservation")
public class AcademicLessonReservation implements Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private WeekDay day;
    private LocalTime startingTime;
    //TODO: Establish business logic to boundary hours
    private LocalTime endingTime;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public AcademicLessonReservation(WeekDay day, LocalTime startingTime, LocalTime endingTime, Classroom classroom, Group group) {
        this.day = day;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.classroom = classroom;
        this.group = group;
    }
}
