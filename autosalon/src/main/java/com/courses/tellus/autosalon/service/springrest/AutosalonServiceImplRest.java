package com.courses.tellus.autosalon.service.springrest;

import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.model.dto.AutosalonDto;
import com.courses.tellus.autosalon.repository.AutosalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutosalonServiceImplRest implements AutosalonServiceRest {

    private final transient AutosalonRepository autosalonRep;

    @Autowired
    public AutosalonServiceImplRest(final AutosalonRepository autosalonRep) {
        this.autosalonRep = autosalonRep;
    }

    /**
     * Get list of autosalon.
     * @return list of autosalon.
     */
    @Override
    public List<Autosalon> getAll() {
        return autosalonRep.findAll();
    }

    /**
     * Add autosalon in database.
     * @param autosalonDto autosalon.
     * @return result.
     */
    @Override
    public Autosalon insert(final AutosalonDto autosalonDto) {
        final Autosalon autosalon = new Autosalon();
        autosalon.setName(autosalonDto.getName());
        autosalon.setAddress(autosalonDto.getAddress());
        autosalon.setTelephone(autosalonDto.getTelephone());
        return autosalonRep.save(autosalon);
    }

    /**
     * Update autosalon in database.
     * @param autosalonDto autosalon.
     * @return auto.
     */
    @Override
    public Autosalon update(final AutosalonDto autosalonDto) {
        final Autosalon autosalon = new Autosalon();
        autosalon.setId(autosalonDto.getId());
        autosalon.setName(autosalonDto.getName());
        autosalon.setAddress(autosalonDto.getAddress());
        autosalon.setTelephone(autosalonDto.getTelephone());
        return autosalonRep.save(autosalon);
    }

    /**
     * Delete autosalon from database by id.
     * @param idAutosalon id autosalon.
     */
    @Override
    public void delete(final Long idAutosalon) {
        autosalonRep.deleteById(idAutosalon);

    }

    /**
     * Get autosalon by id.
     * @param idAutosalon id autosalon.
     * @return autosalon.
     */
    @Override
    public Optional<Autosalon> getAutosalonById(final Long idAutosalon) {
        return autosalonRep.findById(idAutosalon);
    }
}
