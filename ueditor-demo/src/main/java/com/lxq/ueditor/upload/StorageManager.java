package com.lxq.ueditor.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lxq.ueditor.define.AppInfo;
import com.lxq.ueditor.define.BaseState;
import com.lxq.ueditor.define.State;
import com.lxq.ueditor.entity.AliyunConfigEntity;

/**
 * 存储管理类
 * @author l1
 *
 */

@Component
@ConfigurationProperties(prefix = "nginx")
public class StorageManager {

	public static final int BUFFER_SIZE = 8192;

	private static String fileurl;

	public static String getFileurl() {
		return fileurl;
	}

	public static void setFileurl(String fileurl) {
		StorageManager.fileurl = fileurl;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public StorageManager() {
	}

	public static State saveBinaryFile(byte[] data, String path) {
		File file = new File(path);

		State state = valid(file);

		if (!state.isSuccess()) {
			return state;
		}

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(data);
			bos.flush();
			bos.close();
		} catch (IOException ioe) {
			return new BaseState(false, AppInfo.IO_ERROR);
		}

		state = new BaseState(true, file.getAbsolutePath());
		state.putInfo("size", data.length);
		state.putInfo("title", file.getName());
		return state;
	}

	public static State saveFileByInputStream(HttpServletRequest request, InputStream is, String path, String picName,
			long maxSize, AliyunConfigEntity aliyunConfigEntity) {
		State state = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("upfile");

		File files = new File(path);
		// 如果文件夹不存在
		if (!files.exists()) {
			// 创建文件夹
			files.mkdirs();
		}
		path = path + "/" + picName;
		try {
			file.transferTo(new File(path));

			File tmpFile = new File(path);

			// 上传文件
			AliyunUploader aliyunUploader = new AliyunUploader(aliyunConfigEntity.getAccessKeyId(),
					aliyunConfigEntity.getSerect(), aliyunConfigEntity.getEndpoint(),
					aliyunConfigEntity.getBucketName());
			aliyunUploader.fileUpload(aliyunConfigEntity.getFilePath() + picName, path);

			boolean success = true;
			// 如果上传成功
			if (success) {
				state = new BaseState(true);
				state.putInfo("size", tmpFile.length());
				// 文件名填入此处
				state.putInfo("title", picName);
				// 所属group填入此处
				state.putInfo("group", aliyunConfigEntity.getFilePath());
				// 文件访问的url填入此处
				state.putInfo("url", aliyunConfigEntity.getFileUrl() + picName);
				tmpFile.delete();
			} else {
				state = new BaseState(false, 4);
				tmpFile.delete();
			}

			return state;

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	public static State saveFileByInputStream(InputStream is, String path, String picName) {
		State state = null;

		File tmpFile = getTmpFile();

		byte[] dataBuf = new byte[2048];
		BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile),
					StorageManager.BUFFER_SIZE);

			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();

			boolean success = true;

			// 如果上传成功
			if (success) {
				state = new BaseState(true);
				state.putInfo("size", tmpFile.length());
				state.putInfo("title", tmpFile.getName());
				tmpFile.delete();
			} else {
				state = new BaseState(false, 4);
				tmpFile.delete();
			}

			return state;
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static File getTmpFile() {
		File tmpDir = FileUtils.getTempDirectory();
		String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
		return new File(tmpDir, tmpFileName);
	}

	private static State valid(File file) {
		File parentPath = file.getParentFile();

		if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
			return new BaseState(false, AppInfo.FAILED_CREATE_FILE);
		}

		if (!parentPath.canWrite()) {
			return new BaseState(false, AppInfo.PERMISSION_DENIED);
		}

		return new BaseState(true);
	}
}
