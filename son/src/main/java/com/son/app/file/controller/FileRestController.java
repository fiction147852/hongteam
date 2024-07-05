package com.son.app.file.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.son.app.file.common.FileUtils;
import com.son.app.file.service.FileResponse;
import com.son.app.file.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileRestController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	FileUtils fileUtils;
	
	/* 미사용중 */
	
	// 파일 리스트 즈회
	// 과제
	@GetMapping("/lms/task/instructor/{type}/{number}/files")
    public List<FileResponse> findAllFile(@PathVariable String type, @PathVariable Integer number) {
        return fileService.findAllFile(number, type);
	}
	
	// 과제 다운로드
	@GetMapping("/instructor/taskInfo/{taskNumber}/files/{attachmentFileNumber}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable final Integer taskNumber, @PathVariable final Integer attachmentFileNumber) {
        FileResponse file = fileService.findFileByAttachmentFileNumber(attachmentFileNumber);
        
        // 경로 구성 수정
        Path filePath = Paths.get("C:", "uploads").resolve(file.getFilePath().replaceFirst("^/", ""));
        Resource resource = new FileSystemResource(filePath.toFile());

        
        try { 
            String filename = URLEncoder.encode(file.getOriginalFileName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getFileSize() + "")
                    .body(resource);
            
        } catch(UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalFileName());
        }
    }
	
}
