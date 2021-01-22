package com.lxq.ueditor.define;

import java.util.HashMap;
import java.util.Map;

/**
 * MIME 类型
 * @author l1
 *
 */

public class MimeType {

	public static final Map<String, String> TYPE_MAP = new HashMap<String, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 6858273700767648114L;

	{
		put( "image/gif", ".gif" );
		put( "image/jpeg", ".jpg" );
		put( "image/jpg", ".jpg" );
		put( "image/png", ".png" );
		put( "image/bmp", ".bmp" );
	}};
	
	public static String getSuffix ( String mime ) {
		return MimeType.TYPE_MAP.get( mime );
	}
	
}
