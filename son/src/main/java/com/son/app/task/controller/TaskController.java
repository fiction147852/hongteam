package com.son.app.task.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.son.app.file.common.FileUtils;
import com.son.app.file.service.FileRequest;
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
	public Map<String, Object> taskUpdateJSON(@RequestBody TaskVO taskVO) {
		return taskService.updateTask(taskVO);
	}
	
	
	// 삭제
	@GetMapping("/instructor/taskDelete")
	public String taskDelete(Integer taskNo) {
		taskService.deleteTask(taskNo);
		return "redirect:/instructor/taskList";
	}
}
