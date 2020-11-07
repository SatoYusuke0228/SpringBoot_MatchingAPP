package net.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {

	private int userType;

	@Size(min = 1)
	@NotBlank
	private String password;

	@Size(min = 1)
	@NotBlank
	private String password_auth;

	@Size(min = 1, max = 32)
	@NotBlank
	private String firstName;

	@Size(min = 1, max = 32)
	@NotBlank
	private String lastName;

	@Size
	@NotBlank
	private String gender;

	@Email
	@Size(min = 1, max = 256)
	@NotBlank
	private String mail;

	@Pattern(regexp = "0(60|70|80|90|\\d{1,3})\\d{4}\\d{4}")
	@NotBlank
	private String tell;

	//登録地域
	//@NotBlank
	//private String registrationArea;

	/**
	 * コンストラクタ
	 * 作成時にURLの一部からユーザータイプ名を策定して
	 * そのユーザータイプ名を元にコンストラクタ作成した段階でユーザータイプのみ決定
	 * @param userType
	 */
	public User(int userType) {
		this.userType = userType;
	}
}