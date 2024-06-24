package com.son.app.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.member.service.ParentService;

@Controller
public class ParentController {
	
	@Autowired
	ParentService childinfoService;
	
    @GetMapping("parent")
    public String getParent(Model model) {
        // 필요한 모델 데이터를 추가
        return "member/parent/parent_info";
    }
}
	
