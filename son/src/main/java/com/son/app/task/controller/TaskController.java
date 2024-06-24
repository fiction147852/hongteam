package com.son.app.task.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.task.service.TaskService;
import com.son.app.task.service.TaskVO;

@Controller
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	// 전체
	@GetMapping("taskList")
	public String taskList(Model model) {
		List<TaskVO> list = taskService.taskList();
		model.addAttribute("taskList", list);
		return "task/taskList";
	}
	
	// 상세
	@GetMapping("taskInfo")
	public String taskInfo(TaskVO taskVO, Model model) {
		TaskVO findVO = taskService.taskInfo(taskVO);
		
		model.addAttribute("taskInfo", findVO);
		return "task/taskInfo";
	}
	
	// 등록 페이지
	@GetMapping("taskInsert")
	public String taskInsertForm() {
		return "task/taskInsert";
		
	}
	
	// 등록 처리페이지
	@PostMapping("taskInsert")
	public String taskInsertProcess(TaskVO taskVO) {
		taskService.insertTask(taskVO);
		return "redirect:taskList";
	}
	
	// 수정 페이지
	@GetMapping("taskUpdate")
	public String taskUpdateForm(TaskVO taskVO, Model model) {
		TaskVO findVO = taskService.taskInfo(taskVO);
		model.addAttribute("taskInfo", findVO);
		return "task/taskUpdate";
	}
	
	// 수정 처리페이지
	@PostMapping("taskUpdate")
	@ResponseBody
	public Map<String, Object> taskUpdateJSON(@RequestBody TaskVO taskVO) {
		return taskService.updateTask(taskVO);
	}
	
	// 삭제
	@GetMapping("taskDelete")
	public String taskDelete(Integer taskNo) {
		taskService.deleteTask(taskNo);
		return "redirect:taskList";
	}
}
