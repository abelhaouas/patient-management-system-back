package com.hawaslab.patientmanagement.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointement")
@Setter
@Getter

public class Appointement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String patient;
    private Date date;
    private String time;
}
