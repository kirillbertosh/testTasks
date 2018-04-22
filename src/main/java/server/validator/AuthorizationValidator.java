package server.validator;

public class AuthorizationValidator extends Validator {

    public static boolean validate(String email, String password) {
        if (email == null || password == null) {
            return false;
        }
        if (password(password)) {
            return true;
        } else {
            return false;
        }
    }
}
