package net.user.employer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerServiceImpl implements EmployerService {

	@Autowired
	private EmployerRepository repository;

	/**
	 * insert文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void save(EmployerEntity entity) {
		repository.save(entity);
	}

	/**
	 * update文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void saveAndFlash(EmployerEntity entity) {
		repository.saveAndFlush(entity);
	}

	/**
	 * delete文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void delete(EmployerEntity entity) {
		repository.delete(entity);
	}

	/**
	 * ID検索でselect文を実行する実装メソッド
	 */
	@Override
	public EmployerEntity getOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * テーブル内の全件を取得する実装メソッド
	 */
	@Override
	public List<EmployerEntity> findAll() {
		return repository.findAll();
	}
}