import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int row;
        int col;
        String Name1 = "Player 1";
        String P1 = " X ";
        String Name2 = "Player 2";
        String P2 = " O ";
        String playerMove;
        String playerString;

        do{
            clearBoard();
            display();
            int plays = 0;
            for (int turns = 0; turns < 9; turns++)
            {
                if (plays % 2 == 0)
                {
                    playerMove = Name1;
                    playerString = P1;
                }
                else
                {
                    playerMove = Name2;
                    playerString = P2;
                }

                System.out.println("Its " + playerMove + "'s turn!" );

                do {
                    row = SafeInput.getRangedInt(in, "Enter your row coordinate", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in, "Enter your column coordinate", 1, 3) - 1;
                }while(!isValidMove(row, col));

                board[row][col] = playerString;
                display(); //Refreshes Display
                plays += 1;



                if (plays >= 4) ///Check for full row/col
                {
                    if (isWin(playerString))
                    {
                        System.out.println(playerMove + " Wins!");
                        break;
                    }
                    else if (plays >= 7) //Impossible for a winner once past 7 turns and no winner
                    {
                        if(isTie())
                        {
                            System.out.println("Its a tie");
                            break;
                        }
                    }
                }
            }
        }while(SafeInput.getYNConfirm(in, "Play Again?"));
    }

    private static void display()
    {
        String displayBoard = "";
        for (int r = 0; r < ROW; r++)
        {
            for (int c = 0; c < COL; c++)
            {
                if (c == COL - 1)
                {
                    displayBoard += board[r][c];
                } else
                {
                    displayBoard += board[r][c] + "|";
                }
            }
            if (r != ROW - 1)
            {
                displayBoard += "\n---+---+---\n";
            }
        }
        System.out.println(displayBoard);
    }
    private static void clearBoard()
    {
        for (int r = 0; r < ROW; r++)
        {
            for (int c = 0; c < COL; c++)
            {
                board[r][c] = "   ";
            }
        }
    }
    private static boolean isValidMove(int row, int col)
    {
        return (board[row][col].equals("   "));
    }

    //Checking for Wins//

    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        if ((board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) ||
                (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static boolean isTie()
    {
        int deadX = 0;
        int deadO = 0;
        int numTies = 0;
        for (int r = 0; r < ROW; r++)
        {
            for (int c = 0; c < COL; c++)
            {
                if (board[r][c].equals(" X "))
                {
                    deadX++;
                }
                else if (board[r][c].equals(" O "))
                {
                    deadO++;
                }
                if (deadX >= 1 && deadO >= 1) {
                    numTies++;
                }
            }
        }
        if (numTies >= 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}