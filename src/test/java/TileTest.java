import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TileTest {

    @Test
    public void testTileConstructorSetsValue() {
        Tile tile1 = new Tile(9);

        Assertions.assertEquals(9, tile1.getValue());
    }

    @Test
    public void testTileConstructorThrowsExceptionIfInvalid() {

        Assertions.assertThrows(NumberFormatException.class, () -> {
            new Tile(0);
        });

        Assertions.assertThrows(NumberFormatException.class, () -> {
            new Tile(10);
        });

        for (int i = 1; i <= 9; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> {
                new Tile(finalI);
            });
        }
    }

    @Test
    public void testTileValueIsCorrectlyUpdated() {
        Tile tile1 = new Tile();

        Assertions.assertNull(tile1.getValue());

        tile1.setValue(9);

        Assertions.assertEquals(9, tile1.getValue());
    }
}
