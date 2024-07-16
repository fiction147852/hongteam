package com.son.app.exam.service;

import com.son.app.attachment.AttachmentFileVO;
import lombok.Data;

@Data
public class ExamInfoVO {

    private String detailSubjectName;
    private Integer limitTime;
    private Integer studentNumber;

    private Integer questionNumber;
    private String questionTypeCode;
    private String text;
    private Integer score;
    private String realAnswer;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String optionFive;
    private AttachmentFileVO attachmentFileVO;
}
