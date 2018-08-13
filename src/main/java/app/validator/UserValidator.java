package app.validator;


import app.entity.User;
import app.service.UserService;
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
        return User.class.equals(aClass);
    }


    public void validate(Object o, Errors errors) {
        User user = (User) o;
        EmailValidator emailValidator = new EmailValidator();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if (user.getLogin().length() < 4 || user.getLogin().length() > 16) {
            errors.rejectValue("login", "Size.userForm.login");
        }

        if (userService.findUserByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.userForm.login");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 16) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        if (!emailValidator.validate(user.getEmail())){
            errors.rejectValue("email","Invalid.userForm.email");
        }

        if(userService.findUserByEmail(user.getEmail()) != null){
            errors.rejectValue("email","Duplicate.userForm.email");
        }

    }
}
