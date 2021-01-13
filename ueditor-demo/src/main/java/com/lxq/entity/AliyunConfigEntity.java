package com.lxq.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 阿里云配置类
 * @author a1
 *
 */

@Data
@ConfigurationProperties(prefix = "ali.oss")
@Component
public class AliyunConfigEntity {	
 
	/** 阿里云keyid */
	private String accessKeyId;
	
	/** 阿里云秘钥 */
	private String serect;
	
	/** 阿里云endpoint */
	private String endpoint;
	
	/** 阿里云bucketname */
	private String bucketName;
	
	/** 阿里云文件相对路径 */
	private String filePath;
	
	/** 文请求基础地址 */
	private String fileUrl;
	
}
