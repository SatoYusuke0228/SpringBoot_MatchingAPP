package net.user.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	/**
	 * insert文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void save(EmployeeEntity entity) {
		repository.save(entity);
	}

	/**
	 * update文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void saveAndFlash(EmployeeEntity entity) {
		repository.saveAndFlush(entity);
	}

	/**
	 * delete文の実装メソッド
	 *
	 * @param entity
	 */
	@Override
	public void delete(EmployeeEntity entity) {
		repository.delete(entity);
	}

	/**
	 * ID検索でselect文を実行する実装メソッド
	 */
	@Override
	public EmployeeEntity getOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * テーブル内の全件を取得する実装メソッド
	 */
	@Override
	public List<EmployeeEntity> findAll() {
		return repository.findAll();
	}
}