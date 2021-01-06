package net.common.constant;

/**
 * 定数値をまとめたクラス
 * @author SatoYusuke0228
 */
public class Constant {

	public final static String APP_NAME = "SpringBoot_MatchingAPP";
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

		public static final String SRC = "src";
		public static final String MAIN = "/main";
		public static final String RESOURCES = "/resources";

		public static final String STATIC = "/static";
		public static final String IMAGE = "/image";
		public static final String CSS = "/css";
		public static final String JS = "/js";

		public static final String USER = "/user";
		public static final String REGISTRATION = "/registration";
		public static final String LOGIN = "/login";
		public static final String CONTENTS = "/contents";
		public static final String EMPLOYEE = "/employee";
		public static final String EMPLOYER = "/employer";
	}

	public class FileName {

		public static final String THUMBNAIL = "/thumbnail.jpg";

		public static final String INDEX = "/index";
		public static final String SELECT_USER_TYPE = "/select-user-type";
		public static final String FORM = "/form";
		public static final String CONFIRMATION = "/confirmation";
		public static final String RESULT = "/result";
		public static final String LOGIN_SUCCESS = "/login-success";
		public static final String LOGIN = "/login";
		public static final String USER_SEARCH = "/user-search";
	}

	public class RoleName {
		public static final String ROLE = "ROLE_";
		public static final String ADMIN = "ADMIN";
		public static final String EMPLOYEE = "EMPLOYEE";
		public static final String EMPLOYER = "EMPLOYER";

	}
}