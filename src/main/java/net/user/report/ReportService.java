package net.user.report;

import java.util.List;

public abstract interface ReportService {

	public abstract void save(ReportEntity entity);

	/**
	 * insert文 update文 を実行する抽象メソッド
	 */
	public abstract void saveAndFlash(ReportEntity entity);

	/**
	 * ID検索でselect文を実行するメソッド
	 */
	public abstract ReportEntity getOne(Long id) ;

	/**
	 * テーブル内の全件を取得するメソッド
	 */
	public abstract List<ReportEntity> findAll();
}