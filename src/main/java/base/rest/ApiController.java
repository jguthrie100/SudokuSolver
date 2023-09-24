package base.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import base.sudoku.BoardRunner;
import base.sudoku.entity.Board;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiController {

    @PostMapping("/solvePuzzle")
    public ResponseEntity<Object> solvePuzzle(@RequestBody List<List<Integer>> boardInput) {
        if (boardInput.size() != 9) {
            return new ResponseEntity<>("Board must consist of 9 rows", HttpStatus.BAD_REQUEST);
        }

        if (boardInput.stream().anyMatch(row -> row.size() != 9)) {
            return new ResponseEntity<>("Board must consist of 9 columns", HttpStatus.BAD_REQUEST);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Integer value = boardInput.get(i).get(j);
                if (value != null && (value < 1 || value > 9)) {
                    return new ResponseEntity<>("Cell values must be null or 1-9", HttpStatus.BAD_REQUEST);
                }
            }
        }

        Board board = new Board();

        // Create Board object using values from Rest body
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Integer tileValue = boardInput.get(row).get(col);
                if (tileValue != null) {
                    board.getTile(row, col).setValue(tileValue);
                }
            }
        }

        BoardRunner boardRunner = new BoardRunner(board);
        boardRunner.run();

        List<List<Integer>> output = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            output.add(new ArrayList<>());

            for (int col = 0; col < 9; col++) {
                output.get(row).add(board.getBoard().get(row).get(col).getValue());
            }
        }

        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
