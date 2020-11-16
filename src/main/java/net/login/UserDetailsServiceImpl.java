package net.login;

import static net.common.Constant.RoleName.*;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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
	HttpSession session;

	@Autowired
	PasswordEncoder encoder;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

		UserEntity userEntity = Optional.ofNullable(loginRepository.findUser(mail))
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		session.setAttribute("userEntity", userEntity);

		UserDetails userDetails = (UserDetails) new User(
				userEntity.getMail(),
				userEntity.getPassword(),
				getAuthorities(userEntity));

		return userDetails;
	}

	/**
	 * 認証が通った時にこのユーザに与える権限の範囲を設定する。
	 *
	 * @param entity DBから取得したユーザ情報。
	 * @return 権限の範囲のリスト。
	 */
	private Collection<GrantedAuthority> getAuthorities(UserEntity entity) {

		String authority = ROLE;

		switch (entity.getUserType()) {

		case (-1):
			authority += ADMIN;
			break;
		case (0):
			authority += EMPLOYEE;
			break;
		case (1):
			authority += EMPLOYER;
			break;
		}

		return AuthorityUtils.createAuthorityList(authority);
	}
}
