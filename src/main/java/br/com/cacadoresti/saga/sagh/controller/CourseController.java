package br.com.cacadoresti.saga.sagh.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

import br.com.cacadoresti.saga.sagh.model.dto.course.CourseRequestDTO;
import br.com.cacadoresti.saga.sagh.model.dto.course.CourseResponseDTO;
import br.com.cacadoresti.saga.sagh.service.CourseService;

import java.util.List;
import java.util.UUID;

// @RestController
// @RequestMapping("/v1/courses")
@Tag(name = "Courses", description = "Endpoints for courses management")
@SecurityRequirement(name = "jwtAuthentication")
//TODO: Complete documentation
public class CourseController {
    private final CourseService service;
    public CourseController(CourseService courseService) {
        this.service = courseService;
    }

    //CREATE
    @Operation(summary = "Creates a course", method = "POST")
    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO courseCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCourse(courseCreateDTO));
    }

    //READ ALL
    @Operation(summary = "Gets all courses", method = "GET")
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllCourses());
    }

    //READ BY ID
    @Operation(summary = "Gets a course by its ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCourseById(id));
    }

    //UPDATE
    @Operation(summary = "Updates a course by its id", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateDepartment(@PathVariable UUID id, @Valid @RequestBody CourseRequestDTO courseUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCourse(id, courseUpdateDTO));
    }

    //DELETE
    @Operation(summary = "Deletes a course by its ID", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID id) {
        service.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
