package com.courses.tellus.autosalon.controller.springrest;

import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.service.springrest.AutoServiceImplRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/springrest/autosalon/auto")
public class AutoControllerRest {

    private final transient AutoServiceImplRest autoService;

    @Autowired
    public AutoControllerRest(final AutoServiceImplRest autoService) {
        this.autoService = autoService;
    }

    /**
     * Get all auto.
     * @return list of auto.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Auto>> findAllAuto() {
        try {
            final Iterable<Auto> autoList = autoService.getAll();
            return new ResponseEntity<Iterable<Auto>>(autoList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<Iterable<Auto>>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get auto by id.
     * @param idAuto id of auto.
     * @return auto.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Auto>> getAutoById(@PathVariable final Long idAuto) {
        try {
            final Optional<Auto> auto = autoService.getAutoById(idAuto);
            return new ResponseEntity<Optional<Auto>>(auto, HttpStatus.OK);
        }  catch (IllegalArgumentException e) {
            return new ResponseEntity<Optional<Auto>>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete auto by id.
     * @param idAuto id of auto.
     * @return result.
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAutoById(@PathVariable final Long idAuto) {
        try {
            autoService.delete(idAuto);
            return new ResponseEntity<String>("Auto successfully deleted", HttpStatus.OK);
        }   catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update auto.
     * @param autoDto auto.
     * @return result.
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Auto> updateAuto(@RequestBody final Auto autoDto) {
        try {
            final Auto auto = autoService.update(autoDto);
            return new ResponseEntity<Auto>(auto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<Auto>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Insert auto.
     * @param autoDto auto.
     * @return result.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Auto> insertAuto(@RequestBody final Auto autoDto) {
        try {
            final Auto auto = autoService.insert(autoDto);
            return new ResponseEntity<Auto>(auto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<Auto>(HttpStatus.BAD_REQUEST);
        }
    }
}
