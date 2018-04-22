package server.validator;

public class Validator {

    public static boolean name(String name) {
        char buf[] = name.toCharArray();
        if (buf.length < 2 || buf.length > 45)
            return false;
        for (char b : buf) {
            if (b < 'A' || b > 'Z')
                if (b < 'a' || b > 'z')
                    if (b < 'А' || b > 'Я')
                        if (b < 'а' || b > 'я')
                            return false;
        }
        return true;
    }

    public static boolean password(String password) {
        char buf[] = password.toCharArray();
        if (buf.length < 5 || buf.length > 45)
            return false;
        for (char b : buf) {
            if (b < 'A' || b > 'Z')
                if (b < 'a' || b > 'z')
                    if (b < '0' || b > '9')
                        if (b != '_' && b != '-' && b != '%' && b != '*' && b != '(' && b != ')' && b != '?' && b != '@' && b != '#' && b != '$' && b != '~')
                            return false;
        }
        return true;
    }
}
