package top.liaoyichao.model.sql;

import java.util.List;

import top.liaoyichao.model.UserModel;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: UserModel 的Sql定义类
 */
public class UserSqlModel {
	private static final UserModel userModel = new UserModel();
	
	public static UserModel getUserModel(){
		return userModel;
	}
	
	public static UserModel findByName(String name){
		return userModel.findFirst("select * from t_user where user = ?", name);
	}
	
	public static List<UserModel> findAll(){
		return userModel.findAll();
	}
	
	
}
