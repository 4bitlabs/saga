package br.com.cacadoresti.saga.saas.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang3.builder.EqualsBuilder;

import br.com.cacadoresti.saga.saas.interfaces.Space;
import br.com.cacadoresti.saga.saas.model.enums.BuildingType;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tab_building")
public class Building implements Space {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private BuildingType type;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private Set<InternalRoom> internalRooms = new HashSet<>();

    public Building(String code, BuildingType type) {
        this.code = code;
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(object, this);
    }
}
