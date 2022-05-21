package pl.coderslab.wtm.utility;

import org.springframework.stereotype.Component;
import pl.coderslab.wtm.dto.organization.OrganizationUpdateDto;
import pl.coderslab.wtm.dto.user.UserUpdateDto;
import pl.coderslab.wtm.repository.entity.Organization;

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
        return checkUserUpdate();
    }

    public Boolean validateUserUpdate(Boolean dataToValidate, EnumUpdate enumUpdate) {
        this.dataToValidate = dataToValidate;
        this.enumUpdate = enumUpdate;
        return checkUserUpdate();
    }

    public Boolean validateOrganizationUpdate(String dataToValidate) {
        this.dataToValidate = dataToValidate;
        return checkOrganizationUpdate("name");
    }

    public Boolean validateOrganizationUpdate(Boolean dataToValidate) {
        this.dataToValidate = dataToValidate;
        return checkOrganizationUpdate("isActive");
    }

    private Boolean checkUserUpdate() {
        if (dataToValidate == null) {
            return false;
        }
        Set<ConstraintViolation<UserUpdateDto>> violations = validator.validateValue(UserUpdateDto.class, enumUpdate.toString().toLowerCase(), dataToValidate);
        if (violations.isEmpty()) {
            return true;
        }
        return false;
    }

    private Boolean checkOrganizationUpdate(String field) {
        if (dataToValidate == null) {
            return false;
        }
        Set<ConstraintViolation<OrganizationUpdateDto>> violations = validator.validateValue(OrganizationUpdateDto.class, field, dataToValidate);
        if (violations.isEmpty()) {
            return true;
        }
        return false;
    }
}
