package top.liaoyichao.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: 用于生成一个唯一的id
 */
public class MyId {
	public static String getMyId(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss-");
		Date date = new Date();
		// 以  时间戳 + uuid 的方式设置Id
		String id = dateFormat.format(date) + UUID.randomUUID().toString();
		return id;
	}
}
