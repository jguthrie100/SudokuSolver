import './Tile.css';
import { useState } from 'react';

function Tile({ classes }) {

  const [value, setValue] = useState("");

  let id = (Math.random() + "").substr(2);

  classes = classes || [];
  classes.push("tile");

  function handleTileClick() {
    document.getElementById("input_" + id).focus();
  }

  function handleTyping(event) {
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
