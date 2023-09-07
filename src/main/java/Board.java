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

    public Integer getValue(int rowNo, int columnNo) {
        if (rowNo < 0 || rowNo > 8) {
            throw new IllegalArgumentException("Row number must be 0-8");
        }
        if (columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Column number must be 0-8");
        }

        return board.get(rowNo).get(columnNo).getValue();
    }

    public void setValue(int rowNo, int columnNo, Integer value) {
        if (rowNo < 0 || rowNo > 8) {
            throw new IllegalArgumentException("Row number must be 0-8");
        }
        if (columnNo < 0 || columnNo > 8) {
            throw new IllegalArgumentException("Column number must be 0-8");
        }
        if (value != null && (value < 1 || value > 9)) {
            throw new NumberFormatException("Tile value must be null or 1-9");
        }

        board.get(rowNo).get(columnNo).setValue(value);
    }

    public String toString() {
        return BoardHelper.toString(board);
    }
}
