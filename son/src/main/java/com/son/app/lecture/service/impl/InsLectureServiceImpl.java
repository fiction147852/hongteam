package com.son.app.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.mapper.InsLectureMapper;
import com.son.app.lecture.service.InsLectureService;
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
    public LectureVO lectureInfo(Integer lectureNumber) {
        return lectureMapper.selectLectureInfo(lectureNumber);
    }
	
	@Override
	public List<Integer> getStudentNumbersByLecture(Integer lectureNumber) {
		return lectureMapper.getStudentNumbersByLecture(lectureNumber);
	}
}
