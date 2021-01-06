package net.user.block;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.common.constant.Constant.FileName;
import net.user.common.CommonProcess;

@Controller
public class BlockUserController {

	@RequestMapping("/user/employe{c}/{id}/block")
	public ModelAndView registrationBlockUser(
			@PathVariable Character c,
			@PathVariable Long id,
			ModelAndView mav) {

		if (!CommonProcess.checkCharacter(c)) {
			mav.setViewName(FileName.INDEX);
			return mav;
		}

		mav.setViewName("");
		return mav;
	}
}
