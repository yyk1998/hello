package top.liaoyichao.util;

import java.util.ResourceBundle;

import com.jfinal.kit.HashKit;

/**
 * @author Liao
 * @date 2019年3月2日
 * @Description: 主要生成较为保密的Session
 */
public class MySession {
	public static String getSession(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("config-dev");
		return HashKit.md5(resourceBundle.getString("session"));
	}
	
	public static String getSessionKey(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("config-dev");
		return HashKit.md5(resourceBundle.getString("sessionKey"));
	}
	
	public static String getFromSession(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("config-dev");
		return HashKit.md5(resourceBundle.getString("fromSession"));
	}
	
	public static String getFromSessionKey(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("config-dev");
		return HashKit.md5(resourceBundle.getString("fromSessionKey"));
	}
	
}
