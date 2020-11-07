package net.user.employee;

import java.util.List;

public abstract interface EmployeeService {

	/**
	 * insert文を実行する抽象メソッド
	 */
	public abstract void save(EmployeeEntity entity);

	/**
	 * update文を実行する抽象メソッド
	 */
	public abstract void saveAndFlash(EmployeeEntity entity);

	/**
	 * delete文を実行する抽象メソッド
	 */
	public abstract void delete(EmployeeEntity entity);

	/**
	 * ID検索でselect文を実行するメソッド
	 */
	public abstract EmployeeEntity getOne(Long id) ;

	/**
	 * テーブル内の全件を取得するメソッド
	 */
	public abstract List<EmployeeEntity> findAll();
}