import './Board.css';
import Tile from '../Tile/Tile';

function Board() {
  return (
    <div id="board">
      <Tile classes={["left", "top"]} /><Tile classes={["top"]} /><Tile classes={["top", "right"]} /><Tile classes={["top"]} /><Tile classes={["top"]} /><Tile classes={["top", "right"]} /><Tile classes={["top"]} /><Tile classes={["top"]} /><Tile classes={["top", "right"]} />
      <Tile classes={["left"]} /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} />
      <Tile classes={["left", "bottom"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom", "right"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom", "right"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom", "right"]} />
      <Tile classes={["left"]} /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} />
      <Tile classes={["left"]} /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} />
      <Tile classes={["left", "bottom"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom", "right"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom", "right"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom", "right"]} />
      <Tile classes={["left"]} /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} />
      <Tile classes={["left"]} /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} /><Tile /><Tile /><Tile classes={["right"]} />
      <Tile classes={["bottom", "left"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom", "right"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom", "right"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom"]} /><Tile classes={["bottom", "right"]} />
    </div>
  );
}

export default Board;
