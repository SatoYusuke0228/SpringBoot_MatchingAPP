package net.user.talk_request;

import java.util.List;
import java.util.Optional;

public abstract interface TalkRequestService {

	/**
	 * insert文を実行する抽象メソッド
	 */
	public abstract void save(TalkRequestEntity entity);

	/**
	 * update文を実行する抽象メソッド
	 */
	public abstract void saveAndFlash(TalkRequestEntity entity);

	/**
	 * delete文を実行する抽象メソッド
	 */
	public abstract void delete(TalkRequestEntity entity);

	/**
	 * テーブル内の全件を取得するメソッド
	 */
	public abstract List<TalkRequestEntity> findAll();

	/**
	 * ID検索でselect文を実行するメソッド
	 */
	public abstract Optional<TalkRequestEntity> findById(TalkRequestPK id);
}