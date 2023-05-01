import socket

# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect the socket to the server's address and port
server_address = ('localhost', 10000)
print('Connecting to {} port {}'.format(*server_address))
sock.connect(server_address)

# Receive initial board state from server
board_state = sock.recv(1024).decode()
print('Current board state:')
print(board_state[0] + ' | ' + board_state[1] + ' | ' + board_state[2])
print('---------')
print(board_state[3] + ' | ' + board_state[4] + ' | ' + board_state[5])
print('---------')
print(board_state[6] + ' | ' + board_state[7] + ' | ' + board_state[8])

# Game loop
while True:
    # Prompt player for move
    move = input('Enter your move (0-8): ')
    sock.sendall(move.encode())
    
    # Receive updated board state from server
    board_state = sock.recv(1024).decode()
    if board_state.startswith('WIN'):
        winner = board_state[3]
        print('Player', winner, 'wins!')
        break
    elif board_state == 'DRAW':
        print('Draw!')
        break
    
    # Display updated board state
    print('Current board state:')
    print(board_state[0] + ' | ' + board_state[1] + ' | ' + board_state[2])
    print('---------')
    print(board_state[3] + ' | ' + board_state[4] + ' | ' + board_state[5])
    print('---------')
    print(board_state[6] + ' | ' + board_state[7] + ' | ' + board_state[8])
    
# Close socket
sock.close()
