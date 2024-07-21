package com.son.app.lecture.service;

import java.util.List;

import com.son.app.page.PageVO;

public interface InsLectureService {
	public List<LectureVO> lectureList();
	
	public LectureVO lectureInfo(Integer lectureNumber);
	
	List<Integer> getStudentNumbersByLecture(Integer lectureNumber);
	
	List<LectureVO> getStudentInfoByLecture(Integer lectureNumber, String searchType, String searchKeyword, PageVO pageVO);
    PageVO getStudentPageInfo(Integer lectureNumber, String searchType, String searchKeyword, int page);
    
    List<LectureVO> getLecturesByInstructor(int instructorNumber);
}
