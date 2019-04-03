package top.liaoyichao.controller.index;

import java.util.List;

import com.jfinal.core.Controller;

import top.liaoyichao.model.ReservationModel;
import top.liaoyichao.service.ReservationService;

public class ReservationController extends Controller{
	ReservationService dao;
	public void index()
	{
		List<ReservationModel> reservations = dao.findAll();
		setAttr("re",reservations);
		renderFreeMarker("reservation.html");
	}
}
