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

import lombok.Data;
import net.common.converter.GenderConverter;
import net.user.thumbnail.ThumbnailEntity;

@Entity
@Table(name = "USER_TABLE")
@Data
public class UserEntity {
	//implements UserDetails { //userDetailsのinterfaceを使うかどうか

	@Id
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	// 0 == STUDENT / 1 == ENGINEER / -1 == ADMIN (予定)
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

	//ユーザーアカウントの表示フラグ
	@Column(name = "SHOW_FLAG")
	private boolean showFlag;

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

	@Column(name = "REGISTRATION_AREA")
	private String registrationArea;

	@Column(name = "REGISTRATION_DATE", nullable = false)
	private Timestamp registrationDate;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "THUMBNAIL_TABLE", joinColumns = {
			@JoinColumn(name = "ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ID", referencedColumnName = "ID") })
	private ThumbnailEntity thumbnail;

//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "EMPLOYEE_TABLE", joinColumns = {
//			@JoinColumn(name = "ID", referencedColumnName = "ID") }, inverseJoinColumns = {
//					@JoinColumn(name = "ID", referencedColumnName = "ID") })
//	private EmployeeEntity employee;
//
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "EMPLOYER_TABLE", joinColumns = {
//			@JoinColumn(name = "ID", referencedColumnName = "ID") }, inverseJoinColumns = {
//					@JoinColumn(name = "ID", referencedColumnName = "ID") })
//	private EmployerEntity employer;
//
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "TALK_REQUEST_TABLE", joinColumns = {
//			@JoinColumn(name = "ID", referencedColumnName = "ID") })
//	private List<TalkRequestEntity> talkRequest;
//
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "USER_BLOCK_TABLE",
//	joinColumns = { @JoinColumn(name = "ID", referencedColumnName = "ID") })
//	private List<UserBlockEntity> userBlock;

	/**
	 * コンストラクタ
	 *
	 * @param password
	 * @param userType
	 * @param activation
	 * @param mailVerified
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param telle
	 * @param registrationDate
	 */
	public UserEntity(User user) {

		this.password = user.getPassword();
		this.userType = user.getUserType();
		this.activation = false;
		this.mailVerified = false;
		this.showFlag = false;
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.gender = user.getGender();
		this.tell = user.getTell();
		this.mail = user.getMail();
		this.registrationArea = "テスト地域";
		this.registrationDate = new Timestamp(System.currentTimeMillis());
	}

	public UserEntity() {
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return null;
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