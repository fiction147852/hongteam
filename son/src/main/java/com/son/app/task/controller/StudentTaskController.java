package com.son.app.task.controller;

import com.son.app.attachment.AttachmentFileVO;
import com.son.app.security.service.CustomUserDetails;
import com.son.app.task.service.StudentTaskService;
import com.son.app.task.service.TaskListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class StudentTaskController {

    @Autowired
    private StudentTaskService studentTaskService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("student/{lectureNumber}/task")
    public String detailSubjecAttendancePage(@PathVariable Integer lectureNumber, Model model) {
        model.addAttribute("lectureNumber", lectureNumber);

        return "task/student/taskList";
    }

    @GetMapping("student/{lectureNumber}/task/list")
    @ResponseBody
    public List<TaskListVO> getTaskList(@PathVariable Integer lectureNumber, @AuthenticationPrincipal CustomUserDetails principal,  @RequestParam(required = false) String title, @RequestParam(required = false) String taskSubmitStatus, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        int studentNumber = principal.getMember().getIdNumber();
        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;
        return studentTaskService.taskList(lectureNumber, studentNumber, title, taskSubmitStatus, startRow, endRow);
    }

    @GetMapping("student/{lectureNumber}/task/count")
    @ResponseBody
    public int getTaskCount(@PathVariable Integer lectureNumber, @AuthenticationPrincipal CustomUserDetails principal, @RequestParam(required = false) String title, @RequestParam(required = false) String taskSubmitStatus) {
        int studentNumber = principal.getMember().getIdNumber();
        return studentTaskService.taskCount(lectureNumber, studentNumber, title, taskSubmitStatus);
    }

    // 과제 상세 페이지 이동
    @GetMapping("student/{lectureNumber}/task/{taskNumber}")
    public String taskDetailsPage(@PathVariable Integer taskNumber, @AuthenticationPrincipal CustomUserDetails principal, Model model) {
        int studentNumber = principal.getMember().getIdNumber();
        TaskListVO taskListVO = studentTaskService.taskInfo(taskNumber, studentNumber);
        List<AttachmentFileVO> attachmentFileVOList = studentTaskService.taskSubjectFile(taskNumber, studentNumber);

        model.addAttribute("attachmentFileVOList", attachmentFileVOList);
        model.addAttribute("taskListVO", taskListVO);

        return "task/student/taskInfo";
    }

    @GetMapping("student/{lectureNumber}/task/{taskNumber}/download")
    @ResponseBody
    public ResponseEntity<Resource> taskDownload(@RequestParam String originalFileName, @RequestParam String saveFileName) throws MalformedURLException {
        UrlResource urlResource = new UrlResource("file:" + uploadPath + "/" + saveFileName);

        String encodedOriginalFileName = UriUtils.encode(originalFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedOriginalFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

    // 파일 업로드
    @PostMapping("student/{lectureNumber}/task/{taskNumber}")
    public String filesUpload(@RequestPart("files")List<MultipartFile> multipartFileList, @PathVariable Integer taskNumber, @AuthenticationPrincipal CustomUserDetails principal, @PathVariable Integer lectureNumber) {
        int studentNumber = principal.getMember().getIdNumber();

        studentTaskService.uploadFiles(multipartFileList, taskNumber, studentNumber, lectureNumber);

        return "redirect:/student/" + lectureNumber + "/task";
    }

    // 파일 삭제
    @DeleteMapping("student/{lectureNumber}/task/{taskNumber}")
    @ResponseBody
    public void deleteFiles(@PathVariable Integer lectureNumber, @PathVariable Integer taskNumber, @AuthenticationPrincipal CustomUserDetails principal) {
        int studentNumber = principal.getMember().getIdNumber();
        studentTaskService.removeSubmissionFile(taskNumber, lectureNumber, studentNumber);
    }


}
