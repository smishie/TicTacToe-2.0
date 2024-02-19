package Term_Project;

public class GameLogic {
    GameLogic(){ } // default CTOR
    GameLogic(Board board){
        _board = board;
    }

    GameLogic(int players, Board board){
        this._players = players;
        this.maxRowsToWin = _players + 1;
        this._board = board;
    }

    /**
     * determines the acceptable bounds for winners and if user is within
     * @param players is number of players
     * @param input is the number to win
     * @return true if input is within bounds, false otherwise
     */
    public boolean piecesToWin(int players, int input){
        _players = players;
        maxRowsToWin = _players + 1;
        if(input >= minRowsToWin && input <= maxRowsToWin){
            numberToWin = input;
            return true; // input is valid
        }
        return false; // input is invalid
    }

    /**
     * determines if the user input a row and col that is within the bounds
     * @param row
     * @param col
     * @return true or false
     */
    public boolean validPlacement(int row, int col){
        if((row > 0 && row <= _players + 1) && (col > 0 && col <= _players + 1)){
            return true; // within bounds
        }
        return false; // outside bounds
    }
    
    /**
     * finds the index of the winning character
     * @param players array of the players
     * @return the index or 99 if not found
     */
    public int getWinnerIndex(Player[] players){
        for(int i = 0; i < players.length; i++){
            if(winningChar == players[i].getChar()){
                return i; // index of winner
            }
        }
        return 99; // error not found
    }

    /**
     * determines if there has been a winner
     * @return true or false
     */
    public boolean hasWinner(){
        if(winningChar == '-'){
            return false;
        }
        return true;
    }

    /**
     * finds if there is a winner according to rules
     * @param row: array terms
     * @param col: array terms
     * @return true: player won | false: player did not win
     */
    public boolean hasWon(int row, int col){
        // size is number to win on one side, the piece itself, and to win from ather side
        char playerChar = _board.getChar(row, col);
        char[] rowPieces = new char[numberToWin + (numberToWin-1)];
        char[] columnPieces = new char[numberToWin + (numberToWin-1)];
        char[] leftDiagonal = new char[numberToWin + (numberToWin-1)];
        char[] rightDiagonal = new char[numberToWin + (numberToWin-1)]; 

        // collects and stores the pieces around the piece just placed into an array 
         for(int i = numberToWin - 1; i >= 0; i--){
            // left horizontal
            if (col - i >= 0) 
                rowPieces[numberToWin - i - 1] = _board.getChar(row, col - i);

            // right horizontal
            if (col + i < maxRowsToWin)
                rowPieces[numberToWin + i - 1] = _board.getChar(row, col + i);

            // upper vertical
            if (row - i >= 0) 
                columnPieces[numberToWin - i - 1] = _board.getChar(row - i, col);
    
            // lower vertical
            if (row + i < maxRowsToWin)
                columnPieces[numberToWin + i - 1] = _board.getChar(row + i, col);

            // upper left diagonal
            if ((col - i >= 0) && (row - i >= 0)) 
                leftDiagonal[numberToWin - i - 1] = _board.getChar(row - i, col - i);

            // lower left diagonal
            if ((row + i < maxRowsToWin) && (col + i < maxRowsToWin))
                leftDiagonal[numberToWin + i - 1] = _board.getChar(row + i, col + i);

            // lower right diagonal
            if ((col - i >= 0) && (row + i < maxRowsToWin))
                rightDiagonal[numberToWin - i - 1] = _board.getChar(row + i, col - i);   

            // upper right diagonal
            if ((row - i >= 0) && (col + i < maxRowsToWin))
                rightDiagonal[numberToWin + i - 1] = _board.getChar(row - i, col + i);
        }

        if(checkConsecutive(rowPieces, playerChar)){ return true; }
        if(checkConsecutive(columnPieces, playerChar)){ return true; }
        if(checkConsecutive(leftDiagonal, playerChar)){ return true; }
        if(checkConsecutive(rightDiagonal, playerChar)){ return true; }
        return false;
    }

    /**
     * adds up the consecutive pieces
     * @param possibilities: array of char
     * @param playerChar: piece to check
     * @return true: consecutive characters meets win condition
     *         false: consecutive characters does not meet win condition
     */
    private boolean checkConsecutive(char[] possibilities, char playerChar){
        int sum = 0;
        for(int i = 0; i < possibilities.length; i++){
            if(possibilities[i] == playerChar){
                sum++;
                if(sum == numberToWin){
                    winningChar = playerChar; // sets private variable
                    return true;
                }
            }else { sum = 0; } // pieces are not all side by side, reset count
        }
        return false;
    }
    

// private variables
    private static int minRowsToWin = 3;
    private int numberToWin = 99;
    private int _players = 99;
    private char winningChar = '-';
    private Board _board; // connects to the current game's board object
    private int maxRowsToWin = 99;
}
