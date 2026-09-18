package cellularuniverse;

public class RuleValidator {

    public static boolean isValid(String rule) {

        if (rule == null) {
            return false;
        }

        rule = rule.toUpperCase().trim();

        String[] parts =
                rule.split("/");

        if (parts.length != 2) {
            return false;
        }

        if (!parts[0].startsWith("B")) {
            return false;
        }

        if (!parts[1].startsWith("S")) {
            return false;
        }

        for (int i = 1; i < parts[0].length(); i++) {

            char c = parts[0].charAt(i);

            if (c < '0' || c > '8') {
                return false;
            }
        }

        for (int i = 1; i < parts[1].length(); i++) {

            char c = parts[1].charAt(i);

            if (c < '0' || c > '8') {
                return false;
            }
        }

        return true;
    }
}