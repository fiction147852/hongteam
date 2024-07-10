package com.son.app.task.mapper;

import com.son.app.task.service.TaskListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentTaskMapper {

    public List<TaskListVO> studentTaskList(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title, @Param("taskSubmitStatus") String taskSubmitStatus, @Param("startRow") int startRow, @Param("endRow") int endRow);
    public int studentTaskCount(@Param("lectureNumber") Integer lectureNumber, @Param("title") String title, @Param("taskSubmitStatus") String taskSubmitStatus);

    // 과제 상세 정보
    public TaskListVO studentTaskInfo(Integer taskNumber);
}
