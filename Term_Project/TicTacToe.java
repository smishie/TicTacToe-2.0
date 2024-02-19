package Term_Project;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        boolean validPlayerCount = false;
        int playerCount = 0;
        
// prompt user for number of players, check validation
        while(!validPlayerCount){
            System.out.print("Enter a number of players (3-10): ");
            if(scan.hasNextInt()){ // checks if inputted integer
                playerCount = scan.nextInt();
                if(Player.isValidPlayers(playerCount)){ // checks if integer is within bounds
                    System.out.println("Number of players is: " + playerCount);
                    validPlayerCount = true;
                }else {
                    System.out.println("Invalid input, try again...\n");
                }
            }else {
                System.out.println("Invaid input, please try again...\n");
                scan.next();
            }
        } // end of playerCount input
    
        Board board = new Board(playerCount); // create board object
        System.out.println("Your board: ");
        board.printBoard();

        Player[] players = new Player[playerCount]; // creates an array of type Player
        char userIn = ' ';

        System.out.println("Enter your characters one by one.");
        System.out.println("If multiple characters are inputted, only the first will be accepted.");

// sets player pieces
        for(int i = 0; i < playerCount; i++){
            System.out.print("Player " + (i + 1) + " : "); // prompt user
            if(!scan.hasNextInt()){ // checks for chars
                userIn = scan.next().charAt(0);
                if(i >= 1){ // checks with all previous characters
                    while(Player.charTaken(players, i, userIn)){ // gets if the input is valid
                        System.out.println("Character taken! Try again.");
                        System.out.print("Player " + (i + 1) + " : "); // prompt user
                        while(scan.hasNextInt()){
                            scan.next();
                            System.out.println("Invalid input... please try again.");
                            System.out.print("Player " + (i + 1) + " : "); // prompt user
                        }
                        userIn = scan.next().charAt(0);
                    }
                }
                players[i] = new Player(userIn); // initializes new Player object
            }
            else { // error message
                System.out.println("Invalid input... please try again.");
                i--;
                userIn = scan.next().charAt(0);
            }
        } // end of character inputs

// sets the number of winners
        GameLogic gameLogic = new GameLogic(playerCount, board);
        System.out.println("To begin, select the number of pieces in-a-row to win. (3 - " + (playerCount + 1) + ")");
        int winners = 0;
        boolean validNumber = false;
        
        while(!validNumber){        
            System.out.print("How many pieces to win would you like: ");
            if(scan.hasNextInt()){
                winners = scan.nextInt();
                if(gameLogic.piecesToWin(playerCount, winners)){ // checks if input is valid
                    validNumber = true;    
                }else {
                    System.out.println("Invalid input, please try again...\n");
                }
            }else {
                System.out.println("Invalid input, please try again...\n");
                scan.next();
            }
        }

        scan.nextLine();

// INTRO ----------------------------------------------------------------------------
        System.out.println("\nYou have " + playerCount + " players and need " + winners + " in-a-row to win.");
        System.out.println("To place a piece, input the row and column. Separate by a space.");
        System.out.println("Example... for row 3, column 2. You will input: 3 2");
        System.out.println("After each turn you will review your board. Be sure to not try placing your piece on another's!");
        System.out.println("Your board: ");
        board.printBoard();
        System.out.println("\t     READY? \n\tTIME TO START !!!\n");

        String input = "";
        int rowIn = 0;
        int columnIn = 0;

        while(!gameLogic.hasWinner() && !board.isFull()){ // while board isnt full and no one has won yet
            for(int i = 0; i < playerCount; i++){ // each player's turn
                players[i].printPlayer(i);
                System.out.print("Where are you placing your piece: ");
                input = scan.nextLine();
                
                while(input.length() <= 2) { // validataes user inputting row and column
                    System.out.println("Invalid input. Please enter in the format 'row column'.\n");
                    players[i].printPlayer(i);
                    System.out.print("Where are you placing your piece: ");
                    input = scan.nextLine(); 
                } // end of placemebnt validation\
                
                String[] arr = input.split(" "); // strok with delimiter as white space
                rowIn = Integer.parseInt(arr[0]);
                columnIn = Integer.parseInt(arr[1]);
                
                if(gameLogic.validPlacement(rowIn, columnIn)){ // if spot is empty AND within bounds

                    if(board.setPiece(players[i].getChar(), rowIn - 1, columnIn - 1)){
                        board.printBoard();   
                        if(gameLogic.hasWon(rowIn - 1, columnIn - 1))
                            break;
                        if(board.isFull()) // if board is full with no winner or current player won from placement
                            break; // end loop
                    }
                    else { // user did not place
                        System.out.println("Spot is taken!\n");
                        board.printBoard();
                        i--;
                    }   
                }else { // user did not place
                    System.out.println("Invalid input... please try again.\n");
                    board.printBoard();
                    i--;
                }

            }
        }

        if(board.isFull() && !gameLogic.hasWinner()){ // tied
            System.out.println("\tThere is no winner!\n\tThe board has been filled. \n\tPlease try again!");
        }
        else if(gameLogic.hasWinner()){ // winner has been decided
            System.out.println("\n\tWE HAVE A WINNER !!!");
            int winnerIndex = gameLogic.getWinnerIndex(players);
            System.out.print("\t  ");
            players[winnerIndex].printPlayer(winnerIndex);            
        }
        scan.close();
    }
}