package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tab_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String code;
    private String title;
    private String shortTitle;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Course> courses;

    LocalDateTime createdAt;

    public Subject(String code, String title, String shortTitle) {
        this.code = code;
        this.title = title;
        this.shortTitle = shortTitle;
        LocalDateTime now = LocalDateTime.now();
    }
}
