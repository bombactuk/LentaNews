package portal.management.edu.traning.logic.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationResult {

    private boolean result;
    private StringBuilder exception;

    private ValidationResult(Validator builder) {

        if (builder.getErrors() != null) {

            result = true;
            exception = builder.getErrors();

        }

    }

    public StringBuilder getException() {

        return exception;

    }

    public boolean isResult() {

        return result;

    }

    public static class Validator implements ObjBuilder<ValidationResult> {

        private StringBuilder errors = new StringBuilder();

        private static final Pattern loginPattern = Pattern.compile(("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));

        public Validator validLogin(String login) {

            Matcher loginMatcher = loginPattern.matcher(login);

            if (!loginMatcher.matches()) {
                errors.append("Login not correct.");
            }

            return this;

        }

        private static final Pattern passwordPattern = Pattern.compile("^.{8,}$");

        public Validator validPassword(String password) {

            Matcher passwordMatcher = passwordPattern.matcher(password);

            if (!passwordMatcher.matches()) {

                errors.append("Password not correct.");

            }

            return this;

        }

        private static final Pattern namePattern = Pattern.compile("^[a-zA-Z]{0,20}$");

        public Validator validName(String name) {

            Matcher nameMatcher = namePattern.matcher(name);

            if (!nameMatcher.matches()) {
                errors.append("The name must be no more than 20 characters.");
            }

            return this;

        }

        public Validator validLoginPassword(String login, String password) {

            if (login == null || password == null) {

                errors.append("Fill in all the fields.");

            }

            return this;

        }

        public Validator validLoginPasswordNameCountry(String login, String password, String name, String country) {

            if (login == null || password == null || name == null || country == null) {

                errors.append("Fill in all the fields");

            }

            return this;

        }

        @Override
        public ValidationResult build() {

            return new ValidationResult(this);

        }

        public StringBuilder getErrors() {

            if (errors.length() == 0) {

                return null;

            } else {

                return errors;

            }

        }

    }

}
