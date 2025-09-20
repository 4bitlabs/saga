package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.Embeddable;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

import br.com.cacadoresti.saga.sagh.model.enums.YearSemester;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class AcademicPeriod {
    private Year year;

    @Enumerated(EnumType.STRING)
    private YearSemester semester;

    public AcademicPeriod(Integer year, YearSemester semester) {
        this.year = Year.of(year);
        this.semester = semester;
    }

    @Override
    public String toString() {
        return year.toString() + "." + semester.getSemester();
    }
}
