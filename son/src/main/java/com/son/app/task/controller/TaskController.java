package com.son.app.task.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.son.app.file.common.FileUtils;
import com.son.app.file.service.FileRequest;
import com.son.app.file.service.FileResponse;
import com.son.app.file.service.FileService;
import com.son.app.task.service.TaskService;
import com.son.app.task.service.TaskVO;

@Controller
public class TaskController {
	
	@Autowired
	TaskService taskService;
	@Autowired
	FileService fileService;
	@Autowired
	FileUtils fileUtils;
	
	// 전체
	@GetMapping("/instructor/taskList")
	public String taskList(Model model) {
		List<TaskVO> list = taskService.taskList();
		model.addAttribute("taskList", list);
		return "task/instructor/taskList";
	}
	
	// 상세
	@GetMapping("/instructor/taskInfo")
	public String taskInfo(TaskVO taskVO, Model model) {
		TaskVO findVO = taskService.taskInfo(taskVO);
		
		model.addAttribute("taskInfo", findVO);
		return "task/instructor/taskInfo";
	}
	
	// 등록 페이지
	@GetMapping("/instructor/taskInsert")
	public String taskInsertForm() {
		return "task/instructor/taskInsert";
		
	}
	
	// 등록 처리페이지
	@PostMapping("/instructor/taskInsert")
	public String taskInsertProcess(TaskVO taskVO, @RequestParam("files") List<MultipartFile> multipartFiles) {
	    // 태스크 저장
	    int taskNumber = taskService.insertTask(taskVO);
	    
	    // 파일 업로드 및 저장
	    if (multipartFiles != null && !multipartFiles.isEmpty()) {
	        List<FileRequest> fileRequests = fileUtils.uploadFiles(multipartFiles);
	        fileService.saveFiles(null, null, taskNumber, null, null, fileRequests);
	    }
	    
	    return "redirect:/instructor/taskList";
	}
	
	// 수정 페이지
	@GetMapping("/instructor/taskUpdate")
	public String taskUpdateForm(TaskVO taskVO, Model model) {
		TaskVO findVO = taskService.taskInfo(taskVO);
		model.addAttribute("taskInfo", findVO);
		return "task/instructor/taskUpdate";
	}
	
//	// 수정 처리페이지
	@PostMapping("/instructor/taskUpdate")
	@ResponseBody
	public Map<String, Object> taskUpdateJSON(TaskVO taskVO, 
	                                          @RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles,
	                                          @RequestParam(value = "removeFileIdsStr", required = false) String removeFileIdsStr) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		List<Integer> removeFileIds = objectMapper.readValue(removeFileIdsStr, ArrayList.class);
	    // 1. 태스크 정보 수정
	    Map<String, Object> result = taskService.updateTask(taskVO);

	    if (multipartFiles != null && !multipartFiles.isEmpty()) {
	        // 2. 파일 업로드 (to disk)
	        List<FileRequest> uploadFiles = fileUtils.uploadFiles(multipartFiles);
	        
	        // 3. 파일 정보 저장 (to database)
	        fileService.saveFiles(null, null, taskVO.getTaskNumber(), null, null, uploadFiles);
	    }

	    if (removeFileIds != null && !removeFileIds.isEmpty()) {
	        // 4. 삭제할 파일 정보 조회 (from database)
	        List<FileResponse> deleteFiles = fileService.findAllFileByAttachmentFileNumber(removeFileIds);
	        
	        // 5. 파일 삭제 (from disk)
	        fileUtils.deleteFiles(deleteFiles);
	        
	        // 6. 파일 삭제 (from database)
//	        fileService.deleteAllFileByAttachmentFileNumber(removeFileIds);
	    }

	    return result;
	}
	
	
	// 삭제
	@GetMapping("/instructor/taskDelete")
	public String taskDelete(Integer taskNo) {
		taskService.deleteTask(taskNo);
		return "redirect:/instructor/taskList";
	}
}
