package br.com.cacadoresti.saga.saas.model.entity;

import br.com.cacadoresti.saga.saas.interfaces.Laboratory;
import br.com.cacadoresti.saga.saas.model.enums.AcademicRoomStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tab_computer_laboratory")
public class ComputerLaboratory extends InternalRoom implements Laboratory {
    private String code;
    private AcademicRoomStatus status;
}
