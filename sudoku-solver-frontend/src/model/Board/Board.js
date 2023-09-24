import { useState } from 'react';
import './Board.css';
import Tile from '../Tile/Tile';
import { ApiService } from '../../services/ApiService.js';

function Board() {

  const apiService = new ApiService();

  // init board state to empty 2 dimensional array of 9x9
  const [board, setBoard] = useState([...new Array(9)].map(t => [...new Array(9)]));

  let tiles = [];

  // Build up grid of tiles
  for (let i = 0; i < 9; i++) {
    for (let j = 0; j < 9; j++) {

      const id = i + '' + j;
      let classes = ["tile"];

      // Add borders for specific rows
      if (i === 0) {
        classes.push("tile-top");
      }
      if ([2,5,8].includes(i)) {
        classes.push("tile-bottom");
      }

      // Add borders for specific columns
      if (j === 0) {
        classes.push("tile-left");
      }
      if ([2,5,8].includes(j)) {
        classes.push("tile-right");
      }

      tiles.push(<Tile key={'key-' + id} id={id} classes={classes} onChange={handleChange} value={board[i][j]} />);
    }
  }

  async function handleChange(cellId, value) {
      const row = parseInt(cellId[0]);
      const col = parseInt(cellId[1]);

      let newBoard = [...board];
      newBoard[row][col] = parseInt(value);

      setBoard(newBoard);
  }

  async function submitPuzzle() {
    setBoard(await apiService.solvePuzzle(board));
  }

  function clearBoard() {
    setBoard([...new Array(9)].map(t => [...new Array(9)]));
  }

  return (
    <div>
      <div id="board">
        {tiles}
      </div>
      <input id="submitBtn" type="button" value="Solve Puzzle" onClick={submitPuzzle} />
      <input id="clearBtn" type="button" value="Clear Board" onClick={clearBoard} />
    </div>
  );
}

export default Board;
