package top.liaoyichao.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import top.liaoyichao.util.MyCookie;
import top.liaoyichao.util.MySession;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: 全局拦截类
 */
public class OverClassInterceptor implements Interceptor{

	/* (non-Javadoc)
	 * @see com.jfinal.aop.Interceptor#intercept(com.jfinal.aop.Invocation)
	 */
	@Override
	public void intercept(Invocation inv) {
        if(inv.getController().getCookie(MyCookie.getCookieKey()) == null){
            //代表没有Cookie 需要判断是否为合法的url 地址值 这些代表是合法Url地址
            if(inv.getActionKey().equals("/") || inv.getActionKey().equals("/index") || inv.getActionKey().equals("/login/loginIndex")){
                //合法的Url让其 自行运行
                inv.invoke();
            }else{
                //不能匹配上面条件的url 让其转至登录界面
            	//这里可以使用 inv.getController().redirect("/"); 但是要带一个唯一性的东西 然后 / 下的index方法进行判断 如果不存在该唯一性物品 不提示什么进行登陆界面 如果存在 提示请先登陆
            	inv.getController().setSessionAttr(MySession.getSessionKey(), MySession.getSession());
                inv.getController().redirect("/");;
            }
        }else{
        	if(inv.getController().getCookie(MyCookie.getCookieKey()).toString().equals(MyCookie.getCookie())){
                	//代表存在Cookie
                	inv.invoke();
        	}else{
                	//不是本Cookie 这里使用Session 主要是跳转至 登录界面的时候 带提示
			inv.getController().setSessionAttr(MySession.getSessionKey(), MySession.getSession());
                	inv.getController().redirect("/");;
        	}
        }
	}

}
