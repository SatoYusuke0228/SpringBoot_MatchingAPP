package net.user.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Table(name = "EMPLOYEE_TABLE")
@Data
@Transactional
public class EmployeeEntity {

	@Id
	@Column(name = "ID", unique = true)
	private long id;

	@Column(name = "BIRTH_YEAR")
	private String birthYear;

	@Column(name = "BIRTH_MONTH")
	private String birthMonth;

	@Column(name = "BIRTH_DAY")
	private String birthDay;

//	@Column(name = "ZIPCODE")
//	private String zipcode;
//
//	@Column(name = "ADDRESS")
//	private String address;
//
//	@Column(name = "BUILDING_NAME")
//	private String buildingName;

	@Column(name = "SCHOOL_TYPE")
	private String schoolType;

	@Column(name = "SCHOOL_NAME")
	private String schoolName;

	@Column(name = "FACULTY")
	private String faculty;

	@Column(name = "DEPARTMEMT")
	private String department;

	@Column(name = "GRADUATE")
	private boolean graduate;

	@Column(name = "GRADUATE_YEAR")
	private String graduateYear;

	@Column(name = "GRADUATE_MONTH")
	private String graduateMonth;

	@Column(name = "SELF_PR")
	private String selfPR;

	public EmployeeEntity() {}

	public EmployeeEntity(long id, Employee studentRegistration) {

		this.id = id;
		this.birthYear = studentRegistration.getBirthYear();
		this.birthMonth = studentRegistration.getBirthMonth();
		this.birthDay = studentRegistration.getBirthDay();
//		this.zipcode = zipcode;
//		this.address = address;
//		this.buildingName = buildingName;
		this.schoolType = studentRegistration.getSchoolType();
		this.schoolName =studentRegistration.getSchoolName();
		this.faculty = studentRegistration.getFaculty();
		this.department = studentRegistration.getDepartment();
		this.graduate = studentRegistration.getGraduate();
		this.graduateYear = studentRegistration.getGraduateYear();
		this.graduateMonth = studentRegistration.getGraduateMonth();
		this.selfPR = studentRegistration.getSelfPR();
	}
}