package com.son.app.file.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.son.app.file.service.FileResponse;
import com.son.app.file.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileRestController {
	
	@Autowired
	FileService fileService;
	
	/* 미사용중 */
	
	// 파일 리스트 즈회
	// 과제
	@GetMapping("/lms/task/instructor/{type}/{number}/files")
    public List<FileResponse> findAllFile(@PathVariable String type, @PathVariable Integer number) {
        return fileService.findAllFile(number, type);
	}
}
