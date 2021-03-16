import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int menuSelec = 0;
        while (menuSelec != 2) {
            System.out.println("Connect FOUR Menu");
            System.out.println("-----------------");
            System.out.println("1. Play");
            System.out.println("2. Quit");
            menuSelec = scnr.nextInt();

            if (menuSelec == 1) {
                boolean winner = false;
                int row;
                int col;
                //takes in height and length to initialize 2d array
                System.out.print("What would you like the height of the board to be? ");
                int height = scnr.nextInt();
                System.out.println();
                System.out.print("What would you like the length of the board to be? ");
                int length = scnr.nextInt();
                System.out.println();

                char[][] board = new char[height][length];
                initializeBoard(board);
                printBoard(board);

                System.out.println("Player 1: x");                              //assigning character values to player 1 and 2 characters
                System.out.println("Player 2: o");
                System.out.println();
                char player1Token = 'x';
                char player2Token = 'o';
                int winningPlayer = 0;
                while (!winner) {
                    System.out.print("Player 1: Which column would you like to choose? ");      //intakes player 1's choice for column preference and inserts chip into that slot
                    col = scnr.nextInt();
                    System.out.println();
                    row = insertChip(board, col, player1Token);
                    winner = checkIfWinner(board, col, row, player1Token);
                    printBoard(board);
                    if (winner) {
                        winningPlayer = 1;
                        System.out.println("Player 1 won the game!");
                        ;
                        break;
                    }

                    System.out.print("Player 2: Which column would you like to choose? ");  //intakes player 2's preference for column and inserts chip into that slot
                    col = scnr.nextInt();
                    System.out.println();
                    row = insertChip(board, col, player2Token);
                    winner = checkIfWinner(board, col, row, player2Token);
                    printBoard(board);
                    if (winner) {
                        winningPlayer = 2;
                        System.out.println("Player 2 won the game!");
                        ;
                        break;
                    }
                    int res = 0;                                            //checks to see if there's any blank spaces left
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[i].length; j++) {
                            if (board[i][j] == '-')
                                res = 1;
                        }
                    }
                    if (res != 1)
                        break;
                }
                if (winningPlayer == 0)
                    System.out.println("Draw. Nobody wins.");


            }

        }
    }

    public static void printBoard(char[][] array) {
        for (int i = array.length - 1; i >= 0; i--) {           //parses through array, prints every value followed by 3 spaces
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public static void initializeBoard(char[][] array) {
        for (int i = 0; i < array.length; i++) {                //parses through array and assigns every value with '-'
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = '-';
            }
        }
    }

    public static int insertChip(char[][] array, int col, char chipType) {
        int row = 0;
        for (int i = array.length - 1; i >= 0 ; i--) {                  //parses through column and, if no chip presents, adds to row count until chip is present
            if (array[i][col] == '-') {
                row = i;
            }
        }
        array[row][col] = chipType;
        return row;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        boolean result = false;
        int count = 0;
        for (int i = 0; i < array.length; i++) {                //test for vertical wins
            if (array[i][col] == chipType) {
                count++;
                if (count == 4) {
                    result = true;
                    break;
                }
            }
            else {
                count = 0;
            }
        }
        for (int i = 0; i < array[row].length; i++) {
            if (array[row][i] == chipType) {                    //test for horizontal wins
                count++;
                if (count == 4) {
                    result = true;
                    break;
                }
            }
            else {
                count = 0;
            }
        }
        return result;
    }
}
