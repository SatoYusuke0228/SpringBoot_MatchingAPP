package net.login;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.user.UserEntity;
import net.user.UserEntityAndAuthorities;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LoginMapper loginMapper;

	@Override
	@Transactional //(readOnly = true)
	public UserDetails loadUserByUsername(String mailAddress)
			throws UsernameNotFoundException {

		//DBからユーザ情報を取得。
		UserEntity entity = Optional.ofNullable(loginMapper.findAccount(mailAddress))
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		return new UserEntityAndAuthorities(entity, getAuthorities(entity));
	}

	/**
	 * 認証が通った時にこのユーザに与える権限の範囲を設定する。
	 *
	 * @param entity DBから取得したユーザ情報。
	 * @return 権限の範囲のリスト。
	 */
	private Collection<GrantedAuthority> getAuthorities(UserEntity entity) {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
}
