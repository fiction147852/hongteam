package com.son.app.task.service.impl;

import com.son.app.task.mapper.StudentTaskMapper;
import com.son.app.task.service.StudentTaskService;
import com.son.app.task.service.TaskListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTaskServiceImpl implements StudentTaskService {

    @Autowired
    private StudentTaskMapper studentTaskMapper;

    @Override
    public List<TaskListVO> taskList(Integer lectureNumber, String title, String taskSubmitStatus, int startRow, int endRow) {
        return studentTaskMapper.studentTaskList(lectureNumber, title, taskSubmitStatus, startRow, endRow);
    }

    @Override
    public int taskCount(Integer lectureNumber, String title, String taskSubmitStatus) {
        return studentTaskMapper.studentTaskCount(lectureNumber, title, taskSubmitStatus);
    }

    @Override
    public TaskListVO taskInfo(Integer taskNumber) {
        return studentTaskMapper.studentTaskInfo(taskNumber);
    }
}
