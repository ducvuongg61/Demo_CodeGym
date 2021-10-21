package codegym.validator;

import codegym.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AgeCustomValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (user.getAge() < 18 || user.getAge() > 65) {
//            ValidationUtils.rejectIfEmpty(errors, "age", "age.numberLessThan18");
            errors.rejectValue("age", "age.numberLessThan18", new Object[]{18, 65}, "Tuổi lớn hơn 18");
        }
    }
}
