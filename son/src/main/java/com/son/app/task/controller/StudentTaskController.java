package com.son.app.task.controller;

import com.son.app.lecture.service.LectureMaterialVO;
import com.son.app.task.service.StudentTaskService;
import com.son.app.task.service.TaskListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentTaskController {

    @Autowired
    private StudentTaskService studentTaskService;

    @GetMapping("student/{lectureNumber}/task")
    public String detailSubjecAttendancePage(@PathVariable Integer lectureNumber, Model model) {
        model.addAttribute("lectureNumber", lectureNumber);

        return "task/student/taskList";
    }

    @GetMapping("student/{lectureNumber}/task/list")
    @ResponseBody
    public List<TaskListVO> getTaskList(@PathVariable Integer lectureNumber, @RequestParam(required = false) String title, @RequestParam(required = false) String taskSubmitStatus, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;
        return studentTaskService.taskList(lectureNumber, title, taskSubmitStatus, startRow, endRow);
    }

    @GetMapping("student/{lectureNumber}/task/count")
    @ResponseBody
    public int getTaskCount(@PathVariable Integer lectureNumber, @RequestParam(required = false) String title, @RequestParam(required = false) String taskSubmitStatus) {
        return studentTaskService.taskCount(lectureNumber, title, taskSubmitStatus);
    }
}
