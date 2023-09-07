import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BoardTest {

    Board board = new Board();

    @Test
    public void testBoardIsCorrectSizeAfterInit() {
        // Assert 9 rows
        Assertions.assertEquals(9, board.getBoard().size());

        // Assert 9 cols in each row
        for (int i = 0; i < 9; i++) {
            Assertions.assertEquals(9, board.getBoard().get(i).size());
        }
    }

    @Test
    public void testCannotChangeLengthOfColsRows() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            board.getBoard().add(new ArrayList<>());
        });

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            board.getBoard().remove(0);
        });

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            board.getBoard().get(0).add(new Tile());
        });

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            board.getBoard().get(0).remove(0);
        });
    }

    @Test
    public void testGetValueRowValidation() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.getValue(-1, 3);
        }, "Row number must be 0-8");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.getValue(9, 3);
        }, "Row number must be 0-8");

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> {
                board.getValue(finalI, 3);
            });
        }
    }

    @Test
    public void testGetValueColumnValidation() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.getValue(0, -1);
        }, "Column number must be 0-8");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.getValue(0, 9);
        }, "Column number must be 0-8");

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> {
                board.getValue(3, finalI);
            });
        }
    }

    @Test
    public void testSetValueRowValidation() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.setValue(-1, 3, 9);
        }, "Row number must be 0-8");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.setValue(9, 3, 9);
        }, "Row number must be 0-8");

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> {
                board.setValue(finalI, 3, 9);
            });
        }
    }

    @Test
    public void testSetValueColumnValidation() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.setValue(0, -1, 9);
        }, "Column number must be 0-8");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.setValue(0, 9, 9);
        }, "Column number must be 0-8");

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> {
                board.setValue(3, finalI, 9);
            });
        }
    }

    @Test
    public void testSetValueValidation() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            board.setValue(0, 0, 0);
        }, "Tile value must be null or 1-9");

        Assertions.assertThrows(NumberFormatException.class, () -> {
            board.setValue(0, 0, 10);
        }, "Tile value must be null or 1-9");

        for (int i = 1; i <= 9; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> {
                board.setValue(0, 0, finalI);
            });
        }

        Assertions.assertDoesNotThrow(() -> {
            board.setValue(0, 0, null);
        });
    }

    @Test
    public void testSetValue() {
        board.setValue(3, 3, 3);

        Assertions.assertEquals(3, board.getValue(3, 3));
    }
}
