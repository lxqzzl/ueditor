package com.lxq.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lxq.entity.AliyunConfigEntity;
import com.lxq.ueditor.ActionEnter;

/**
 * 用于处理关于ueditor插件相关的请求
 * @author Guoqing
 * @date 2017-11-29
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/ueditor")
public class UeditorController {

	@Autowired
	private AliyunConfigEntity aliyunConfigEntity;
	
	@RequestMapping(value = "/config")
	@ResponseBody
	public String exec(HttpServletRequest request) throws UnsupportedEncodingException{ 
		request.setCharacterEncoding("utf-8");
		String rootPath=request.getServletContext().getRealPath("/");
		return new ActionEnter( request, rootPath, aliyunConfigEntity ).exec();
	}
	
}
