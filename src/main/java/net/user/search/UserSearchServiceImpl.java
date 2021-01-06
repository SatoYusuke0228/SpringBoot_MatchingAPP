package net.user.search;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import net.user.UserEntity;
import net.user.UserRepository;

@Service
public class UserSearchServiceImpl {

	@Autowired
	UserRepository userRepository;

	/**
	 *
	 *
	 * @param c       引数１ URLから取得した@PathValiable ユーザータイプを示す
	 * @param keyword 引数2 キーワード検索ボックスないの文字列
	 * @return 検索結果のユーザー一覧 検索結果が０件の場合はnullを返す
	 */
	public List<UserEntity> findByKeyword(char c, String keyword) {

		// 複数キーワードに分割する
		final List<String> splittedKeyword = splitQuery(keyword);

		//何もしないSpecificationを生成する。
		//reduceの初期値として利用する
		//Specification.where()にnullを渡せば、何もしないSpecificationが生成される
		final Specification<UserEntity> zero = Specification
				.where((Specification<UserEntity>) null);

		//分岐のために検索条件specの初期化
		Specification<UserEntity> spec = zero;

		if (c == 'e') {

			//キーワードのリストをそれぞれSpecificationにマッピングして、andで結合する
			spec = splittedKeyword
					.stream()
					.map(this::findEmployee)
					.reduce(zero, Specification<UserEntity>::and);

		} else if (c == 'r') {

			//キーワードのリストをそれぞれSpecificationにマッピングして、andで結合する
			spec = splittedKeyword
					.stream()
					.map(this::findEmployerByKeyword)
					.reduce(zero, Specification<UserEntity>::and);
		}

		return userRepository.findAll(spec);
	}

	/**
	 * ユーザーをAND検索する為のヘルパー関数
	 * "word1 word2 word3"のようなクエリ文をばらして
	 * ["word1", "word2", "word3"]にするのに使用するヘルパー関数
	 */
	private List<String> splitQuery(String keyword) {

		// 以下のパターンにマッチした部分を単一の半角スペースに変換する
		final String monoSpaceQuery = keyword.replaceAll("[\\s　]+", " ");

		// splitするとき、余分な空要素が生成されるのを防ぐため、先頭と末尾のスペースを削除する
		// trim()メソッドは全角スペースはTrimmingできないことに留意
		final String trimmedMonoSpaceQuery = monoSpaceQuery.trim();

		// 半角スペースでクエリをsplitする
		return Arrays.asList(trimmedMonoSpaceQuery.split(" "));
	}

	/**
	 * ユーザーをAND検索する為のヘルパー関数
	 * 単一キーワードによる絞り込み条件を表すSpecificationを返す
	 * @param 入力キーワード
	 * @return 絞り込み条件
	 */
	private Specification<UserEntity> findEmployee(String keyword) {

		return Specification
				//userType == 0のもの(Employee)を抽出
				.where(new Specification<UserEntity>() {
					@Override
					public Predicate toPredicate(
							Root<UserEntity> root,
							CriteriaQuery<?> query,
							CriteriaBuilder cb) {

						return cb.equal(root.get("userType"), 0);
					}
				})
				//activation == tureのものを抽出
				.and(new Specification<UserEntity>() {
					@Override
					public Predicate toPredicate(
							Root<UserEntity> root,
							CriteriaQuery<?> query,
							CriteriaBuilder cb) {

						return cb.isTrue(root.get("activation"));
					}
				})
				//mailVerified == tureのものを抽出
				.and(new Specification<UserEntity>() {
					@Override
					public Predicate toPredicate(
							Root<UserEntity> root,
							CriteriaQuery<?> query,
							CriteriaBuilder cb) {

						return cb.isTrue(root.get("mailVerified"));
					}
				})
				//showFlag == tureのものを抽出
				.and(new Specification<UserEntity>() {
					@Override
					public Predicate toPredicate(
							Root<UserEntity> root,
							CriteriaQuery<?> query,
							CriteriaBuilder cb) {

						return cb.isTrue(root.get("showFlag"));
					}
				})
				//キーワードを使用した検索条件を指定
				.and(findEmployeeByKeyword(keyword));

	}

	private Specification<UserEntity> findEmployeeByKeyword(String keyword) {

		return Specification
				.where(new Specification<UserEntity>() {
					@Override
					public Predicate toPredicate(
							Root<UserEntity> root,
							CriteriaQuery<?> query,
							CriteriaBuilder cb) {
						return cb.like(root.get("registrationArea"), "%" + keyword.toLowerCase() + "%");
					}
				})
				//学校名like検索
				.or(new Specification<UserEntity>() {
					@Override
					public Predicate toPredicate(
							Root<UserEntity> root,
							CriteriaQuery<?> query,
							CriteriaBuilder cb) {

						//同一IDで複数データある場合は１つだけ抽出
						query.distinct(true);

						return cb.like(
								root.join("employeeEntity", JoinType.LEFT)
										.get("schoolName"),
								"%" + keyword.toLowerCase() + "%");
					}
				})
				//学部名like検索
				.or(new Specification<UserEntity>() {
					@Override
					public Predicate toPredicate(
							Root<UserEntity> root,
							CriteriaQuery<?> query,
							CriteriaBuilder cb) {

						//同一IDで複数データある場合は１つだけ抽出
						query.distinct(true);

						return cb.like(
								root.join("employeeEntity", JoinType.LEFT)
										.get("faculty"),
								"%" + keyword.toLowerCase() + "%");
					}
				})
				//学科名like検索
				.or(new Specification<UserEntity>() {
					@Override
					public Predicate toPredicate(
							Root<UserEntity> root,
							CriteriaQuery<?> query,
							CriteriaBuilder cb) {

						//同一IDで複数データある場合は１つだけ抽出
						query.distinct(true);

						return cb.like(
								root
										.join("employeeEntity", JoinType.LEFT)
										.get("department"),
								"%" + keyword.toLowerCase() + "%");
					}
				});
	}

	private Specification<UserEntity> findEmployerByKeyword(String keyword) {

		return Specification
				//userTyoe == 0のもの(Employee)を抽出
				.where(new Specification<UserEntity>() {
					@Override
					public Predicate toPredicate(
							Root<UserEntity> root,
							CriteriaQuery<?> query,
							CriteriaBuilder cb) {

						return cb.equal(root.get("userType"), 0);
					}
				});
	}
}
