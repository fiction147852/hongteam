package com.son.app.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.member.service.ParentService;
import com.son.app.member.service.ParentVO;
import com.son.app.member.service.StudentVO;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class ParentController {
    
    @Autowired
    ParentService parentService;

    @GetMapping("/parent")
    public String getParent(@AuthenticationPrincipal CustomUserDetails principal, HttpServletRequest request, Model model) {
        int parentNumber = principal.getMember().getIdNumber();
        
        List<StudentVO> list = parentService.ParentInfoList(parentNumber);
        HttpSession session = request.getSession();
        
        session.setAttribute("parent", list);
        
        return "member/parent/parent_info";
    }
    
    @GetMapping("/parent/{studentNumber}")
    public String childInfo() {
        return "member/parent/child_info";
    }
    
    @GetMapping("parent/mypage")
    public String myPage(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
        int parentNumber = principal.getMember().getIdNumber();
        List<ParentVO> parent = parentService.mypageInfo(parentNumber);
        model.addAttribute("parents", parent);
        List<StudentVO> student = parentService.ParentInfoList(parentNumber);
        model.addAttribute("child",student);
        return "member/parent/mypage";
    }
    

	/*
	 * @PostMapping("/save") public String saveProfile(@ModelAttribute ParentVO
	 * parent) { // 폼에서 전송된 데이터를 사용하여 사용자 정보를 업데이트하거나 저장하는 로직을 수행합니다.
	 * parentService.save(parent); return "redirect:/member/parent/mypage"; }
	 */
}
