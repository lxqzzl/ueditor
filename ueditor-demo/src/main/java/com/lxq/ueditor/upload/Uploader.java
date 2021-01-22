package com.lxq.ueditor.upload;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.lxq.ueditor.define.State;
import com.lxq.ueditor.entity.AliyunConfigEntity;

/**
 * 文件上传
 * @author l1
 *
 */

public class Uploader {
	
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	private static String TRUE = "true";
	private static String IS_BASE64 = "isBase64";
	
	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final State doExec(AliyunConfigEntity aliyunConfigEntity) {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;        
		if (TRUE.equals(this.conf.get(IS_BASE64))) {
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf, aliyunConfigEntity);
		} else {
			state = BinaryUploader.save(this.request, this.conf, aliyunConfigEntity);
		}
		return state;
	}
}
