package net.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/admin/need-login")
	public String adminsNeedLoginPage() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("NAME : " + auth.getName());
		System.out.println("ROLE : " + auth.getAuthorities());

		return "/admin/need-login";
	}

	@RequestMapping("/user/need-login")
	public String UsersNeedLoginPage() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("NAME : " + auth.getName());
		System.out.println("ROLE : " + auth.getAuthorities());

		return "/user/need-login";
	}

	@RequestMapping("/user/employee/employee-only")
	public String employeeOnlyPage() {
		return "/user/employee/employee-only";
	}

	@RequestMapping("/user/employer/employer-only")
	public String employerOnlyPage() {
		return "/user/employer/employer-only";
	}
}
