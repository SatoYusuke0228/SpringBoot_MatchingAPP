package net.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import net.common.validation.CompareEquals;

@Data
@CompareEquals(message = "再入力エラー", value1 = "reEnterPassword", value2 = "password")
public class User {

	private int userType;

	@NotBlank
	@Size(min = 8 , max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;

	private String reEnterPassword;

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