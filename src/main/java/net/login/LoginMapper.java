package net.login;

import org.apache.ibatis.annotations.Mapper;

import net.user.UserEntity;


@Mapper
public interface LoginMapper {

	public UserEntity findAccount(String name);
}
