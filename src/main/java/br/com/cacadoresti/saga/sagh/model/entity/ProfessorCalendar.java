package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import br.com.cacadoresti.saga.sagh.interfaces.Calendar;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tab_professor_calendar")
public class ProfessorCalendar implements Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
//    private Professor professor;
}
