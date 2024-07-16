package com.son.app.task.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.son.app.attachment.AttachmentFileVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskListVO {
    private int rowNum;
    private Integer lectureNumber;
    private Integer taskSubmitNumber;
    private Integer taskNumber;
    private Integer studentNumber;
    private String title;
    private String description;
    private String taskSubmitStatus;
    private String feedbackStatus;
    private String feedback;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date postDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submitDeadline;

    private List<AttachmentFileVO> attachmentFileVOList;
}
