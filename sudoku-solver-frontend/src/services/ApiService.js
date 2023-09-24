export function ApiService() {

  return {
    solvePuzzle: async function(boardState) {
      return await fetch('http://localhost:8080/api/v1/solvePuzzle', {
        method: 'POST',
        body: JSON.stringify(boardState),
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
        },
      })
      .then((response) => response.json())
      .then((data) => {
        return data;
      })
      .catch((err) => {
        console.error(err.message);
        return boardState;
      });
    }
  }
}

export default ApiService;