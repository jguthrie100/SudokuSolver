import java.util.HashSet;
import java.util.Set;

public class BoardRunner {

    private Board board;
    private int iteration = 0;
    private int currentRow = 0;
    private int currentColumn = 0;

    public BoardRunner(Board initialBoard) {
        this.board = initialBoard;
    }

    /*
    Runs a step of the execution algorithm.
    Returns true if the puzzle is solved

    General algorithm technique is to iterate through every cell on the board and work out the possible values for each cell
      by calculating which values are already in the row/column/3x3 section that a cell is in. When the possibleValues Set only
      has 1 possible value, then we switch the value of the cell to that value.
      Keep looping around the board, narrowing down all the possible values until the board is complete
     */
    public boolean step() {
        Tile currentTile = board.getBoard().get(currentRow).get(currentColumn);

        if (currentTile.getValue() == null) {
            Set<Integer> blockedValues = new HashSet<>();

            // Collect all values in corresponding row/column/section
            blockedValues.addAll(BoardHelper.rowValues(board.getBoard(), currentRow));
            blockedValues.addAll(BoardHelper.columnValues(board.getBoard(), currentColumn));
            blockedValues.addAll(BoardHelper.sectionValues(board.getBoard(), currentRow/3, currentColumn/3));

            currentTile.getPossibleValues().removeAll(blockedValues);

            if (currentTile.getPossibleValues().size() == 1) {
                currentTile.setValue((Integer) currentTile.getPossibleValues().toArray()[0]);
                System.out.println("SET VALUE: " + currentTile.getValue());
                return endStep();
            }

            Set<Integer> exclusivePossibleValues = BoardHelper.exclusivePossibleValues(board.getBoard(), currentRow, currentColumn);
            if (exclusivePossibleValues.size() == 1) {
                currentTile.setValue((Integer) exclusivePossibleValues.toArray()[0]);
                System.out.println("SET VALUE: " + currentTile.getValue());
                return endStep();
            }
        }

        endStep();

        return false;
    }

    private boolean endStep() {
        incrementPosition();

        System.out.println("Next iteration: " + iteration);
        System.out.println("Next position - Row: " + currentRow + ", Col: " + currentColumn);
        System.out.println(board.toString());
        System.out.println("\n_______________________________________________________\n");

        return false;
    }

    private void incrementPosition() {
        iteration++;

        if (currentColumn == 8) {
            currentColumn = 0;
            currentRow++;
        } else {
            currentColumn++;
        }

        if (currentRow > 8) {
            currentRow = 0;
        }
    }
}
