package net.user.profile;

import static net.common.constant.Constant.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.user.UserEntity;
import net.user.UserService;
import net.user.employee.EmployeeService;
import net.user.employer.EmployerService;
import net.user.thumbnail.ThumbnailService;

@Controller
public class UserConfigController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployerService employerService;

	@Autowired
	private ThumbnailService thumbnailService;

	/**
	 * プロフィール編集画面
	 *
	 * @param id
	 * @param userEntity
	 * @param mav
	 * @return
	 */
	@RequestMapping("/user/profile/config/{id}")
	public ModelAndView edit(
			@PathVariable long id,
			UserEntity userEntity,
			ModelAndView mav) {

		userEntity = userService.getOne(id);

		if (userEntity.getUserType() == 0) {
			mav.addObject(USER_TYPE);
		} else if (userEntity.getUserType() == 1) {
			mav.addObject(USER_TYPE);
		}

		mav.setViewName("プロフィール画面");
		return mav;
	}
}
