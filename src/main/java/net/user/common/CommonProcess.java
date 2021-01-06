package net.user.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import net.user.UserEntity;

public class CommonProcess {

	/**
	 * この文字を含めてURL中の文字列が
	 * "/employee" または "/employer" になる事を想定して
	 * Character型の変数cの値をチェックするメソッド。
	 *
	 * @param  URL"user/employe{c}"の中のCharacter型の引数
	 * @return 引数cの値が 'e' || 'r' で真
	 */
	public static boolean checkCharacter(char c) {

		return c == 'e' || c == 'r';
	}

	/**
	 * 引数のEntityのメールアドレスと
	 * ログイン中のユーザーのユーザーネーム（メールアドレス）を比較
	 * 一致する場合は"真"を返すメソッド
	 *
	 * @param arguEntity
	 * @return メールアドレスを比較して一致すれば真。一致しなければ偽。
	 */
	public static boolean compareEqualsMailAddress(UserEntity arguEntity) {

		final String arguEntityMail = new String(arguEntity.getMail());

		Authentication loginUser = SecurityContextHolder.getContext().getAuthentication();
		final String loginUserMail = loginUser.getName();

		return arguEntityMail.equals(loginUserMail);
	}

}
