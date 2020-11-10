package net.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserEntityAndAuthorities
		extends org.springframework.security.core.userdetails.User {

	//ユーザ情報。
	private final UserEntity userEntity;

	public UserEntityAndAuthorities(
			UserEntity userEntity,
			Collection<GrantedAuthority> authorities) {

		super((userEntity.getFirstName() + userEntity.getLastName()),
				userEntity.getPassword(),
				true, true, true, true, authorities);

		this.userEntity = userEntity;
	}

	public UserEntity getAccount() {
		return userEntity;
	}

}