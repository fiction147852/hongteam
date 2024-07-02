package com.son.app.lecture.service;

import com.son.app.attachment.AttachmentFileVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentLectureService {

    // 페이징 및 강의 자료 목록 조회
    public List<LectureMaterialVO> lectureMaterialList(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title, @Param("startRow") int startRow, @Param("endRow") int endRow);
    public int lectureMaterialCount(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title);

    // 강의 자료 상세 페이지
    public LectureMaterialDetailVO lectureMaterialInfo(Integer lectureMaterialNumber);
}
