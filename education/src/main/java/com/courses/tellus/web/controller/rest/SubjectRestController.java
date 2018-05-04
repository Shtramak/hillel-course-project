package com.courses.tellus.web.controller.rest;

import java.util.List;
import java.util.NoSuchElementException;

import com.courses.tellus.entity.dto.SubjectDTO;
import com.courses.tellus.entity.model.Subject;
import com.courses.tellus.service.rest.SubjectRestServiceImpl;
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
@RequestMapping("/rest/subject")
public class SubjectRestController {

    private final transient SubjectRestServiceImpl subjectService;

    @Autowired
    public SubjectRestController(final SubjectRestServiceImpl subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * Method provide all subjects from database to json format.
     *
     * @return all entity in json format
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Subject>> getAllEntity() {
        final List<Subject> subjects = subjectService.getAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    /**
     * Method for search entity by Id into database.
     *
     * @param subjectId path variable
     * @return entity by id
     */
    @GetMapping(value = "/{subjectId:[\\d]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Subject> getSubjectById(@PathVariable final Long subjectId) {
        final Subject subject = subjectService.getEntityById(subjectId).get();
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    /**
     * Method provide creating new object and insert into database.
     *
     * @param subject dto object parsed from json
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> insertEntity(@Validated @RequestBody final SubjectDTO subject) {
        subjectService.insert(subject);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method provide delete subject from database.
     *
     * @param subjectId part of path and id of needed object
     */
    @DeleteMapping(value = "/{subjectId:[\\d]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteEntity(@PathVariable final Long subjectId) {
        subjectService.delete(subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method provide updating object into database.
     *
     * @param subject dto object parsed from json to dto object
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{subjectId:[\\d]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateEntity(@Validated @PathVariable final Long subjectId, @RequestBody final SubjectDTO subject) {
        subjectService.update(subjectId, subject);
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
