package net.user.employee;

import lombok.Data;

@Data
public class Employee {

	private String birthYear;

	private String birthMonth;

	private String birthDay;

//	@NotBlank
//	private String zipcode;
//	@NotBlank
//	private String address;
//	@NotBlank
//	private String buildingName;

	private int schoolType;

	private String schoolName;

	private String faculty;

	private String department;

	private boolean graduate;

	private String graduateYear;

	private String graduateMonth;

	private String selfPR;

	/**
	 * 学校を卒業したか否かのフラグ
	 * @return 卒業Flag
	 */
	public boolean isGraduate() {
		return this.graduate;
	}

	public Employee() {

	}


	public Employee(String hoge) {

		this.birthYear = hoge + "年生まれ";
		this.birthMonth = hoge + "月生まれ";
		this.birthDay = hoge + "日生まれ";
		this.schoolType = 0;
		this.schoolName = hoge + "大学";
		this.faculty = hoge + "学部";
		this.department = hoge + "学科";
		this.graduate = true;
		this.graduateYear = hoge + "年卒業";
		this.graduateMonth = hoge + "月卒業";
		this.selfPR = hoge + hoge + hoge + hoge;
	}
}