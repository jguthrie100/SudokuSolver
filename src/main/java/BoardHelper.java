import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static Set<Integer> rowValues(List<List<Tile>> board, int rowNo) {
        if (rowNo < 0 || rowNo > 8) {
            throw new IllegalArgumentException("Row number must be 0-8");
        }

        return board.get(rowNo).stream().filter(tile -> tile.getValue() != null).map(tile -> tile.getValue()).collect(Collectors.toSet());
    }

    public static Set<Integer> columnValues(List<List<Tile>> board, int columnNo) {
        if (columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Column number must be 0-8");
        }

        return board.stream().map(row -> row.get(columnNo)).filter(tile -> tile.getValue() != null).map(tile -> tile.getValue()).collect(Collectors.toSet());
    }

    public static Set<Integer> sectionValues(List<List<Tile>> board, int sectionRow, int sectionColumn) {
        if (sectionRow < 0 || sectionRow > 8 || sectionColumn < 0 || sectionColumn > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Set<Integer> output = new HashSet<>();

        for (int rowI = sectionRow * 3; rowI < (sectionRow * 3) + 3; rowI++) {
            for (int colJ = sectionColumn * 3; colJ < (sectionColumn * 3) + 3; colJ++) {
                output.add(board.get(rowI).get(colJ).getValue());
            }
        }

        output.remove(null);
        return output;
    }

    /*
      Checks the row/column/3x3 section for any values that are not possible to use in any other cell in the row/column/3x3 section
     */
    public static Set<Integer> exclusivePossibleValues(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Set<Integer> exclusiveRowPossibleValues = exclusiveRowPossibleValues(board, rowNo, columnNo);
        if (exclusiveRowPossibleValues.size() == 1) {
            return exclusiveRowPossibleValues;
        }

        Set<Integer> exclusiveColumnPossibleValues = exclusiveColumnPossibleValues(board, rowNo, columnNo);
        if (exclusiveColumnPossibleValues.size() == 1) {
            return exclusiveColumnPossibleValues;
        }

        Set<Integer> exclusiveSectionPossibleValues = exclusiveSectionPossibleValues(board, rowNo, columnNo);
        if (exclusiveSectionPossibleValues.size() == 1) {
            return exclusiveSectionPossibleValues;
        }

        return new HashSet<>();
    }

    private static Set<Integer> exclusiveRowPossibleValues(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Set<Integer> output = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // Collect all non-possible values from other cells in the row
        IntStream
                .range(0, board.size())
                .filter(colI -> colI != columnNo)
                .forEach(colI -> {
                    output.removeAll(board.get(rowNo).get(colI).getPossibleValues());
                });

        return output;
    }

    private static Set<Integer> exclusiveColumnPossibleValues(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Set<Integer> output = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // Collect all non-possible values from other cells in the column
        IntStream
                .range(0, board.size())
                .filter(rowI -> rowI != rowNo)
                .forEach(rowI -> {
                    output.removeAll(board.get(rowI).get(columnNo).getPossibleValues());
                });

        return output;
    }

    private static Set<Integer> exclusiveSectionPossibleValues(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Set<Integer> output = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // Collect all non-possible values from other cells in the section
        for (int rowI = (rowNo / 3) * 3; rowI < (rowNo / 3) * 3 + 3; rowI++) {
            for (int colJ = (columnNo / 3) * 3; colJ < (columnNo / 3) * 3 + 3; colJ++) {
                if (rowI == rowNo && colJ == columnNo) continue;

                output.removeAll(board.get(rowI).get(colJ).getPossibleValues());
            }
        }

        return output;
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
