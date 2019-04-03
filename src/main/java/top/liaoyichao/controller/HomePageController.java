package top.liaoyichao.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

import top.liaoyichao.interceptor.MySessionInterceptor.MySessionIndexInterceptor;
import top.liaoyichao.interceptor.validator.HomePagePersonSaveValidator;
import top.liaoyichao.interceptor.validator.HomePagePersonUpdateValidator;
import top.liaoyichao.model.PersonModel;
import top.liaoyichao.service.PersonService;
import top.liaoyichao.util.MyCookie;
import top.liaoyichao.util.MyId;
import top.liaoyichao.util.MySession;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: 主页控制类 对应界面 HomePage.html
 */
public class HomePageController extends Controller{
	
	@Inject
	PersonService personService;
	
	//点击左上角 去个人中心
	public void userHome(){
		render("myHome.html");
	}
	
	//点击退出 返回登陆界面
	public void queryHomePage(){
		removeCookie(MyCookie.getCookieKey());
		redirect("/");
	}
	
	//点击人员添加 用于跳转至 添加界面
	public void addPerson(){
		
		//设置标识性FromSession 标识该表单只能提交一次 且需要从该方法中 才能设置识别fromSession
		setSessionAttr(MySession.getFromSessionKey(), MySession.getFromSession());
		
		setAttr("insertPersonTAG", "insertPersonTAG");
		renderFreeMarker("add.html");
	}
	
	//保存 添加界面传输过来的数据
	@Before({HomePagePersonSaveValidator.class , MySessionIndexInterceptor.class})
	public void addSavePerson(){
        try {
			personService.insertPerson(MyId.getMyId(), getPara("PersonModel.name","null"), Integer.parseInt(getPara("PersonModel.age")), getPara("PersonModel.address","null"));
			//防止保存时 Cookie失效 被拦截器 拦截的状况
			setCookie(MyCookie.getCookieKey(), MyCookie.getCookie(), 60);
			removeSessionAttr(MySession.getSessionKey());
			redirect("/home/HomePage");
        } catch (NumberFormatException e) {
        	setAttr("insertPersonTAG", "insertPersonTAG");
			setAttr("SaveAgeError","请输入1~3位之间的数字哦！");
			renderFreeMarker("add.html");
        }
	}
	
	//点击编辑 转至编辑界面 需要进行数据回显 在url中得到id值 并回显至修改界面
	public void update(){
		String id = getPara();
		//根据 this.编辑 传过来的id值 进行查询 并回显数据
		PersonModel find = personService.findById(id);
		
		//设置该Session 主要防止表单重复提交 每次提交表单 如果没有该session 不予提交 实际测试中....没看到效果
		setSessionAttr(MySession.getFromSessionKey(), MySession.getFromSession());
		
		//为了方便后面的值 每个都取出
		setAttr("id", find.get("id"));
		setAttr("name", find.get("name"));
		setAttr("age", find.get("age"));
		setAttr("address", find.get("address"));
		setAttr("updatePersonTAG", "updatePersonTAG");
		renderFreeMarker("add.html");
	}
	
	//编辑界面提交的数据方法
	
	@Before({HomePagePersonUpdateValidator.class , MySessionIndexInterceptor.class})
	public void updatePersonDb(){
		
		try {
			personService.updateById(getPara("id"), getPara("updateName",getPara("defaultName")), Integer.parseInt(getPara("updateAge")), getPara("updateAddress",getPara("defaultAddress")));
			//这里设置的目的 是怕Cookie如果过期 需要重新登录 这是我Url拦截没有做好的地方 暂且先使用
			setCookie(MyCookie.getCookieKey() , MyCookie.getCookie(), 60);
			removeSessionAttr(MySession.getFromSessionKey());
			redirect("/home/HomePage");
		} catch (NumberFormatException e) {
			setAttr("id", getPara("id"));
			setAttr("updatePersonTAG", "updatePersonTAG");
			setAttr("updateAgeError", "请输入1~3之间的数字~");
			renderFreeMarker("add.html");
		}
	}
	
	
	//点击删除 根据url中传入的id值 进行删除后 跳转至HomePage界面
	public void delectPerson(){
		String id = getPara();
		System.out.println(id);
		personService.delectPerson(id);
		redirect("/home/HomePage");
	}
	
	//查询t_person表 返回至 HomePage界面
	public void HomePage(){
		List<PersonModel> findAll = personService.findAll();
		setAttr("personAll", findAll);
		renderFreeMarker("HomePage.html");
	}
	
}
