package net.user.employee;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Employee {

	@NotBlank
	private String birthYear;

	@NotBlank
	private String birthMonth;

	@NotBlank
	private String birthDay;

//	@NotBlank
//	private String zipcode;
//	@NotBlank
//	private String address;
//	@NotBlank
//	private String buildingName;

	private String schoolType;

	private String schoolName;

	private String faculty;

	private String department;

	private boolean graduate;

	private String graduateYear;

	private String graduateMonth;

	private String selfPR;

	public Employee() {

	}

	/**
	 * 学校を卒業したか否かのフラグ
	 * @return 卒業Flag
	 */
	public boolean getGraduate() {
		return this.graduate;
	}
}
