package net.user.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegistration {

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
	@Size(min = 1, max = 256, message = "※")
	@NotBlank
	private String mail;

	@Pattern(regexp = "0(60|70|80|90|120|\\d{1,3})\\d{2,4}\\d{3,4}")
	@NotBlank
	private String tell;

	/**
	 * コンストラクタ
	 * 作成時にURLの一部からユーザータイプ名を策定して
	 * そのユーザータイプ名を元にコンストラクタ作成した段階でユーザータイプのみ決定
	 * @param userType
	 */
	public UserRegistration(int userType) {
		this.userType = userType;
	}
}