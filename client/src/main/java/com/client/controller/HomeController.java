package com.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by z00382545 on 10/12/16.
 */

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "index";
    }


}
