package changePwd;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePwdCommandValidator implements Validator {
	//ChangePwdCommand클래스와 폼에 입력된 값이 올바른지 검사하기 위한 클래스
	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePwdCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
	}

}
