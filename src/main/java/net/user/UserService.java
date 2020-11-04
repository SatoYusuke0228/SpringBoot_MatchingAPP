package net.user;

import java.util.List;

public abstract interface UserService {

	/**
	 * insert文 update文 を実行する抽象メソッド
	 */
	public abstract void saveAndFlash(UserEntity entity);

	/**
	 * ID検索でselect文を実行するメソッド
	 */
	public abstract UserEntity getOne(Long id) ;

	/**
	 * テーブル内の全件を取得するメソッド
	 */
	public List<UserEntity> findAll();
}