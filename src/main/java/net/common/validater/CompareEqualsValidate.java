package net.common.validater;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

public class CompareEqualsValidate implements ConstraintValidator<CompareEquals, Object> {

	private String value1;
	private String value2;
	private String message;

	/**
	 * 初期化処理
	 */
	@Override
	public void initialize(CompareEquals annotation) {
		//アノテーションの引数情報を設定する。
		this.value1 = annotation.value1();
		this.value2 = annotation.value2();
		this.message = annotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		// 2つの値を取得して比較する。
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		Object value1 = beanWrapper.getPropertyValue(this.value1);
		Object value2 = beanWrapper.getPropertyValue(this.value2);

		//比較処理
		//同値でも型が違う場合はfalseを返却する。
		boolean matched = ObjectUtils.nullSafeEquals(value1, value2);

		if (matched) {
			return true;
		}

		//ッセージを設定する。
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message)
		.addPropertyNode(this.value1)
		.addConstraintViolation();

		return false;
	}

}