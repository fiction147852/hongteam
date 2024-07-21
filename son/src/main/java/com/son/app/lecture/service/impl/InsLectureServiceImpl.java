package com.son.app.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.mapper.InsLectureMapper;
import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureVO;
import com.son.app.page.PageVO;

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
	
    @Override
    public List<LectureVO> getStudentInfoByLecture(Integer lectureNumber, String searchType, String searchKeyword, PageVO pageVO) {
        int start = Math.max(0, (pageVO.getPage() - 1) * pageVO.getPageSize());
        int end = start + pageVO.getPageSize();
        return lectureMapper.getStudentInfoByLecture(lectureNumber, searchType, searchKeyword, start, end);
    }

    @Override
    public PageVO getStudentPageInfo(Integer lectureNumber, String searchType, String searchKeyword, int page) {
        int totalItems = lectureMapper.countStudentsByLecture(lectureNumber, searchType, searchKeyword);
        return new PageVO(page, totalItems, 5, 5);
    }
    
    @Override
    public List<LectureVO> getLecturesByInstructor(int instructorNumber) {
        return lectureMapper.selectLecturesByInstructor(instructorNumber);
    }
}
