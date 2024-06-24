package com.son.app.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.member.service.ParentService;
import com.son.app.member.service.StudentVO;

@Controller
public class ParentController {
	
	@Autowired
	ParentService parentService;
	
    @GetMapping("parent")
    public String getParent(Model model) {
		List<StudentVO> list = parentService.ParentInfoList();
		model.addAttribute("parent", list);
		
        return "member/parent/parent_info";
    }
}
	
