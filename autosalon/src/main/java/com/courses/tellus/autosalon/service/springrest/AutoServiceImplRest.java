package com.courses.tellus.autosalon.service.springrest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.courses.tellus.autosalon.model.Auto;
import com.courses.tellus.autosalon.model.dto.AutoDto;
import com.courses.tellus.autosalon.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoServiceImplRest implements AutoServiceRest {

    private final transient AutoRepository autoRepository;

    @Autowired
    public AutoServiceImplRest(final AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    /**
     * Get list of auto.
     * @return list of auto.
     */
    @Override
    public List<Auto> getAll() {
        return autoRepository.findAll();
    }

    /**
     * Add auto in database.
     * @param autoDto auto.
     * @return result.
     */
    @Override
    public Auto insert(final AutoDto autoDto) {
        final Auto auto = new Auto();
        auto.setBrand(autoDto.getBrand());
        auto.setModel(autoDto.getModel());
        auto.setManufactYear(Integer.valueOf(autoDto.getManufactYear()));
        auto.setProducerCountry(autoDto.getProducerCountry());
        auto.setPrice(BigDecimal.valueOf(Long.parseLong(String.valueOf(autoDto.getPrice()))));
        return autoRepository.save(auto);

    }

    /**
     * Update auto in database.
     * @param autoDto auto.
     * @return auto.
     */
    @Override
    public Auto update(final AutoDto autoDto) {
        final Auto auto = new Auto();
        auto.setId(autoDto.getId());
        auto.setBrand(autoDto.getBrand());
        auto.setModel(autoDto.getModel());
        auto.setManufactYear(Integer.valueOf(autoDto.getManufactYear()));
        auto.setProducerCountry(autoDto.getProducerCountry());
        auto.setPrice(BigDecimal.valueOf(Long.parseLong(String.valueOf(autoDto.getPrice()))));
        return autoRepository.save(auto);
    }

    /**
     * Delete auto from database by id.
     * @param idAuto id auto.
     */
    @Override
    public void delete(final Long idAuto) {
        autoRepository.deleteById(idAuto);
    }

    /**
     * Get auto by id.
     * @param idAuto id auto.
     * @return auto.
     */
    @Override
    public Optional<Auto> getAutoById(final Long idAuto) {
        return autoRepository.findById(idAuto);
    }
}
