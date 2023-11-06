import java.util.Scanner;
public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String space =" ";
    private static String board[][] = new String[ROW][COL];

    //step 5 'helper' methods
    private static void clearBoard() { //clear to space and set player to X(first move)
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                board[r][c] = space;
            }
        }
    }
    private static void display() { //used to show game to user
        int line = 0; // Display board
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                System.out.printf(board[r][c]);
                if (line == (ROW) - 1 || line == 5) {
                    System.out.printf("\n");
                } else if (line == (ROW * COL) - 1) {
                    System.out.printf("\n");
                } else {
                    System.out.printf(" | ");
                }
                line++;
            }
        }
    }
    private static boolean isValidMove(int row, int col) { //return true if there is space for a give move to be made
        return board[row][col].equals(space);
    }
    private static boolean isColWin(String player) { //checks for column win for specified player by checking if all spaces in column match a player
        for (int c = 0; c < COL; c++) {
            if(board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String player) {//checks for row win for specified player by checking if all spaces in row match a player
        for (int r = 0; r < ROW; r++) {
            if(board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagWin(String player) {//checks for diagonal win for specified player by checking if all spaces in diagonal match a player
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || (board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(player));
    }
    private static boolean isWin(String player) { //checks for win from X or O, using isColWin, isRowWin, and isDiagWin
        if (isColWin(player) || isRowWin(player) || isDiagWin(player)) {
            System.out.printf("\n%S WINS!!", player);
            return true;
        }
        return false;
    }
    private static boolean isColTie() {
        int colTies = 0;
        for (int c = 0; c < COL; c++) { //COL Tie
            if ((board[0][c].equals("X") || board[1][c].equals("X") || board[2][c].equals("X")) && (board[0][c].equals("O") || board[1][c].equals("O") || board[2][c].equals("O"))) {
                colTies += 1; //increments if there is a tie in Column c
            }
        }
        if (colTies >= COL-1) { //returns true if all three columns contain a tie
            return true;
        }
        return false;
    }
    private static boolean isRowTie() {
        int rowTies = 0;
        for (int r = 0; r < COL; r++){ //ROW tie
            if((board[r][0].equals("X") || board[r][1].equals("X") || board[r][2].equals("X")) && (board[r][0].equals("O") || board[r][1].equals("O") || board[r][2].equals("O"))) {
                rowTies += 1; //increments if there is a tie in Row r
                }
        }
        if (rowTies >= ROW-1) { //returns true if all three rows contain a tie
            return true;
        }
        return false;
    }
    private static boolean isDiagTie() {
        boolean diagTie = false;
        boolean diagTie2 = false;
        diagTie = ((board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) && (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")));
        //Diag Tie
        diagTie2 = ((board[2][0].equals("X") || board[1][1].equals("X") || board[0][2].equals("X")) && (board[2][0].equals("O") || board[1][1].equals("O") || board[0][2].equals("O")));
        //Diag Tie
        if (diagTie || diagTie2) {
            return true; //return true if tie in both diagonals
        } else {
            return false;
        }
    }
    private static boolean isTie() {//checks for tie condition(all spaces filled or X and O in every win condition)
        if ((isColTie() && isRowTie() && isDiagTie())) {
            System.out.print("\nIt's a Tie.");
            return true;
        } else {
            return false;
        }
    }

    //coordinates for move given by user in int from 1-3 (-1 to match array)
    //check for win/tie at points it may be possible - announce if true
    //toggle player from X to O
    public static void main(String[] args) {
        int moveCount = 0;
        Scanner in = new Scanner(System.in);
        String player = "X";
        boolean end = false;
        boolean play = true;
        System.out.printf("Welcome to TicTacToe!\nEach space is a row/column coordinate as shown in the example below:\n");
        int line = 0;
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                System.out.printf("[%d:%d]", r, c );
                if (line == (ROW) - 1 || line == 5) {
                    System.out.printf("\n");
                } else if (line == (ROW * COL) - 1) {
                    System.out.printf("\n");
                } else {
                    System.out.printf(" | ");
                }
                line++;
            }
        } // example board with coordinates written out
        clearBoard();
        display();

        do {
            System.out.printf("\nPlayer %S enter your move coordinates.", player); //player inputs move
            int rowMove = SafeInput.getRangedInt(in, "Choose row coordinate", 0, ROW);
            int colMove = SafeInput.getRangedInt(in, "Choose column coordinate",0, COL);
            if (!isValidMove(rowMove, colMove)) {
                do {
                    System.out.printf("\nInvalid coordinate Row: %d Column: %d. Please try again.\n", rowMove, colMove);
                    display();
                    rowMove = SafeInput.getRangedInt(in, "Choose row coordinate", 0, ROW);
                    colMove = SafeInput.getRangedInt(in, "Choose column coordinate", 0, COL);
                } while (!isValidMove(rowMove, colMove)); //ensure valid move input
                board[rowMove][colMove] = player;
                display();
            }
            else {
                board[rowMove][colMove] = player;
                display();
            }

            switch (moveCount) { //check for Win/Tie scenarios when possible
                case 4, 5, 6: //earliest move a win could occur
                    end = isWin(player);
                    break;
                case 7, 8, 9:
                    end = isWin(player) || isTie();
                    break;
                default:
                    end = false;
                    break;
            }
            moveCount += 1; //keep track of number of moves made

            if (end) { //end = true: game should end, reset board, moveCount, and player to X, prompt to play again.
                play = SafeInput.getYNConfirm(in, "\nPlay again?");
                if (play) {
                    clearBoard();
                    moveCount = 0;
                    player = "X";
                    display();
                }
                else {
                    System.out.printf("Thanks for playing.");
                    break;
                }
            }
            else {
                switch (player) { //toggle player between X and O
                    case "X":
                        player = "O";
                        break;
                    case "O":
                        player = "X";
                        break;
                }
            }
        } while (play); //loop for replay-ability
    }
}