package com.son.app.file.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.son.app.file.mapper.FileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileMapper fileMapper;

	@Transactional
	public void saveFiles(Integer lectureMaterialNumber, Integer onlineLectureNumber, Integer taskNumber,
			Integer questionNumber, Integer taskSubmitNumber, Integer studentQuestionNumber, List<FileRequest> files) {
		if(CollectionUtils.isEmpty(files)) {
			return;
		}
		for (FileRequest file : files) {
			file.setLectureMaterialNumber(lectureMaterialNumber);
			file.setOnlineLectureNumber(onlineLectureNumber);
			file.setQuestionNumber(questionNumber);
			file.setTaskNumber(taskNumber);
			file.setTaskSubmitNumber(taskSubmitNumber);
			file.setStudentQuestionNumber(studentQuestionNumber);
		}
		fileMapper.saveAll(files);
	}
	
	/**
     * 파일 리스트 조회
     * @param numbers - FK 번호 (FK)
     * @return 파일 리스트
     */
	public List<FileResponse> findAllFile(Integer number, String type) {
		return fileMapper.findAll(number, type);
	}
	
    /**
     * 파일 리스트 조회
     * @param attachmentFileNumber - PK 리스트
     * @return 파일 리스트
     */
	public List<FileResponse> findAllFileByAttachmentFileNumber(List<Integer> numbers) {
		if(CollectionUtils.isEmpty(numbers)) {
			return Collections.emptyList();
		}
		return fileMapper.findAllByAttachmentFileNumber(numbers);
	}
	
    /**
     * 파일 삭제 (from Database)
     * @param Numbers - PK 리스트
     */
	@Transactional
	public void deleteAllFileByAttachmentFileNumber(List<Integer> numbers) {
		if(CollectionUtils.isEmpty(numbers)) {
			return;
		}
		fileMapper.deleteAllByAttachmentFileNumber(numbers);
	}
	
    /**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보
     */
	public FileResponse findFileByAttachmentFileNumber(Integer attachmentFileNumber) {
		return fileMapper.findByAttachmentFileNumber(attachmentFileNumber);
	}
}
