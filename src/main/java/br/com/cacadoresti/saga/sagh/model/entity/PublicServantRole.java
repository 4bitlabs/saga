package br.com.cacadoresti.saga.sagh.model.entity;

import br.com.cacadoresti.saga.sagh.model.enums.CivilServantRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(
    name = "tab_public_servant_role",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "un_role_tab_public_servant_role",
            columnNames = {"role"}
        )
    }
)
public class PublicServantRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private CivilServantRole role;

    @Override
    public String toString() {
        return this.role.name();
    }
}