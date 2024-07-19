package com.son.app.task.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.task.service.ParentTaskListVO;

public interface ParentTaskMapper {

    public List<ParentTaskListVO> parentTaskList(
    		@Param("lectureNumber") Integer lectureNumber, 
    		@Param("studentNumber") Integer studentNumber);

}
