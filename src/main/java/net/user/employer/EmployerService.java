package net.user.employer;

import java.util.List;

public abstract interface EmployerService {

	/**
	 * insert文を実行する抽象メソッド
	 */
	public abstract void save(EmployerEntity entity);

	/**
	 * update文を実行する抽象メソッド
	 */
	public abstract void saveAndFlash(EmployerEntity entity);

	/**
	 * delete文を実行する抽象メソッド
	 */
	public abstract void delete(EmployerEntity entity);

	/**
	 * ID検索でselect文を実行するメソッド
	 */
	public abstract EmployerEntity getOne(Long id) ;

	/**
	 * テーブル内の全件を取得するメソッド
	 */
	public abstract List<EmployerEntity> findAll();
}