package com.hawaslab.patientmanagement.service;


import com.hawaslab.patientmanagement.model.Appointement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface AppointementService {

    /**
     * Save a appoitement.
     *
     * @param appointement the entity to save.
     * @return the persisted entity.
     */
    Appointement save(Appointement appointement);

    /**
     * Get all the appoitements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Appointement> findAll(Pageable pageable);


    /**
     * Get the "id" appoitement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Appointement> findOne(Long id);

    /**
     * Delete the "id" appoitement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);


}
