package com.son.app.lecture.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.mapper.InsLectureMapper;
import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureInstructorVO;
import com.son.app.lecture.service.LectureVO;

@Service
public class InsLectureServiceImpl implements InsLectureService {
	
	@Autowired
	InsLectureMapper lectureMapper;

	@Override
	public List<LectureVO> lectureList() {
		return lectureMapper.selectLectureAll();
	}

	@Override
	public LectureVO lectureInfo(LectureVO lectureVO) {
		return lectureMapper.selectLectureInfo(lectureVO);
	}

	@Override
	public int insertLecture(LectureVO lectureVO) {
		int result = lectureMapper.insertLectureInfo(lectureVO);
		
		return result == 1 ? lectureVO.getLectureNumber() : -1;
	}

	@Override
	public Map<String, Object> updateLecture(LectureVO lectureVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = lectureMapper.updateLetureInfo(lectureVO);
		
		if(result == 1) {
			inSuccessed = true;
		}
		
		map.put("result", inSuccessed);
		map.put("target", lectureVO);
		
		return map;
	}

	@Override
	public int deleteLecture(int lectureNo) {
		return lectureMapper.deleteLetureInfo(lectureNo);
	}
}
