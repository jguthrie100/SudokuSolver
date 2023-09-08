import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class Tile {

    @Getter
    private Integer value;

    @Getter
    private Set<Integer> possibleValues = new HashSet<>();

    public Tile() {
        initPossibleValues();
    }

    public Tile(Integer value) {
        setValue(value);

        // One value in the possibleValues set
        possibleValues.add(value);
    }

    public void setValue(Integer value) {
        validate(value, true);

        this.value = value;

        possibleValues.clear();
        possibleValues.add(value);
    }

    public void addPossibleValue(Integer value) {
        validate(value, false);

        this.possibleValues.add(value);
    }

    public void removePossibleValue(Integer value) {
        validate(value, false);

        this.possibleValues.remove(value);
    }

    private void initPossibleValues() {
        for (int i = 1; i <= 9; i++) {
            this.possibleValues.add(i);
        }
    }

    private void validate(Integer value, boolean nullAllowed) {
        if (nullAllowed) {
            if (value != null && (value < 1 || value > 9)) {
                throw new NumberFormatException("Tile value must be null or 1-9");
            }
        } else {
            if (value == null || (value < 1 || value > 9)) {
                throw new NumberFormatException("Tile value must be 1-9");
            }
        }
    }
}
