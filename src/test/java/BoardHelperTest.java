import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

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
            - - - - - - 9 8 -
         */

        board.getTile(0, 0).setValue(9);
        board.getTile(0, 0).getPossibleValues().retainAll(Set.of(9));

        board.getTile(0, 4).setValue(2);
        board.getTile(0, 4).getPossibleValues().retainAll(Set.of(2));

        board.getTile(1, 7).setValue(1);
        board.getTile(1, 7).getPossibleValues().retainAll(Set.of(1));

        board.getTile(3, 0).setValue(1);
        board.getTile(3, 0).getPossibleValues().retainAll(Set.of(1));

        board.getTile(4, 1).setValue(9);
        board.getTile(4, 1).getPossibleValues().retainAll(Set.of(9));

        board.getTile(5, 4).setValue(1);
        board.getTile(5, 4).getPossibleValues().retainAll(Set.of(1));

        board.getTile(5, 8).setValue(9);
        board.getTile(5, 8).getPossibleValues().retainAll(Set.of(9));

        board.getTile(6, 2).setValue(3);
        board.getTile(6, 2).getPossibleValues().retainAll(Set.of(3));

        board.getTile(8, 6).setValue(9);
        board.getTile(8, 6).getPossibleValues().retainAll(Set.of(9));

        board.getTile(8, 7).setValue(8);
        board.getTile(8, 7).getPossibleValues().retainAll(Set.of(8));

        // Test relevant possibleValues for row 7, col 2
        // Column values
        board.getTile(0, 2).getPossibleValues().removeAll(Set.of(2, 3, 9));
        board.getTile(1, 2).getPossibleValues().removeAll(Set.of(1, 3, 9));
        board.getTile(2, 2).getPossibleValues().removeAll(Set.of(3, 9));
        board.getTile(3, 2).getPossibleValues().removeAll(Set.of(1, 3, 9));
        board.getTile(4, 2).getPossibleValues().removeAll(Set.of(1, 3, 9));
        board.getTile(5, 2).getPossibleValues().removeAll(Set.of(1, 3, 9));
        board.getTile(8, 2).getPossibleValues().removeAll(Set.of(3, 8, 9));

        // Row values
        board.getTile(7, 0).getPossibleValues().removeAll(Set.of(1, 3, 9));
        board.getTile(7, 1).getPossibleValues().removeAll(Set.of(3, 9));
        board.getTile(7, 3).getPossibleValues().removeAll(Set.of());
        board.getTile(7, 4).getPossibleValues().removeAll(Set.of(1, 2));
        board.getTile(7, 5).getPossibleValues().removeAll(Set.of());
        board.getTile(7, 6).getPossibleValues().removeAll(Set.of(8, 9));
        board.getTile(7, 7).getPossibleValues().removeAll(Set.of(1, 8, 9));
        board.getTile(7, 8).getPossibleValues().removeAll(Set.of(8, 9));

        // Section values
        board.getTile(6, 0).getPossibleValues().removeAll(Set.of(1, 3, 9));
        board.getTile(6, 1).getPossibleValues().removeAll(Set.of(3, 9));
        board.getTile(7, 0).getPossibleValues().removeAll(Set.of(1, 3, 9));
        board.getTile(7, 1).getPossibleValues().removeAll(Set.of(3, 9));
        board.getTile(8, 0).getPossibleValues().removeAll(Set.of(1, 3, 8, 9));
        board.getTile(8, 1).getPossibleValues().removeAll(Set.of(3, 8, 9));
        board.getTile(8, 2).getPossibleValues().removeAll(Set.of(3, 8, 9));

        // Add possible values in same row & column of a section
        // 6 only exists as a possible value in one row of the last section
        board.getTile(6, 6).getPossibleValues().removeAll(Set.of(6));
        board.getTile(6, 7).getPossibleValues().retainAll(Set.of(6));
        board.getTile(6, 8).getPossibleValues().retainAll(Set.of(6));
        board.getTile(7, 6).getPossibleValues().removeAll(Set.of(6));
        board.getTile(7, 7).getPossibleValues().removeAll(Set.of(6));
        board.getTile(7, 8).getPossibleValues().removeAll(Set.of(6));
        board.getTile(8, 6).getPossibleValues().removeAll(Set.of(6));
        board.getTile(8, 7).getPossibleValues().removeAll(Set.of(6));
        board.getTile(8, 8).getPossibleValues().removeAll(Set.of(6));

        board.getTile(3, 3).getPossibleValues().removeAll(Set.of(5));
        board.getTile(4, 3).getPossibleValues().removeAll(Set.of(5));
        board.getTile(5, 3).getPossibleValues().removeAll(Set.of(5));
        board.getTile(3, 4).getPossibleValues().retainAll(Set.of(5));
        board.getTile(4, 4).getPossibleValues().retainAll(Set.of(5));
        board.getTile(5, 4).getPossibleValues().retainAll(Set.of(5));
        board.getTile(3, 5).getPossibleValues().removeAll(Set.of(5));
        board.getTile(4, 5).getPossibleValues().removeAll(Set.of(5));
        board.getTile(5, 5).getPossibleValues().removeAll(Set.of(5));

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

    @Test
    public void testGetRowValues() {
        Assertions.assertEquals(Set.of(2, 9), BoardHelper.rowValues(board.getBoard(), 0));
        Assertions.assertEquals(Set.of(), BoardHelper.rowValues(board.getBoard(), 2));
        Assertions.assertEquals(Set.of(1), BoardHelper.rowValues(board.getBoard(), 3));
        Assertions.assertEquals(Set.of(1, 9), BoardHelper.rowValues(board.getBoard(), 5));
        Assertions.assertEquals(Set.of(8, 9), BoardHelper.rowValues(board.getBoard(), 8));
    }

    @Test
    public void testGetColumnValues() {
        Assertions.assertEquals(Set.of(1, 9), BoardHelper.columnValues(board.getBoard(), 0));
        Assertions.assertEquals(Set.of(3), BoardHelper.columnValues(board.getBoard(), 2));
        Assertions.assertEquals(Set.of(), BoardHelper.columnValues(board.getBoard(), 3));
        Assertions.assertEquals(Set.of(1, 2), BoardHelper.columnValues(board.getBoard(), 4));
        Assertions.assertEquals(Set.of(9), BoardHelper.columnValues(board.getBoard(), 8));
    }

    @Test
    public void testGetSectionValues() {
        Assertions.assertEquals(Set.of(9), BoardHelper.sectionValues(board.getBoard(), 0, 0));
        Assertions.assertEquals(Set.of(1), BoardHelper.sectionValues(board.getBoard(), 0, 2));
        Assertions.assertEquals(Set.of(1, 9), BoardHelper.sectionValues(board.getBoard(), 1, 0));
        Assertions.assertEquals(Set.of(3), BoardHelper.sectionValues(board.getBoard(), 2, 0));
        Assertions.assertEquals(Set.of(), BoardHelper.sectionValues(board.getBoard(), 2, 1));
        Assertions.assertEquals(Set.of(8, 9), BoardHelper.sectionValues(board.getBoard(), 2, 2));
    }

    @Test
    public void textExclusivePossibleValues() {
        Assertions.assertEquals(Set.of(9), BoardHelper.exclusivePossibleValues(board.getBoard(), 7, 2));
    }

    @Test
    public void testDetermineNonPossibleValuesInRowOrColumn() {
        Assertions.assertEquals(Set.of(6), BoardHelper.determineNonPossibleValuesInRowOrColumn(board.getBoard(), 6, 5));
        Assertions.assertEquals(Set.of(5), BoardHelper.determineNonPossibleValuesInRowOrColumn(board.getBoard(), 8, 4));
    }

    @Test
    public void testFilterMatchingPossibleValuesFromOtherTiles() {
        Board board = new Board();
        board.getTile(0, 0).getPossibleValues().retainAll(Set.of(1, 2));
        board.getTile(0, 1).getPossibleValues().retainAll(Set.of(1, 2, 3, 4));
        board.getTile(0, 2).getPossibleValues().removeAll(Set.of(1, 2));
        board.getTile(1, 0).getPossibleValues().removeAll(Set.of(1, 2));
        board.getTile(1, 1).getPossibleValues().removeAll(Set.of(1, 2));
        board.getTile(1, 2).getPossibleValues().removeAll(Set.of(1, 2));
        board.getTile(2, 0).getPossibleValues().removeAll(Set.of(1, 2));
        board.getTile(2, 1).getPossibleValues().removeAll(Set.of(1, 2));
        board.getTile(2, 2).getPossibleValues().removeAll(Set.of(1, 2));

        BoardHelper.filterMatchingPossibleValuesFromOtherTiles(board.getBoard(), 0, 0);

        Assertions.assertTrue(board.getTile(0, 1).getPossibleValues().equals(Set.of(1, 2)));

        board = new Board();
        board.getTile(0, 0).getPossibleValues().retainAll(Set.of(1, 2, 3));
        board.getTile(0, 1).getPossibleValues().removeAll(Set.of(1, 2, 3, 4, 5));
        board.getTile(0, 2).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(1, 0).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(1, 1).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(1, 2).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(2, 0).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(2, 1).getPossibleValues().retainAll(Set.of(1, 2, 3, 4, 5));
        board.getTile(2, 2).getPossibleValues().retainAll(Set.of(1, 2, 3));

        BoardHelper.filterMatchingPossibleValuesFromOtherTiles(board.getBoard(), 2, 2);

        Assertions.assertTrue(board.getTile(2, 1).getPossibleValues().equals(Set.of(1, 2, 3)));

        board = new Board();
        board.getTile(0, 0).getPossibleValues().retainAll(Set.of(1, 2, 3));
        board.getTile(0, 1).getPossibleValues().removeAll(Set.of(1, 2, 3, 4, 5));
        board.getTile(0, 2).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(1, 0).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(1, 1).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(1, 2).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(2, 0).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(2, 1).getPossibleValues().removeAll(Set.of(1, 2, 3));
        board.getTile(2, 2).getPossibleValues().retainAll(Set.of(1, 2, 3, 4, 5));

        BoardHelper.filterMatchingPossibleValuesFromOtherTiles(board.getBoard(), 0, 0);

        Assertions.assertTrue(board.getTile(2, 2).getPossibleValues().equals(Set.of(1, 2, 3, 4, 5)));
    }

    @Test
    public void testRemoveNonPossibleSectionValues() {
        Board board = new Board();

        for (int i = 0; i <= 6; i++) {
            board.getTile(i, 0).setValue(i+1);
        }

        BoardHelper.removeNonPossibleSectionValues(board.getBoard(), 7, 0);

        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6), board.getTile(6, 1).getPossibleValues());
        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6), board.getTile(6, 2).getPossibleValues());
        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6), board.getTile(7, 1).getPossibleValues());
        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6), board.getTile(7, 2).getPossibleValues());
        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6), board.getTile(8, 1).getPossibleValues());
        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6), board.getTile(8, 2).getPossibleValues());

        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9), board.getTile(5, 3).getPossibleValues());
        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9), board.getTile(6, 3).getPossibleValues());
        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9), board.getTile(7, 3).getPossibleValues());
        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9), board.getTile(8, 3).getPossibleValues());
    }

}
