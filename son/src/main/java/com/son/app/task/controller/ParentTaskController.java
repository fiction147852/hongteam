package com.son.app.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.task.service.ParentTaskListVO;
import com.son.app.task.service.ParentTaskService;

@Controller
public class ParentTaskController {

    @Autowired
    private ParentTaskService parentTaskService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("parent/{studentNumber}/{lectureNumber}/task")
    public String mainPage(@PathVariable Integer studentNumber, 
    					   @PathVariable Integer lectureNumber, 
    					   Model model) {
        model.addAttribute("lectureNumber", lectureNumber);
        model.addAttribute("studentNumber", studentNumber);

        return "task/parent/taskList";
    }

    @GetMapping("parent/{studentNumber}/{lectureNumber}/task/list")
    @ResponseBody
    public List<ParentTaskListVO> getTaskList(@PathVariable Integer studentNumber,
    										  @PathVariable Integer lectureNumber) {

        return parentTaskService.taskList(lectureNumber, studentNumber);
    }
 
}
