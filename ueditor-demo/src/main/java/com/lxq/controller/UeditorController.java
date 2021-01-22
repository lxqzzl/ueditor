package com.lxq.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lxq.ueditor.ActionEnter;
import com.lxq.ueditor.entity.AliyunConfigEntity;

/**
 * 用于处理关于ueditor插件相关的请求
 * @author l1
 *
 */

@RestController
@RequestMapping("/ueditor")
public class UeditorController {

	/** 阿里云配置类 */
	@Autowired
	AliyunConfigEntity aliyunConfigEntity;
	
	/** 文件临时存储位置 */
	@Value("${file.temp.path}")
	private String fileTempPath;
	
	@RequestMapping(value = "/config")
	@ResponseBody
	public String exec(HttpServletRequest request) throws UnsupportedEncodingException{ 
		request.setCharacterEncoding("utf-8");
		String rootPath=request.getServletContext().getRealPath("/");
		return new ActionEnter( request, rootPath, aliyunConfigEntity, fileTempPath ).exec();
	}
}
