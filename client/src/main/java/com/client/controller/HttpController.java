package com.client.controller;

import com.client.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

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
    public String getResource() throws IOException {
        httpService.getResource();

        return "index";
    }
}
