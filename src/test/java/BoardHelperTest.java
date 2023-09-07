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

        board.setValue(0, 0, 9);
        board.setValue(0, 4, 2);
        board.setValue(1, 7, 1);
        board.setValue(3, 0, 1);
        board.setValue(4, 1, 9);
        board.setValue(5, 4, 1);
        board.setValue(5, 8, 9);
        board.setValue(6, 2, 3);
        board.setValue(8, 7, 8);

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
