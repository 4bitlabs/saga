package br.com.cacadoresti.saga.sagh.model.entity;

import br.com.cacadoresti.saga.sagh.model.enums.AppRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    name = "tab_user_role",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "un_role_tab_user_role",
            columnNames = {"role"}
        )
    }
)
public class UserRole {
    @Id
    private Integer id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppRole role;

    public UserRole(AppRole role) {
        this.id = (Integer) role.ordinal();
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role.name();
    }
}
