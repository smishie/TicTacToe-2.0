package Term_Project;

public class Player {
    Player(){ // default CTOR
        playerChar = ' '; 
    }

    Player(char choice) {
        this.playerChar = choice;
    }

    public void setChar(char choice){
        playerChar = choice;
    }

    public char getChar(){ return playerChar; }

    /**
     * determines if a character has been chosen previously or not
     * @param players is array of Player objects
     * @param currentIndex is the player's current index (off by one)
     * @param input is what user wants their object to hold 
     * @return true if already chosen, false otherwise
     */
    public static boolean charTaken(Player[] players, int currentIndex, char input){
        for(int i = 0; i < currentIndex; i++){ // checks each existing player
            if(input == players[i].getChar()){ 
                return true; // taken
            }
        }
        return false; // free char
    }

    public void printPlayer(int i){
        System.out.println("Player " + (i + 1) + " (" + playerChar + ")");
    }

    /**
     * determines if the input in within proper bounds
     * lower: 3, upper: 10
     * @param number
     * @return true if within bounds, false otherwise
     */
    public static boolean isValidPlayers(int number){
        if(number >= minPlayers && number <= maxPlayers){
            return true; // valid input
        }
        return false; // invalid input
    }

// private variables
    private char playerChar;
    private static final int minPlayers = 3;
    private static final int maxPlayers = 10;
}
