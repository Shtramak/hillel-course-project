package com.courses.tellus.autosalon.controller.springrest;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.model.dto.AutosalonDto;
import com.courses.tellus.autosalon.service.springrest.AutosalonServiceImplRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springrest/autosalon/salon")
public class AutosalonControllerRests {

    private final transient AutosalonServiceImplRest autosalonService;

    @Autowired
    public AutosalonControllerRests(final AutosalonServiceImplRest autosalonService) {
        this.autosalonService = autosalonService;
    }

    /**
     * Get all autosalon.
     * @return list of autosalon.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Autosalon>> findAllAutoSalon() {
        try {
            final List<Autosalon> autoList = autosalonService.getAll();
            return new ResponseEntity<List<Autosalon>>(autoList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<List<Autosalon>>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get autosalon by id.
     * @param idAutosalon id of autosalon.
     * @return autosalon.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Autosalon>> getAutosalonById(@PathVariable("id") final Long idAutosalon) {
        try {
            final Optional<Autosalon> autosalon = autosalonService.getAutosalonById(idAutosalon);
            return new ResponseEntity<Optional<Autosalon>>(autosalon, HttpStatus.OK);
        }  catch (IllegalArgumentException e) {
            return new ResponseEntity<Optional<Autosalon>>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete autosalon by id.
     * @param idAutosalon id of autosalon.
     * @return string.
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAutosalonById(@PathVariable ("id") final Long idAutosalon) {
        try {
            autosalonService.delete(idAutosalon);
            return new ResponseEntity<String>("Autosalon successfully deleted", HttpStatus.OK);
        }   catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update autosalon.
     * @param autosalonDto autosalon.
     * @return autosalon.
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Autosalon> updateAutosalon(@RequestBody final AutosalonDto autosalonDto) {
        try {
            final Autosalon autosalon = autosalonService.update(autosalonDto);
            return new ResponseEntity<Autosalon>(autosalon, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<Autosalon>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Insert autosalon.
     * @param autosalonDto autosalon.
     * @return autosalon.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Autosalon> insertAutosalon(@RequestBody final AutosalonDto autosalonDto) {
        try {
            final Autosalon autosalon = autosalonService.insert(autosalonDto);
            return new ResponseEntity<Autosalon>(autosalon, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<Autosalon>(HttpStatus.BAD_REQUEST);
        }
    }
}
