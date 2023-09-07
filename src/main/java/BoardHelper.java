import java.util.List;

public class BoardHelper {

    public static boolean rowContains(List<List<Tile>> board, int rowNo, Integer value) {
        if (rowNo < 0 || rowNo > 8 || value < 1 || value > 9) {
            return false;
        }

        return board.get(rowNo).stream().anyMatch(tile -> tile.getValue() == value);
    }

    public static boolean columnContains(List<List<Tile>> board, int columnNo, Integer value) {
        if (columnNo < 0 || columnNo > 8 || value < 1 || value > 9) {
            return false;
        }

        for (int rowI = 0; rowI < 9; rowI++) {
            if (board.get(rowI).get(columnNo).getValue() == value) {
                return true;
            }
        }

        return false;
    }

    /*
      Does the specified 3x3 section contain the given value?

      * sectionRow is which row the section is in (0-2)
      * sectionColumn is which column its in (0-2)
     */
    public static boolean sectionContains(List<List<Tile>> board, int sectionRow, int sectionColumn, Integer value) {
        if (sectionRow < 0 || sectionRow > 2 || sectionColumn < 0 || sectionColumn > 2 || value < 1 || value > 9) {
            return false;
        }

        for (int rowI = sectionRow * 3; rowI < (sectionRow * 3) + 3; rowI++) {
            for (int colJ = sectionColumn * 3; colJ < (sectionColumn * 3) + 3; colJ++) {
                if (board.get(rowI).get(colJ).getValue() == value) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String toString(List<List<Tile>> board) {
        StringBuilder boardOut = new StringBuilder();

        for (List<Tile> row : board) {
            for (Tile tile : row) {
                boardOut.append(tile.getValue() != null ? tile.getValue() + " " : "- ");
            }
            boardOut.append("\n");
        }

        return boardOut.toString();
    }
}
