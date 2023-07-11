import java.util.Random;
public class Player {

    // Variables declaration:

    char myBoardPositions[][];    // declare a two dimensional array that stores board information.
    int boardSize = 8;
    char mySoldier;
    char myKnight;
    char enemySoldier;
    char enemyKnight;
    int pieceCount;            // counts the number of pieces eaten. when it's 0, the player has won.
    Move nextMove;
    // related only to minimax sofwar
    int maxDepth;
    int depth;
    boolean flipped;
    // end of related to minimax
    //Start of SA section:
    PossibleMoves possibleMoves;
    // we need function computePossibleMoves


    // End of variable declaration.

    // Default constructor:
    Player (char soliderX, char knightX, char enemySoldierX, char enemyKnightX) {

        // Initialize variables:
        mySoldier = soliderX;
        myKnight = knightX;
        enemySoldier = enemySoldierX;
        enemyKnight = enemyKnightX;
        myBoardPositions = new char[boardSize][boardSize];
        pieceCount = 16;
        depth = 0;
        maxDepth = 100;
        flipped = false;


        // Fill the board with empty positions as 'X'
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                myBoardPositions[i][j] = 'X';

        // End of Filling the board empty.


        // Fill player's board with default soldiers
//        for (int i = 0; i < boardSize; i++)
//            for (int j = 0; j < 2; j++)
//                myBoardPositions[j][i] = enemySoldier;
//
//        for (int i = 0; i < boardSize; i++)
//            for (int j = boardSize-2; j < boardSize; j++)
//                myBoardPositions[j][i] = mySoldier;

        //filling my Soldiers
        for(int j =0;j<boardSize;j++)
        {
            if(j==((j/2)*2)) // if even
            {
                myBoardPositions[boardSize-1][j] = mySoldier;
                myBoardPositions[boardSize-3][j] = mySoldier;
            }
            else
            {
                myBoardPositions[boardSize-2][j] = mySoldier;
            }
        }

        flipBoard ();

        // filling enemy Soldier
        for(int j =0;j<boardSize;j++)
        {
            if(j==((j/2)*2)) // if even
            {
                myBoardPositions[boardSize-1][j] = enemySoldier;
                myBoardPositions[boardSize-3][j] = enemySoldier;
            }
            else
            {
                myBoardPositions[boardSize-2][j] = enemySoldier;
            }
        }

        flipBoard ();






        // End of filling the board.


        // End of initialization.

    } // End of default constructor.


    // copy constructor

    Player (Player player2) {
        boardSize = 8;
        myBoardPositions = new char[boardSize][boardSize];
        // hard copy board.
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                myBoardPositions[i][j] = player2.getPositions ()[i][j];
            }

        }


        mySoldier = player2.getMySoldier ();
        myKnight = player2.getMyKnight ();
        enemySoldier = player2.getEnemySoldier ();
        enemyKnight = player2.getEnemyKnight ();
        pieceCount = player2.getPieceCount ();
        nextMove = player2.getNextMove ();
        maxDepth = player2.getMaxDepth ();
        depth = player2.getDepth ();
        flipped = player2.getFlipped ();


    }

    void copyPlayer (Player player2) {

        boardSize = 8;
        myBoardPositions = new char[boardSize][boardSize];
        // hard copy board.
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                myBoardPositions[i][j] = player2.getPositions ()[i][j];
            }

        }


        mySoldier = player2.getMySoldier ();
        myKnight = player2.getMyKnight ();
        enemySoldier = player2.getEnemySoldier ();
        enemyKnight = player2.getEnemyKnight ();
        pieceCount = player2.getPieceCount ();
        nextMove = player2.getNextMove ();
        maxDepth = player2.getMaxDepth ();
        depth = player2.getDepth ();
        flipped = player2.getFlipped ();


    }


    // function getPositions(): returns the player's board.

    char[][] getPositions () {

        return myBoardPositions;

    }

    // End of function getPositions().


    // function setPosition(): sets the player's board, by taking another board as input.

    void setPositions (char[][] positions) {

        myBoardPositions = positions;

    }

    // End of function: setPositions().


    // function getPiece(): returns player's Soldier's char.

    char getPiece () {
        return mySoldier;
    }

    // End of function getPiece().

    void setPieceCount (int x) {
        pieceCount = x;
    }
    // function getPieceCount(): returns the player's pieceCount. Used to check if it's 0 or not in order to end the game.

    int getPieceCount () {
        return pieceCount;
    }

    // End of function getPieceCount().

    int getDepth () {
        return depth;
    }

    int getMaxDepth () {
        return maxDepth;
    }

    Move getNextMove () {
        return nextMove;
    }

    int calculatePieceCount () {
        pieceCount = 0;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (myBoardPositions[i][j] == enemyKnight || myBoardPositions[i][j] == enemySoldier)
                    pieceCount++;

            }
        }

        return pieceCount;
    }

    // function printPlayerBoard(): prints player's board.

    void printPlayerBoard () {


        System.out.println ( "Printing board:" );
        System.out.print ( "\t" );           // to make it more spacious.


        // Prints column indices.

        for (int x = 0; x < boardSize; x++)
            System.out.print ( x + "\t" );

        System.out.println (); // just to make new lines.
        System.out.println (); // ^ same.
        // End of printing column indices.


        // print row indices along with the board itself:
        for (int i = 0; i < boardSize; i++) {
            System.out.print ( i + "\t" );
            for (int j = 0; j < boardSize; j++)
                System.out.print ( myBoardPositions[i][j] + "\t" ); // print each row.

            System.out.println (); // just to make new lines.
            System.out.println (); // ^ same.
        }
        // End of board and row index printing.

    }
    // end of function: printPlayerBoard().


    // function: flipHorizontal(): to swap the horizontal array.

    void flipHorizontal (int row) {       // swap the content of a row.
        char temp;
        for (int j = 0; j < (boardSize / 2); j++) {
            temp = myBoardPositions[row][j];
            myBoardPositions[row][j] = myBoardPositions[row][boardSize - 1 - j];
            myBoardPositions[row][boardSize - 1 - j] = temp;

        }

    }
    // end of function: flipHorizontal().


    // function: flipVertical(): to swap vertical array.
    void flipVertical (int column) {

        char temp;
        for (int i = 0; i < (boardSize / 2); i++) {
            temp = myBoardPositions[i][column];
            myBoardPositions[i][column] = myBoardPositions[boardSize - 1 - i][column];
            myBoardPositions[boardSize - 1 - i][column] = temp;

        }


    }
    // End of function: flipVertical().

    void setFlipped () {
        if (flipped)
            flipped = false;
        else
            flipped = true;
    }

    boolean getFlipped () {
        return flipped;
    }


// function: flipBoard(): to flip the board, uses flipHorizontal and flipVertical to loop through the board and swap the arrays in them.

    void flipBoard () {
        for (int i = 0; i < boardSize; i++)
            flipHorizontal ( i );

        for (int j = 0; j < boardSize; j++)
            flipVertical ( j );

    }

    // End of function: flipBoard().


// function: movePiece(), take as input positionX,positionY, and the movement character.
    // then it checks if the movement is legal.
    // then it processes the movement if it's legal, otherwise prints error message.


    boolean movePiece (int positionX, int positionY, char movementDirection) {

        boolean ate[];
        int legalX = 0;
        int legalY =0;

     ate= checkMovementLegal(positionX,positionY,movementDirection);



        if (ate[0]) {
            // IF IT'S UPPER LEFT:
            if (movementDirection == 'L') {

                if (myBoardPositions[positionX - 1][positionY - 1] == 'X') // If upper left of the position is empty.
                {
                    if (positionX == 1) {
                        myBoardPositions[positionX][positionY] = myKnight;
                    }
                    myBoardPositions[positionX - 1][positionY - 1] = myBoardPositions[positionX][positionY]; // move to the upper left.
                    myBoardPositions[positionX][positionY] = 'X'; // empty the original position.

                } else { // if the upper left of the position has enemy solider or knight
                    if (positionX == 2) {
                        myBoardPositions[positionX][positionY] = myKnight;
                    }

                    myBoardPositions[positionX - 2][positionY - 2] = myBoardPositions[positionX][positionY]; // move to the NEXT UPPER LEFT where it is empty.
                    //  We know it's empty because we checked with checkMovementLegal if the NEXT UPPER LEFT is empty or not.
                    myBoardPositions[positionX][positionY] = 'X'; // clear original position.
                    myBoardPositions[positionX - 1][positionY - 1] = 'X'; // clear the position we've eaten.
                    pieceCount--; // every time a piece is eaten, pieceCount is decrement to determine whether the player has won or not.
                    legalX = positionX-2;
                    legalY = positionY-2;

                }

            } // END OF IF IT'S UPPER LEFT.


            // IF IT's UPPER RIGHT:
            else if (movementDirection == 'R') {
                if (myBoardPositions[positionX - 1][positionY + 1] == 'X')  // If upper right of the position is empty.
                {
                    if (positionX == 1) {
                        myBoardPositions[positionX][positionY] = myKnight;
                    }

                    myBoardPositions[positionX - 1][positionY + 1] = myBoardPositions[positionX][positionY]; // move to the upper right.
                    myBoardPositions[positionX][positionY] = 'X'; // clear original position.

                } else { // if upper right has enemy soldier or knight.
                    if (positionX == 2) {
                        myBoardPositions[positionX][positionY] = myKnight;
                    }
                    myBoardPositions[positionX - 2][positionY + 2] = myBoardPositions[positionX][positionY]; // move to the NEXT UPPER RIGHT.
                    myBoardPositions[positionX][positionY] = 'X'; // clear original position.
                    myBoardPositions[positionX - 1][positionY + 1] = 'X'; // clear upper right position.
                    pieceCount--; // decrement pieceCount. used to check if the player has won or not.
                    legalX = positionX-2;
                    legalY = positionY+2;

                }

            }
            // END OF IT'S UPPER RIGHT.


            // IF IT'S LOWER LEFT:
            else if (movementDirection == 'G') {
                if (myBoardPositions[positionX + 1][positionY - 1] == 'X')        // if lower left position is empty.
                {
                    myBoardPositions[positionX + 1][positionY - 1] = myBoardPositions[positionX][positionY]; // move to lower left.
                    myBoardPositions[positionX][positionY] = 'X'; // empty original position.

                } else {                                                            // if lower left position has enemy knight or soldier.
                    myBoardPositions[positionX + 2][positionY - 2] = myBoardPositions[positionX][positionY]; // move to the NEXT LOWER LEFT.
                    myBoardPositions[positionX][positionY] = 'X'; // empty original position.
                    myBoardPositions[positionX + 1][positionY - 1] = 'X'; // empty enemy solider or knight's position.
                    pieceCount--; // decrement pieceCount. used to check if the player has won.
                    legalX = positionX+2;
                    legalY = positionY-2;

                }


            }
            // END OF IF IT'S LOWER LEFT.


            // IF IT'S LOWER RIGHT.
            else if (movementDirection == 'H') {
                if (myBoardPositions[positionX + 1][positionY + 1] == 'X') // If lower right position is empty.
                {
                    myBoardPositions[positionX + 1][positionY + 1] = myBoardPositions[positionX][positionY]; // move to lower right.
                    myBoardPositions[positionX][positionY] = 'X'; // empty original position.

                } else {                                                           // if lower right has enemy soldier or knight.
                    myBoardPositions[positionX + 2][positionY + 2] = myBoardPositions[positionX][positionY]; // move to NEXT LOWER RIGHT.
                    myBoardPositions[positionX][positionY] = 'X'; // Empty original position.
                    myBoardPositions[positionX + 1][positionY + 1] = 'X'; // empty enemy soldier or knight position.
                    pieceCount--; // decrement piece count.
                    legalX = positionX+2;
                    legalY = positionY+2;

                }


            }
            // END OF IF IT'S LOWER RIGHT.

        } else             // If checkMovementLegal() turns out to be false.
        {
         //   System.out.println("Movement is Illegal");
        }

        if(ate[1]) // if it resulted in us eating, then we must check if we can eat some more, if we can we return true, otherwise return false
        {
            ate[1] = false;

                    ate=  checkMovementLegal ( legalX,legalY,'L' );
                    if(ate[1])
                        return true;
                    ate = checkMovementLegal ( legalX,legalY,'R' );
                    if(ate[1])
                        return true;
                    ate = checkMovementLegal ( legalX,legalY,'G' );
                    if(ate[1])
                        return true;
                    ate= checkMovementLegal ( legalX,legalY,'H' );
                    if(ate[1])
                        return true;





            // keep cuz not sure about the law yet...
//            ate[1] = false;
//            for(int i =0;i<boardSize;i++)
//            {
//                for(int j=0;j<boardSize;j++)
//                {
//                  ate=  checkMovementLegal ( i,j,'L' );
//                  if(ate[1])
//                    return true;
//                  ate = checkMovementLegal ( i,j,'R' );
//                  if(ate[1])
//                      return true;
//                  ate = checkMovementLegal ( i,j,'G' );
//                  if(ate[1])
//                      return true;
//                ate= checkMovementLegal ( i,j,'H' );
//                if(ate[1])
//                    return true;
//
//                }
//            }
        }

return false; // why false? because if we ate and found another eatable move, then we would've returned true, if we didnt eat then its automatically false
    }


    // End of function: movePiece().


    // function: checkMovementLegal(), take positionX,positionY, and the movementDirection as input, used in movePiece function.
    // used to check if the movement is legal.
    // return true or false, along with error message if it's false.
    // NOTE: CHECK main page to UNDERSTAND the positioning of the board.

    boolean [] checkMovementLegal (int positionX, int positionY, char movementDirection) {
        boolean eat[] = new boolean[2];
        PossibleMoves tempPossibleMoves = new PossibleMoves ();
        PossibleMoves tempPossibleEatMoves = new PossibleMoves();
        for (int i = 0; i < boardSize; i++) {

            for (int j = 0; j < boardSize; j++) {

                if (myBoardPositions[i][j] == mySoldier || myBoardPositions[i][j] == myKnight)
                // checks if selected position is this player's knight or soldier.
                {



                    if ( (i > 0) && j> 0)
                    // if moving to the UPPER LEFT, and the position of rowIndex(positionX) is greater than zero( not in the upper most of the board) and
                    // position of columnIndex(positionY) is greater than zero( not in the left most of the board).
                    {
                        if (myBoardPositions[i - 1][j - 1] == 'X' || myBoardPositions[i - 1][j - 1] == enemySoldier || myBoardPositions[i - 1][j - 1] == enemyKnight) {
                            // if the UPPER LEFT position is empty then or it has enemy soldier or knight then:
                            if (myBoardPositions[i - 1][j- 1] == 'X') // if the UPPER LEFT is empty then:
                                tempPossibleMoves.addPossibleMove ( i,j,'L' ); // movement is legal.


                            else if ((myBoardPositions[i - 1][j - 1] == enemySoldier || myBoardPositions[i - 1][j - 1] == enemyKnight) && ((i - 2) > -1 && j - 2 > -1 && myBoardPositions[i - 2][j - 2] == 'X'))
                                // if the UPPER LEFT position has enemy knight or enemy soldier AND the NEXT UPPER LEFT IS EMPTY then:
                                tempPossibleEatMoves.addPossibleMove ( i,j,'L' ); // movement is legal.
                            else {
                                //   System.out.println ( "cannot move to the UPPER LEFT, Either: not empty, has your soldiers or moving to the NEXT UPPER LEFT is Illegal" );
                            }

                        } else {
                            //System.out.println ( "UPPER LEFT is neither empty nor has enemy soldiers, in other words: it has your soldier." );
                             // movement is illegal.
                        }

                    }

                    // END OF checking UPPER LEFT movement.

                    // Check UPPER RIGHT MOVEMENT.
                     if ( (i > 0) && (j < boardSize - 1))  // check if chosen movement is to the right,
                    // and if the positionX(rowIndex) is not in the UPPER MOST position, and positionY(columnIndex) is not in the RIGHT MOST position.
                    {

                        if (myBoardPositions[i - 1][j + 1] == 'X' || myBoardPositions[i - 1][j + 1] == enemySoldier || myBoardPositions[i - 1][j + 1] == enemyKnight) {
                            // if position in the UPPER RIGHT is empty or has enemy soldier or enemy knight then:
                            if (myBoardPositions[i - 1][j+ 1] == 'X') // If position in the UPPER RIGHT is empty.
                                tempPossibleMoves.addPossibleMove ( i,j,'R' ); // movement is legal.

                            else if ((myBoardPositions[i - 1][j + 1] == enemySoldier || myBoardPositions[i - 1][j + 1] == enemyKnight) && ((i - 2) > -1 && j + 2 < (boardSize) && myBoardPositions[i - 2][j + 2] == 'X'))
                                // check if position in the UPPER RIGHT is enemy soldier AND position in the NEXT UPPER RIGHT is empty
                                tempPossibleEatMoves.addPossibleMove ( i,j,'R' ); // movement is legal.
                            else {
                                //   System.out.println ( "cannot move to the UPPER RIGHT, Either: not empty, has your soldiers or moving to the NEXT UPPER RIGHT is Illegal" );
                            }
                        } else {
                            //   System.out.println ( "UPPER RIGHT is neither empty nor has enemy soldiers, in other words: it has your soldier." );
                            // movement is illegal.
                        }


                    }
                    // End of Check UPPER RIGHT movement.


                    // Check LOWER LEFT movement.
                     if ((myBoardPositions[i][j] == myKnight)) // Check if selected movement is to the LOWER LEFT & selected unit is the player's knight.
                    {
                        if (i < (boardSize - 1) && (j > 0)) // Check if the position in not in the LOWEST or LEFTEST position.
                        {
                            if (myBoardPositions[i + 1][j - 1] == 'X' || myBoardPositions[i + 1][j - 1] == enemyKnight || myBoardPositions[i + 1][j- 1] == enemySoldier)
                            // check if the position in the LOWER LEFT is either empty or has enemy knight or solider
                            {
                                if (myBoardPositions[i + 1][j - 1] == 'X') // if position in the LOWER LEFT is empty.
                                    tempPossibleMoves.addPossibleMove ( i,j,'G' ); // movement is legal.
                                else if ((myBoardPositions[i + 1][j - 1] == enemyKnight || myBoardPositions[i + 1][j - 1] == enemySoldier) && (i + 2) < (boardSize) && (j - 2) > -1)
                                // if position in the LOWER LEFT has enemy knight or soldier.
                                {
                                    if (myBoardPositions[i + 2][j - 2] == 'X') // if the position in the NEXT LOWER LEFT IS EMPTY
                                        tempPossibleEatMoves.addPossibleMove ( i,j,'G' ); // movement is legal, because position is empty.
                                    else {
                                        //    System.out.println("position in the NEXT LOWER LEFT IS NOT EMPTY");
                                         // movement is illegal, because the position is not empty.
                                    }
                                } else {
                                    //  System.out.println("cannot move to the LOWER LEFT, Either: not empty, has your soldiers or moving to the NEXT LOWER LEFT is Illegal");
                                     // movement is illegal, because position selected is neither empty nor has enemy pieces, it has your pieces.
                                }
                            } else {
                                //  System.out.println("LOWER LEFT has your soldier.");
                                 // movement is illegal, because position selected is neither empty nor has enemy pieces, it has your pieces.
                            }
                        }

                    }  if ((myBoardPositions[i][j] == myKnight)) // check if selected movement is to the LOWER RIGHT & selected piece is the player's knight.
                    {
                        if (i < (boardSize - 1) && j < (boardSize - 1))  // check if position is not in the LOWEST or in the RIGHTMOST position.
                        {
                            if (myBoardPositions[i + 1][j + 1] == 'X' || myBoardPositions[i + 1][j + 1] == enemyKnight || myBoardPositions[i + 1][j + 1] == enemySoldier)
                            // check if the LOWER RIGHT position is empty or has enemy knight or enemy soldier.
                            {
                                if (myBoardPositions[i + 1][j + 1] == 'X') // check if the LOWER RIGHT position is empty.
                                    tempPossibleMoves.addPossibleMove ( i,j,'H' ); // movement is legal.
                                else if ((myBoardPositions[i + 1][j + 1] == enemyKnight || myBoardPositions[i + 1][j + 1] == enemySoldier) && (i + 2) < (boardSize) && (j + 2) < (boardSize))
                                // check if the LOWER RIGHT position has enemy knight or enemy soldier & the NEXT LOWER RIGHT is not out of bounds.
                                {
                                    if (myBoardPositions[i + 2][j + 2] == 'X') // check if the next LOWER RIGHT is empty.
                                        tempPossibleEatMoves.addPossibleMove(i,j,'H'); // movement is legal, because position is empty.
                                    else {
                                        //    System.out.println ( "movement is illegal, position at the next LOWER RIGHT is not empty." );
                                         // movement is illegal, position at the next LOWER RIGHT is not empty.

                                    }
                                } else {
                                    //System.out.println ( "movement is illegal, position selected has your piece." );
                                     // movement is illegal, because position selected is neither empty nor has enemy pieces, it has your pieces.
                                }
                            } else {
                                //  System.out.println ( "movement is illegal, position selected has your piece." );
                                 // movement is illegal, because position selected is neither empty nor has enemy pieces, it has your pieces.
                            }
                        } else {
                            //   System.out.println("movement is illegal, position is in the Rightmost or lowest position.");
                        }

                    }


                } else {
                    // System.out.println ( "movement illegal, because either enemy solider was selected or empty position was selected" );

                }

            }
        }
        eat[1] = false;
        eat[0] = false;
        // 0 means its legal
        // 1 means it can make another move


        if(tempPossibleEatMoves.currentSize>0)
        {
            if(tempPossibleEatMoves.compareMove(positionX,positionY,movementDirection))
            {
                eat[0] = true;
                eat[1] = true;
                return eat;
            }
            else {
                eat[0] = false;
                eat[1] = false;
                return eat;
            }

        }
        else if(tempPossibleMoves.currentSize>0)
        {
            if(tempPossibleMoves.compareMove(positionX,positionY,movementDirection))
            {
                eat[0] = true;
                eat[1] = false;
                return eat;
            }
            else {
                eat[0] = false;
                eat[1] = false;
                return eat;
            }
        }
        else {
            eat[0] = false;
            eat[1] = false;
            return eat;
        }


    }
// END OF checkMovementLegal().

    // self explanatory.

    char getMySoldier () {
        return mySoldier;

    }

    // self explanatory.

    char getMyKnight () {

        return myKnight;
    }

    // setEnemySolider(): takes player object as input, retrieve their knights char, stores them as enemyKnight and enemySoldier.

    void setEnemySolider (Player player) {
        enemyKnight = player.getMyKnight ();
        enemySoldier = player.getMySoldier ();


    }

    void setMySoldier (char x) {
        mySoldier = x;
    }

    void setMyKnight (char x) {
        myKnight = x;
    }

    void setMyEnemySoldier (char x) {
        enemySoldier = x;
    }

    void setMyEnemyKnight (char x) {
        enemyKnight = x;
    }

    char getEnemySoldier () {
        return enemySoldier;
    }

    char getEnemyKnight () {
        return enemyKnight;
    }

    void setDepth (int x) {
        depth = x;
    }

    void setMaxDepth (int x) {
        maxDepth = x;
    }


    // AI stuff:
    // we dont use this chit:
//    Move evaluatePlayerMinimax (Player myPlayer) {
//
//        evaluateBoard ( myPlayer );
//        myPlayer.flipped = false;
//        myPlayer.depth = 0;
//        return myPlayer.nextMove;
//
//
//    }

//    int evaluateBoard (Player player) {
//        Tree playerTree = new Tree (); // the tree is not specific to the player, the tree is not store in the player's set of variables. its only specific to this function.
//        int moveValue;
////        Board myBoard = new Board();
/////        Player board = player; // I think the big problem is here
//        Player board = new Player ( player ); // call copy constructor to copy the object.
//        depth++; // depth is specific to this class object.
//        board.printPlayerBoard ();
//
//        // how do we check for every possible move?
//// answer: we look for every piece on the board, if it’s mySoldier or myKnight, then
//        // for every position:
//        for (int i = 0; i < boardSize; i++) {
//            for (int j = 0; j < boardSize; j++) {
//                if (board.checkMovementLegal ( i, j, 'L' )) // we have to do this because it's specific to the board itself. not the local stuff.
//                {
//                    //  board.printPlayerBoard(); // for debugging purposes
//                    moveValue = evaluateMove ( i, j, 'L', board ); // we dont use board.evaluateMove() correct? i think so because we want to keep track of depth. // same goes with evaluateMove()
//                    playerTree.addMove ( i, j, 'L', moveValue );
//                    //   playerTree.printTree();
//                }
//
//                if (board.checkMovementLegal ( i, j, 'R' )) {
//                    //  board.printPlayerBoard();
//                    moveValue = evaluateMove ( i, j, 'R', board );
//                    playerTree.addMove ( i, j, 'R', moveValue );
//                    //     playerTree.printTree();
//                }
//
//
//                if (board.checkMovementLegal ( i, j, 'G' )) {
//                    //    board.printPlayerBoard();
//                    moveValue = evaluateMove ( i, j, 'G', board );
//                    playerTree.addMove ( i, j, 'G', moveValue );
//                    //    playerTree.printTree();
//                }
//
//                if (board.checkMovementLegal ( i, j, 'H' )) {
//                    //   board.printPlayerBoard();
//                    moveValue = evaluateMove ( i, j, 'H', board );
//                    playerTree.addMove ( i, j, 'H', moveValue );
//                    //      playerTree.printTree();
//                }
//            }
//        }
//
//
//        // return the value
//        //  playerTree.printTree();
//        moveValue = playerTree.evaluateTree ();
//        player.nextMove = playerTree.getMoveMax (); // this is only useful in the first level of the tree, no i dont think it is...
//        if (depth == maxDepth)
//            playerTree.printTree ();
//        depth = maxDepth;
//        // register the move to the board.
//        return moveValue;
//
//    }


//    int evaluateMove (int i, int j, char movement, Player board) {
////        Board board;
////        board.setBoard(player.getPositions());
//        // evaluate the worth of a move.
//        Player nextLevelBoard = new Player ( board );
//        int boardPieceCount = nextLevelBoard.getPieceCount ();
//        nextLevelBoard.movePiece ( i, j, movement );
//        boardPieceCount = boardPieceCount - nextLevelBoard.getPieceCount ();
//        // nextLevelBoard.printPlayerBoard();
//
//
//        if (nextLevelBoard.getPieceCount () == 0 || maxDepth == depth)  // if we reach the base case
//        {
//            if (nextLevelBoard.getFlipped ())
//                return -boardPieceCount; // or boardPieceCount, but we want to clarify that it's not because it's pieceCount but rather because it's the worth of the move.
//            else
//                return boardPieceCount;
//        } else {
//
//            nextLevelBoard.flipBoard (); // we flipped the board.
//            char temp = nextLevelBoard.getMySoldier ();
//            nextLevelBoard.setMySoldier ( nextLevelBoard.getEnemySoldier () );
//            nextLevelBoard.setMyEnemySoldier ( temp );
//            temp = nextLevelBoard.getMyKnight ();
//            nextLevelBoard.setMyKnight ( nextLevelBoard.getEnemyKnight () );
//            nextLevelBoard.setMyEnemyKnight ( temp );
//            // we swapped the pieces.
//            nextLevelBoard.calculatePieceCount ();
//            //          nextLevelBoard.printPlayerBoard();
//            // we updated the pieceCount for the new board.
//
//            // before passing it to evaluateBoard
//            boolean currentFlip = nextLevelBoard.getFlipped ();
//            nextLevelBoard.setFlipped ();
//            // end of swapping
//
//            int x = evaluateBoard ( nextLevelBoard );
//
//            if (currentFlip) {
//                return x - boardPieceCount; // like dis?
//            } else
//                return x + boardPieceCount;
//
//        }
//// where is the base case?
//
//
//    }

    // Simulated annealing section:

  //   takes as input the board, produce as output an array of possible moves
//    void computePossibleMoves () {
//        Move tempMove = new Move ();
//        possibleMoves = new PossibleMoves ();
//
//        for (int i = 0; i < boardSize; i++) {
//            for (int j = 0; j < boardSize; j++) {
//                if (checkMovementLegal ( i, j, 'L' )) {
//                    tempMove.setMove ( i, j, 'L' );
//                    possibleMoves.addPossibleMove ( tempMove );
//
//                }
//                if (checkMovementLegal ( i, j, 'R' )) {
//                    tempMove.setMove ( i, j, 'R' );
//                    possibleMoves.addPossibleMove ( tempMove );
//                }
//
//                if (checkMovementLegal ( i, j, 'G' )) {
//                    tempMove.setMove ( i, j, 'G' );
//                    possibleMoves.addPossibleMove ( tempMove );
//                }
//                if (checkMovementLegal ( i, j, 'H' )) {
//                    tempMove.setMove ( i, j, 'H' );
//                    possibleMoves.addPossibleMove ( tempMove );
//                }
//
//
//            }
//
//        }
//
//    }

    PossibleMoves getPossibleMovesList () {
        return possibleMoves;
    }

    void setPossibleMovesList (PossibleMoves x) {
        for (int i = 0; i < x.getCurrentSize (); i++) {
            possibleMoves.listOfPossibleMoves[i] = x.listOfPossibleMoves[i];
        }

    }


    boolean checkGameEnded () {
        calculatePieceCount ();
        if (pieceCount == 0)
            return true;
        else
            return false;

    }

    void swapPieces () {
        char temp;
        temp = mySoldier;
        mySoldier = enemySoldier;
        enemySoldier = temp;

        temp = myKnight;
        myKnight = enemyKnight;
        enemyKnight = temp;
        calculatePieceCount ();

    }

    void switchPlayer () {
        flipBoard ();
        swapPieces ();
    }

    boolean checkEven (int number) {
        int x = number;
        x = x / 2;
        x = x * 2;

        if (x == number)
            return true;
        else
            return false;


    }

    int calculateMyPieces () {
        int myPieceCounter = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {

                if (myBoardPositions[i][j] == mySoldier || myBoardPositions[i][j] == myKnight)
                    myPieceCounter++;

            }

        }

        return myPieceCounter;


    }

    int calculateEnemyPieces () {
        int myPieceCounter = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {

                if (myBoardPositions[i][j] == enemySoldier || myBoardPositions[i][j] == enemyKnight)
                    myPieceCounter++;

            }

        }

        return myPieceCounter;


    }

    double calculateAcceptanceProbability (int currentSolution, int neighborSolution, double temperature) {
        if (neighborSolution > currentSolution)
            return 1.0;

        return Math.exp ( (currentSolution - neighborSolution) / temperature );

    }

    int generateRandomOdd (int maxIndex) {

        Random rand = new Random ();
        int randomNumber = rand.nextInt ( maxIndex );
        while (checkEven ( randomNumber )) {
            randomNumber = rand.nextInt ( maxIndex );
        }

        return randomNumber;


    }

    int generateRandomEven (int maxIndex) {
        Random rand = new Random ();
        int randomNumber = rand.nextInt ( maxIndex );
        while (!checkEven ( randomNumber )) {
            randomNumber = rand.nextInt ( maxIndex );
        }

        return randomNumber;

    }

    double specialEvaluateBoard () {
//        int myPiecesCounter = 0;
//        int hisPiecesCounter = 0;
//        double sum = 0;
//        if ((mySoldier == 'O'))
//            switchPlayer ();
//
//        for (int i = 0; i < boardSize; i++) {
//            for (int j = 0; j < boardSize; j++) {
//                if (myBoardPositions[i][j] == mySoldier) {
//                    myPiecesCounter++;
//                    sum = sum + 1.0; //+ (1.0 / (i + 1.0));
//                } else if (myBoardPositions[i][j] == myKnight) {
//                    myPiecesCounter++;
//                    sum = sum + 2.0;
//                } else if (myBoardPositions[i][j] == enemySoldier) {
//                    hisPiecesCounter++;
//                    sum = sum - (1.0 ); //+ ((i + 1.0) / 8.0));
//                } else if (myBoardPositions[i][j] == enemyKnight) {
//                    hisPiecesCounter++;
//                    sum = sum - 2;
//                }
//
//
//            }
//        }
//
//        if(myPiecesCounter == 0)
//            sum = sum-1000.0;
//        else if(hisPiecesCounter==0)
//            sum = sum+1000.0; // may not even reach to this point
//        else if(myPiecesCounter+hisPiecesCounter<7)
//        {
//            // check for every possible legal move, and make sure that when there are no legal moves you get a penalty.
//            // also as part of what is considered a win, is when you end up in a situation when there is no possible move
//            Point myPoints[];
//            Point hisPoints[];
//            int myCounter =0;
//            int hisCounter =0;
//            myPoints =  new Point[7];
//            hisPoints = new Point[7];
//
//            for(int i =0;i<boardSize;i++)
//            {
//                for(int j =0;j<boardSize;j++)
//                {
//                    if(myBoardPositions[i][j] == mySoldier || myBoardPositions[i][j] == myKnight)
//                    {
//                        myPoints[myCounter] = new Point(); // there was a problem here, i just added this and it worked for some reason
//                        myPoints[myCounter].setPoint ( i,j );
//                        myCounter++;
//                    }
//                    else if(myBoardPositions[i][j] == enemySoldier || myBoardPositions[i][j] == enemyKnight)
//                    {   hisPoints[hisCounter] = new Point();
//                        hisPoints[hisCounter].setPoint(i,j);
//                        hisCounter++;
//                    }
//
//
//
//                }
//
//
//            }
//
//            double mySumI = 0;
//            double mySumJ = 0;
//            double hisSumI = 0;
//            double hisSumJ = 0;
//
//
//            for(int i =0;i<myCounter;i++)
//            {
//
//                mySumI = mySumI + myPoints[i].getPointI ();
//                mySumJ = mySumJ + myPoints[i].getPointJ ();
//
//            }
//
//            mySumI = mySumI/myCounter;
//            mySumJ = mySumJ/myCounter;
//
//            for(int i =0;i<hisCounter;i++)
//            {
//                hisSumI = hisSumI + hisPoints[i].getPointI();
//                hisSumJ = hisSumJ + hisPoints[i].getPointJ();
//
//            }
//
//            hisSumI = hisSumI/hisCounter;
//            hisSumJ = hisSumJ/hisCounter;
//
//
//            double x = mySumI - hisSumI;
//            x = x*x;
//            double y = mySumJ - hisSumJ;
//            y = y*y;
//
//            double difference = y+x;
//
//            double distanceDifference = Math.sqrt(difference); // the lower the better.
//            distanceDifference = distanceDifference * 0.02; //
//
//            sum = sum - distanceDifference; // because its the lower value the better choice for player Q, we subtract the difference from the orginal value of the board, the lower the distance, the lesser the subtraction.
//
//
//
//        }
//
//        return sum;


        // try again:
        int myPieceCounter =0;
        int hisPieceCounter =0;
        double boardValue = 0;
        int myPawnsCounter =0;
        int hisPawnsCounter = 0;

        if ((mySoldier == 'O'))
            switchPlayer ();


        for(int i =0;i<boardSize;i++)
        {
            for(int j =0;j<boardSize;j++)
            {
                if(myBoardPositions[i][j] == mySoldier)
                {
                    myPieceCounter++;
                    myPawnsCounter++;
                    boardValue = boardValue + 5 + (7- i);

                }
                else if(myBoardPositions[i][j] == enemySoldier)
                {
                    hisPieceCounter++;
                    hisPawnsCounter++;
                    boardValue = boardValue -(5+i);
                }
                else if(myBoardPositions[i][j] == myKnight)
                {
                    myPieceCounter++;
                    boardValue = boardValue + 5 + 8 + 2;
                }
                else if(myBoardPositions[i][j] == enemyKnight)
                {
                    hisPieceCounter++;
                    boardValue = boardValue - (5 + 8 + 2);
                }





            }


        }

        double totalPieces = myPieceCounter + hisPieceCounter;
        boardValue = (boardValue/totalPieces);

        if(myPawnsCounter+hisPawnsCounter == 0) // this if clause should be elsed with the previous part
        {
            boolean winning;
           if(myPieceCounter > hisPieceCounter)
               winning = true;
           else
               winning = false;

           // remember the board value should be as low as possible if you're him and as high as possible if you're me

           double totalDistance = 0;

            for(int i =0;i<boardSize;i++)
            {

                for(int j =0;j<boardSize;j++)
                {
                    if(myBoardPositions[i][j] == myKnight)
                    {
                       totalDistance = totalDistance+ totalDistanceBetweenKnights ( i,j ); // true not necessairy

                    }

                }

            }

            if(winning)
            {
                boardValue = totalDistance;

            }
            else
            {
                boardValue = -totalDistance;
            }


        }


        return boardValue;




    }

    double totalDistanceBetweenKnights(int x,int y)
    {
        double horizontal = 0.0;
        double vertical =0.0;
        double sumDistance = 0;




            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (myBoardPositions[i][j] == enemyKnight) {
                        horizontal = y - j;
                        vertical = x - i;

                        vertical = vertical * vertical;
                        horizontal = horizontal * horizontal;
                        sumDistance = sumDistance + Math.sqrt ( (horizontal + vertical) );


                    }

                }
            }






        return sumDistance;

    }



    boolean computePossibility(int randomIndex,int maxDepth,double temperature)
    {

        double energyDifference = randomIndex - maxDepth;
        double exponent = energyDifference/temperature;
        double probability =Math.exp (exponent);
        Random rand = new Random();

        if(rand.nextDouble() < probability )
            return true;
        else
            return false;

    }


    Move evaluateSA(int intelligence,double thinkingDuration,boolean withoutNewModification,int counter) // the right one
    {

        DecisionTree decisionTree = new DecisionTree (); // created a root
        decisionTree.maxDepth = intelligence; // changed the max depth to whatever intelligence value it is.
        NewSequence bestSequence,currentSequence,neighborSequence,tempBestSequence;
        currentSequence = new NewSequence(decisionTree.maxDepth); // this is a real problem



        boolean playerTurn;
        if(mySoldier =='Q')
            playerTurn = true;
        else
            playerTurn = false; // is this necessairy?




        Random rand = new Random();
        int randomIndex;
        //** DEBUGGING ONLY
//        if(counter ==3 ) {
//
//            //first time
//            System.out.println ( "Chosen Depth:" + 0 );
//            decisionTree.generateRandomAtIndex ( 0, this, currentSequence );
//            System.out.println ( "newly generated sequence:" );
//            currentSequence.print ();
//
//            // next times:
//            randomIndex = rand.nextInt ( currentSequence.currentSize ); // WAIT WHAT?! what happens to the damn currentSize?
//            System.out.println ( "Chosen Depth:" + randomIndex );
//            decisionTree.generateRandomAtIndex ( randomIndex, this, currentSequence ); // another suspect, we should print current sequence
//            System.out.println ();
//            System.out.println ( "newly generated sequence:" );
//            currentSequence.print ();
//            System.out.println ();
//
//            decisionTree.traverseTree ( decisionTree.root );  // a less likely suspect, it puts in the root as input because it calls itself
//            System.out.println("traversed tree:");
//            decisionTree.root.nodeSeq.print();
//            System.out.println();
//            System.out.println("root value: " + decisionTree.root.nodeValue);
//
//
//        }


        // traversal result:






        // ** END OF DEBUGGING

        // so far so good
        //System.out.println("current Sequence:");
        //currentSequence.print();
       // System.out.println("Nothing");

        decisionTree.generateRandomAtIndex ( 0,this,currentSequence ); // nothing wrong so far

       // System.out.println();
       // System.out.println("Chosen Depth:" + 0);
       // System.out.println("newly generated sequence:");
       // currentSequence.print();
       // System.out.println();
        // what does generateRandomAtIndex do? starting from the index specified and the board passed onto it, we will fill currentSequence with random set of moves starting from index specified.
        bestSequence= new NewSequence(currentSequence); // content of currentSequence is copied onto bestSequence
       // bestSequence.print();
        decisionTree.traverseTree ( decisionTree.root);
       // System.out.println("traversed sequence:");
       // decisionTree.root.nodeSeq.print();
       // System.out.println("root value:");
       // System.out.println((decisionTree.root.nodeValue));
        //decisionTree.print(decisionTree.root);





        bestSequence.copySequence( decisionTree.root.nodeSeq); // may not be necessairy to copy the whole sequence but, just to be sure
       // bestSequence.print();
      //  System.out.println("first sequence:");
       // bestSequence.print();
        neighborSequence= new NewSequence(bestSequence);
   //     neighborSequence.print();
        tempBestSequence = new  NewSequence(bestSequence);



        double temperature = 10000.0;
        temperature = thinkingDuration;
        double coolingRate = 0.03;
        int newBestCounter = 0;
        int newSearchCounter = 0;
        int failedSearchCounter = 0;
        int worseSequenceCounter = 0;

        /*  debugging portion:*/

//        NewSequence listOfBestSequences[] = new NewSequence[10000];
//        int listOfBestSequencesCounter = 0;
//        listOfBestSequences[listOfBestSequencesCounter] = new NewSequence(tempBestSequence);
//        listOfBestSequencesCounter++;
        int duplicateCounter = 0;
//




        /*                     */

        while(temperature > 1) {
            //   System.out.println("best Sequence:");
          //  System.out.println("temperature is: "+ temperature);
           // System.out.println("counter: " + counter);
            // bestSequence.print();

            // System.out.println(temperature);

            randomIndex = rand.nextInt ( neighborSequence.currentSize );
            currentSequence.copySequence ( neighborSequence );
      //      System.out.println("current Sequence:");
       //     currentSequence.print();
            decisionTree.generateRandomAtIndex ( randomIndex, this, currentSequence ); // another suspect, we should print current sequence
        //    System.out.println();
         //   System.out.println("Chosen Depth:" + randomIndex);
          //  System.out.println("newly generated sequence:");
           // currentSequence.print();
          //  System.out.println();



            decisionTree.traverseTree ( decisionTree.root );  // a less likely suspect, it puts in the root as input because it calls itself, you dont need to put root in the parameter

    //        System.out.println("traversed tree:");
    //        decisionTree.root.nodeSeq.print();
     //       System.out.println();
       //     System.out.println("root value: " + decisionTree.root.nodeValue);


            if(decisionTree.root.nodeList[tempBestSequence.moveArrayIndex[0]].nodeValue < decisionTree.root.nodeValue || withoutNewModification) // could be either index 0 or 1 or
            tempBestSequence.copySequence (   decisionTree.root.nodeSeq);
            else
                worseSequenceCounter++;// would hard copy make a difference(it shouldn't) but if it did why? ,the crime scene


            // I think perhaps there's a fundamental problem with the comparison or comparison logic, its not yet clear, please clarify it:
            // we compare the bestSequence with the tempBestSequence, if they are the same(and here's the problem IF THEY ARE THE SAME!!! it has nothing to do with them being same?
            // no, the traversal algorithm will return the best sequence, the best sequence can be the same several times, if they are the same, then the newly generated sequence does not produce a better
            // sequence for anyone, if they are different, then the newly generated sequence produced a better sequence for someone (draw it)
            // the causes could be many not just one



            if(bestSequence.compareSequence(tempBestSequence)) //  compareSequence is working, no longer a suspect, but the cause of the problem is them being a...
            {
                bestSequence.copySequence(tempBestSequence);
        //        System.out.println("good news: comparing tempBest with best turned out to be true");

                if(computePossibility ( randomIndex,decisionTree.maxDepth,temperature )) { // one suspect
                    neighborSequence.copySequence ( currentSequence ); // a high level suspect
                    newSearchCounter++; // the crime, if its in the middle then its even harder to spot
                }
                else{
                    failedSearchCounter++;
                }

            }
            else // what it's telling me is that there are sequences here, that are duplicate but somehow not equal to best sequence
            { // a new best sequence

//                listOfBestSequences[listOfBestSequencesCounter] = new NewSequence(tempBestSequence);
//                listOfBestSequencesCounter++;
//                for(int i =0;i<listOfBestSequencesCounter-1;i++)
//                {
//                    if(listOfBestSequences[i].compareSequence ( tempBestSequence ))
//                        duplicateCounter++;
//
//                }


                bestSequence.copySequence ( tempBestSequence );
                neighborSequence.copySequence(tempBestSequence);
              //  System.out.println("new best is found");
                newBestCounter++; // the weapon

            }


            temperature = temperature *(1-coolingRate);



        }
        Move tempMove;
        if(decisionTree.root.currentSize ==0)
        {
             tempMove = new Move('3','3','F');
            System.out.println("simulated annealing discovered contradiction");
            return tempMove;
        }
        else
        tempMove = decisionTree.root.nodeList[bestSequence.moveArrayIndex[0]].getNodeMove ();

        //System.out.println();
        //System.out.println();
        //if(withoutNewModification)
        //{
         //   System.out.println("&");
          //  System.out.println("without new modification:");

        //}
        //else
        //{
          //  System.out.println("*");
           // System.out.println("with new modification:");
        //}

       // System.out.println("New Best Counter: " + newBestCounter);
        //System.out.println("New search counter: " + newSearchCounter); // it cannot be zero or a very low number, because this implies that all of the newly discovered sequences are the best and none of them are worse than the best
        //System.out.println("duplicate counter: " + duplicateCounter); // if this is a positive integer, then either the traversal process or the comparison process is at fault
        //System.out.println("failed search counter:" + failedSearchCounter);
        //System.out.println("worse sequence counter:" + worseSequenceCounter);

        //System.out.println("best solution value: " + decisionTree.root.nodeValue);
        //System.out.println("best sequence: ");
        //decisionTree.root.nodeSeq.print();

        return tempMove;


    }

    boolean checkIdentical(Player board)
    {

        for(int i =0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                if(myBoardPositions[i][j] != board.myBoardPositions[i][j])
                return false;
            }

        }
     return true;

    }

    Move evaluateMinimax(int intelligence) // the problem right now is when the AI is down to a limited number of pieces, and they corner themselves into not being able to make any move, one could argue that reaching this point should result in
            // in automatic loss, another is to make sure that the AI doesn't put itself into this situation.
            // this can be done by changing specialEvaluateBoard() to include this state a highly negative board position
    {
        DecisionTree onlyTree = new DecisionTree();
        onlyTree.maxDepth = intelligence;
        boolean maximizingPlayer;
        if(mySoldier=='Q')
            maximizingPlayer = true;
        else
            maximizingPlayer = false;

        double moveValue  =onlyTree.evaluatMinimax ( this,0,maximizingPlayer,onlyTree.root,-10000,10000 ); // maybe instead of true maybe false (ok i changed this and its a bit more smarter)

        int bestIndex =0;
        double best = onlyTree.root.nodeList[bestIndex].nodeValue;
        // a temporary solution would be to return an illegal move and the other player will eat u
        if(onlyTree.root.currentSize==0 )//||moveValue == -10000|| moveValue ==10000)
        {
            Move tempMove = new Move(3,3,'F');
            return tempMove;
        }

        NewSequence randomBest = new NewSequence(onlyTree.root.currentSize+1);
        int randomBestCounter = 0; // redundant through optimization

        if(maximizingPlayer) {

            for (int i = 0; i < onlyTree.root.currentSize; i++) {

                if (best < onlyTree.root.nodeList[i].nodeValue) {
                    best = onlyTree.root.nodeList[i].nodeValue;
                    bestIndex = i;
                    randomBestCounter =0;
                    randomBest.currentSize =0;
                    randomBest.addIndex(randomBestCounter,bestIndex);

                }
                else if(best == onlyTree.root.nodeList[i].nodeValue )
                {
                    bestIndex = i;
                    randomBestCounter++;
                    randomBest.addIndex(randomBestCounter,bestIndex);

                }


            }
        }
        else
        {

            for (int i = 0; i < onlyTree.root.currentSize; i++) {

                if (best > onlyTree.root.nodeList[i].nodeValue) {
                    best = onlyTree.root.nodeList[i].nodeValue;
                    bestIndex = i;
                    randomBestCounter =0;
                    randomBest.currentSize =0;
                    randomBest.addIndex(randomBestCounter,bestIndex);
                }
                else if(best == onlyTree.root.nodeList[i].nodeValue )
                {
                    bestIndex = i;
                    randomBestCounter++;
                    randomBest.addIndex(randomBestCounter,bestIndex);

                }


            }



        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(randomBest.currentSize);

        bestIndex = randomBest.moveArrayIndex[randomIndex];




        return onlyTree.root.nodeList[bestIndex].nodeMove;


    }






//    Move evaluateSA()
//    {
//        Random rand = new Random();
//        double temperature = 10000;
//        double coolingRate = 0.003;
//        int currentSolution;
//        int neighboringSolution;
//        int randomIndex;
//        double acceptanceProbability = 0;
//        // copy function is needed~!!!!!!!!!!!!!!!!!!!!!!!!!!! this method doesn't work. // fixed
//        MoveSequence bestSequence = new MoveSequence();
//        MoveSequence neighborSequence = new MoveSequence();
//        MoveSequence currentSequence = new MoveSequence();
//        currentSequence.generateRandomAtIndex(0,this);
//        bestSequence.copySequence ( currentSequence );
//        neighborSequence.copySequence( currentSequence);
//        boolean thisPlayerTurn = true;
//
//        while(temperature>1)
//        {
//            System.out.println("Current temperature:" + String.valueOf(temperature));
//
//
//
//                if(thisPlayerTurn)
//            {
//               randomIndex = generateRandomEven(neighborSequence.getCurrentSize ());
//               neighborSequence.generateRandomAtIndex(randomIndex,this);
//                currentSolution = currentSequence.getMoveSequenceWorth ();
//                neighboringSolution = neighborSequence.getMoveSequenceWorth ();
//                acceptanceProbability= calculateAcceptanceProbability (currentSolution,neighboringSolution,temperature  );
//
//
//            }
//                if(!thisPlayerTurn)
//                {
//                    randomIndex = generateRandomOdd(neighborSequence.getCurrentSize ());
//                    neighborSequence.generateRandomAtIndex(randomIndex,this);
//                    currentSolution = currentSequence.getMoveSequenceWorth ();
//                    neighboringSolution = neighborSequence.getMoveSequenceWorth ();
//                    Math.abs(currentSolution);
//                    acceptanceProbability = calculateAcceptanceProbability(Math.abs(currentSolution),Math.abs(neighboringSolution),temperature);
//
//
//                }
//
//
//
//
//
////                currentSolution = currentSequence.getMoveSequenceWorth ();   // what really determines the worth of a sequence? is it logically correct?
////                neighboringSolution = neighborSequence.getMoveSequenceWorth ();
////           acceptanceProbability= calculateAcceptanceProbability (currentSolution,neighboringSolution,temperature  );
//
//
//
//           if(acceptanceProbability>=rand.nextDouble()) // check if the probability is really between 1 and 0
//            {
//                currentSequence.copySequence ( neighborSequence ); //  <----- and thats the problem, you need to create a copy function
//
//            }
//           else
//           {
//               neighborSequence.copySequence(currentSequence);
//           }
//           if(thisPlayerTurn) {
//               if (currentSequence.getMoveSequenceWorth () > bestSequence.getMoveSequenceWorth ())
//                   bestSequence.copySequence ( currentSequence );
//               thisPlayerTurn = false;
//           }
//           else
//           {
//               if (currentSequence.getMoveSequenceWorth () < bestSequence.getMoveSequenceWorth ())
//                   bestSequence.copySequence ( currentSequence );
//               thisPlayerTurn = true;
//
//           }
//
//           temperature = temperature *(1-coolingRate);
//
//                }
//        bestSequence.printSequence ( this );
//
//        return bestSequence.moveArray[0];
//
//        }



//                    movSeq1.generateRandomAtIndex(randomIndex,this);
//                    neighboringSolution = movSeq1;
//                }
//                If(!player1turn)
//                {
//#generate a random index that ranges from 0 to the movSeq1.getCurrentSize()
//                    While(!checkEven(generatedRandomIndex))
//                    {
//#generate a random index that ranges from 0 to the movSeq1.getCurrentSize()
//#until the random number is even
//                    }
//                    movSeq1.generateRandomAtIndex(randomIndex,this);
//                    newSolution= movSeq1;
//                }
//                DeltaEnergy = newSolution.getBoardValue(player1Turn) -neighboringSolution.getBoardValue(player1Turn
//                        If(deltaEnergy >=1)
//                {
//                    bestSolution = newSolution;
//                    neighboringSolution = newSolution;
//                }
//                If(computeTemp(deltaEnergy,currentTemp) > rand.generateRandomFrom0To1()
//                {
//                    neighboringSolution = newSolution;
//                }
//
//                Temp =temp *(1-decayRate)








//    }

//int treeMaxDepth = 6;
//int treeCurrentDepth = 0;
//void printTree(NewSequence sequence[], int numberOfSequences)
//{
//    int commonIndices[] = new int[numberOfSequences];
//    int indicesSize = 0;
//
//for(int i =0;i<numberOfSequences;i++)
//{
//// see if any of them is identical at the current Depth
//    // if they are identical we pass them in a recursive function (together)
//    //
//    for(int j = 0;j<numberOfSequences;j++)
//    {
//
//        if(sequence[i].moveArrayIndex[treeCurrentDepth] == sequence[j].moveArrayIndex[treeCurrentDepth])
//        {
//            commonIndices[indicesSize] = j;
//            indicesSize++;
//
//        }
//
//    }
//
//
//
//
//}
//
//
//
//}




}

