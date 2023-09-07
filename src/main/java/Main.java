import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        System.out.println("Please enter each row on the sudoku you wish to solve: (Add a space between each number, and hit enter to start a new row. Use dashes - for empty cells)");

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            String row = sc.nextLine();

            String[] rowValues = row.split(" ");

            if (rowValues.length != 9) {
                throw new IllegalArgumentException("There must be 9 values in each row");
            }

            for (int j = 0; j < 9; j++) {
                if (!rowValues[j].equals("-")) {
                    board.getBoard().get(i).get(j).setValue(Integer.valueOf(rowValues[j]));
                }
            }
        }

        System.out.println("Thank you");

        System.out.println(board);

        System.out.println(board.getTile(0, 0).getPossibleValues());
    }
}