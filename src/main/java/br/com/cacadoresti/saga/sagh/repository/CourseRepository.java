package br.com.cacadoresti.saga.sagh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cacadoresti.saga.sagh.model.entity.Course;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

}
