package com.example.testtask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mainPage")
public class MainPageController {

    @GetMapping("")
    public String showMainPage(Model model) {
        return "index";
    }
}
