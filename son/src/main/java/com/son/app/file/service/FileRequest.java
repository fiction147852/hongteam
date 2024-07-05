package com.son.app.file.service;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileRequest {
	private Integer attachmentFileNumber;
	private String originalFileName;
	private String saveFileName;
	private long fileSize;
	private boolean isDeleted;
    private Integer lectureMaterialNumber;
    private Integer onlineLectureNumber;
    private Integer taskNumber;
    private Integer questionNumber;
    private Integer taskSubmitNumber;
    private Integer studentQuestionNumber;
    private String filePath;
	
	@Builder
	public FileRequest(String originalFileName, String saveFileName, String filePath, long fileSize) {
		this.originalFileName = originalFileName;
		this.saveFileName = saveFileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
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
