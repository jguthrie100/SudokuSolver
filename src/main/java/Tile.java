import lombok.Getter;

public class Tile {

    @Getter
    Integer value;

    public Tile() {

    }

    public Tile(Integer value) {
        validate(value);

        setValue(value);
    }

    public void setValue(Integer value) {
        validate(value);

        this.value = value;
    }

    private void validate(Integer value) {
        if (value != null && (value < 1 || value > 9)) {
            throw new NumberFormatException("Tile value must be null or 1-9");
        }
    }
}
