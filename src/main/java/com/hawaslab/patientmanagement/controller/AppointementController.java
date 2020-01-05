package com.hawaslab.patientmanagement.controller;


import com.hawaslab.patientmanagement.exception.BadRequestAlertException;
import com.hawaslab.patientmanagement.model.Appointement;
import com.hawaslab.patientmanagement.service.AppointementService;
import com.hawaslab.patientmanagement.util.HeaderUtil;
import com.hawaslab.patientmanagement.util.PaginationUtil;
import com.hawaslab.patientmanagement.util.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AppointementController {


    private static final String ENTITY_NAME = "appointement";


    private final AppointementService appointementService;

    public AppointementController(AppointementService appointementService) {
        this.appointementService = appointementService;
    }

    /**
     * {@code POST  /appointements} : Create a new appointement.
     *
     * @param appointement the appointement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appointement, or with status {@code 400 (Bad Request)} if the appointement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/appointement")
    public ResponseEntity<Appointement> createAppointement(@Valid @RequestBody Appointement appointement) throws URISyntaxException {
        if (appointement.getId() != null) {
            throw new BadRequestAlertException("A new appointement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Appointement result = appointementService.save(appointement);
        return ResponseEntity.created(new URI("/api/appointements/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("api", false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /appointements} : Updates an existing appointement.
     *
     * @param appointement the appointement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appointement,
     * or with status {@code 400 (Bad Request)} if the appointement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appointement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/appointements")
    public ResponseEntity<Appointement> updateAppointement(@Valid @RequestBody Appointement appointement) throws URISyntaxException {

        if (appointement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Appointement result = appointementService.save(appointement);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("api", false, ENTITY_NAME, appointement.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /appointements} : get all the appointements.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appointements in body.
     */
    @GetMapping("/appointements")
    public ResponseEntity<List<Appointement>> getAllAppointements(Pageable pageable) {
        Page<Appointement> page = appointementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /appointements/:id} : get the "id" appointement.
     *
     * @param id the id of the appointement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appointement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/appointements/{id}")
    public ResponseEntity<Appointement> getAppointement(@PathVariable Long id) {
        Optional<Appointement> appointement = appointementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appointement);
    }

    /**
     * {@code DELETE  /appointements/:id} : delete the "id" appointement.
     *
     * @param id the id of the appointement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/appointements/{id}")
    public ResponseEntity<Void> deleteAppointement(@PathVariable Long id) {
        appointementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert("api", false, ENTITY_NAME, id.toString())).build();
    }
}
