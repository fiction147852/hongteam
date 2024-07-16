package com.son.app.task.mapper;

import com.son.app.attachment.AttachmentFileVO;
import com.son.app.task.service.TaskListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentTaskMapper {

    public List<TaskListVO> studentTaskList(@Param("lectureNumber") Integer lectureNumber, @Param("studentNumber") Integer studentNumber, @Param("title") String title, @Param("taskSubmitStatus") String taskSubmitStatus, @Param("startRow") int startRow, @Param("endRow") int endRow);
    public int studentTaskCount(@Param("lectureNumber") Integer lectureNumber, @Param("studentNumber") Integer studentNumber, @Param("title") String title, @Param("taskSubmitStatus") String taskSubmitStatus);

    // 과제 상세 정보
    public TaskListVO studentTaskInfo(@Param("taskNumber") Integer taskNumber, @Param("studentNumber") Integer studentNumber);

    // 첨부 파일 저장
    public int studentTaskUploadFile(AttachmentFileVO attachmentFileVO);
    // 과제 제출 상태 변경
    public int studentTaskStatus(@Param("taskNumber") Integer taskNumber, @Param("studentNumber") Integer studentNumber);

    // 과제 제출 파일 조회
    public List<AttachmentFileVO> studentTaskSubjectFile(@Param("taskNumber") Integer taskNumber, @Param("studentNumber") Integer studentNumber);
    public int studentSubmissionFileDelete(@Param("taskNumber") Integer taskNumber, @Param("studentNumber") Integer studentNumber);
}
