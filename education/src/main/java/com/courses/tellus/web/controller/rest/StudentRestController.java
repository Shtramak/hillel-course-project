package com.courses.tellus.web.controller.rest;

import java.util.List;
import java.util.NoSuchElementException;

import com.courses.tellus.entity.dto.StudentDto;
import com.courses.tellus.entity.model.Student;
import com.courses.tellus.service.rest.StudentRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MediaTypeNotSupportedStatusException;

@RestController
@RequestMapping("/rest/student")
public class StudentRestController {
    @Autowired
    private final transient StudentRestServiceImpl restService;

    @Autowired
    public StudentRestController(final StudentRestServiceImpl studentService) {
        this.restService = studentService;
    }

    /**
     * Method provide all students from database to json format.
     *
     * @return all entity in json format
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Student>> getAll() {
        final List<Student> students = restService.getAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * Method for search entity by Id into database.
     *
     * @param studentId path variable
     * @return entity by id
     */
    @GetMapping(value = "/{studentId:[\\d]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> getById(@PathVariable final Long studentId) {
        final Student student = restService.getEntityById(studentId).get();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /**
     * Method provide creating new object and insert into database.
     *
     * @param student dto object parsed from json
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> insert(@Validated @RequestBody final StudentDto student) {
        restService.insert(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method provide delete student from database.
     *
     * @param studentId part of path and id of needed object
     */
    @DeleteMapping(value = "/{studentId:[\\d]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable final Long studentId) {
        restService.delete(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method provide updating object into database.
     *
     * @param student dto object parsed from json to dto object
     */

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{studentId:[\\d]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> update(@PathVariable final Long studentId, @RequestBody final StudentDto student) {
        restService.update(studentId, student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Exception handling for maintain working state.
     *
     * @return http status - bad request
     */
    @ExceptionHandler({IllegalArgumentException.class,
            NoSuchElementException.class,
            EmptyResultDataAccessException.class})
    public ResponseEntity<String> exceptionCatchMain() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handling for maintain working state.
     *
     * @return http status - unsupported media type
     */
    @ExceptionHandler({MediaTypeNotSupportedStatusException.class})
    public ResponseEntity<String> unsupportedMediaTypeCatch() {
        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    }

