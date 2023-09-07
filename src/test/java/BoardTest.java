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
    public void assertTileValuesCanBeUpdated() {
        board.getBoard().get(0).get(0).setValue(9);

        Assertions.assertEquals(9, board.getBoard().get(0).get(0).getValue());
    }
}
