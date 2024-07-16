package com.son.app.task.service.impl;

import com.son.app.attachment.AttachmentFileVO;
import com.son.app.task.mapper.StudentTaskMapper;
import com.son.app.task.service.StudentTaskService;
import com.son.app.task.service.TaskListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public List<TaskListVO> taskList(Integer lectureNumber, Integer studentNumber, String title, String taskSubmitStatus, int startRow, int endRow) {
        return studentTaskMapper.studentTaskList(lectureNumber, studentNumber, title, taskSubmitStatus, startRow, endRow);
    }

    @Override
    public int taskCount(Integer lectureNumber, Integer studentNumber , String title, String taskSubmitStatus) {
        return studentTaskMapper.studentTaskCount(lectureNumber, studentNumber, title, taskSubmitStatus);
    }

    @Override
    public TaskListVO taskInfo(Integer taskNumber, Integer studentNumber) {
        return studentTaskMapper.studentTaskInfo(taskNumber, studentNumber);
    }

    @Override
    public List<AttachmentFileVO> taskSubjectFile(Integer taskNumber, Integer studentNumber) {
        return studentTaskMapper.studentTaskSubjectFile(taskNumber, studentNumber);
    }

    @Override
    public int removeSubmissionFile(Integer taskNumber, Integer lectureNumber, Integer studentNumber) {
        List<AttachmentFileVO> attachmentFileVOList = studentTaskMapper.studentTaskSubjectFile(taskNumber, lectureNumber);

        for (AttachmentFileVO attachmentFileVO : attachmentFileVOList) {
            String fullPath = getFullPath(attachmentFileVO.getSaveFileName(), lectureNumber);
            File existingFile = new File(fullPath);

            existingFile.delete();
        }
        return studentTaskMapper.studentSubmissionFileDelete(taskNumber, studentNumber);
    }

    @Override
    @Transactional
    public void uploadFiles(List<MultipartFile> files, Integer taskNumber, Integer studentNumber, Integer lectureNumber) {
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String saveFileName = createSaveFileName(originalFilename);

            // 지정된 경로에 대한 File 객체 생성 후 반환
            File fileDirectory = new File(getFullPath(saveFileName, lectureNumber));

            // 파일 명을 제외한 모든 경로에 대한 File 객체 반환
            File parentDirectory = fileDirectory.getParentFile();

            // 파일을 저장하려는 경로에서 파일명을 제외한 모든 상위 디렉토리를 확인하고, 해당 경로 상의 디렉토리들 중 존재하지 않는 것이 있다면 필요한 모든 디렉토리를 생성
            if(!parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }

            try {
                // 파일 데이터를 지정된 경로에 실제 파일로 저장한다.
                if(file != null && file.getSize() > 0) {
                    file.transferTo(fileDirectory);
                    Long fileSize = file.getSize();

                    AttachmentFileVO attachmentFileVO = new AttachmentFileVO();
                    attachmentFileVO.setOriginalFileName(originalFilename);
                    attachmentFileVO.setSaveFileName(saveFileName);
                    attachmentFileVO.setTaskNumber(taskNumber);
                    attachmentFileVO.setStudentNumber(studentNumber);
                    attachmentFileVO.setFileSize(fileSize);

                    studentTaskMapper.studentTaskUploadFile(attachmentFileVO);
                }

            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        studentTaskMapper.studentTaskStatus(taskNumber, studentNumber);
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

    private String getFullPath(String filename, Integer lectureNumber) {
        return uploadPath + lectureNumber + "/" + filename;
    }
}
