package top.liaoyichao.service;

import java.util.List;

import top.liaoyichao.model.HotelCommModel;
import top.liaoyichao.model.ReservationModel;

public class HotelCommService {
	private static final HotelCommModel dao = new HotelCommModel().dao();
	
	public List<HotelCommModel> findAll()//遍历所有信息
	{
		return dao.find("select * from hotel_comment");
	}
	
	public List<HotelCommModel> findById(int id)//根据主索引找到信息
	{
		return dao.find("select * from hotel_comment where hc_id = "+id);
	}
	
	public List<HotelCommModel> findByUser(int id)//根据用户id找到信息
	{
		return dao.find("select * from hotel_comment where u_id = "+id);
	}
	
	public List<HotelCommModel> findByHotel(int id)//根据用户id找到信息
	{
		return dao.find("select * from hotel_comment where h_id = "+id);
	}
	
	public void insert(int u_id,int h_id ,String h_comment,String time)//添加新的预订信息
	{
		new HotelCommModel().set("u_id", u_id).set("h_id", h_id).set("h_comment", h_comment).set("time", time).save();
	}
	
	public void deleteById(int id)
	{
		dao.deleteById(id);
	}
	
}
