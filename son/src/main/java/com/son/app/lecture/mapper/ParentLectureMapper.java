package com.son.app.lecture.mapper;

import java.util.List;

import com.son.app.lecture.service.ChildLectureVO;
import com.son.app.lecture.service.LectureInstructorVO;

public interface ParentLectureMapper {
    List<LectureInstructorVO> ParentLectureInfoAll();
    
    List<ChildLectureVO> ChildLectureList(int StudentNumber);
}