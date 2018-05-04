package com.courses.tellus.web.controller.rest;

import java.util.List;

import com.courses.tellus.entity.dto.UniversityDto;
import com.courses.tellus.entity.model.University;
import com.courses.tellus.service.rest.UniversityRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/university")
public class UniversityRestController {
    @Autowired
    private final transient UniversityRestServiceImpl restService;

    public UniversityRestController(final UniversityRestServiceImpl restService) {
        this.restService = restService;
    }

   /**
            * Method provide all entities from database to json format.
     *
             * @return all entities in json format
     */

   @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<University> getAll() {
        return restService.getAll();
    }

    /**
     * Method for getting entity by Id.
     * @param uniId unique id of object
     * @return Optional of university
     * */

    @GetMapping(value = "/{uniId:[\\d]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public University getById(@PathVariable final Long uniId) {
        return restService.getById(uniId).get();
    }

    /**
     * Method provide creating new object and insert it into database.
     *
     * @param university dto object obtained from jsp
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void insert(@RequestBody final UniversityDto university) {
        restService.insert(university);
    }

    /**
     * Method provide delete entity from database.
     *
     * @param uniId part of path and id of selected object
     */

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{uniId:[\\d]+}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable final Long uniId) {
        restService.delete(uniId);
    }

    /**
     * Method provide updating object into database.
     *
     * @param university dto object obtained from jsp
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{uniId:[\\d]+}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public void update(@PathVariable final Long uniId, @RequestBody final UniversityDto university) {
        if (restService.getById(uniId).isPresent()) {
            restService.update(university);
        }
    }
}
