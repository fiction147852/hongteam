package com.son.app.lecture.service.impl;

import com.son.app.lecture.mapper.StudentLectureMapper;
import com.son.app.lecture.service.LectureMaterialVO;
import com.son.app.lecture.service.StudentLectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentLectureServiceImpl implements StudentLectureService {

    @Autowired
    StudentLectureMapper studentLectureMapper;

    @Override
    public List<LectureMaterialVO> lectureMaterialList(Integer lectureNumber, String title, int startRow, int pageSize) {
        return studentLectureMapper.lectureMaterialList(lectureNumber, title, startRow, pageSize);
    }

    @Override
    public int lectureMaterialCount(Integer lectureNumber, String title) {
        return studentLectureMapper.lectureMaterialCount(lectureNumber, title);
    }
}
