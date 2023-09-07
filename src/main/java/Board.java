import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    @Getter
    List<List<Tile>> board;

    public Board() {
        this.board = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            List<Tile> row = new ArrayList<>();

            for (int j = 0; j < 9; j++) {
                row.add(new Tile());
            }

            this.board.add(Collections.unmodifiableList(row));
        }

        this.board = Collections.unmodifiableList(this.board);
    }

    public boolean rowContains(int rowNo, int value) {
        if (rowNo < 0 || rowNo > 8 || value < 1 || value > 9) {
            return false;
        }

        return board.get(rowNo).contains(value);
    }

    public boolean columnContains(int columnNo, int value) {
        if (columnNo < 0 || columnNo > 8 || value < 1 || value > 9) {
            return false;
        }

        for (int i = 0; i < 9; i++) {
            if (board.get(i).get(columnNo).getValue() == value) {
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
    public boolean sectionContains(int sectionRow, int sectionColumn, int value) {
        if (sectionRow < 0 || sectionRow > 2 || sectionColumn < 0 || sectionColumn > 2 || value < 1 || value > 9) {
            return false;
        }

        for (int i = sectionRow * 3; i < (sectionRow * 3) + 3; i++) {
            for (int j = sectionColumn * 3; j < (sectionColumn * 3) + 3; j++) {
                if (board.get(sectionRow).get(sectionColumn).getValue() == value) {
                    return true;
                }
            }
        }

        return false;
    }

    public String toString() {
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
