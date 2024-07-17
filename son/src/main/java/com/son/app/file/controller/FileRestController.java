package com.son.app.file.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

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
	
	@Value("${upload.path}")
	private String uploadPath;
	
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
        //Path filePath = Paths.get("C:", "uploads").resolve(file.getFilePath().replaceFirst("^/", ""));
        
        Resource resource = new FileSystemResource(new File(uploadPath, file.getFilePath()));

        
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
	
	@GetMapping("instructor/{lectureNumber}/task/{taskNumber}/submittedInfo/download")
	@ResponseBody
	public ResponseEntity<Resource> submittedTaskDownload(
												        @PathVariable Integer lectureNumber,
												        @PathVariable Integer taskNumber,
												        @RequestParam Integer taskSubmitNumber,
												        @RequestParam String originalFileName,
												        @RequestParam String saveFileName) throws MalformedURLException, FileNotFoundException {

	    String filePath = uploadPath + taskSubmitNumber + "/" + saveFileName;
	    
	    UrlResource urlResource = new UrlResource("file:" + filePath);

	    // 파일이 존재하는지 확인합니다.
	    if (!urlResource.exists()) {
	        throw new FileNotFoundException("File not found: " + originalFileName);
	    }

	    String encodedOriginalFileName = UriUtils.encode(originalFileName, StandardCharsets.UTF_8);
	    String contentDisposition = "attachment; filename=\"" + encodedOriginalFileName + "\"";

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
	            .body(urlResource);
	}
	
	
}
