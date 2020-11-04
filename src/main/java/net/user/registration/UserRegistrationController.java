package net.user.registration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import net.user.UserEntity;
import net.user.UserService;

@Controller
public class UserRegistrationController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;

	private final String USER_REGISTRATION_OBJECT = "userRegistration";

	private final String PAGE_TITLE = "pageTitle";
	private final String FOLDER_NAME = "user/registration";
	private final String MESSAGE = "message";

	/**
	 * Form入力欄にスペース等が入れられた場合にトリミングするメソッド
	 * @author SatoYusuke0228
	 */
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, editor);
	}

	//[ユーザー登録ボタン]が押された場合にページを表示する
	//ＶＩＥＷで,ユーザータイプ(STUDENT or ENGINEER)選択ボタンを表示する
	@RequestMapping("/user/registration")
	public String selectUserTypePage() {

		return FOLDER_NAME + "/select-user-type";
	}

	//[ユーザータイプ選択ボタン]が押された場合に表示するページ
	//引数でint型UserTypeを受け取る
	//ＶＩＥＷでユーザーのプロフィール情報の入力FORMを表示
	//選択されたユーザータイプと入力FORMの内容をUerEntityコンストラクタに代入
	//そのコンストラクタをスコープに保存
	//ＶＩＥＷで入力内容確認ボタンを用意する
	@GetMapping("/user/registration/{userType}")
	public ModelAndView inputUserProfilePage(
			@PathVariable String userType,
			Integer userTypeNum,
			ModelAndView mav) {

		if ("student".equals(userType)) {
			userTypeNum = 0;
			mav.addObject(PAGE_TITLE, "エンジニアになりたい！");
		} else if ("engineer".equals(userType)) {
			userTypeNum = 1;
			mav.addObject(PAGE_TITLE, "エンジニアになりたい人を探したい！");
		} else {
			mav.setViewName(FOLDER_NAME + "/select-user-type");
			return mav;
		}

		mav.addObject(USER_REGISTRATION_OBJECT, new UserRegistration(userTypeNum));
		mav.setViewName(FOLDER_NAME + "/profile-form");

		return mav;
	}

	//[入力内容確認ボタン]が押された場合に表示するページ
	//前のページの入力情報を確認画面として表示する
	//ＶＩＥＷで登録完了ボタンを表示する
	@PostMapping("/user/registration/{userType}/confirmation")
	public ModelAndView confirmationUserProfilePage(
			@PathVariable String userType,
			@Validated UserRegistration userRegistration,
			BindingResult result,
			ModelAndView mav) {

		//バリデーションエラーがある場合は元の画面に戻る
		if (result.hasErrors()) {
			mav.setViewName(FOLDER_NAME + "/profile-form");
			//mav.setViewName("redirect:/user/registration/" + userType);
			return mav;
		}

		//パスワードの再入力が間違っている場合は元の画面に戻る
		final String password = userRegistration.getPassword();
		final String passwordAuth = userRegistration.getPassword_auth();

		if (!password.equals(passwordAuth) || !passwordAuth.equals(password)) {
			mav.addObject(MESSAGE, "※パスワードが間違っています");
			mav.setViewName(FOLDER_NAME + "/profile-form");
			return mav;
		}

		mav.addObject(USER_REGISTRATION_OBJECT, userRegistration);
		mav.setViewName(FOLDER_NAME + "/confirmation");

		session.setAttribute(USER_REGISTRATION_OBJECT, userRegistration);

		return mav;
	}

	//	[登録完了ボタン]が押された場合に表示するページ
	//	UserEntityの情報をDBに保存
	//	USERテーブルのIDを取得して同じIDでThumbnailEntityクラスのコンストラクタを作成してDBに保存
	//	USERテーブルのIDとUSER_TYPEを使用して
	//	StudentEntityかEngineerEntityのコンストラクタを作成してDBに保存
	//	ユーザーの登録アドレスに確認メールを送信
	@RequestMapping("/user/registration/{userType}/result")
	public ModelAndView resultRegistrationPage(
			@PathVariable String userType,
			@SessionAttribute("userRegistration") UserRegistration userRegistration,
			ModelAndView mav
			) {

		UserEntity userEntity = new UserEntity(userRegistration);

		//USERテーブルへの新規登録処理を記述
		//userService.saveAndFlash(userEntity);

		//ここにサムネイルテーブルの新規登録処理を記述
		//TumbnailEntity tumbnailEntity = new TumbnailEntity(userEntity.getId());
		//TumbnailService.saveAndFlash()

		System.out.println(userEntity.getUserType());

		if (userEntity.getUserType() == 0) {
			//STUDENT_TABLEに新規登録処理を記述
		} else if (userEntity.getUserType() == 1) {
			//ENGINEER_TABLEに新規登録処理を記述
		} else {

		}

		//メール確認FlagをONにするためのメールの送信処理を記述

		mav.setViewName(FOLDER_NAME + "/result");

		return mav;
	}

	//ユーザーの登録アドレスに受信した登録確認メール内のリンクを踏むことでアカウントのMAIL_VERFIEDを有効化
	@RequestMapping("/user/registration/{userType}/verified/{id}")
	public ModelAndView confirMailVerified(
			@PathVariable String userType,
			@PathVariable Long id,
			UserEntity userEntity,
			ModelAndView mav) {

		userEntity = userService.getOne(id);
		userEntity.setMailVerified(true);
		userService.saveAndFlash(userEntity);

		return mav;
	}

}
