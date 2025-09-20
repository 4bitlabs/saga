package br.com.cacadoresti.saga.sagh.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.cacadoresti.saga.sagh.exception.course.CourseUUIDNotFoundException;
import br.com.cacadoresti.saga.sagh.model.dto.course.CourseRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.course.CourseResponseDTO;
import br.com.cacadoresti.saga.sagh.model.entity.Course;
import br.com.cacadoresti.saga.sagh.repository.CourseRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//TODO: Add @Transactional annotation on methods
public class CourseService {
    private final CourseRepository repository;
    public CourseService(CourseRepository courseRepository) {
        this.repository = courseRepository;
    }

    //CREATE
    @Transactional
    public CourseResponseDTO createCourse(CourseRequestDTO createCourseDTO) {
        return this.toCourseResponseDTO(
            repository.save(this.toCourse(createCourseDTO))
        );
    }

    //READ ALL
    public List<CourseResponseDTO> getAllCourses() {
        return repository.findAll().stream()
                                   .map(this::toCourseResponseDTO)
                                   .collect(Collectors.toList());
    }

    //READ BY ID
    public CourseResponseDTO getCourseById(UUID id) {
        return this.toCourseResponseDTO(
            repository.findById(id).orElseThrow(
                () -> new CourseUUIDNotFoundException(
                    String.format("Course with UUID '%s' not found", id)
                )
            )
        );
    }

    //UPDATE
    public CourseResponseDTO updateCourse(UUID id, CourseRequestDTO courseUpdateDTO) {
        Course savedCourse = repository.findById(id).orElseThrow(
            () -> new CourseUUIDNotFoundException(
                String.format("Course with UUID '%s' not found", id)
            )
        );
        
        savedCourse.setCode(courseUpdateDTO.code());
        savedCourse.setTitle(courseUpdateDTO.title());
        savedCourse.setShortTitle(courseUpdateDTO.shortTitle());
        savedCourse.setQuantityOfSemesters(courseUpdateDTO.quantityOfSemesters());

        return this.toCourseResponseDTO(repository.save(savedCourse));
    }
    
    //DELETE
    public void deleteCourse(UUID id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new CourseUUIDNotFoundException(
                String.format("Course with UUID '%s' not found", id)
            );
        }
    };


    private CourseResponseDTO toCourseResponseDTO(Course course) {
        return new CourseResponseDTO(
            course.getCode(),
            course.getTitle(),
            course.getShortTitle(),
            course.getType(),
            course.getQuantityOfSemesters()
        );
    }

    private Course toCourse(CourseRequestDTO courseRequestDTO) {
        return new Course(
            courseRequestDTO.code(),
            courseRequestDTO.title(),
            courseRequestDTO.shortTitle(),
            courseRequestDTO.type(),
            courseRequestDTO.quantityOfSemesters()
        );
    }
}
