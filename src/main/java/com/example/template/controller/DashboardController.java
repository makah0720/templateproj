package com.example.template.controller;

import com.example.template.service.MaverickSpendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final MaverickSpendService maverickSpendService;

    public DashboardController(MaverickSpendService maverickSpendService) {
        this.maverickSpendService = maverickSpendService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("maverickSpend", maverickSpendService.findMaverickSpend());
        return "dashboard";
    }
}
