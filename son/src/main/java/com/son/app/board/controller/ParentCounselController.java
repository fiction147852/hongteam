package com.son.app.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.board.service.ParentCounselService;
import com.son.app.board.service.ParentCounselVO;

@Controller
public class ParentCounselController {

    @Autowired
    ParentCounselService parentcounselService;

    //전체 목록 조회
    @GetMapping("parent/counselList")
    public String ParentCounselList(Model model) {
        List<ParentCounselVO> list = parentcounselService.ParentCounselList();
        model.addAttribute("ParentCounselList", list);
        return "member/parent/parent_counsel_list";
    }

    // 상세 정보 조회
    @GetMapping("parent/counselInfo")
    public String ParentCounselInfo(ParentCounselVO parentcounselVO, Model model) {
        ParentCounselVO findVO = parentcounselService.ParentCounselInfo(parentcounselVO);
        model.addAttribute("counselInfo", findVO);
        return "member/parent/parent_counsel_info";
    }

    // 등록 페이지로 이동
    @GetMapping("parent/counselInsert")
    public String ParentCounselInsertForm() {
    	
        return "member/parent/parent_counsel_insert";
    }

    // 등록 처리
    @PostMapping("parent/counselInsert")
    public String ParentCounselInsertProcess(ParentCounselVO parentcounselVO) {
        parentcounselService.insertParentCounsel(parentcounselVO);
        return "redirect:/instructor/taskList";
    }

    // 수정 페이지로 이동
    @GetMapping("parent/counselUpdate")
    public String taskUpdateForm(ParentCounselVO parentcounselVO, Model model) {
        ParentCounselVO findVO = parentcounselService.ParentCounselInfo(parentcounselVO);
        model.addAttribute("taskInfo", findVO);
        return "task/instructor/taskUpdate";
    }

    // 수정 처리
    @PostMapping("parent/counselUpdate")
    @ResponseBody
    public Map<String, Object> ParentCounselUpdateJSON(@RequestBody ParentCounselVO parentcounselVO) {
        return parentcounselService.updateParentCounsel(parentcounselVO);
    }

    // 삭제 처리
    @GetMapping("parent/counselDelete")
    public String ParentCounselDelete(Integer counselNumber) {
        parentcounselService.deleteParentCounsel(counselNumber);
        return "redirect:/parent/ParentCounselList";
    }

    // 기타 새로운 기능 추가
    // 예를 들어, 다른 메소드 추가 가능

}
