package com.son.app.lecture.service;

import com.son.app.attachment.AttachmentFileVO;
import lombok.Data;

import java.util.List;

@Data
public class LectureMaterialDetailVO {

    private LectureMaterialVO lectureMaterialVO;
    private List<AttachmentFileVO> attachmentFileVOList;
}
