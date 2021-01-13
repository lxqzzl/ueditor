package com.lxq.ueditor.upload;

import java.io.File;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

/**
 * 阿里云上传
 * @author lxq
 *
 */

public class AliyunUploader {
	private String accessKeyId;
    private String accessKeySecert;
    private String endpoint;
    private String bucketName;
    
    /**
     * 构造方法
     */
    public AliyunUploader(String accessKeyId, String accessKeySecert, String endpoint, String bucketName) {
		this.accessKeyId=accessKeyId;
		this.accessKeySecert=accessKeySecert;
		this.endpoint=endpoint;
		this.bucketName=bucketName;
	}

    /**
     * 直接上传阿里云
     * @param fileName
     * @param filePath
     */
	public String fileUpload(String fileName, String filePath) {		
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecert);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new File(filePath));
		PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
		// 关闭OSSClient。
		ossClient.shutdown();
		return putObjectResult.toString();
	}	
}
