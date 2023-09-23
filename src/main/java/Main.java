import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        List<String> boardString = new ArrayList<>();
        boardString.add("--9-----2");
        boardString.add("--8-4----");
        boardString.add("6--1--97-");
        boardString.add("--6--825-");
        boardString.add("---5----3");
        boardString.add("2-------1");
        boardString.add("-7---1---");
        boardString.add("-------3-");
        boardString.add("9--7--56-");

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            String row = boardString.get(i);

            String[] rowValues = row.split("");

            if (rowValues.length != 9) {
                throw new IllegalArgumentException("There must be 9 values in each row");
            }

            for (int j = 0; j < 9; j++) {
                if (!rowValues[j].equals("-")) {
                    board.getBoard().get(i).get(j).setValue(Integer.valueOf(rowValues[j]));
                }
            }
        }

        BoardRunner boardRunner = new BoardRunner(board);

        while (true) {
            if (boardRunner.step()) break;
        }
    }
}