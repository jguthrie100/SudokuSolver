import './Tile.css';

function Tile({ id, classes, onChange, value }) {

  function highlightTile(tileId = id) {
    document.getElementsByClassName("tile-selected")?.[0]?.classList?.remove("tile-selected");
    document.getElementById("tile_" + tileId).classList.add("tile-selected");
  }

  function handleTileClick(event, tileId = id) {
    document.getElementById("input_" + tileId).focus();

    highlightTile(tileId);
  }

  function handleKeyPress(event) {
    let row = parseInt(("" + id)[0]);
    let col = parseInt(("" + id)[1]);
    switch (event.code) {
      case "ArrowUp":
        if (row > 0) handleTileClick(null, (row-1) + "" + col)
        break;
      case "ArrowDown":
        if (row < 8) handleTileClick(null, (row+1) + "" + col)
        break;
      case "ArrowLeft":
        if (col > 0) handleTileClick(null, row + "" + (col-1))
        break;
      case "ArrowRight":
        if (col < 8) handleTileClick(null, row + "" + (col+1))
        break;
    }
  }

  function handleTyping(event) {
    highlightTile();

    if (event.key === "Delete" || event.key === "Backspace") {
      onChange(id, "");
    }

    if (event.key !== value && event.key.match(/^[1-9]?$/)) {
      onChange(id, event.key);
    }
  }

  // Strange bug where input is not focussed when using onMouseDown - so use onClick for tile too
  return (
    <div id={"tile_" + id} className={classes.join(" ")} onMouseDown={handleTileClick} onClick={handleTileClick} onKeyDown={handleKeyPress}>
      <input id={"input_" + id} onChange={() => {}} onKeyDown={handleTyping} value={value || ""} />
    </div>
  );
}

export default Tile;
