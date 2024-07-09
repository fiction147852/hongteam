package com.son.app.lecture.controller;

import com.son.app.lecture.service.LectureMaterialDetailVO;
import com.son.app.lecture.service.LectureMaterialVO;
import com.son.app.lecture.service.StudentLectureService;
import com.son.app.security.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class StudentLectureController {

    @Autowired
    private StudentLectureService studentLectureService;

    @GetMapping("student/{lectureNumber}/lectureMaterials")
    public String detailSubjecAttendancePage(@PathVariable Integer lectureNumber, Model model) {
        model.addAttribute("lectureNumber", lectureNumber);

        return "lecture/student/lectureMaterials";
    }

    @GetMapping("student/{lectureNumber}/lectureMaterials/list")
    @ResponseBody
    public List<LectureMaterialVO> getLectureMaterials(@PathVariable Integer lectureNumber, @RequestParam(required = false) String title, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;
        return studentLectureService.lectureMaterialList(lectureNumber, title, startRow, endRow);
    }

    @GetMapping("student/{lectureNumber}/lectureMaterials/count")
    @ResponseBody
    public int getLectureMaterialsCount(@PathVariable Integer lectureNumber, @RequestParam(required = false) String title) {
        return studentLectureService.lectureMaterialCount(lectureNumber, title);
    }

    @GetMapping("student/{lectureNumber}/lectureMaterials/{lectureMaterialNumber}")
    public String lectureMaterialInfo(@PathVariable Integer lectureMaterialNumber, Model model) {
        LectureMaterialDetailVO lectureMaterialDetailVO = studentLectureService.lectureMaterialInfo(lectureMaterialNumber);
        model.addAttribute("lectureMaterialDetailVO", lectureMaterialDetailVO);

        return "lecture/student/lectureMaterialInfo";
    }

    @GetMapping("student/{lectureNumber}/lectureMaterials/{lectureMaterialNumber}/download")
    @ResponseBody
    public ResponseEntity<Resource> materialDownload(@RequestParam String originalFileName, @RequestParam String saveFileName) throws MalformedURLException {
        UrlResource urlResource = new UrlResource("file:" + "/Users/sondonghan/documents/uploads/materials/b001/" + saveFileName);

        String encodedOriginalFileName = UriUtils.encode(originalFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedOriginalFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }
}
