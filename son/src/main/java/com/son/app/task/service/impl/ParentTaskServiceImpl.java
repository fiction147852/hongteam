package com.son.app.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.attachment.AttachmentFileVO;
import com.son.app.task.mapper.ParentTaskMapper;
import com.son.app.task.service.ParentTaskListVO;
import com.son.app.task.service.ParentTaskService;
import com.son.app.task.service.TaskListVO;

@Service
public class ParentTaskServiceImpl implements ParentTaskService {

    @Autowired
    private ParentTaskMapper parentTaskMapper;

    @Override
    public List<ParentTaskListVO> taskList(Integer lectureNumber, Integer studentNumber) {
        return parentTaskMapper.parentTaskList(lectureNumber, studentNumber);
    }


}
