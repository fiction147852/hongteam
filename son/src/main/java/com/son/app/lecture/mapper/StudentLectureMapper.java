package com.son.app.lecture.mapper;

import com.son.app.lecture.service.LectureMaterialVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentLectureMapper {

    public List<LectureMaterialVO> lectureMaterialList(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    public int lectureMaterialCount(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title);

}
