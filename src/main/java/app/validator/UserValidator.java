package app.validator;


import app.dto.UserDTO;
import app.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surName", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "Required");


        if (userService.findUserByLogin(userDTO.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.userForm.login");
        }
        if (userService.findUserByEmail(userDTO.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }
        if (userDTO.getLogin().length() < 4 || userDTO.getLogin().length() > 16) {
            errors.rejectValue("login", "Size.userForm.login");
        }

        if (userDTO.getPassword().length() < 4 || userDTO.getPassword().length() > 16) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userDTO.getConfirmPassword().equals(userDTO.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(userDTO.getEmail())) {
            errors.rejectValue("email", "Invalid.userForm.email");
        }

        if (userDTO.getPhoneNumber().length() != 10) {
            errors.rejectValue("phoneNumber", "Invalid.userForm.phoneNumber");
        }

    }
}
