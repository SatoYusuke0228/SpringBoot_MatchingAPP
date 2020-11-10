package net.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.user.UserEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

		UserEntity userEntity = Optional.ofNullable(loginRepository.findUser(mail))
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		if (userEntity == null) {
			System.out.println("User not found.");
		}

		//権限のリスト
		//AdminやUserなどが存在するが、今回は利用しないのでUSERのみを仮で設定
		//権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("USER");
		grantList.add(authority);

		UserDetails userDetails = (UserDetails) new User(userEntity.getMail(),
				encoder.encode(userEntity.getPassword()), grantList);

		return userDetails;
	}

	/**
	 * 認証が通った時にこのユーザに与える権限の範囲を設定する。
	 *
	 * @param entity DBから取得したユーザ情報。
	 * @return 権限の範囲のリスト。
	 */
	//	private Collection<GrantedAuthority> getAuthorities(UserEntity entity) {
	//
	//		String authority = new String();
	//
	//		if (entity.getUserType() == 0) {
	//			authority = "EMPLOYEE";
	//		} else if (entity.getUserType() == 1) {
	//			authority = "EMPLOYER";
	//		}
	//
	//		return AuthorityUtils.createAuthorityList(authority);
	//	}
}
