package net.common;

public class Constant {

	public final static String PAGE_TITLE = ObjectName.pageTitle.getString();
	public final static String MESSAGE = ObjectName.message.getString();

	public enum ObjectName {

		pageTitle("pageTitle"), message("message"),
		userRegistration("userRegistration"), userEntity("userEntity"),
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

		public static final String USER = "/user";
		public static final String REGISTRATION = "/registration";
	}

	public class FileName {

		public static final String SELECT_USER_TYPE = "/select-user-type";
		public static final String FORM = "/form";
		public static final String CONFIRMATION = "/confirmation";
		public static final String RESULT = "/result";
	}
}