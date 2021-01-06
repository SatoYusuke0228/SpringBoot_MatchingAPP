package net.user.talk_request;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TalkRequestServiceImpl implements TalkRequestService {

	@Autowired
	TalkRequestRepository repository;

	/**
	 * insert文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void save(TalkRequestEntity entity) {
		repository.save(entity);
	}

	/**
	 * update文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void saveAndFlash(TalkRequestEntity entity) {
		repository.saveAndFlush(entity);
	}

	/**
	 * delete文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void delete(TalkRequestEntity entity) {
		repository.delete(entity);
	}

	/**
	 * テーブル内の全件を取得する実装メソッド
	 */
	@Override
	public List<TalkRequestEntity> findAll() {
		return repository.findAll();
	}

	/**
	 * ID検索でselect文を実行する実装メソッド
	 */
	@Override
	public Optional<TalkRequestEntity> findById(TalkRequestPK id) {
		return Optional.ofNullable(repository.findById(id))
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));
	}
}