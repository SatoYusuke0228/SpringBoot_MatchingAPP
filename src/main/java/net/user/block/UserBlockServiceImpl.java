package net.user.block;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserBlockServiceImpl implements UserBlockService {

	@Autowired
	UserBlockRepository repository;

	/**
	 * insert文の実装メソッド
	 * クエリ実行直前にパスワードのハッシュ化をここで行う
	 * @param entity
	 */
	@Override
	public void save(UserBlockEntity entity) {
		repository.save(entity);
	}

	/**
	 * update文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void saveAndFlash(UserBlockEntity entity) {
		repository.saveAndFlush(entity);
	}

	/**
	 * delete文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void delete(UserBlockEntity entity) {
		repository.delete(entity);
	}

	/**
	 * テーブル内の全件を取得する実装メソッド
	 */
	@Override
	public List<UserBlockEntity> findAll() {
		return repository.findAll();
	}

	/**
	 * ID検索でselect文を実行する実装メソッド
	 */
	@Override
	public Optional<UserBlockEntity> findById(UserBlockPK id) {
		return Optional.ofNullable(repository.findById(id))
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));
	}
}