package net.converter;

import javax.persistence.AttributeConverter;

/**
 * DB側でbit型のカラムを使用していれば必要ないかもしれない
 *
 * @author user
 */
public class ShowFlagConverter implements AttributeConverter<Boolean, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Boolean showFlag) {

		int showFlagInDB = 1;

		if (showFlag) {
			showFlagInDB = 0;
		}

		return showFlagInDB;
	}

	@Override
	public Boolean convertToEntityAttribute(Integer showFlagInDB) {

		Boolean showFlag = false;

		if (showFlagInDB == 0) {
			showFlag = true;
		}

		return showFlag;
	}
}
