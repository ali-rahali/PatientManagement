package com.client.controller;

import com.client.domain.Patient;
import com.client.service.HttpService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by z00382545 on 10/11/16.
 */

@Controller
public class HttpController {

    private String authCode;
    private String token;

    @Autowired
    private HttpService httpService;

    @RequestMapping("/authCode")
    public String showAuthCode(@RequestParam("code") String authCode, Model model) {
        this.authCode=authCode;
        System.out.println(authCode);

        model.addAttribute("authCode", authCode);

        return "index";
    }

    @RequestMapping("/token")
    public String showAccessToken(@RequestBody Map<String, String> tokenInfo, Model model) {

        model.addAttribute("token", token);

        return "index";
    }


    @RequestMapping("/getAuthCode")
    public String redirect() {
        return "redirect:http://localhost:8081/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://127.0.0.1:8080/authCode";
    }

    @RequestMapping("/getToken")
    public String getToken(Model model) throws IOException {
        JSONObject jsonObject = httpService.getToken(this.authCode);

        this.token = (String) jsonObject.get("access_token");

        model.addAttribute("authCode", "Auth Code Expired");
        model.addAttribute("token", token);

        return "index";
    }

    @RequestMapping("/getResource")
    public String getResource(Model model) throws IOException {
        List<Patient> patientList = httpService.getResource(this.token);

        model.addAttribute("authCode", "Auth Code Expired");
        model.addAttribute("token", token);
        model.addAttribute("patientList", patientList);

        return "index";
    }

    @RequestMapping("/getPublicResource")
    public String getPublicResource(Model model) throws IOException {
        String sentence = httpService.getPublicInfo();
        model.addAttribute("sentence", sentence);

        return "index";
    }
}
