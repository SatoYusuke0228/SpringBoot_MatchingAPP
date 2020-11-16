package net.common;

/**
 * 定数値をまとめたクラス
 * @author SatoYusuke0228
 */
public class Constant {

	public final static String PAGE_TITLE = ObjectName.PAGE_TITLE.getString();
	public final static String MESSAGE = ObjectName.MESSAGE.getString();
	public final static String REDIRECT = ObjectName.REDIRECT.getString();
	public final static String USER_TYPE = ObjectName.USER_TYPE.getString();

	public static enum ObjectName {

		PAGE_TITLE("pageTitle"), MESSAGE("message"),
		REDIRECT("redirect:"), USER_TYPE("userType"),

		USER("user"), USER_ENTITY("userEntity"),
		EMPLOYEE("employee"), EMPLOYEE_ENTITY("employeeEntity"),
		EMPLOYER("employer"), EMPLOYER_ENTITY("employerEntity"),
		;

		private final String text;

		private ObjectName(final String text) {
			this.text = text;
		}

		public String getString() {
			return this.text;
		}
	}

	public class FolderName {

		public static final String SRC = "/src";
		public static final String MAIN = "/main";
		public static final String RESOURCES = "/resoucers";

		public static final String USER = "/user";
		public static final String STUDENT = "/student";
		public static final String ENGINEER = "/engineer";
		public static final String REGISTRATION = "/registration";
	}

	public class FileName {

		public static final String SELECT_USER_TYPE = "/select-user-type";
		public static final String FORM = "/form";
		public static final String CONFIRMATION = "/confirmation";
		public static final String RESULT = "/result";
	}

	public class RoleName {
		public static final String ROLE = "ROLE_";
		public static final String ADMIN = "ADMIN";
		public static final String EMPLOYEE = "EMPLOYEE";
		public static final String EMPLOYER = "EMPLOYER";

	}
}