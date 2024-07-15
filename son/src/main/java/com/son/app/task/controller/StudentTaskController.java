package com.son.app.task.controller;

import com.son.app.lecture.service.LectureMaterialVO;
import com.son.app.task.service.StudentTaskService;
import com.son.app.task.service.TaskListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
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

    // 과제 상세 페이지 이동
    @GetMapping("student/{lectureNumber}/task/{taskNumber}")
    public String taskDetailsPage(@PathVariable Integer taskNumber, Model model) {
        TaskListVO taskListVO = studentTaskService.taskInfo(taskNumber);
        model.addAttribute("taskListVO", taskListVO);

        return "task/student/taskInfo";
    }

    @GetMapping("student/{lectureNumber}/task/{taskNumber}/download")
    @ResponseBody
    public ResponseEntity<Resource> taskDownload(@RequestParam String originalFileName, @RequestParam String saveFileName) throws MalformedURLException {
        UrlResource urlResource = new UrlResource("file:" + "/Users/sondonghan/documents/uploads/tasks/b001/" + saveFileName);

        String encodedOriginalFileName = UriUtils.encode(originalFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedOriginalFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

    @PostMapping("student/{lectureNumber}/task/{taskNumber}")
    public String filesUpload(@RequestPart("files")List<MultipartFile> multipartFileList, @PathVariable Integer taskNumber, @PathVariable Integer lectureNumber) {
        studentTaskService.uploadFiles(multipartFileList, taskNumber);

        return "redirect:/student/" + lectureNumber + "/task/" + taskNumber +"/update";
    }

    // 과제 수정 페이지로 이동
    @GetMapping("student/{lectureNumber}/task/{taskNumber}/update")
    public ResponseEntity<String> taskUpdatePage() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("성공!!");
    }
}
