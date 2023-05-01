import socket

# Initialize the board state
board_state = [' '] * 9

# Define a function to check if the game is over
def game_over(board_state):
    # Check rows
    for i in range(0, 9, 3):
        if board_state[i] != ' ' and board_state[i] == board_state[i+1] and board_state[i+1] == board_state[i+2]:
            return board_state[i]
    # Check columns
    for i in range(3):
        if board_state[i] != ' ' and board_state[i] == board_state[i+3] and board_state[i+3] == board_state[i+6]:
            return board_state[i]
    # Check diagonals
    if board_state[0] != ' ' and board_state[0] == board_state[4] and board_state[4] == board_state[8]:
        return board_state[0]
    if board_state[2] != ' ' and board_state[2] == board_state[4] and board_state[4] == board_state[6]:
        return board_state[2]
    # Check for a draw
    if ' ' not in board_state:
        return 'DRAW'
    # Game is not over
    return None

# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Bind the socket to a specific address and port
server_address = ('localhost', 10000)
print('Starting up on {} port {}'.format(*server_address))
sock.bind(server_address)

# Listen for incoming connections
sock.listen(1)

# Wait for a connection
print('Waiting for a connection...')
connection, client_address = sock.accept()
print('Connection from', client_address)

# Send initial board state to client
connection.sendall(''.join(board_state).encode())

# Game loop
while True:
    # Receive client's move
    data = connection.recv(1024).decode()
    move = int(data)
    
    # Check if move is valid
    if move < 0 or move > 8 or board_state[move] != ' ':
        connection.sendall('ERROR'.encode())
        continue
    
    # Update board state with client's move
    board_state[move] = 'X'
    
    # Check if game is over
    winner = game_over(board_state)
    if winner is not None:
        if winner == 'DRAW':
            connection.sendall('DRAW'.encode())
            break
        else:
            connection.sendall(('WIN' + winner).encode())
            break
    
    # Generate random move for server
    while True:
        server_move = random.randint(0, 8)
        if board_state[server_move] == ' ':
            break
    
    # Update board state with server's move
    board_state[server_move] = 'O'
    
    # Check if game is over
    winner = game_over(board_state)
    if winner is not None:
        if winner == 'DRAW':
            connection.sendall('DRAW'.encode())
            break
        else:
            connection.sendall(('WIN' + winner).encode())
            break
    
    # Send updated board state to client
    connection.sendall(''.join(board_state).encode())

# Close the connection
connection.close()
