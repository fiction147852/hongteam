package com.son.app.lecture.mapper;

import com.son.app.attachment.AttachmentFileVO;
import com.son.app.lecture.service.LectureMaterialVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentLectureMapper {

    // 페이징 및 강의 자료 목록 조회
    public List<LectureMaterialVO> lectureMaterialList(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title, @Param("startRow") int startRow, @Param("endRow") int endRow);
    public int lectureMaterialCount(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title);

    // 강의 자료 상세 페이지
    public LectureMaterialVO lectureMaterialInfo(Integer lectureMaterialNumber);
    public List<AttachmentFileVO> getAttachmentsForLectureMaterial(Integer lectureMaterialNumber);
    public int incrementLectureMaterialViewCount(Integer lectureMaterialNumber);
}
