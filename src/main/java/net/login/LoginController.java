package net.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import net.common.Constant;
import net.user.UserEntity;

@Controller
public class LoginController {

	@RequestMapping({ "/", "/index" })
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			ModelAndView mav) {

		if ("error".equals(error)) {
			mav.addObject(Constant.MESSAGE, "hogehoge");
		}

		mav.setViewName("login");
		return mav;
	}

	@RequestMapping("/login-success")
	public String loginSuccess(
			@SessionAttribute("userEntity") UserEntity userEntity) {

		System.out.println("ID : " + userEntity.getId());

		return "login-success";
	}
}
