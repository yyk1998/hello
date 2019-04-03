package top.liaoyichao.interceptor.MySessionInterceptor;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import top.liaoyichao.util.MySession;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: 表单Session 拦截类 如果是通过正常流程通过的类 会从存在Session
 */
public class MySessionIndexInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
        if(inv.getController().getSessionAttr(MySession.getFromSessionKey()).toString().equals(MySession.getFromSession())){
            //代表存在Session
            inv.invoke();
        }else{
            inv.getController().setAttr("msg","请不要重复提交");
            inv.getController().renderFreeMarker("/index.html");
        }
	}
}
