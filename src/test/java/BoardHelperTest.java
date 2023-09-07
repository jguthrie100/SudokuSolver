import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BoardHelperTest {

    static Board board = new Board();

    @BeforeAll
    protected static void setUp() {
        /*
            9 - - - 2 - - - -
            - - - - - - - 1 -
            - - - - - - - - -
            1 - - - - - - - -
            - 9 - - - - - - -
            - - - - 1 - - - 9
            - - 3 - - - - - -
            - - - - - - - - -
            - - - - - - - 8 -
         */

        board.getTile(0, 0).setValue(9);
        board.getTile(0, 4).setValue(2);
        board.getTile(1, 7).setValue(1);
        board.getTile(3, 0).setValue(1);
        board.getTile(4, 1).setValue(9);
        board.getTile(5, 4).setValue(1);
        board.getTile(5, 8).setValue(9);
        board.getTile(6, 2).setValue(3);
        board.getTile(8, 7).setValue(8);

    }

    @Test
    public void testRowContains() {
        Assertions.assertTrue(BoardHelper.rowContains(board.getBoard(), 0, 9));
        Assertions.assertTrue(BoardHelper.rowContains(board.getBoard(), 0, 2));
        Assertions.assertTrue(BoardHelper.rowContains(board.getBoard(), 5, 1));
        Assertions.assertTrue(BoardHelper.rowContains(board.getBoard(), 5, 9));
        Assertions.assertTrue(BoardHelper.rowContains(board.getBoard(), 8, 8));

        Assertions.assertFalse(BoardHelper.rowContains(board.getBoard(), -1, 9));
        Assertions.assertFalse(BoardHelper.rowContains(board.getBoard(), 9, 8));
        Assertions.assertFalse(BoardHelper.rowContains(board.getBoard(), 0, 0));
        Assertions.assertFalse(BoardHelper.rowContains(board.getBoard(), 0, 10));

        Assertions.assertFalse(BoardHelper.rowContains(board.getBoard(), 0, 8));
        Assertions.assertFalse(BoardHelper.rowContains(board.getBoard(), 5, 8));
        Assertions.assertFalse(BoardHelper.rowContains(board.getBoard(), 7, 8));
    }

    @Test
    public void testColumnContains() {
        Assertions.assertTrue(BoardHelper.columnContains(board.getBoard(), 0, 9));
        Assertions.assertTrue(BoardHelper.columnContains(board.getBoard(), 0, 1));
        Assertions.assertTrue(BoardHelper.columnContains(board.getBoard(), 7, 1));
        Assertions.assertTrue(BoardHelper.columnContains(board.getBoard(), 7, 8));
        Assertions.assertTrue(BoardHelper.columnContains(board.getBoard(), 8, 9));

        Assertions.assertFalse(BoardHelper.columnContains(board.getBoard(), -1, 9));
        Assertions.assertFalse(BoardHelper.columnContains(board.getBoard(), 9, 9));
        Assertions.assertFalse(BoardHelper.columnContains(board.getBoard(), 0, 0));
        Assertions.assertFalse(BoardHelper.columnContains(board.getBoard(), 0, 10));

        Assertions.assertFalse(BoardHelper.columnContains(board.getBoard(), 0, 8));
        Assertions.assertFalse(BoardHelper.columnContains(board.getBoard(), 8, 1));
    }

    @Test
    public void testSectionContains() {
        Assertions.assertTrue(BoardHelper.sectionContains(board.getBoard(), 0, 0, 9));
        Assertions.assertTrue(BoardHelper.sectionContains(board.getBoard(), 0, 1, 2));
        Assertions.assertTrue(BoardHelper.sectionContains(board.getBoard(), 0, 2, 1));
        Assertions.assertTrue(BoardHelper.sectionContains(board.getBoard(), 1, 0, 1));
        Assertions.assertTrue(BoardHelper.sectionContains(board.getBoard(), 1, 0, 9));
        Assertions.assertTrue(BoardHelper.sectionContains(board.getBoard(), 2, 0, 3));
        Assertions.assertTrue(BoardHelper.sectionContains(board.getBoard(), 2, 2, 8));

        Assertions.assertFalse(BoardHelper.sectionContains(board.getBoard(), -1, 0, 9));
        Assertions.assertFalse(BoardHelper.sectionContains(board.getBoard(), 3, 0, 1));
        Assertions.assertFalse(BoardHelper.sectionContains(board.getBoard(), 0, -1, 9));

        Assertions.assertFalse(BoardHelper.sectionContains(board.getBoard(), 0, 0, 1));
        Assertions.assertFalse(BoardHelper.sectionContains(board.getBoard(), 0, 2, 2));
    }
}
