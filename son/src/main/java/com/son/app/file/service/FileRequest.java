package com.son.app.file.service;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileRequest {
	private Integer attachmentFileNumber;
	private String originalFileName;
	private String saveFileName;
	private long fileSize;
    private int lectureMaterialNumber;
    private int onlineLectureNumber;
    private int taskNumber;
    private int questionNumber;
    private int taskSubmitNumber;
	
	@Builder
	public FileRequest(String originalFileName, String saveFileName, long fileSize) {
		this.originalFileName = originalFileName;
		this.saveFileName = saveFileName;
		this.fileSize = fileSize;
	}
	
	public void setLectureMaterialNumber(int lectureMaterialNumber) {
		this.lectureMaterialNumber = lectureMaterialNumber;
	}

	public void setOnlineLectureNumber(int onlineLectureNumber) {
		this.onlineLectureNumber = onlineLectureNumber;
	}

	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public void setTaskSubmitNumber(int taskSubmitNumber) {
		this.taskSubmitNumber = taskSubmitNumber;
	}
	
	/*
	// 일반적인 생성자를 통한 객체 생성
    FileRequest fileRequest = new FileRequest("테스트.txt", "abcdeabcde.txt", 10768);
    
    // 빌더 패턴을 통한 객체 생성 1
    FileRequest fileRequest = FileRequest.builder()
            .originalName("테스트.txt")
            .saveName("abcdeabcde.txt")
            .size(10768)
            .build();
    
    // 빌더 패턴을 통한 객체 생성 2
    FileRequest fileRequest = FileRequest.builder()
            .size(10768)
            .saveName("abcdeabcde.txt")
            .originalName("테스트.txt")
            .build();
    
    // 빌더 패턴을 통한 객체 생성 3
    FileRequest fileRequest = FileRequest.builder()
            .saveName("abcdeabcde.txt")
            .build();
	*/
}
