package com.son.app.task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.son.app.file.common.FileUtils;
import com.son.app.file.service.FileRequest;
import com.son.app.file.service.FileResponse;
import com.son.app.file.service.FileService;
import com.son.app.page.PageVO;
import com.son.app.task.mapper.TaskMapper;
import com.son.app.task.service.TaskService;
import com.son.app.task.service.TaskVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TaskController {
	
	@Autowired
	TaskService taskService;
	@Autowired
	TaskMapper taskMapper;
	@Autowired
	FileService fileService;
	@Autowired
	FileUtils fileUtils;
	
	// 전체
	@GetMapping("/instructor/{lectureNumber}/taskList")
	public String taskList(@PathVariable Integer lectureNumber, 
					       @RequestParam(defaultValue = "1") int page, 
					       Model model) {
			PageVO pageVO = taskService.getPageInfo(lectureNumber, page);
			List<TaskVO> taskList = taskService.taskList(lectureNumber, pageVO);
			
			model.addAttribute("taskList", taskList);
			model.addAttribute("pageVO", pageVO);
			model.addAttribute("lectureNumber", lectureNumber);
		return "task/instructor/taskList";
	}
	
	// 상세
	@GetMapping("/instructor/{lectureNumber}/taskInfo")
	public String taskInfo(@PathVariable Integer lectureNumber, @RequestParam Integer taskNumber, Model model) {
	    TaskVO taskInfo = taskService.taskInfo(taskNumber);

	        model.addAttribute("taskInfo", taskInfo);
	        model.addAttribute("lectureNumber", lectureNumber);
	        return "task/instructor/taskInfo";
	    }
	
	@GetMapping("/instructor/{lectureNumber}/task/{taskNumber}/submittedStudents")
	public String submittedStudentList(@PathVariable Integer lectureNumber,
									   @PathVariable Integer taskNumber,
									   @RequestParam(defaultValue = "1") int page,
									   Model model) {
		PageVO pageVO = taskService.getSubmittedStudentPageInfo(taskNumber, page);
		List<TaskVO> submittedStudents = taskService.getSubmittedStudentsList(taskNumber, lectureNumber, pageVO);
		
		model.addAttribute("submittedStudents", submittedStudents);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("lectureNumber", lectureNumber);
		model.addAttribute("taskNumber", taskNumber);
		return "task/instructor/submittedList";
	}
	
	@GetMapping("instructor/{lectureNumber}/task/{taskNumber}/submittedInfo")
	public String submittedStudentInfo(@PathVariable Integer lectureNumber,
									   @PathVariable Integer taskNumber,
									   @RequestParam Integer taskSubmitNumber,
									   Model model) {
		TaskVO submittedInfo = taskMapper.selectSubmittedInfo(taskSubmitNumber);
		
		model.addAttribute("submittedInfo", submittedInfo);
		model.addAttribute("lectureNumber", lectureNumber);
		model.addAttribute("taskNumber", taskNumber);
		model.addAttribute("taskSubmitNumber", taskSubmitNumber);
		return "task/instructor/submittedInfo";
	}
	
	// 등록 페이지
	@GetMapping("/instructor/{lectureNumber}/taskInsert")
	public String taskInsertForm(@PathVariable Integer lectureNumber, Model model) {
	    model.addAttribute("lectureNumber", lectureNumber);
	    return "task/instructor/taskInsert";
	}
	
	// 등록 처리페이지
	@PostMapping("/instructor/{lectureNumber}/taskInsert")
	public String taskInsertProcess(@PathVariable Integer lectureNumber, TaskVO taskVO, @RequestParam("files") List<MultipartFile> multipartFiles) {
	    taskVO.setLectureNumber(lectureNumber);
	    // 태스크 저장
	    int taskNumber = taskService.insertTask(taskVO);
	    
	    // 파일 업로드 및 저장
	    if (multipartFiles != null && !multipartFiles.isEmpty()) {
	    	System.out.println("Files received: " + multipartFiles.size());
	        List<FileRequest> fileRequests = fileUtils.uploadFiles(multipartFiles);
	        System.out.println("FileRequests created: " + fileRequests.size());
	        fileService.saveFiles(null, null, taskNumber, null, null, null, fileRequests);
	    } else {
	    	System.out.println("No files received");
	    }
	    
	    return "redirect:/instructor/" + lectureNumber + "/taskList";
	}
	
	// 수정 페이지
	@GetMapping("/instructor/{lectureNumber}/taskUpdate/{taskNumber}")
	public String taskUpdateForm(@PathVariable Integer lectureNumber, @PathVariable Integer taskNumber, Model model) {
	    TaskVO findVO = taskService.taskInfo(taskNumber);
	    if (findVO != null && findVO.getLectureNumber() == lectureNumber) {
	        model.addAttribute("taskInfo", findVO);
	        model.addAttribute("lectureNumber", lectureNumber);
	        return "task/instructor/taskUpdate";
	    } else {
	        return "redirect:/error";
	    }
	}
	
	// 수정 처리페이지
	@PostMapping("/instructor/{lectureNumber}/taskUpdate/{taskNumber}")
	@ResponseBody
	public Map<String, Object> taskUpdateJSON(@PathVariable Integer lectureNumber, 
	                                          @PathVariable Integer taskNumber,
	                                          TaskVO taskVO,
	                                          @RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles,
	                                          @RequestParam(value = "removeFileIdsStr", required = false) String removeFileIdsStr) {
	    
	    final Logger log = LoggerFactory.getLogger(ArrayList.class);
	    Map<String, Object> result = new HashMap<>();
	    
	    try {
	    	if (!(taskVO.getLectureNumber() == lectureNumber)) {
	            throw new IllegalArgumentException("Invalid lecture number");
	        }
	    	
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<Integer> removeFileIds = removeFileIdsStr != null ? 
	            objectMapper.readValue(removeFileIdsStr, new TypeReference<List<Integer>>(){}) : 
	            new ArrayList<>();

	        // 1. 태스크 정보 수정
	        result = taskService.updateTask(taskVO);

	        if (multipartFiles != null && !multipartFiles.isEmpty()) {
	            // 2. 파일 업로드 (to disk)
	            List<FileRequest> uploadFiles = fileUtils.uploadFiles(multipartFiles);
	            
	            // 3. 파일 정보 저장 (to database)
	            fileService.saveFiles(null, null, taskVO.getTaskNumber(), null, null, null, uploadFiles);
	        }

	        if (!removeFileIds.isEmpty()) {
	            // 4. 삭제할 파일 정보 조회 (from database)
	            List<FileResponse> deleteFiles = fileService.findAllFileByAttachmentFileNumber(removeFileIds);

	            // 5. 파일 삭제 (from disk)
	            fileUtils.deleteFiles(deleteFiles);

	            // 6. 파일 삭제 (from database)
	            fileService.deleteAllFileByAttachmentFileNumber(removeFileIds);
	        }

	        result.put("success", true);
	    } catch (Exception e) {
	        result.put("success", false);
	        result.put("error", e.getMessage());
	        // 로그에 상세한 오류 정보 기록
	        log.error("Error in taskUpdateJSON", e);
	    }

	    return result;
	}
	
	
	// 삭제
	@GetMapping("/instructor/{lectureNumber}/taskDelete")
	public String taskDelete(@PathVariable Integer lectureNumber, @RequestParam Integer taskNo) {
	    TaskVO task = taskService.taskInfo(taskNo);
	    if (task != null && task.getLectureNumber() == lectureNumber) {
	        taskService.deleteTask(taskNo);
	        return "redirect:/instructor/" + lectureNumber + "/taskList";
	    } else {
	        // 잘못된 접근 처리
	        return "redirect:/error";
	    }
	}
}
