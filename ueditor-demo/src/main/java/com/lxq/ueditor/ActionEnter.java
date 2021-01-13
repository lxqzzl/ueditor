package com.lxq.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import com.lxq.entity.AliyunConfigEntity;
import com.lxq.ueditor.define.ActionMap;
import com.lxq.ueditor.define.AppInfo;
import com.lxq.ueditor.define.BaseState;
import com.lxq.ueditor.define.State;
import com.lxq.ueditor.hunter.FileManager;
import com.lxq.ueditor.hunter.ImageHunter;
import com.lxq.ueditor.upload.Uploader;

/**
 * 
 * @author lxq
 *
 */

public class ActionEnter {
	
	private AliyunConfigEntity aliyunConfigEntity = null;
	
	private HttpServletRequest request = null;
	
	private String rootPath = null;
	private String contextPath = null;
	
	private String actionType = null;
		
	private ConfigManager configManager = null;
	
	private static String REGEX = "^[a-zA-Z_]+[\\w0-9_]*$";

	public ActionEnter ( HttpServletRequest request, String rootPath, AliyunConfigEntity aliyunConfigEntity) {
		
		this.request = request;
		this.rootPath = rootPath;
		this.actionType = request.getParameter( "action" );
		this.contextPath = request.getContextPath();
		this.configManager = ConfigManager.getInstance( this.rootPath, this.contextPath, request.getRequestURI() );
		
		this.aliyunConfigEntity = aliyunConfigEntity;
	}
	
	public String exec () throws JSONException {
		String callbackName = this.request.getParameter("callback");
		
		if ( callbackName != null ) {

			if ( !validCallbackName( callbackName ) ) {
				return new BaseState( false, AppInfo.ILLEGAL ).toJsonString();
			}
			
			return this.invoke();
			
		} else {
			return this.invoke();
		}

	}
	
	public String invoke() throws JSONException {
		if ( actionType == null || !ActionMap.MAPPING_MAP.containsKey( actionType ) ) {
			return new BaseState( false, AppInfo.INVALID_ACTION ).toJsonString();
		}
		
		if ( this.configManager == null || !this.configManager.valid() ) {
			return new BaseState( false, AppInfo.CONFIG_ERROR ).toJsonString();
		}
		
		State state = null;
		
		int actionCode = ActionMap.getType( this.actionType );
		
		Map<String, Object> conf = null;
		
		switch ( actionCode ) {
		
			case ActionMap.CONFIG:
				return this.configManager.getAllConfig().toString();
				
			case ActionMap.UPLOAD_IMAGE:
			case ActionMap.UPLOAD_SCRAWL:
			case ActionMap.UPLOAD_VIDEO:
			case ActionMap.UPLOAD_FILE:
				conf = this.configManager.getConfig( actionCode );
				state = new Uploader( request, conf ).doExec( aliyunConfigEntity );
				break;
				
			case ActionMap.CATCH_IMAGE:
				conf = configManager.getConfig( actionCode );
				String[] list = this.request.getParameterValues( (String)conf.get( "fieldName" ) );
				state = new ImageHunter( conf ).capture( list );
				break;
				
			case ActionMap.LIST_IMAGE:
			case ActionMap.LIST_FILE:
				conf = configManager.getConfig( actionCode );
				int start = this.getStartIndex();
				state = new FileManager( conf ).listFile( start );
				break;
				
			default:
				break;
		}
		
		return state.toJsonString();
		
	}
	
	public int getStartIndex () {
		
		String start = this.request.getParameter( "start" );
		
		try {
			return Integer.parseInt( start );
		} catch ( Exception e ) {
			return 0;
		}
		
	}
	
	/**
	 * callback参数验证
	 */
	public boolean validCallbackName ( String name ) {
		
		if (name.matches(REGEX)) {
			return true;
		}
		
		return false;
		
	}
	
}