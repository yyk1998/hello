package top.liaoyichao.interceptor.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

import top.liaoyichao.util.MyCookie;

/**
 * @author Liao
 * @date 2019年3月2日
 * @Description: HomePageController 控制类中 更新t_person表中字段的数据校验类
 */
public class HomePagePersonUpdateValidator extends Validator{

	/* (non-Javadoc)
	 * @see com.jfinal.validate.Validator#validate(com.jfinal.core.Controller)
	 */
	@Override
	protected void validate(Controller c) {		
		
		/**
		 * 校验条件
		 * 	Name 的字符不能大于30
		 * 	Age 只能是1~3的数字
		 *  Address 的字符不能大于30 以后可以加入正则 必须使用 xx省xx市的方式编写
		 */
		validateString("updateName", 0, 30, "updateNameError", "请输入1~30之间的字符~");
		validateString("updateAge", 0, 4, "updateAgeError", "请输入1~3之间的数字~");
		validateString("updateAddress", 0, 30, "updateAddressError", "请输入1~30之间的字符~");
	}

	/* (non-Javadoc)
	 * @see com.jfinal.validate.Validator#handleError(com.jfinal.core.Controller)
	 */
	@Override
	protected void handleError(Controller c) {
		c.setAttr("id", c.getPara("id"));
		c.setAttr("updatePersonTAG", "updatePersonTAG");
		c.renderFreeMarker("add.html");
	}

}
