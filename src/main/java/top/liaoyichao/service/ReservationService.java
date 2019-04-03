package top.liaoyichao.service;

import java.util.List;

import top.liaoyichao.model.ReservationModel;
import top.liaoyichao.model.UserModel;
import top.liaoyichao.model.sql.ReservationSqlModel;

public class ReservationService {
	private static final ReservationModel dao = new ReservationModel().dao();
	
	public List<ReservationModel> findAll()//遍历所有信息
	{
		return dao.find("select * from reservation");
	}
	
	public List<ReservationModel> findById(int id)//根据主索引找到信息
	{
		return dao.find("select * from reservation where r_id = "+id);
	}
	
	public List<ReservationModel> findByUser(int id)//根据用户id找到信息
	{
		return dao.find("select * from reservation where u_id = "+id);
	}
	
	public List<ReservationModel> findByHotel(int id)//根据用户id找到信息
	{
		return dao.find("select * from reservation where h_id = "+id);
	}
	
	public void insert(int u_id,int h_id ,int r_number,int r_num,String getInTime,String getOutTime)//添加新的预订信息
	{
		new ReservationModel().set("u_id", u_id).set("h_id", h_id).set("r_number", r_number).set("r_num", r_num).set("getInTime", getInTime).set("getOutTime", getOutTime).save();
	}
	
	public void deleteById(int id)
	{
		dao.deleteById(id);
	}
	
}
