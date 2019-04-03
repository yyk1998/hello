package top.liaoyichao.service;

import java.util.List;

import top.liaoyichao.model.SightCommModel;

public class SightCommService {
	private static final SightCommModel dao = new SightCommModel().dao();
	
	public List<SightCommModel> findAll()//遍历所有信息
	{
		return dao.find("select * from sight_comment");
	}
	
	public List<SightCommModel> findById(int id)//根据主索引找到信息
	{
		return dao.find("select * from sight_comment where sc_id = "+id);
	}
	
	public List<SightCommModel> findByUser(int id)//根据用户id找到信息
	{
		return dao.find("select * from sight_comment where u_id = "+id);
	}
	
	public List<SightCommModel> findByHotel(int id)//根据用户id找到信息
	{
		return dao.find("select * from sight_comment where s_id = "+id);
	}
	
	public void insert(int u_id,int s_id ,String s_comment,String time)//添加新的预订信息
	{
		new SightCommModel().set("u_id", u_id).set("s_id", s_id).set("s_comment", s_comment).set("time", time).save();
	}
	
	public void deleteById(int id)
	{
		dao.deleteById(id);
	}
	
}
