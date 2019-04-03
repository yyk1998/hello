package top.liaoyichao.model.sql;

import java.util.List;

import top.liaoyichao.model.PersonModel;

/**
 * @author Liao
 * @date 2019年3月1日
 * @Description: PersonModel 的Sql主要定义集中区
 */
public class PersonSqlModel {
	
	private static final PersonModel personModel = new PersonModel();
	
	public PersonModel getPersonModel(){
		return personModel;
	}
	
	public PersonModel findById(String id){
		return personModel.findById(id);
	}
	
	public List<PersonModel> findAll(){
		return personModel.findAll();
	}
	
	public boolean updateById(String id, String name, Integer age, String address){
		personModel.set("id", id);
		personModel.set("name", name);
		personModel.set("age", age);
		personModel.set("address", address);
		return personModel.update();
	}
	
	public boolean insertPerson(String id, String name, Integer age, String address){
		personModel.set("id", id);
		personModel.set("name", name);
		personModel.set("age", age);
		personModel.set("address", address);
		return personModel.save();
	}
	
	public boolean delectPerson(String id){
		return personModel.deleteById(id);
	}
	
}
