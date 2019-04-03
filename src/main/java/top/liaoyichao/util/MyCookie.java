package top.liaoyichao.util;

import java.util.ResourceBundle;

import com.jfinal.kit.HashKit;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: 用于设定一个较为保密的Cookie
 */
public class MyCookie {
	
	public static String getCookie(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("config-dev");
		return HashKit.md5(resourceBundle.getString("cookie"));
	}
	
	public static String getCookieKey(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle("config-dev");
		return HashKit.md5(resourceBundle.getString("cookieKey"));
	}
	
}
