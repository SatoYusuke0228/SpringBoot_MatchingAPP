package net.user;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;
import net.common.GenderConverter;
import net.user.thumbnail.ThumbnailEntity;

@Entity
@Table(name = "USER_TABLE")
@Data
@Transactional
public class UserEntity {
	//implements UserDetails { //userDetailsのinterfaceを使うかどうか

	@Id
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	//0 == STUDENT / 1 == ENGINEER / -1 == ADMIN (予定)
	@Column(name = "USER_TYPE")
	private int userType;

	//アカウントのアクティベーションフラグ１
	//特に何に使うか未定
	@Column(name = "ACTIVATION")
	private boolean activation;

	//アカウントのアクティベーションフラグ２
	//会員登録時に使用する
	@Column(name = "MAIL_VERIFIED")
	private boolean mailVerified;

	//@Column(name = "SHOW_FLAG")
	//private boolean showFlag;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "GENDER", nullable = false)
	@Convert(converter = GenderConverter.class)
	private String gender;

	@Column(name = "TELL", nullable = false)
	private String tell;

	@Column(name = "MAIL", nullable = false, unique = true)
	private String mail;

	//登録地域
	//@Column(name = "REGISTRATION_AREA")
	//private String registrationArea;

	@Column(name = "REGISTRATION_DATE", nullable = false)
	private Timestamp registrationDate;

	/**
	 * @param password
	 * @param userType
	 * @param activation
	 * @param mailVerified
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param tell
	 * @param mail
	 * @param registrationDate
	 */
	public UserEntity(User userRegistration) {

		if (userRegistration.getPassword().equals(userRegistration.getPassword_auth())) {
			this.password = userRegistration.getPassword();
		}

		this.userType = userRegistration.getUserType();
		this.activation = true;
		this.mailVerified = false;
//		this.showFlag = false;
		this.firstName = userRegistration.getFirstName();
		this.lastName = userRegistration.getLastName();
		this.gender = userRegistration.getGender();
		this.tell = userRegistration.getTell();
		this.mail = userRegistration.getMail();
//		this.registrationArea = userRegistration.getRegistrationArea();
		this.registrationDate = new Timestamp(System.currentTimeMillis());
	}

	public UserEntity() {
		super();
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "THUMBNAIL_TABLE", joinColumns = {
			@JoinColumn(name = "ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ID", referencedColumnName = "ID") })
	private ThumbnailEntity thumbnailEntity;

	//	@Override
	//	public Collection<? extends GrantedAuthority> getAuthorities() {
	//		return ;
	//	}
	//
	//	@Override
	//	public String getUsername() {
	//		return this.firstName + this.lastName;
	//	}
	//
	//	@Override
	//	public boolean isAccountNonExpired() { //アカウントの有効期限が切れていない
	//		return false;
	//	}
	//
	//	@Override
	//	public boolean isAccountNonLocked() { //アカウントがロックされていない
	//		return false;
	//	}
	//
	//	@Override
	//	public boolean isCredentialsNonExpired() { //資格情報の有効期限が切れていない
	//		return false;
	//	}
	//
	//	@Override
	//	public boolean isEnabled() { //有効
	//		return this.activation;
	//	}
}