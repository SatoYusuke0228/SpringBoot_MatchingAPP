package net.user.block;

import java.util.List;
import java.util.Optional;

public abstract interface UserBlockService {

	/**
	 * insert文を実行する抽象メソッド
	 */
	public abstract void save(UserBlockEntity entity);

	/**
	 * update文を実行する抽象メソッド
	 */
	public abstract void saveAndFlash(UserBlockEntity entity);

	/**
	 * delete文を実行する抽象メソッド
	 */
	public abstract void delete(UserBlockEntity entity);

	/**
	 * テーブル内の全件を取得するメソッド
	 */
	public abstract List<UserBlockEntity> findAll();

	/**
	 * ID検索でselect文を実行する抽象メソッド
	 *
	 * @param id 利用者のユーザーID
	 * @param blockedUserId 利用者がブロックしているユーザーのID
	 * @return 1件のブロックユーザー
	 */
	public abstract Optional<UserBlockEntity> findById(UserBlockPK id);
}