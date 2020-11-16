package net.login;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.user.UserEntity;

@Repository
public class LoginRepository {

	@Autowired
	protected EntityManager em;

	/**
	 * フォームの入力値から該当するユーザを検索
	 * 合致するものが無い場合Nullが返される
	 *
	 * @param  <code>mail</code>
	 *         ログイン画面の入力フォームから取得したメールアドレス
	 *         ユーザー登録時に入力されたメールアドレスを探すために使用(<code>unique = true</code>)
	 *
	 * @return 一致するユーザが存在する場合:<code>UserEntity</code>
	 *         存在しない場合:<code>Null</code>
	 */
	public UserEntity findUser(String mail) {

		//setParameterで引数の値を代入できるようにNamedParameterを利用
		final String query = new String("SELECT * FROM USER_TABLE WHERE MAIL = :mail");

		//EntityManagerで取得された結果はオブジェクトとなるのでキャストが必要
		return (UserEntity) em.createNativeQuery(query, UserEntity.class)
				.setParameter("mail", mail)
				.getSingleResult();
	}
}
