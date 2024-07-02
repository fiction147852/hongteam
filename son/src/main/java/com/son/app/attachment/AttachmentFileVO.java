package com.son.app.attachment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AttachmentFileVO {

    private Integer attachmentFileNumber; // 기본 키
    private Integer lectureMaterialNumber;
    private Integer onlineLectureNumber;
    private Integer taskNumber;
    private Integer questionNumber;
    private Integer taskSubmitNumber;
    private String originalFileName;
    private String saveFileName;
    private String filePath;
    private Long fileSize;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deletedDate;

    // 0 or 1
    private Integer isDeleted;

}
