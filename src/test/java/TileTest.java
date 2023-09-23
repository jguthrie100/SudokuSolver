import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import base.sudoku.entity.Tile;

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

    @Test
    public void testTileStartsOffWith9PossibleValues() {
        Tile tile = new Tile();

        Assertions.assertFalse(tile.getPossibleValues().contains(0));

        for (int i = 1; i <= 9; i++) {
            Assertions.assertTrue(tile.getPossibleValues().contains(i));
        }

        Assertions.assertFalse(tile.getPossibleValues().contains(10));
    }

    @Test
    public void testAddAndRemovePossibleValue() {
        Tile tile = new Tile();

        Assertions.assertTrue(tile.getPossibleValues().contains(1));

        tile.removePossibleValue(1);

        Assertions.assertFalse(tile.getPossibleValues().contains(1));

        tile.addPossibleValue(1);

        Assertions.assertTrue(tile.getPossibleValues().contains(1));
    }
}
