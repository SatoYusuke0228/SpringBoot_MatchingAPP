package net.user.student.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import net.user.UserEntity;
import net.user.UserService;

@Controller
public class StudentRegistrationController {

	@Autowired
	UserService userService;

	private final String USER_ENTITY_OBJECT = "userEntity";
	private final String PAGE_TITLE = "pageTitle";
	private final String FOLDER_NAME = "student/registration";
	private final String MESSAGE = "message";

	//ユーザーの登録アドレスに受信した登録確認メール内のリンクを踏むことでアカウントのMAIL_VERFIEDを有効化
	//ユーザー登録時に選択されたユーザータイプに応じてSTUDENT_TABLE or ENGINEER_TABLEを作成する入力FORMを表示
	@GetMapping(value = "/student/registration/")
	@PostMapping
	public ModelAndView confirMailVerified(
			@PathVariable Long id,
			UserEntity userEntity,
			ModelAndView mav) {

		userEntity = userService.getOne(id);
		userEntity.setMailVerified(true);
		userService.saveAndFlash(userEntity);

		if (userEntity.getUserType() == 0) {
			//STUDENT_TABLEに新規登録処理を記述
		} else if (userEntity.getUserType() == 1) {
			//ENGINEER_TABLEに新規登録処理を記述
		}

		mav.addObject(USER_ENTITY_OBJECT, userEntity);
		mav.setViewName(FOLDER_NAME + "");

		return mav;
	}

}
