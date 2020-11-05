package net.user.registration;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import net.common.Constant;
import net.common.Constant.FileName;
import net.common.Constant.FolderName;
import net.common.Constant.ObjectName;
import net.user.UserEntity;
import net.user.UserService;

@Controller
public class UserRegistrationController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;

	private final String FOLDER_PATH = FolderName.USER + FolderName.REGISTRATION;
	private final String USER_REGISTRTION_OBJECT = ObjectName.userRegistration.getString();

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
	@RequestMapping("/user/registration/select-user-type")
	public String selectUserType() {

		return FOLDER_PATH + FileName.SELECT_USER_TYPE;
	}

	//[ユーザータイプ選択ボタン]が押された場合に表示するページ
	//引数でint型UserTypeを受け取る
	//ＶＩＥＷでユーザーのプロフィール情報の入力FORMを表示
	//選択されたユーザータイプと入力FORMの内容をUerEntityコンストラクタに代入
	//そのコンストラクタをスコープに保存
	//ＶＩＥＷで入力内容確認ボタンを用意する
	@RequestMapping("/user/registration/form/{userType}")
	public ModelAndView form(
			@PathVariable String userType,
			Integer userTypeNum,
			ModelAndView mav) {

		if ("student".equals(userType)) {
			userTypeNum = 0;
			mav.addObject(Constant.PAGE_TITLE, "エンジニアになりたい！");
		} else if ("engineer".equals(userType)) {
			userTypeNum = 1;
			mav.addObject(Constant.PAGE_TITLE, "エンジニアになりたい人を探したい！");
		} else {
			mav.setViewName("redirect:" + FOLDER_PATH + FileName.SELECT_USER_TYPE);
			return mav;
		}

		mav.addObject(USER_REGISTRTION_OBJECT, new UserRegistration(userTypeNum));
		mav.setViewName(FOLDER_PATH + FileName.FORM);

		return mav;
	}

	//[入力内容確認ボタン]が押された場合に表示するページ
	//前のページの入力情報を確認画面として表示する
	//ＶＩＥＷで登録完了ボタンを表示する
	@PostMapping("/user/registration/confirmation")
	public ModelAndView confirmation(
			@Validated UserRegistration userRegistration,
			BindingResult result,
			ModelAndView mav) {

		//バリデーションエラーがある場合は元の画面に戻る
		if (result.hasErrors()) {
			mav.setViewName(FOLDER_PATH + FileName.FORM);
			return mav;
		}

		//パスワードの再入力が間違っている場合は元の画面に戻る
		final String password = userRegistration.getPassword();
		final String passwordAuth = userRegistration.getPassword_auth();

		if (!password.equals(passwordAuth) || !passwordAuth.equals(password)) {
			mav.addObject(Constant.MESSAGE, "※パスワードが間違っています");
			mav.setViewName(FOLDER_PATH + FileName.FORM);
			return mav;
		}

		//もし新規登録のメールアドレスが既に登録済みの場合は元の画面に戻る
		List<UserEntity> allUserEntityInDB = new ArrayList<>();
		allUserEntityInDB = userService.findAll();

		for (int i = allUserEntityInDB.size() - 1; 0 <= i; i--) {

			if (userRegistration.getMail().equals(allUserEntityInDB.get(i).getMail())) {
				mav.addObject(Constant.MESSAGE, "※登録済みのメールアドレスです");
				mav.setViewName(FOLDER_PATH + FileName.FORM);
				return mav;
			}
		}

		session.setAttribute(USER_REGISTRTION_OBJECT, userRegistration);
		mav.addObject(USER_REGISTRTION_OBJECT, userRegistration);
		mav.setViewName(FOLDER_PATH + FileName.CONFIRMATION);

		return mav;
	}

	//	[登録完了ボタン]が押された場合に表示するページ
	//	UserEntityの情報をDBに保存
	//	USERテーブルのIDを取得して同じIDでThumbnailEntityクラスのコンストラクタを作成してDBに保存
	//	USERテーブルのIDとUSER_TYPEを使用して
	//	StudentEntityかEngineerEntityのコンストラクタを作成してDBに保存
	//	ユーザーの登録アドレスに確認メールを送信
	@RequestMapping("/user/registration/result")
	public ModelAndView result(
			@SessionAttribute("userRegistration") UserRegistration userRegistration,
			ModelAndView mav) {

		UserEntity userEntity = new UserEntity(userRegistration);

		//USERテーブルへの新規登録処理を記述
		userService.save(userEntity);

		//ここにサムネイルテーブルの新規登録処理を記述
		//TumbnailEntity tumbnailEntity = new TumbnailEntity(userEntity.getId());
		//TumbnailService.saveAndFlash()

		//メール確認FlagをONにするためのメールの送信処理を記述

		mav.setViewName(FOLDER_PATH + FileName.RESULT);

		session.setAttribute(USER_REGISTRTION_OBJECT, null);

		return mav;
	}
}
