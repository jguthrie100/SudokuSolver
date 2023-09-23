import './Tile.css';
import { useState } from 'react';

function Tile({ classes }) {

  const [value, setValue] = useState("");

  let id = (Math.random() + "").substr(2);

  classes = classes || [];
  classes.push("tile");

  function highlightTile() {
    document.getElementsByClassName("tile-selected")?.[0]?.classList?.remove("tile-selected");
    document.getElementById("tile_" + id).classList.add("tile-selected");
  }

  function handleTileClick() {
    highlightTile();

    document.getElementById("input_" + id).focus();
  }

  function handleTyping(event) {
    highlightTile();

    if (event.key === "Delete" || event.key === "Backspace") {
      setValue("");
    }

    if (event.key.match(/^[1-9]?$/)) {
      setValue(event.key);
    }
  }

  return (
    <div id={"tile_" + id} className={classes.join(" ")} onClick={handleTileClick}>
      <input id={"input_" + id} onChange={() => {}} onKeyDown={handleTyping} value={value} />
    </div>
  );
}

export default Tile;
