package net.user.thumbnail;

public abstract interface ThumbnailService {

	/**
	 * insert文を実行する抽象メソッド
	 */
	public abstract void save(ThumbnailEntity entity);

	/**
	 * update文を実行する抽象メソッド
	 */
	public abstract void saveAndFlash(ThumbnailEntity entity);
}
