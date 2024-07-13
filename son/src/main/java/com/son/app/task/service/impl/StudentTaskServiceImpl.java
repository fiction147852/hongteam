package com.son.app.task.service.impl;

import com.son.app.attachment.AttachmentFileVO;
import com.son.app.task.mapper.StudentTaskMapper;
import com.son.app.task.service.StudentTaskService;
import com.son.app.task.service.TaskListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class StudentTaskServiceImpl implements StudentTaskService {

    @Autowired
    private StudentTaskMapper studentTaskMapper;

    private String uploadPath = "/Users/sondonghan/Documents/uploads/";

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

    @Override
    @Transactional
    public void uploadFiles(List<MultipartFile> files, Integer taskNumber) {
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String saveFileName = createSaveFileName(originalFilename);

            try {
                // 파일 객체가 가리키는 경로에 실제 파일로 저장한다.
                file.transferTo(new File(getFullPath(saveFileName)));
                Long fileSize = file.getSize();

                AttachmentFileVO attachmentFileVO = new AttachmentFileVO();
                attachmentFileVO.setOriginalFileName(originalFilename);
                attachmentFileVO.setSaveFileName(saveFileName);
                attachmentFileVO.setTaskNumber(taskNumber);

                studentTaskMapper.studentTaskUploadFile(attachmentFileVO);
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        studentTaskMapper.studentTaskStatus(taskNumber);
    }

    // 업로드할 파일의 저장 파일명을 생성한다.
    private String createSaveFileName(String originalFilename) {
        String extension = extractExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + extension;
    }

    // 확장자를 구하는 메서드
    private String extractExtension(String originalFilename) {
        int extensionPosition = originalFilename.lastIndexOf(".");
        return originalFilename.substring(extensionPosition + 1);
    }

    private String getFullPath(String filename) {
        return uploadPath + filename;
    }
}
