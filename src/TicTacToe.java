import java.util.Scanner;
public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String space = " ";
    private static String board [][] = new String[ROW][COL];

    //step 5 'helper' methods
    private static void clearBoard() { //clear to space and set player to X(first move)
        for (int r = 0; r < ROW; r++) {
            for (int c = 1; c < COL; c++) {
                board[r][c] = space;
            }
        }
    }
    private static void display() { //used for prompt to show game to user
        int line = 0; // Display board
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                System.out.printf(board[r][c]);
                if (line == (ROW) - 1  || line == (ROW*2)-1 || line == (ROW*3) - 1) {
                    System.out.printf("\n");
                }
                else {
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
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if(board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(player)) {
            return true;
        }
        return false;
    }
    private static boolean isWin(String player) { //checks for win from X or O, using isColWin, isRowWin, and isDiagWin
        if (isColWin(player) || isRowWin(player) || isDiagWin(player)) {
            return true;
        }
        else {
            return false;
        }
    }
    private static boolean isColTie() {

        for (int c = 0; c < COL; c++) { //COL Tie
            if (board[0][c].equals("X") || board[1][c].equals("X") || board[2][c].equals("X")) {
                if (board[0][c].equals("O") || board[1][c].equals("O") || board[2][c].equals("O")) {
                    return true; //Is Col Tie in column c
                } else {
                    return false; //Is not Col Tie (There is blank space in column c)
                }
            } else {
                return false; //Is not a Col Tie
            }
        }
        return false;
    }
    private static boolean isRowTie() {
        for (int r = 0; r < COL; r++){ //ROW tie
            if(board[r][0].equals("X") || board[r][1].equals("X") || board[r][2].equals("X")) {
                for (r = 0; r < COL; r++) {
                    if (board[r][0].equals("O") || board[r][1].equals("O") || board[r][2].equals("O")) {
                        return true; //Row tie in row r
                    } else {
                        return false; //Not Row tie (blank space in row r)
                    }
                }
            } else {
                return false; //Not a Row tie
            }
        }
        return false;
    }
    private static boolean isDiagTie() {
        boolean diagTie = false;
        boolean diagTie2 = false;
        if (board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) { //Diag Tie
            if (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")) {
                diagTie = true;
            } else {
                diagTie = false;
            }
        } else {
            diagTie = false;
        }
        if (board[2][0].equals("X") || board[1][1].equals("X") || board[0][2].equals("X")) { //Diag Tie
            if (board[2][0].equals("O") || board[1][1].equals("O") || board[0][2].equals("O")) {
                System.out.printf("Diag Tie\n");
                diagTie2 = true;
            } else {
                diagTie2 = false;
            }
        } else {
            diagTie2 = false;
        }
        if (diagTie || diagTie2) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean isTie() {//checks for tie condition(all spaces filled or X and O in every win condition)
        if (isColTie() || isRowTie() || isDiagTie()) {
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
        boolean play = true;

        do {
            do {
                
            } while (!isWin(player) || !isTie());
            System.out.printf("Player %S enter your move coordinates.", player);
            int rowMove = SafeInput.getRangedInt(in, "Choose row coordinate", 0, ROW);
            int colMove = SafeInput.getRangedInt(in, "Choose column coordinate",0, COL);
            do {
                System.out.printf("Invalid coordinate Row: %d Column: %d. Please try again.", rowMove, colMove);
                rowMove = SafeInput.getRangedInt(in, "Choose row coordinate", 0, ROW);
                colMove = SafeInput.getRangedInt(in, "Choose column coordinate",0, COL);
            } while (!isValidMove(rowMove, colMove));

            if (player == "X") { //toggle player from X to O, or vice versa
                player = "O";
            }
            else {
                player = "X";
            }

            play = SafeInput.getYNConfirm(in, "Play again?");
            clearBoard();
            moveCount = 0;
            player = "X";
        } while (play == true);


    }
}