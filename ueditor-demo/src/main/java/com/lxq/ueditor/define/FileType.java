package com.lxq.ueditor.define;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件类型
 * @author a1
 *
 */

public class FileType {

	public static final String JPG = "JPG";
	
	private static final Map<String, String> TYPE_MAP = new HashMap<String, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = -4325713904191382763L;

	{
		
		put( FileType.JPG, ".jpg" );
		
	}};
	
	public static String getSuffix ( String key ) {
		return FileType.TYPE_MAP.get( key );
	}
	
	/**
	 * 根据给定的文件名,获取其后缀信息
	 * @param filename
	 * @return
	 */
	public static String getSuffixByFilename ( String filename ) {
		
		return filename.substring( filename.lastIndexOf( "." ) ).toLowerCase();
		
	}
	
}
