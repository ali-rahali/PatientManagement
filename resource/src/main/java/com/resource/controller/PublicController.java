package com.resource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by z00382545 on 10/12/16.
 */

@RestController
@RequestMapping("/public")
public class PublicController {

    @RequestMapping("/resource1")
    public String resource1() {
        return "Here is public resource 1.";
    }

    @RequestMapping("/resource2")
    public String resource2() {
        return "Here is public resource 2.";
    }

}
