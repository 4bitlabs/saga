package br.com.cacadoresti.saga.sagh.model.enums;

import lombok.Getter;

@Getter
public enum YearSemester {
    FIRST_SEMESTER(1), SECOND_SEMESTER(2);

    private final int semester;

    private YearSemester(int semester) {
        this.semester = semester;
    }
}
