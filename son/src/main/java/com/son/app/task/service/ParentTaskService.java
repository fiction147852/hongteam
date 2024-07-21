package com.son.app.task.service;

import java.util.List;

import com.son.app.attachment.AttachmentFileVO;

public interface ParentTaskService {

    public List<ParentTaskListVO> taskList(Integer studentNumber, Integer lectureNumber);

}
