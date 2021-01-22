package com.lxq.ueditor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.alibaba.fastjson.JSONObject;
import com.lxq.ueditor.define.ActionMap;
import com.lxq.ueditor.entity.UeditorConfigEntity;

/**
 * 配置管理器
 * @author l1
 *
 */

public final class ConfigManager {

	private final String rootPath;
	
	/** ueditor配置 */
	private UeditorConfigEntity ueditorConfigEntity = null;
	/** 文件临时存储位置 */
	private String fileTempPath;
	
	
	/** 涂鸦上传filename定义 */
	private final static String SCRAWL_FILE_NAME = "scrawl";
	
	/** 远程图片抓取filename定义 */
	private final static String REMOTE_FILE_NAME = "remote";
	
	/**
	 * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
	 */
	private ConfigManager ( String rootPath, String contextPath, String uri, String fileTempPath ) throws FileNotFoundException, IOException {
		
		rootPath = rootPath.replace( "\\", "/" );
		
		this.rootPath = rootPath;
		
		this.fileTempPath = fileTempPath;
		
		this.initEnv();
		
	}
	
	/**
	 * 配置管理器构造工厂
	 * @param rootPath 服务器根路径
	 * @param contextPath 服务器所在项目路径
	 * @param uri 当前访问的uri
	 * @return 配置管理器实例或者null
	 */
	public static ConfigManager getInstance ( String rootPath, String contextPath, String uri, String fileTempPath ) {
		
		try {
			return new ConfigManager(rootPath, contextPath, uri, fileTempPath);
		} catch ( Exception e ) {
			return null;
		}
		
	}
	
	/**
	 * 验证配置文件加载是否正确
	 */
	public boolean valid () {
		return this.ueditorConfigEntity != null;
	}
	
	public JSONObject getAllConfig () {		
		String ueditorConfig_jsonString = JSONObject.toJSONString(ueditorConfigEntity);
		return JSONObject.parseObject(ueditorConfig_jsonString);
		
	}
	
	public Map<String, Object> getConfig ( int type ) throws JSONException {
		
		Map<String, Object> conf = new HashMap<String, Object>(9);
		String savePath = null;
		String localSavePathPrefix = null;
		
		switch ( type ) {
		
			case ActionMap.UPLOAD_FILE:
				conf.put( "isBase64", "false" );
				conf.put( "maxSize", ueditorConfigEntity.getFileMaxSize());
				conf.put( "allowFiles", ueditorConfigEntity.getFileAllowFiles());
				conf.put( "fieldName", ueditorConfigEntity.getFileFieldName());
				savePath = ueditorConfigEntity.getFilePathFormat();
				break;
				
			case ActionMap.UPLOAD_IMAGE:
				conf.put( "isBase64", "false" );
				conf.put( "maxSize", ueditorConfigEntity.getImageMaxSize());
				conf.put( "allowFiles", ueditorConfigEntity.getImageAllowFiles());
				conf.put( "fieldName", ueditorConfigEntity.getImageFieldName());
				savePath = ueditorConfigEntity.getImagePathFormat();
				localSavePathPrefix = ueditorConfigEntity.getLocalSavePathPrefix();
				break;
				
			case ActionMap.UPLOAD_VIDEO:
				conf.put( "maxSize", ueditorConfigEntity.getVideoMaxSize());
				conf.put( "allowFiles", ueditorConfigEntity.getVideoAllowFiles());
				conf.put( "fieldName", ueditorConfigEntity.getVideoFieldName());
				savePath = ueditorConfigEntity.getVideoPathFormat();
				localSavePathPrefix = ueditorConfigEntity.getLocalSavePathPrefix();
				break;
				
			case ActionMap.UPLOAD_SCRAWL:
				conf.put( "filename", ConfigManager.SCRAWL_FILE_NAME );
				conf.put( "maxSize",ueditorConfigEntity.getScrawlMaxSize());
				conf.put( "fieldName", ueditorConfigEntity.getScrawlFieldName());
				conf.put( "isBase64", "true");
				savePath = ueditorConfigEntity.getScrawlPathFormat();
				break;
				
			case ActionMap.CATCH_IMAGE:
				conf.put( "filename", ConfigManager.REMOTE_FILE_NAME );
				conf.put( "filter", ueditorConfigEntity.getCatcherLocalDomain());
				conf.put( "maxSize", ueditorConfigEntity.getCatcherMaxSize());
				conf.put( "allowFiles", ueditorConfigEntity.getCatcherAllowFiles());
				conf.put( "fieldName", ueditorConfigEntity.getCatcherFieldName() + "[]" );
				savePath = ueditorConfigEntity.getCatcherPathFormat();
				localSavePathPrefix = ueditorConfigEntity.getLocalSavePathPrefix();
				break;
				
			case ActionMap.LIST_IMAGE:
				conf.put( "allowFiles", ueditorConfigEntity.getImageManagerAllowFiles());
				conf.put( "dir", ueditorConfigEntity.getImageManagerListPath());
				conf.put( "count", ueditorConfigEntity.getImageManagerListSize());
				break;
				
			case ActionMap.LIST_FILE:
				conf.put( "allowFiles", ueditorConfigEntity.getFileManagerAllowFiles());
				conf.put( "dir", ueditorConfigEntity.getFileManagerListPath());
				conf.put( "count", ueditorConfigEntity.getFileManagerListSize());
				break;
				
			default:
				break;
		}
		
		conf.put( "savePath", savePath );
		conf.put( "localSavePathPrefix", localSavePathPrefix );
		conf.put( "rootPath", this.rootPath );
		
		return conf;
		
	}
	
	private void initEnv () throws FileNotFoundException, IOException {
		try{
			this.ueditorConfigEntity = new UeditorConfigEntity(fileTempPath);
		} catch ( Exception e ) {
			this.ueditorConfigEntity = null;
		}		
	}	
}
