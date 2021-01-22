package com.lxq.ueditor.hunter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lxq.ueditor.PathFormat;
import com.lxq.ueditor.define.AppInfo;
import com.lxq.ueditor.define.BaseState;
import com.lxq.ueditor.define.MimeType;
import com.lxq.ueditor.define.MultiState;
import com.lxq.ueditor.define.State;
import com.lxq.ueditor.upload.StorageManager;

/**
 * 图片抓取器
 * @author l1
 *
 */

public class ImageHunter {

	private String filename = null;
	private String savePath = null;
	
	private List<String> allowTypes = null;
	private long maxSize = -1;
	private String localSavePathPrefix = null;
	
	private List<String> filters = null;

	public ImageHunter ( Map<String, Object> conf ) {
		
		this.filename = (String)conf.get( "filename" );
		this.savePath = (String)conf.get( "savePath" );
		
		this.maxSize = (Long)conf.get( "maxSize" );
		this.allowTypes = Arrays.asList( (String[])conf.get( "allowFiles" ) );
		this.filters = Arrays.asList( (String[])conf.get( "filter" ) );
		this.localSavePathPrefix = (String) conf.get("localSavePathPrefix");
		
	}
	
	public State capture ( String[] list ) {
		
		MultiState state = new MultiState( true );
		
		for ( String source : list ) {
			state.addState( captureRemoteData( source ) );
		}
		
		return state;
		
	}

	public State captureRemoteData ( String urlStr ) {
		
		HttpURLConnection connection = null;
		URL url = null;
		String suffix = null;
		
		try {
			url = new URL( urlStr );

			if ( !validHost( url.getHost() ) ) {
				return new BaseState( false, AppInfo.PREVENT_HOST );
			}
			
			connection = (HttpURLConnection) url.openConnection();
		
			connection.setInstanceFollowRedirects( true );
			connection.setUseCaches( true );
		
			if ( !validContentState( connection.getResponseCode() ) ) {
				return new BaseState( false, AppInfo.CONNECTION_ERROR );
			}
			
			suffix = MimeType.getSuffix( connection.getContentType() );
			
			if ( !validFileType( suffix ) ) {
				return new BaseState( false, AppInfo.NOT_ALLOW_FILE_TYPE );
			}
			
			if ( !validFileSize( connection.getContentLength() ) ) {
				return new BaseState( false, AppInfo.MAX_SIZE );
			}
			
			System.out.println(url);
			
			String savePath = this.getPath( this.savePath, this.filename, suffix );
			String physicalPath = this.localSavePathPrefix + savePath;
			String path = physicalPath.substring(0, physicalPath.lastIndexOf("/"));
			String picName = physicalPath.substring(physicalPath.lastIndexOf("/")+1, physicalPath.length());

			System.out.println("savePath:"+savePath);
			System.out.println("physicalPath："+physicalPath);
			System.out.println("path:"+path);
			System.out.println("picName:"+picName);
			
			State state = StorageManager.saveFileByInputStream( connection.getInputStream(), path, picName );
			
			if ( state.isSuccess() ) {
				state.putInfo( "url", null);
				state.putInfo( "source", urlStr );
			}
			
			return state;
			
		} catch ( Exception e ) {
			return new BaseState( false, AppInfo.REMOTE_FAIL );
		}
		
	}
	
	private String getPath ( String savePath, String filename, String suffix  ) {
		
		return PathFormat.parse( savePath + suffix, filename );
		
	}
	
	private boolean validHost ( String hostname ) {
		
		return !filters.contains( hostname );
		
	}
	
	private boolean validContentState ( int code ) {
		
		return HttpURLConnection.HTTP_OK == code;
		
	}
	
	private boolean validFileType ( String type ) {
		
		return this.allowTypes.contains( type );
		
	}
	
	private boolean validFileSize ( int size ) {
		return size < this.maxSize;
	}
	
}
