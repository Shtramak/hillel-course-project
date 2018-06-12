package com.courses.tellus.web.controller.rest;

import java.util.List;
import java.util.NoSuchElementException;

import com.courses.tellus.entity.dto.UniversityDto;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.service.rest.UniversityRestServiceImpl;
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
@RequestMapping("/rest/university")
public class UniversityRestController {
    @Autowired
    private final transient UniversityRestServiceImpl restService;

    @Autowired
    public UniversityRestController(final UniversityRestServiceImpl universityService) {
        this.restService = universityService;
    }

    /**
     * Method provide all universities from database to json format.
     *
     * @return all entity in json format
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<University>> getAll() {
        final List<University> university = restService.getAll();
        return new ResponseEntity<>(university, HttpStatus.OK);
    }

    /**
     * Method for search entity by Id into database.
     *
     * @param uniId path variable
     * @return entity by id
     */
    @GetMapping(value = "/{uniId:[\\d]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<University> getById(@PathVariable final Long uniId) {
        final University university = restService.getEntityById(uniId).get();
        return new ResponseEntity<>(university, HttpStatus.OK);
    }

    /**
     * Method provide creating new object and insert into database.
     *
     * @param university dto object parsed from json
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> insert(@Validated @RequestBody final UniversityDto university) {
        restService.insert(university);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method provide delete university from database.
     *
     * @param uniId part of path and id of needed object
     */
    @DeleteMapping(value = "/{uniId:[\\d]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> delete(@PathVariable final Long uniId) {
        restService.delete(uniId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method provide updating object into database.
     *
     * @param university dto object parsed from json to dto object
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{uniId:[\\d]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> update(@PathVariable final Long uniId, @RequestBody final UniversityDto university) {
        restService.update(uniId, university);
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

