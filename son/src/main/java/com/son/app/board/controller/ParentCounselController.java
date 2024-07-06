package com.son.app.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.board.service.LectureNameVO;
import com.son.app.board.service.ParentCounselVO;
import com.son.app.board.service.impl.ParentCounselServiceImpl;
import com.son.app.member.service.StudentVO;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class ParentCounselController {

	@Autowired
	ParentCounselServiceImpl parentcounselService;

	// 전체 목록 조회
	@GetMapping("parent/counselList")
	public String ParentCounselList(Model model) {
		List<ParentCounselVO> list = parentcounselService.ParentCounselList();
		model.addAttribute("ParentCounselList", list);
		return "member/parent/parent_counsel_list";
	}

	// 상세 정보 조회
	@GetMapping("parent/counselInfo")
	public String ParentCounselInfo(@RequestParam int counselNumber, Model model) {
		ParentCounselVO findVO = parentcounselService.ParentCounselInfo(counselNumber);
		model.addAttribute("counselInfo", findVO);
		return "member/parent/parent_counsel_info";
	}

	// 등록 페이지로 이동
	@GetMapping("parent/counselInsert")
	public String ParentCounselInsertForm(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		int parentNumber = userDetails.getMember().getIdNumber(); // 로그인한 학부모의 번호를 가져옵니다.
		List<StudentVO> students = parentcounselService.getStudentsByParent(parentNumber); // 학부모와 연결된 학생 정보를 조회합니다.
		model.addAttribute("students", students); // 학생 정보를 모델에 추가합니다.
		return "member/parent/parent_counsel_insert";
	}

	// 등록 처리
	@PostMapping("parent/counselInsert")
	@ResponseBody
	public int ParentCounselInsertProcess(@AuthenticationPrincipal CustomUserDetails userDetails, ParentCounselVO parentcounselVO) {
		int parentNumber = userDetails.getMember().getIdNumber();
		parentcounselVO.setParentNumber(parentNumber);
		return parentcounselService.insertParentCounsel(parentcounselVO);

	}

	// 강의목록 가져오는 ajax
	@PostMapping("parent/StudentInLectureList")
	@ResponseBody
	public List<LectureNameVO> StudentInLectureList(int studentNumber) {
		List<LectureNameVO> list = parentcounselService.StudentInLecture(studentNumber);
		return list;
	}
	// 수정
	@GetMapping("parent/counselUpdate")
	public String taskUpdateForm(@AuthenticationPrincipal CustomUserDetails userDetails,ParentCounselVO parentcounselVO, Model model) {
		int parentNumber = userDetails.getMember().getIdNumber(); // 로그인한 학부모의 번호를 가져옵니다.
		List<StudentVO> students = parentcounselService.getStudentsByParent(parentNumber); // 학부모와 연결된 학생 정보를 조회합니다.
		model.addAttribute("students", students); // 학생 정보를 모델에 추가합니다.
		
		ParentCounselVO findVO = parentcounselService.ParentCounselInfo(parentcounselVO.getCounselNumber());
		model.addAttribute("counselInfo", findVO);
		
		List<LectureNameVO> list = parentcounselService.StudentInLecture(findVO.getStudentNumber());
		model.addAttribute("lectureList", list);
		return "member/parent/parent_counsel_update";
	}
	// 수정
	@PutMapping("parent/counselUpdate")
	@ResponseBody
	public Map<String, Object> ParentCounselUpdateJSON(@RequestBody ParentCounselVO parentcounselVO) {
		return parentcounselService.updateParentCounsel(parentcounselVO);
	}
	// 삭제
	@GetMapping("parent/counselDelete")
	public String ParentCounselDelete(Integer counselNumber) {
		parentcounselService.deleteParentCounsel(counselNumber);
		
		return "redirect:/parent/counselList";
	}

}
