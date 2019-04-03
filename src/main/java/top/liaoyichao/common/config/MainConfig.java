package top.liaoyichao.common.config;

import com.jfinal.config.Constants;

import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.plugin.druid.DruidPlugin;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.render.ViewType;

import top.liaoyichao.controller.HomePageController;
import top.liaoyichao.controller.index.IndexController;
import top.liaoyichao.controller.index.LoginController;
import top.liaoyichao.controller.index.ReservationController;
import top.liaoyichao.interceptor.OverClassInterceptor;
import top.liaoyichao.model.*;

/**
 * 
 * @author Liao
 * @date 2019年3月2日
 * @Description: JFinal全局配置类
 */
public class MainConfig extends JFinalConfig {
	/**
	 * 将全局配置提出来 方便其他地方重用
	 */
	private static Prop p;
	private WallFilter wallFilter;
	/**
	 * 配置JFinal常量
	 */
	@Override
	public void configConstant(Constants me) {
		//读取数据库配置文件
		loadConfig();
		//设置当前是否为开发模式
		me.setDevMode(p.getBoolean("devMode"));
		//设置默认上传文件保存路径 getFile等使用
		me.setBaseUploadPath("upload/temp/");
		//设置上传最大限制尺寸
		//me.setMaxPostSize(1024*1024*10);
		//设置默认下载文件路径 renderFile使用
		me.setBaseDownloadPath("download");
		//设置默认视图类型
		me.setViewType(ViewType.FREE_MARKER);
		//设置404渲染视图
		me.setError404View("404.html");
		me.setError500View("500.html");
		
		//设置启用依赖注入
		me.setInjectDependency(true);
		
		
	}
	/**
	 * 配置项目路由
	 * 路由拆分到 FrontRutes 与 AdminRoutes 之中配置的好处：
	 * 1：可分别配置不同的 baseViewPath 与 Interceptor
	 * 2：避免多人协同开发时，频繁修改此文件带来的版本冲突
	 * 3：避免本文件中内容过多，拆分后可读性增强
	 * 4：便于分模块管理路由
	 */
	@Override
	public void configRoute(Routes me) {
		//推荐拆分方式 如果需要就解开注释 创建对应的 Routes
		//me.add(new AdminRoutes());//配置后台管理系统路由
		
		//me.add(new WechatRoutes());//配置微信端访问路由
		
		
		//普通不拆分的方式配置 如下
		//设置默认访问首页路由 可使用http://localhost:port 直接访问 如果80端口 port可以省略
		me.add("/", IndexController.class , "/static");
		me.add("/login" , LoginController.class , "/static");
		me.add("/home",HomePageController.class , "/static");
		me.add("/reservation" , ReservationController.class , "/static");
	}
	// 先加载开发环境配置，再追加生产环境的少量配置覆盖掉开发环境配置
	static void loadConfig() {
		if (p == null) {
			p = PropKit.use("config-dev.properties").appendIfExists("config-pro.properties");
		}
	}
	/**
	 * 获取数据库插件
	 * 抽取成独立的方法，便于重用该方法，减少代码冗余
	 */
	public static DruidPlugin getDruidPlugin() {
		loadConfig();
		return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
	}
	/**
	 * 配置JFinal插件
	 * 数据库连接池
	 * ActiveRecordPlugin
	 * 缓存
	 * 定时任务
	 * 自定义插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		loadConfig();
		//配置数据库连接池插件
		DruidPlugin dbPlugin=getDruidPlugin();
		wallFilter = new WallFilter();			// 加强数据库安全
		wallFilter.setDbType("mysql");
		dbPlugin.addFilter(wallFilter);
		dbPlugin.addFilter(new StatFilter());	// 添加 StatFilter 才会有统计数据
		
		//数据映射 配置ActiveRecord插件
		ActiveRecordPlugin arp=new ActiveRecordPlugin(dbPlugin);
		arp.setShowSql(p.getBoolean("devMode"));
		arp.setDialect(new MysqlDialect());
		dbPlugin.setDriverClass("com.mysql.jdbc.Driver");
		/********在此添加数据库 表-Model 映射*********/
		//如果使用了JFinal Model 生成器 生成了BaseModel 把下面注释解开即可
		//_MappingKit.mapping(arp);
		
		//把数据库中的表进行映射
		//arp.addMapping("t_user", UserModel.class);
		//arp.addMapping("t_person", UserModel.class);
		arp.addMapping("user", UserModel.class);
		arp.addMapping("hotel", HotelModel.class);
		arp.addMapping("hotel_collection", HotelColModel.class);
		arp.addMapping("hotel_comment","hc_id", HotelCommModel.class);
		arp.addMapping("hotel_image", HotelImageModel.class);
		arp.addMapping("reservation","r_id", ReservationModel.class);
		arp.addMapping("sight", SightModel.class);
		arp.addMapping("sight_collection", SightColModel.class);
		arp.addMapping("sight_comment","sc_id", SightCommModel.class);
		arp.addMapping("sight_image", SightImageModel.class);

		//添加到插件列表中
		me.add(dbPlugin);
		me.add(arp);

	}
	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new SessionInViewInterceptor());
		me.add(new OverClassInterceptor());
	}
	/**
	 * 配置全局处理器
	 */
	@Override
	public void configHandler(Handlers me) {
		//说明：druid的统计页面涉及安全性 需要自行处理根据登录权限判断是否能访问统计页面 
		//me.add(DruidKit.getDruidStatViewHandler()); // druid 统计页面功能
		
//		Example: me.add(new UrlSkipHandler(".+\\.\\w{1,4}", false));
		
		//只能拦截 webapp 目录下的
//		me.add(new UrlSkipHandler(".*.html", false));
//		me.add(new UrlSkipHandler(".*.htm|.*.html|.*.js|.*.css|.*.json|.*.png|.*.gif|.*.jpg|.*.jpeg|.*.bmp|.*.ico|.*.exe|.*.txt|.*.zip|.*.rar|.*.7z", false));
	}
	/**
	 * 项目启动后调用
	 */
	@Override
	public void onStart() {
		wallFilter.getConfig().setSelectUnionCheck(false);
	}
	
	/**
	 * 配置模板引擎 
	 */
	@Override
	public void configEngine(Engine me) {
		//配置模板支持热加载
		me.setDevMode(p.getBoolean("engineDevMode", false));
		//这里只有选择JFinal TPL的时候才用
		//配置共享函数模板
		//me.addSharedFunction("/view/common/layout.html")
	}
	
	public static void main(String[] args) {
		UndertowServer.create(MainConfig.class,"undertow.properties").start();
	}
	

}