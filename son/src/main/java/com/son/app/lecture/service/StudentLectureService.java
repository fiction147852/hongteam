package com.son.app.lecture.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentLectureService {

    public List<LectureMaterialVO> lectureMaterialList(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    public int lectureMaterialCount(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title);
}
