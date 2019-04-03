package top.liaoyichao.controller.index;
import java.util.List;

import com.jfinal.core.Controller;

import top.liaoyichao.model.ReservationModel;
import top.liaoyichao.service.ReservationService;
import top.liaoyichao.util.MySession;

/**
 * IndexController 指向系统访问首页
 * @author jbolt.cn and Liao
 * @date 2019年3月1日
 */
public class IndexController extends Controller {
	/**
	 * 首页Action
	 * 
	 */
	ReservationService dao = new ReservationService();
	public void index() {
			
			List<ReservationModel> reservations = dao.findAll();
			
			setAttr("re",reservations);
			renderFreeMarker("reservation.html");
		
		
	}
}