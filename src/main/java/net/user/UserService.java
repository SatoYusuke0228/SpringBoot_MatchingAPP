package net.user;

import java.util.List;

public abstract interface UserService {

	/**
	 * insert文を実行する抽象メソッド
	 */
	public abstract void save(UserEntity entity);

	/**
	 * update文を実行する抽象メソッド
	 */
	public abstract void saveAndFlash(UserEntity entity);

	/**
	 * delete文を実行する抽象メソッド
	 */
	public abstract void delete(UserEntity entity);

	/**
	 * ID検索でselect文を実行するメソッド
	 */
	public abstract UserEntity getOne(Long id);

	/**
	 * テーブル内の全件を取得するメソッド
	 */
	public abstract List<UserEntity> findAll();
}