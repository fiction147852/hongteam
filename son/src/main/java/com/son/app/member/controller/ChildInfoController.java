package com.son.app.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.son.app.member.service.ChildInfoService;

@Controller
public class ChildInfoController {
	
	@Autowired
	ChildInfoService childinfoService;
}
