package com.son.app.task.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentTaskService {

    public List<TaskListVO> taskList(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title,  @Param("taskSubmitStatus") String taskSubmitStatus, @Param("startRow") int startRow, @Param("endRow") int endRow);
    public int taskCount(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title, @Param("taskSubmitStatus") String taskSubmitStatus);
}
