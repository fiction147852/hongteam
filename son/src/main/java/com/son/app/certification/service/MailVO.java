package com.son.app.certification.service;

import lombok.Data;

@Data
public class MailVO {
	private String receiver;
    private String title;
    private String content;
}
