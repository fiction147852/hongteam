package com.son.app.lecture.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ParentLectureService {
	//전체조회
	public List<LectureInstructorVO> ParentLectureInfoList();
	
	public List<ChildLectureVO> LectureList(int parentNumber);
}
