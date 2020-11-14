package net.common.converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampFormatConverter {

	/**
	 * 引数に設定したTimestamp型の変数を"yyyy/MM/dd HH:mm"のString型に変換して返す
	 *
	 * @param Timestamp t
	 * @return String
	 */
	public String formatTimestampUpToSecond(Timestamp t) {

		String str = null;

		if (t != null && !t.toString().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			str = sdf.format(t);
		}

		return str;
	}

	/**
	 * 引数に設定したTimestamp型の変数を"yyyy/MM/dd"のString型に変換して返す
	 *
	 * @param Timestamp t
	 * @return String
	 */
	public String formatTimestampUpToDate(Timestamp t) {

		String str = null;

		if (t != null && !t.toString().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			str = sdf.format(t);
		}

		return str;
	}
}
