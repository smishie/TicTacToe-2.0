package Term_Project;

public class Board {
// Constructors
    Board(){ }

    Board(int players){
        this._players = players;
        this._size = _players + 1;
        this._board = new char[_size][_size];
        for(int i = 0; i < _board.length; i++){
            for(int j = 0; j < _board[i].length; j++){
                _board[i][j] = '-';
            }
        }
    }

// Methods
    // outputs the current board
    public void printBoard(){
        for(int i = 0; i < _board.length; i++){
            System.out.print("\t   ");
            for(int j = 0; j < _board[i].length; j++){
               System.out.print( _board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * determines if a player's char can be placed
     * @param playerChar is the character being placed
     * @param row 
     * @param col
     * @return true if placed, false if spot is taken
     */
    public boolean setPiece(char playerChar, int row, int col){
        if(_board[row][col] == '-'){
            _board[row][col] = playerChar;
            return true; // piece is placed
        }
        return false; // piece is not placed
    }

    /**
     * determines if the board is full
     * @return true: board if full | false: board is not full
     */
    public boolean isFull(){
        for(int i = 0; i < _size; i++){
            for(int j = 0; j < _size; j++){
                if(_board[i][j] == '-'){
                    return false; // full board
                }
            }
        }
        return true; // empty spot
    }

    /**
     * returns the player's char based on the given row and column
     * @param row: array notation
     * @param col: array noation
     * @return char at given location
     */
    public char getChar(int row, int col){
        return _board[row][col];
    }

// private variables
    private char[][] _board;
    private int _size;
    private int _players;
}
