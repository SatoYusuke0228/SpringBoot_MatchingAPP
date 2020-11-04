package net.converter;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<String, Integer> {

	@Override
	public Integer convertToDatabaseColumn(String gender) {

		int genderInDB = 0;

		if (!gender.equals(null) && !gender.isBlank()) {

			if ("男性".equals(gender)) {
				genderInDB = 1;
			} else if ("女性".equals(gender)) {
				genderInDB = 2;
			} else if ("その他".equals(gender)) {
				genderInDB = 3;
			}

		}

		return genderInDB;
	}

	@Override
	public String convertToEntityAttribute(Integer genderInDB) {

		String gender = new String();

		switch (genderInDB) {

		case (0):
			gender = "不明";
		case (1):
			gender = "男性";
		case (2):
			gender = "女性";
		case (3):
			gender = "その他";
		}

		return gender;
	}
}
