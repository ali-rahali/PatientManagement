package com.client.controller;

import com.client.domain.Patient;
import com.client.service.HttpService;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * Created by z00382545 on 10/11/16.
 */

@Controller
public class HttpController {

    @Autowired
    private HttpService httpService;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/getResource")
    public String getResource(Model model) throws IOException {
        List<Patient> patientList = httpService.getResource();

//        for (JSONObject jsonObject : jsonObjectList) {
//            System.out.println(jsonObject);
//            System.out.println(jsonObject.get("firstName"));
//        }
        model.addAttribute("patientList", patientList);

        return "index";
    }
}
