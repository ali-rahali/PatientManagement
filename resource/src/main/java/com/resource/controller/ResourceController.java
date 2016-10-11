package com.resource.controller;

import com.resource.domain.Patient;
import com.resource.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by z00382545 on 10/11/16.
 */

@RestController
public class ResourceController {

    @Autowired
    private PatientService patientService;

    @RequestMapping("/resource/patient/all")
    public List<Patient> findAll() {
        return patientService.findAll();
    }

}
