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
    let value = event.target.value.substr(-1);

    if (value.match(/^[1-9]?$/)) {
      setValue(value);
    }
  }

  return (
    <div id={"tile_" + id} className={classes.join(" ")} onClick={handleTileClick}>
      <input id={"input_" + id} onChange={handleTyping} value={value} />
    </div>
  );
}

export default Tile;
