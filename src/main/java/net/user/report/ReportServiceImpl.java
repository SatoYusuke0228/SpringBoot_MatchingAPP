package net.user.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository repository;

	@Override
	public void save(ReportEntity entity) {
		repository.save(entity);
	}

	/**
	 * Insert文 && Update文の実装メソッド
	 */
	@Override
	public void saveAndFlash(ReportEntity entity) {
		repository.saveAndFlush(entity);
	}

	/**
	 * ID検索でselect文を実行する実装メソッド
	 */
	@Override
	public ReportEntity getOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * テーブル内の全件を取得する実装メソッド
	 */
	@Override
	public List<ReportEntity> findAll() {
		return repository.findAll();
	}
}