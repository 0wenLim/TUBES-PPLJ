const net = require('net');
const readline = require('readline');

// Define a readline interface for user input
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

// Create a TCP socket and connect to the server
const socket = net.createConnection({ host: 'localhost', port: 3000 }, () => {
  console.log('Connected to server');
});

// Handle data received from the server
socket.on('data', (data) => {
  const message = data.toString();
  
  // Check if the message is a winning message
  if (message.startsWith('Winner:')) {
    console.log(message);
    socket.end();
    rl.close();
  } else {
    // Print the current state of the board
    const board = JSON.parse(message);
    console.log(`  0 1 2`);
    console.log(`0 ${board[0][0]} ${board[0][1]} ${board[0][2]}`);
    console.log(`1 ${board[1][0]} ${board[1][1]} ${board[1][2]}`);
    console.log(`2 ${board[2][0]} ${board[2][1]} ${board[2][2]}`);
    
    // Ask the user for a move
    rl.question('Enter row and column (e.g. 1 2): ', (answer) => {
      const [row, col] = answer.split(' ').map((x) => parseInt(x));
      const move = { row, col, symbol: '' };
      
      // Determine the user's symbol based on the current turn
      if (message.includes('X')) {
        move.symbol = 'O';
      } else {
        move.symbol = 'X';
      }
      
      // Send the move to the server
      socket.write(JSON.stringify(move));
    });
  }
});

// Handle connection termination
socket.on('end', () => {
  console.log('Disconnected from server');
});

// Handle errors
socket.on('error', (err) => {
  console.error(`Error: ${err}`);
  socket.end();
  rl.close();
});
