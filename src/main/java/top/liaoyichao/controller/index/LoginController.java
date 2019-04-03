package top.liaoyichao.controller.index;

import java.util.List;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

import top.liaoyichao.model.PersonModel;
import top.liaoyichao.model.UserModel;
import top.liaoyichao.service.PersonService;
import top.liaoyichao.service.UserService;
import top.liaoyichao.util.MyCookie;
import top.liaoyichao.util.MySession;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: 登陆界面的控制类 对应index.html
 */
public class LoginController extends Controller{
	
	@Inject
	UserService userService;
	
	@Inject
	PersonService personService;
	
	public void loginIndex(){
		
		//得到输入框中的账号 密码
		String user = getPara("UserModel.user");
		String password = getPara("UserModel.password");
		
		//根据用户输入的账号进行数据库匹配
		UserModel findByUser = userService.findByName(user);
		
		/**
		 * 登录有几种情况
		 * 1) 登录成功
		 * 		成功 账号 密码与数据库中的相匹配 进入 addlist.html 界面
		 * 2）登录失败
		 * 		失败原因
		 * 			账号错误
		 * 				返回登录界面 并提示 账号不存在
		 * 			密码错误
		 * 				返回登录界面 并提示 密码错误
		 */
		
		if(findByUser != null){
			//代表有值 账号正确 匹配密码
			if(password.equals(findByUser.get("password"))){
				//这里代表匹配成功 成功之后需要设置Cookie 并查询 t_person 所有数据并返回至 addlist.html 界面
				List<PersonModel> t_person = personService.findAll();
				
				//代表登陆成功删除标识Session
				removeSessionAttr(MySession.getSessionKey());
				//设置标识 Cookie
				setCookie(MyCookie.getCookieKey(), MyCookie.getCookie(), 600);
				//用于在主页中显示用户姓名的Cookie
				setSessionAttr("User", user);
				setAttr("personAll" , t_person);
				redirect("/home/HomePage");
			}else{
				//这里代表匹配失败 返回登录界面 并提示密码错误
				setAttr("msg" , "密码错误");
				renderFreeMarker("index.html");
			}
		}else{
			//代表其中没有值 登录失败 账号不存在
			setAttr("msg" , "账号不存在！！");
			renderFreeMarker("index.html");
		}
		
	}
	
}
