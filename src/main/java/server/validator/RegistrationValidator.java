package server.validator;

public class RegistrationValidator extends Validator {

    public static boolean validate(String email, String password, String firstName, String lastName) {
        if (email == null || password == null || firstName == null || lastName == null)
            return false;
        if (password(password) && name(firstName) && name(lastName))
            return true;
        else
            return false;
    }
}
