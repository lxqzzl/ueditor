package com.lxq.ueditor.define;

/**
 * 处理状态接口
 * @author l1
 *
 */

public interface State {
	/**
	 * 是否成功
	 * @return boolean
	 */
	public boolean isSuccess ();
	
	/**
	 * 配置信息
	 * @param name
	 * @param val
	 */
	public void putInfo( String name, String val );
	
	/**
	 * 配置信息
	 * @param name
	 * @param val
	 */
	public void putInfo ( String name, long val );
	
	/**
	 * 转为JSON字符串
	 * @return String
	 */
	public String toJsonString ();

}
