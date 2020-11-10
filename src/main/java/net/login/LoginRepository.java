package net.login;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.user.UserEntity;

@Repository
public class LoginRepository {

	@Autowired
	EntityManager em;

	/**
	 * フォームの入力値から該当するユーザを検索
	 * 合致するものが無い場合Nullが返される
	 *
	 * @param userName
	 * @return 一致するユーザが存在するとき:UserEntity、存在しないとき:Null
	 */
	public UserEntity findUser(String mail) {

		//setParameterで引数の値を代入できるようにNamedParameterを利用
		String query = new String("SELECT * FROM USER_TABLE WHERE MAIL = :mail");

		//EntityManagerで取得された結果はオブジェクトとなるのでキャストが必要
		return (UserEntity) em.createNativeQuery(query, UserEntity.class)
				.setParameter("mail", mail)
				.getSingleResult();
	}
}
