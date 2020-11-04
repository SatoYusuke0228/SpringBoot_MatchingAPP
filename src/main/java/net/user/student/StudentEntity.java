package net.user.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Table(name = "STUDENT")
@Data
@Transactional
public class StudentEntity {

	@Id
	@Column(name = "ID", unique = true)
	private long id;

	@Column(name = "SHOW_FLAG")
	private boolean showFlag;

	//登録地域
	@Column(name = "REGISTRATION_AREA")
	private String registrationArea;

	@Column(name = "BIRTH_YEAR")
	private String birthYear;

	@Column(name = "BIRTH_MONTH")
	private String birthMonth;

	@Column(name = "BIRTH_DAY")
	private String birthDay;

	@Column(name = "ZIPCODE")
	private String zipcode;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "BUILDING_NAME")
	private String buildingName;

	@Column(name = "SCHOOL")
	private String school;

	@Column(name = "FACULTY")
	private String faculty;

	@Column(name = "DEPARTMEMT")
	private String department;

	@Column(name = "GRADUATE_YEAR")
	private String graduateYear;

	@Column(name = "GRADUATE_MONTH")
	private String graduateMonth;

	@Column(name = "SELF_PR")
	private String selfPR;
}
