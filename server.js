const net = require('net');

// Define the Tic Tac Toe board
const board = [
  ['_', '_', '_'],
  ['_', '_', '_'],
  ['_', '_', '_']
];

// Define the turn counter to keep track of whose turn it is
let turn = 0;

// Define a function to check for a winner
function checkWinner() {
  let winner = null;
  // Check rows for a winner
  for (let i = 0; i < 3; i++) {
    if (board[i][0] !== '_' && board[i][0] === board[i][1] && board[i][1] === board[i][2]) {
      winner = board[i][0];
    }
  }
  // Check columns for a winner
  for (let j = 0; j < 3; j++) {
    if (board[0][j] !== '_' && board[0][j] === board[1][j] && board[1][j] === board[2][j]) {
      winner = board[0][j];
    }
  }
  // Check diagonals for a winner
  if (board[0][0] !== '_' && board[0][0] === board[1][1] && board[1][1] === board[2][2]) {
    winner = board[0][0];
  }
  if (board[0][2] !== '_' && board[0][2] === board[1][1] && board[1][1] === board[2][0]) {
    winner = board[0][2];
  }
  return winner;
}

// Create a TCP server
const server = net.createServer((socket) => {
  console.log(`Client connected: ${socket.remoteAddress}:${socket.remotePort}`);
  
  // Send the current state of the board to the client
  socket.write(JSON.stringify(board));
  
  // Handle data received from the client
  socket.on('data', (data) => {
    const move = JSON.parse(data.toString());
    
    // Check if it is the client's turn
    if (turn % 2 === 0 && move.symbol === 'X' || turn % 2 !== 0 && move.symbol === 'O') {
      // Check if the move is valid
      if (board[move.row][move.col] === '_') {
        // Place the symbol on the board
        board[move.row][move.col] = move.symbol;
        
        // Check for a winner
        const winner = checkWinner();
        if (winner) {
          // Send the winning message to both clients
          server.connections.forEach((client) => {
            client.write(`Winner: ${winner}`);
            client.end();
          });
        } else {
          // Send the updated state of the board to both clients
          server.connections.forEach((client) => {
            client.write(JSON.stringify(board));
          });
          
          // Increment the turn counter
          turn++;
        }
      } else {
        // Send an error message to the client
        socket.write('Invalid move');
      }
    } else {
      // Send an error message to the client
      socket.write('Not your turn');
    }
  });
  
  // Handle client disconnection
  socket.on('end', () => {
    console.log(`Client disconnected: ${socket.remoteAddress}:${socket.remotePort}`);
  });
});

// Start the server
server.listen(3000, () => {
  console.log('Server listening on port 3000');
});
