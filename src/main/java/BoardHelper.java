import java.util.*;
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

        return board.get(rowNo).stream().filter(tile -> tile.getValue() != null).map(Tile::getValue).collect(Collectors.toSet());
    }

    public static Set<Integer> columnValues(List<List<Tile>> board, int columnNo) {
        if (columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Column number must be 0-8");
        }

        return board.stream().map(row -> row.get(columnNo)).filter(tile -> tile.getValue() != null).map(Tile::getValue).collect(Collectors.toSet());
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
      i.e. if 2 is not a possible value to use in any cell in the same row (and in a different section), then return 2 in the output
     */
    public static Set<Integer> exclusivePossibleValues(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        boolean result = false;
        Set<Integer> combinedPossibleValues = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        Set<Integer> exclusiveRowPossibleValues = exclusiveRowPossibleValues(board, rowNo, columnNo);
        if (exclusiveRowPossibleValues.size() > 0) {
            result = true;
            combinedPossibleValues.retainAll(exclusiveRowPossibleValues);
        }

        Set<Integer> exclusiveColumnPossibleValues = exclusiveColumnPossibleValues(board, rowNo, columnNo);
        if (exclusiveColumnPossibleValues.size() > 0) {
            result = true;
            combinedPossibleValues.retainAll(exclusiveColumnPossibleValues);
        }

        Set<Integer> exclusiveSectionPossibleValues = exclusiveSectionPossibleValues(board, rowNo, columnNo);
        if (exclusiveSectionPossibleValues.size() > 0) {
            result = true;
            combinedPossibleValues.retainAll(exclusiveSectionPossibleValues);
        }

        return result ? combinedPossibleValues : new HashSet<>();
    }

    /*
      Check all other cells in the same row (and in another section), and return any possibleValues that are not possibleValues for any other cell
     */
    private static Set<Integer> exclusiveRowPossibleValues(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Set<Integer> output = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // Collect all possible values from other cells in the row (and not in the same section), and remove them from the possible values for this cell
        IntStream
                .range(0, board.size())
                .filter(colI -> colI != columnNo)
                .forEach(colI -> {
                    output.removeAll(board.get(rowNo).get(colI).getPossibleValues());
                });

        return output;
    }

    /*
      Check all other cells in the same column (and in another section), and return any possibleValues that are not possibleValues for any other cell
     */
    private static Set<Integer> exclusiveColumnPossibleValues(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Set<Integer> output = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // Collect all possible values from other cells in the column (and not in the same section), and remove them from the possible values for this cell
        IntStream
                .range(0, board.size())
                .filter(rowI -> rowI != rowNo)
                .forEach(rowI -> {
                    output.removeAll(board.get(rowI).get(columnNo).getPossibleValues());
                });

        return output;
    }

    /*
      Check all other cells in the section, and return any possibleValues that are not possibleValues for any other cell
     */
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

    /*
      If a possible value is only in one row or column of another section, then that value is impossible in the same row or column in the current section
      Return those values
     */
    public static Set<Integer> determineNonPossibleValuesInRowOrColumn(List<List<Tile>> board, int rowNo, int columnNo) {
        Set<Integer> output = new HashSet<>();

        // Collect all non-possible values from other sections in the row
        IntStream
                .range(0, board.size())
                .filter(colI -> colI / 3 != columnNo / 3)
                .forEach(colI -> {
                    output.addAll(possibleValuesOnlyInSameRowOfSection(board, rowNo, colI));
                });

        // Collect all non-possible values from other sections in the column
        IntStream
                .range(0, board.size())
                .filter(rowI -> rowI / 3 != rowNo / 3)
                .forEach(rowI -> {
                    output.addAll(possibleValuesOnlyInSameColumnOfSection(board, rowI, columnNo));
                });

        return output;
    }

    /*
      Returns a Set of possible values that are only found in the same row as the current cell.
      i.e. if 5 is a possible value of [0, 0], then:
                        If 5 is a possible value of [0, 1] and no other cell, then returns true.
                        If 5 is a possible value of [1, 1], returns false
     */
    private static Set<Integer> possibleValuesOnlyInSameRowOfSection(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Tile tile = board.get(rowNo).get(columnNo);
        Set<Integer> possibleTileValues = new HashSet<>(tile.getPossibleValues());

        // Identify possible values in other cells in the section, outside of the row
        for (int rowI = (rowNo / 3) * 3; rowI < (rowNo / 3) * 3 + 3; rowI++) {
            for (int colJ = (columnNo / 3) * 3; colJ < (columnNo / 3) * 3 + 3; colJ++) {
                if (rowI == rowNo) continue;

                possibleTileValues.removeAll(board.get(rowI).get(colJ).getPossibleValues());
            }
        }

        return possibleTileValues;
    }

    /*
      Returns a Set of possible values that are only found in the same column as the current cell.
      i.e. if 5 is a possible value of [0, 0], then:
                        If 5 is a possible value of [1, 0] and no other cell, then returns Set containing 5.
                        If 5 is a possible value of [1, 1], returns Set not containing 5
     */
    private static Set<Integer> possibleValuesOnlyInSameColumnOfSection(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Tile tile = board.get(rowNo).get(columnNo);
        Set<Integer> possibleTileValues = new HashSet<>(tile.getPossibleValues());

        // Identify possible values in other cells in the section, outside of the column
        for (int rowI = (rowNo / 3) * 3; rowI < (rowNo / 3) * 3 + 3; rowI++) {
            for (int colJ = (columnNo / 3) * 3; colJ < (columnNo / 3) * 3 + 3; colJ++) {
                if (colJ == columnNo) continue;

                possibleTileValues.removeAll(board.get(rowI).get(colJ).getPossibleValues());
            }
        }

        return possibleTileValues;
    }

    // Returns true if a change is made
    public static boolean filterMatchingPossibleValuesFromOtherTiles(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Tile tile = board.get(rowNo).get(columnNo);

        // Map containing which Tiles contain the possibleValue key
        Map<Integer, Set<Tile>> possibleValuesMap = new HashMap<>();

        // Identify possible values in other cells in the section
        for (Integer possibleValue : tile.getPossibleValues()) {
            for (int rowI = (rowNo / 3) * 3; rowI < (rowNo / 3) * 3 + 3; rowI++) {
                for (int colJ = (columnNo / 3) * 3; colJ < (columnNo / 3) * 3 + 3; colJ++) {
                    if (board.get(rowI).get(colJ).getPossibleValues().contains(possibleValue)) {
                        Set<Tile> possibleValueTiles = possibleValuesMap.getOrDefault(possibleValue, new HashSet<>());
                        possibleValueTiles.add(board.get(rowI).get(colJ));
                        possibleValuesMap.put(possibleValue, possibleValueTiles);
                    }
                }
            }
        }

        // Find keys that have the same value (i.e. the same set of tiles).
        // If 2 keys match and have 2 tiles, then remove all other possible values from those tiles apart from the two keys
        // Likewise, if 3 keys match, and there are 3 tiles, then remove all other possible values apart from the three keys
        // Same for 4, 5, 6 etc
        for (Map.Entry<Integer, Set<Tile>> possibleValue : possibleValuesMap.entrySet()) {
            Set<Map.Entry<Integer, Set<Tile>>> matchingTiles = possibleValuesMap.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(possibleValue.getValue()))
                    .collect(Collectors.toSet());

            if (matchingTiles.size() == possibleValue.getValue().size()) {
                Set<Integer> values = matchingTiles.stream().map(entry -> entry.getKey()).collect(Collectors.toSet());

                for (int rowI = (rowNo / 3) * 3; rowI < (rowNo / 3) * 3 + 3; rowI++) {
                    for (int colJ = (columnNo / 3) * 3; colJ < (columnNo / 3) * 3 + 3; colJ++) {
                        board.get(rowI).get(colJ).getPossibleValues().removeAll(values);
                    }
                }

                possibleValue.getValue()
                        .stream()
                        .forEach(entry -> {
                            entry.getPossibleValues().clear();
                            entry.getPossibleValues().addAll(values);
                        });

                return true;
            }
        }

        return false;
    }

    public static boolean removeNonPossibleSectionValues(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        boolean output = false;

        Set<Integer> cellColumnPossibleValues = forcedPossibleValuesInSectionColumn(board, rowNo, columnNo);
        Set<Integer> cellRowPossibleValues = forcedPossibleValuesInSectionRow(board, rowNo, columnNo);

        for (int rowI = (rowNo / 3) * 3; rowI < (rowNo / 3) * 3 + 3; rowI++) {
            for (int colJ = (columnNo / 3) * 3; colJ < (columnNo / 3) * 3 + 3; colJ++) {
                if (columnNo != colJ) {
                    output = board.get(rowI).get(colJ).getPossibleValues().removeAll(cellColumnPossibleValues) || output;
                }

                if (rowNo != rowI) {
                    output = board.get(rowI).get(colJ).getPossibleValues().removeAll(cellRowPossibleValues) || output;
                }
            }
        }

        return output;
    }

    private static Set<Integer> forcedPossibleValuesInSectionColumn(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Set<Integer> output = new HashSet<>(board.get(rowNo).get(columnNo).getPossibleValues());

        for (int i = 0; i < 9; i++) {
            if (i / 3 == rowNo / 3) continue;

            output.removeAll(board.get(i).get(columnNo).getPossibleValues());
        }

        return output;
    }

    private static Set<Integer> forcedPossibleValuesInSectionRow(List<List<Tile>> board, int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8 || columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Row & Column numbers must be 0-8");
        }

        Set<Integer> output = new HashSet<>(board.get(rowNo).get(columnNo).getPossibleValues());

        for (int i = 0; i < 9; i++) {
            if (i / 3 == columnNo / 3) continue;

            output.removeAll(board.get(rowNo).get(i).getPossibleValues());
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
