import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import base.sudoku.entity.Board;
import base.sudoku.entity.Tile;

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
    public void testGetTileRowValidation() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.getTile(-1, 3);
        }, "Row number must be 0-8");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.getTile(9, 3);
        }, "Row number must be 0-8");

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> {
                board.getTile(finalI, 3);
            });
        }
    }

    @Test
    public void testGetTileColumnValidation() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.getTile(0, -1);
        }, "Column number must be 0-8");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            board.getTile(0, 9);
        }, "Column number must be 0-8");

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            Assertions.assertDoesNotThrow(() -> {
                board.getTile(3, finalI);
            });
        }
    }

    @Test
    public void testGetTile() {
        Assertions.assertNull(board.getTile(0, 0).getValue());

        board.getTile(0, 0).setValue(9);

        Assertions.assertEquals(9, board.getTile(0, 0).getValue());
    }
}
