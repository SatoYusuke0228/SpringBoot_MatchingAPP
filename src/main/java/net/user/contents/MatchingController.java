package net.user.contents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.common.constant.Constant.FileName;
import net.common.constant.Constant.FolderName;
import net.common.constant.Constant.ObjectName;
import net.user.UserEntity;
import net.user.UserService;
import net.user.common.CommonProcess;
import net.user.employee.EmployeeEntity;
import net.user.employee.EmployeeService;
import net.user.employer.EmployerEntity;
import net.user.employer.EmployerService;

@Controller
public class MatchingController {

	@Autowired
	UserService userService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployerService employerService;

	final String EMPLOYEE_ENTITY_OBJECT = ObjectName.EMPLOYEE.getString();
	final String EMPLOYER_ENTITY_OBJECT = ObjectName.EMPLOYER.getString();
	final String FOLDER_PATH = FolderName.USER + FolderName.CONTENTS;

	/**
	 * ユーザー一覧を表示するページのController
	 *
	 * @param c
	 * @param mav
	 * @return
	 */
	@RequestMapping("/user/employe{c}/all")
	public ModelAndView findAllUsersPage(
			@PathVariable Character c,
			ModelAndView mav) {

		if (c == 'e') { //求職者一覧を取得した場合の処理
			List<EmployeeEntity> employeeEntity = employeeService.findAll();
			mav.addObject(EMPLOYEE_ENTITY_OBJECT, employeeEntity);
		} else if (c == 'r') { //求人者一覧を取得した場合の処理
			List<EmployerEntity> employerEntity = employerService.findAll();
			mav.addObject(EMPLOYER_ENTITY_OBJECT, employerEntity);
		} else {
			mav.setViewName(FileName.INDEX);
			return mav;
		}

		mav.setViewName(FOLDER_PATH + FileName.USER_SEARCH);

		return mav;
	}

	/**
	 * ユーザーをキーワード検索で表示するページのController
	 *
	 * @param c
	 * @param mav
	 * @return
	 */
	@GetMapping("/user/employe{c}/search")
	public ModelAndView findByKeywordUsersPage(
			@PathVariable Character c,
			ModelAndView mav) {

		if (c == 'e') { //求職者一覧を取得した場合の処理
			//List<EmployeeEntity> employeeEntity = employeeService.findByKeyword();
			//mav.addObject(EMPLOYEE_ENTITY_OBJECT, employeeEntity);
		} else if (c == 'r') { //求人者一覧を取得した場合の処理
			//List<EmployerEntity> employerEntity = employerService.findByKeyword();
			//mav.addObject(EMPLOYER_ENTITY_OBJECT, employerEntity);
		} else {
			mav.setViewName(FileName.INDEX);
			return mav;
		}

		mav.setViewName(FOLDER_PATH + FileName.USER_SEARCH);

		return mav;
	}

	@RequestMapping("/user/employe{c}/{id}")
	public ModelAndView userDetailsPage(
			@PathVariable Character c,
			@PathVariable Long id,
			ModelAndView mav) {

		if (!CommonProcess.checkCharacter(c)) {
			mav.setViewName(FileName.INDEX);
			return mav;
		}

		//{id}からDBに登録されているユーザーを取得
		//{id}からユーザーが取得できなかった場合の例外処理はUserServiceImplで実行
		final UserEntity user = userService.getOne(id);

		//もし自分のIDだった場合は自分のプロフィール詳細画面に遷移
		if (!CommonProcess.compareEqualsMailAddress(user)) {
			mav.setViewName(FileName.INDEX);
			return mav;
		}

		/**
		 * ・未実装の処理
		 *
		 * 閲覧者のユーザーIDと閲覧先のユーザーIDを用意
		 * 上記のIDでブロックテーブルに登録されているかどうか真偽チェック
		 * もし閲覧先のブロックテーブルに閲覧者のIDが登録されている場合はユーザー検索ページに戻る
		 * 話したいリクエストテーブルに登録されているかどうか真偽チェック
		 * もし話したいリクエストテーブルの承認カラムの真偽チェック
		 */

		//ここまで問題なければViewにオブジェクト情報などを渡して表示させる準備
		mav.addObject("user", user);
		mav.setViewName("");

		return mav;
	}

	@RequestMapping("/user/employe{c}/{id}/message")
	public ModelAndView showMessagePage(
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