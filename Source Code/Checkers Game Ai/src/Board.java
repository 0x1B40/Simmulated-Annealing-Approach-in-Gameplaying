public class Board {


    int boardSize = 8;
    char board[][];
    char emptySlot = 'X';
    int pieceCount;



    // fillWhiteSoldiers(): takes as input player object, fills the board with player 1 pieces.

   public void fillWhiteSoldiers(Player player)
   {
           // fill whiteSoldiers

//           for (int i = 0; i < 2; i++)
//               for (int j = 0; j < boardSize; j++)
//                   board[i][j] = player.getPiece(); //fill Soldiers per row
       for(int j =0;j<boardSize;j++)
       {
           if(j==((j/2)*2)) // if even
           {
               board[boardSize-1][j] = player.getPiece();
               board[boardSize-3][j] = player.getPiece();
           }
           else
           {
               board[boardSize-2][j] = player.getPiece();
           }
       }

               // next row



   }
    // End of fillWhiteSoldiers().

    // fillBlackSoldiers() : takes as input player object, fills the board with player 2 pieces.

    public void fillBlackSoldiers(Player player)
    {
        // fill blackSolider
        for(int j =0;j<boardSize;j++)
        {
            if(j==((j/2)*2)) // if even
            {
                board[0][j] = player.getPiece();
                board[2][j] = player.getPiece();
            }
            else
            {
                board[1][j] = player.getPiece();
            }
        }

    }


// this was never used.

//    void printBoard()
//    {
//
//        // print board
//        System.out.println("Printing board:");
//
//        for (int i = 0; i < boardSize; i++)
//        {
//
//            for (int j = 0; j < boardSize; j++)
//            {
//
//
//                System.out.print(board[i][j] + "\t"); // print each row.
//
//            }
//            System.out.println();
//            System.out.println();
//
//
//        }
//
//    }


    // fillBoardEmpty(): used to fill the board with empty character

    void fillBoardEmpty()
    {




        for (int i = 0; i < boardSize; i++)
        {

            for (int j = 0; j < boardSize; j++)
            {
                board[i][j] = emptySlot;
            }

        }


    }

    // end of fillBoardEmpty()


    //Default Constructor:

    Board(Player player1,Player player2)
    {
        board = new char[boardSize][boardSize];

        fillBoardEmpty();                                      // fills board with empty character.
        fillWhiteSoldiers(player1);                            // fill the board with player 1 pieces.
        fillBlackSoldiers(player2);                            // fill the board with player 2 pieces.
        player1.setEnemySolider ( player2 );                   // this sets the enemy soliders pieces inside the player 1 class.
        player2.setEnemySolider ( player1 );                   // this sets the enemy soliders pieces inside the player 2 class.
        player1.setPositions(board);                           // sets the board for player 1.
        player2.setPositions(board);                           // sets the board for player 2.


    }
    Board()
    {
        board = new char[boardSize][boardSize];
    }

    // End of default constructor.

    public void updateBoard(Player player)
    {

        board = player.getPositions();


    }

    public void move(int positionX,int positionY,char movement)
    {






    }

    public char[][] getBoard()
    {

        return board;
    }

    void setBoard(char list[][])
    {
        board=list;

    }

    void setPieceCount(int x)
    {
        pieceCount = x;
    }
    int getPieceCount()
    {
        return pieceCount;
    }





}
