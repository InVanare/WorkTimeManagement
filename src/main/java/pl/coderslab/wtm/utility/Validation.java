package pl.coderslab.wtm.utility;

import org.springframework.stereotype.Component;
import pl.coderslab.wtm.dto.user.UserUpdateDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class Validation {
    private final Validator validator;
    private Object dataToValidate;
    private EnumUpdate enumUpdate;

    public Validation(Validator validator) {
        this.validator = validator;
    }

    public Boolean validateUserUpdate(String dataToValidate, EnumUpdate enumUpdate) {
        this.dataToValidate = dataToValidate;
        this.enumUpdate = enumUpdate;
        return check();

    }

    public Boolean validateUserUpdate(Boolean dataToValidate, EnumUpdate enumUpdate) {
        this.dataToValidate = dataToValidate;
        this.enumUpdate = enumUpdate;
        return check();

    }

    private Boolean check() {
        if (dataToValidate == null) {
            return false;
        }
        Set<ConstraintViolation<UserUpdateDto>> violations = validator.validateValue(UserUpdateDto.class, enumUpdate.toString().toLowerCase(), dataToValidate);
        if (violations.isEmpty()) {
            return true;
        }
        return false;
    }
}
