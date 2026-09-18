package cellularuniverse;

public class CARule {

    private boolean[] birth;
    private boolean[] survival;

    public CARule(String rule) {

        birth = new boolean[9];
        survival = new boolean[9];

        parseRule(rule);
    }

    private void parseRule(String rule) {

        rule = rule.toUpperCase().trim();

        String[] parts = rule.split("/");

        if (parts.length != 2) {
            throw new IllegalArgumentException(
                "Rule must look like B3/S23"
            );
        }

        String birthPart = parts[0];
        String survivalPart = parts[1];

        if (!birthPart.startsWith("B") ||
            !survivalPart.startsWith("S")) {

            throw new IllegalArgumentException(
                "Rule must use B/S notation"
            );
        }

        for (int i = 1; i < birthPart.length(); i++) {

            int number =
                Character.getNumericValue(
                    birthPart.charAt(i)
                );

            if (number >= 0 && number <= 8) {
                birth[number] = true;
            }
        }

        for (int i = 1; i < survivalPart.length(); i++) {

            int number =
                Character.getNumericValue(
                    survivalPart.charAt(i)
                );

            if (number >= 0 && number <= 8) {
                survival[number] = true;
            }
        }
    }

    public boolean willBeAlive(
        boolean currentlyAlive,
        int neighbors
    ) {

        if (currentlyAlive) {
            return survival[neighbors];
        }

        return birth[neighbors];
    }
}