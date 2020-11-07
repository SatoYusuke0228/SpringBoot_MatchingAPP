package net.user.registration;

import static net.common.Constant.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

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

import net.common.Constant.FileName;
import net.common.Constant.FolderName;
import net.common.Constant.ObjectName;
import net.user.User;
import net.user.UserEntity;
import net.user.UserService;
import net.user.employee.Employee;
import net.user.employee.EmployeeEntity;
import net.user.employee.EmployeeService;
import net.user.employer.Employer;
import net.user.employer.EmployerEntity;
import net.user.employer.EmployerService;
import net.user.thumbnail.ThumbnailEntity;
import net.user.thumbnail.ThumbnailService;

@Controller
public class UserRegistrationController {

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

	private final String FOLDER_PATH = FolderName.USER + FolderName.REGISTRATION;
	private final String USER_OBJECT = ObjectName.USER.getString();
	private final String USER_ENTITY_OBJECT = ObjectName.USER_ENTITY.getString();
	private final String EMPLOYEE_OBJECT = ObjectName.EMPLOYEE.getString();
	private final String EMPLOYEE_ENTITY_OBJECT = ObjectName.EMPLOYEE_ENTITY.getString();
	private final String EMPLOYER_OBJECT = ObjectName.EMPLOYER.getString();
	private final String EMPLOYER_ENTITY_OBJECT = ObjectName.EMPLOYER_ENTITY.getString();

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
	@RequestMapping({ "/user/registration", "/user/registration/select-user-type" })
	public String selectUserType() {

		return FOLDER_PATH + FileName.SELECT_USER_TYPE;
	}

	//[ユーザータイプ選択ボタン]が押された場合に表示するページ
	//引数でint型UserTypeを受け取る
	//ＶＩＥＷでユーザーのプロフィール情報の入力FORMを表示
	//選択されたユーザータイプと入力FORMの内容をUerEntityコンストラクタに代入
	//そのコンストラクタをスコープに保存
	//ＶＩＥＷで入力内容確認ボタンを用意する
	@GetMapping("/user/registration/form/user-type={userType}")
	public ModelAndView form(
			@PathVariable String userType,
			Integer userTypeNum,
			ModelAndView mav) {

		if ("employee".equals(userType)) {
			userTypeNum = 0;
			mav.addObject(EMPLOYEE_OBJECT, new Employee());
		} else if ("employer".equals(userType)) {
			userTypeNum = 1;
			mav.addObject(EMPLOYER_OBJECT, new Employer());
		} else {
			mav.setViewName(REDIRECT + FOLDER_PATH + FileName.SELECT_USER_TYPE);
			return mav;
		}

		mav.addObject(USER_TYPE, userTypeNum);
		mav.addObject(USER_OBJECT, new User(userTypeNum));
		mav.setViewName(FOLDER_PATH + FileName.FORM);

		return mav;
	}

	//[入力内容確認ボタン]が押された場合に表示するページ
	//前のページの入力情報を確認画面として表示する
	//ＶＩＥＷで登録完了ボタンを表示する
	@PostMapping("/user/registration/confirmation")
	public ModelAndView confirmation(
			@Validated User user,
			BindingResult userBR,
			@Validated Employee employee,
			BindingResult employeeBR,
			@Validated Employer employer,
			BindingResult employerBR,
			ModelAndView mav) {

		//バリデーションエラーがある場合は元の画面に戻る
		if (user.getUserType() != 0 && user.getUserType() != 1) {
			mav.setViewName(REDIRECT + FOLDER_PATH + FileName.SELECT_USER_TYPE);
			return mav;
		} else if (user.getUserType() == 0 && (userBR.hasErrors() || employeeBR.hasErrors())) {
			mav.setViewName(FOLDER_PATH + FileName.FORM);
			return mav;
		} else if (user.getUserType() == 1 && (userBR.hasErrors() || employerBR.hasErrors())) {
			mav.setViewName(FOLDER_PATH + FileName.FORM);
			return mav;
		}

		//パスワードの再入力が間違っている場合は元の画面に戻る
		final String password = user.getPassword();
		final String passwordAuth = user.getPassword_auth();

		if (!password.equals(passwordAuth) || !passwordAuth.equals(password)) {
			mav.addObject(MESSAGE, "※パスワードが間違っています");
			mav.setViewName(FOLDER_PATH + FileName.FORM);
			return mav;
		}

		//もし新規登録のメールアドレスが既に登録済みの場合は元の画面に戻る
		List<UserEntity> allUserEntityInDB = new ArrayList<>();
		allUserEntityInDB = userService.findAll();

		for (int i = allUserEntityInDB.size() - 1; 0 <= i; i--) {

			if (user.getMail().equals(allUserEntityInDB.get(i).getMail())) {
				mav.addObject(MESSAGE, "※登録済みのメールアドレスです");
				mav.setViewName(FOLDER_PATH + FileName.FORM);
				return mav;
			}
		}

		session.setAttribute(USER_OBJECT, user);
		mav.addObject(USER_OBJECT, user);

		if (user.getUserType() == 0) {
			session.setAttribute(EMPLOYEE_OBJECT, employee);
			mav.addObject(EMPLOYEE_OBJECT, employee);
		} else if (user.getUserType() == 1) {
			session.setAttribute(EMPLOYER_OBJECT, employer);
			mav.addObject(EMPLOYER_OBJECT, employer);
		}

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
			@SessionAttribute("user") User user,
			Object hoge,
			ModelAndView mav) {

		//USERテーブルへの新規登録処理を記述
		session.setAttribute(USER_OBJECT, null);
		UserEntity userEntity = new UserEntity(user);
		userService.save(userEntity);

		//EMPLOYEE or EMPLOYERテーブルに新規登録処理の記述
		 if (user.getUserType() == 0) {
			hoge = session.getAttribute(EMPLOYEE_OBJECT);
			session.setAttribute(EMPLOYEE_OBJECT, null);
			EmployeeEntity employeeEntity = new EmployeeEntity(userEntity.getId(), (Employee) hoge);
			employeeService.save(employeeEntity);
		} else if (user.getUserType() == 1) {
			hoge = (Employer) session.getAttribute(EMPLOYER_OBJECT);
			session.setAttribute(EMPLOYER_OBJECT, null);
			EmployerEntity employerEntity = new EmployerEntity(userEntity.getId(), (Employer) hoge);
			employerService.save(employerEntity);
		}

		//TUMBNAILテーブルに新規登録
		ThumbnailEntity thumbnailEntity = new ThumbnailEntity(userEntity.getId());
		thumbnailService.save(thumbnailEntity);

		//メール確認FlagをONにするためのメールの送信処理を記述
		//hogehogehugahuga

		mav.setViewName(FOLDER_PATH + FileName.RESULT);

		return mav;
	}

	//ユーザーの登録アドレスに受信した登録確認メール内のリンクを踏むことでアカウントのMAIL_VERFIEDを有効化
	//ユーザー登録時に選択されたユーザータイプに応じてSTUDENT_TABLE or ENGINEER_TABLEを作成する入力FORMを表示
	@RequestMapping(value = "/student/registration/form/{id}", method = { GET, POST })
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
		mav.setViewName(FOLDER_PATH + "");

		return mav;
	}
}
