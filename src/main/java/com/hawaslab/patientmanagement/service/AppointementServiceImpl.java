package com.hawaslab.patientmanagement.service;

import com.hawaslab.patientmanagement.model.Appointement;
import com.hawaslab.patientmanagement.repository.AppointementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service
@Transactional
public class AppointementServiceImpl implements AppointementService {

    @Autowired
    AppointementRepository appointementRepository;

    @Override
    public Appointement save(Appointement appointement) {
        return appointementRepository.save(appointement);
    }

    @Override
    public Page<Appointement> findAll(Pageable pageable) {
        return appointementRepository.findAll(pageable);
    }

    @Override
    public Optional<Appointement> findOne(Long id) {
        return appointementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        appointementRepository.deleteById(id);
    }
}
