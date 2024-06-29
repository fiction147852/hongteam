package com.son.app.task.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskWithFiles extends TaskVO {
    private Integer attachmentFileNumber;
    private String originalFileName;
    private String saveFileName;
    private Long fileSize;

}
