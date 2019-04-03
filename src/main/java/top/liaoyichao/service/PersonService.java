package top.liaoyichao.service;

import java.util.List;

import com.jfinal.aop.Inject;

import top.liaoyichao.model.PersonModel;
import top.liaoyichao.model.sql.PersonSqlModel;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: PersonModel 的业务层 后期可以在此加上一些数据校验 双重保护
 */
public class PersonService {
	
	@Inject
	PersonSqlModel personSqlModel;
	
	public PersonModel findById(String id){
		return personSqlModel.findById(id);
	}
	
	public List<PersonModel> findAll(){
		return personSqlModel.findAll();
	}
	
	public boolean updateById(String id, String name, Integer age, String address){
		return personSqlModel.updateById(id, name, age, address);
	}
	
	public boolean insertPerson(String id, String name, Integer age, String address){
		return personSqlModel.insertPerson(id, name, age, address);
	}
	
	public boolean delectPerson(String id){
		return personSqlModel.delectPerson(id);
	}
}
