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


        if (userService.getByLogin(userDTO.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.userForm.login");
        }
        if (userService.getByEmail(userDTO.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        if (!userDTO.getConfirmPassword().equals(userDTO.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(userDTO.getEmail())) {
            errors.rejectValue("email", "Invalid.userForm.email");
        }

    }
}
