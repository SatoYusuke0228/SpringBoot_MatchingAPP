package net.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * Insert文 && Update文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void saveAndFlash(UserEntity entity) {
		repository.saveAndFlush(entity);
	}

	/**
	 * ID検索でselect文を実行する実装メソッド
	 */
	@Override
	public UserEntity getOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * テーブル内の全件を取得する実装メソッド
	 */
	@Override
	public List<UserEntity> findAll() {
		return repository.findAll();
	}
}