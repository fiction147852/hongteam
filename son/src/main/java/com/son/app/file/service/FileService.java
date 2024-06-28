package com.son.app.file.service;

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
	public void saveFiles(final int lectureMaterialNumber, final int onlineLectureNumber, final int taskNumber,
			final int questionNumber, final int taskSubmitNumber, final List<FileRequest> files) {
		if(CollectionUtils.isEmpty(files)) {
			return;
		}
		for (FileRequest file : files) {
			file.setLectureMaterialNumber(lectureMaterialNumber);
			file.setOnlineLectureNumber(onlineLectureNumber);
			file.setQuestionNumber(questionNumber);
			file.setTaskNumber(taskNumber);
			file.setTaskSubmitNumber(taskSubmitNumber);
		}
		fileMapper.saveAll(files);
	}
}
