package net.user.talk_request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.common.constant.Constant.FileName;
import net.user.common.CommonProcess;

@Controller
public class TalkRequestController {

	@RequestMapping("/user/employe{c}/{id}/request")
	public ModelAndView submitTalkRequest(
			@PathVariable Character c,
			@PathVariable Long id,
			ModelAndView mav) {

		if (!CommonProcess.checkCharacter(c)) {
			mav.setViewName(FileName.INDEX);
			return mav;
		}

		//{id}からユーザーをDB取得
		//{id}からユーザーが取得できなかった場合の処理
		//{id}から取得したユーザーのユーザータイプとURLを比較して"employe{c}"を比較して矛盾が生じた場合はfindUsersPageに戻る

		//『話したいリクエストテーブル』に新規作成

		mav.setViewName("");
		return mav;
	}
}
