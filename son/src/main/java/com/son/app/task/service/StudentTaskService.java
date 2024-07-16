package com.son.app.task.service;

import com.son.app.attachment.AttachmentFileVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentTaskService {

    public List<TaskListVO> taskList(Integer lectureNumber, Integer studentNumber, String title,  String taskSubmitStatus, int startRow, int endRow);
    public int taskCount(Integer lectureNumber, Integer studentNumber, String title, String taskSubmitStatus);

    // 과제 상세 정버
    public TaskListVO taskInfo(Integer taskNumber, Integer studentNumber);

    // 과제 파일 업로드
    public void uploadFiles(List<MultipartFile> files, Integer taskNumber, Integer studentNumber, Integer lectureNumber);

    // 과제 제출 파일 조회
    public List<AttachmentFileVO> taskSubjectFile(Integer taskNumber, Integer studentNumber);

    public int removeSubmissionFile(Integer taskNumber, Integer lectureNumber, Integer studentNumber);
}
