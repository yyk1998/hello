package top.liaoyichao.service;

import java.util.List;

import com.jfinal.aop.Inject;

import top.liaoyichao.model.UserModel;
import top.liaoyichao.model.sql.UserSqlModel;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: UserModel 的业务层
 */
public class UserService {
	
	@Inject
	UserSqlModel userSqlModel;
	
	public UserModel findByName(String name){
		return userSqlModel.findByName(name);
	}
	
	public List<UserModel> findAll(){
		return userSqlModel.findAll();
	}
}
