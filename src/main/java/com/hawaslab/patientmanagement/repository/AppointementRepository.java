package com.hawaslab.patientmanagement.repository;


import com.hawaslab.patientmanagement.model.Appointement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointementRepository extends JpaRepository<Appointement, Long> {

}
