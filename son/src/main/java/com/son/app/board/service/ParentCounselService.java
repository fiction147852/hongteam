package com.son.app.board.service;

import java.util.List;
import java.util.Map;

import com.son.app.member.service.StudentVO;

public interface ParentCounselService {
	// 학부모 계정에서의 학부모 QNA게시판 조회
	public List<ParentCounselVO> ParentCounselList();
	
	public ParentCounselVO ParentCounselInfo(int counselNumber);
	
	public int insertParentCounsel(ParentCounselVO parentcounselVO);
	
	public Map<String, Object> updateParentCounsel(ParentCounselVO parentcounselVO);
	
	public int deleteParentCounsel (int counselNumber);
	
	public List<LectureNameVO> StudentInLecture(int studentNumber);

	public List<StudentVO> getStudentsByParent(int parentNumber);
}
