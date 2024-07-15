package com.son.app.task.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentTaskService {

    public List<TaskListVO> taskList(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title,  @Param("taskSubmitStatus") String taskSubmitStatus, @Param("startRow") int startRow, @Param("endRow") int endRow);
    public int taskCount(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title, @Param("taskSubmitStatus") String taskSubmitStatus);

    // 과제 상세 정버
    public TaskListVO taskInfo(Integer taskNumber);

    // 과제 파일 업로드
    public void uploadFiles(List<MultipartFile> files, Integer taskNumber);
}
