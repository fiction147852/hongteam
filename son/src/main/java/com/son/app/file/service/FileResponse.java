package com.son.app.file.service;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FileResponse {
	private Integer attachmentFileNumber;
	private String originalFileName;
	private String saveFileName;
	private long fileSize;
	private boolean isDeleted;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDateTime createdDate;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDateTime deletedDate;
	
    private Integer lectureMaterialNumber;
    private Integer onlineLectureNumber;
    private Integer taskNumber;
    private Integer questionNumber;
    private Integer taskSubmitNumber;
    private Integer studentQuestionNumber;
}
