package com.son.app.lecture.service.impl;

import com.son.app.attachment.AttachmentFileVO;
import com.son.app.lecture.mapper.StudentLectureMapper;
import com.son.app.lecture.service.LectureMaterialDetailVO;
import com.son.app.lecture.service.LectureMaterialVO;
import com.son.app.lecture.service.StudentLectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentLectureServiceImpl implements StudentLectureService {

    @Autowired
    private StudentLectureMapper studentLectureMapper;

    @Override
    public List<LectureMaterialVO> lectureMaterialList(Integer lectureNumber, String title, int startRow, int endRow) {
        return studentLectureMapper.lectureMaterialList(lectureNumber, title, startRow, endRow);
    }

    @Override
    public int lectureMaterialCount(Integer lectureNumber, String title) {
        return studentLectureMapper.lectureMaterialCount(lectureNumber, title);
    }

    // 동일한 트랜잭션 내에서 실행된다.
    @Transactional
    @Override
    public LectureMaterialDetailVO lectureMaterialInfo(Integer lectureMaterialNumber) {
        studentLectureMapper.incrementLectureMaterialViewCount(lectureMaterialNumber);
        LectureMaterialVO lectureMaterialVO = studentLectureMapper.lectureMaterialInfo(lectureMaterialNumber);
        List<AttachmentFileVO> attachmentFileVOList = studentLectureMapper.getAttachmentsForLectureMaterial(lectureMaterialNumber);

        LectureMaterialDetailVO lectureMaterialDetailVO = new LectureMaterialDetailVO();
        lectureMaterialDetailVO.setLectureMaterialVO(lectureMaterialVO);
        lectureMaterialDetailVO.setAttachmentFileVOList(attachmentFileVOList);

        return lectureMaterialDetailVO;
    }

}
