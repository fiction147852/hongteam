package com.son.app.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String getIndex(Model model) {
        // 필요한 모델 데이터를 추가
        return "parent";
    }
}