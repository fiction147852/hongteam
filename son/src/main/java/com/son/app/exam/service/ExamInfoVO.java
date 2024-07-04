package com.son.app.exam.service;

import com.son.app.attachment.AttachmentFileVO;
import lombok.Data;

@Data
public class ExamInfoVO {

    private String detailSubjectName;
    private Integer minute;

    private Integer questionNumber;
    private String text;
    private Integer score;
    private AttachmentFileVO attachmentFileVO;
    private QuestionOptionsVO questionOptionsVO;
}
