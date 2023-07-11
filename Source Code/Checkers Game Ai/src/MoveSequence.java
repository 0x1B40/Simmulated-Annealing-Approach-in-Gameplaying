import java.util.Random;

//
//public class MoveSequence {
//    Move moveArray[];
//    int currentSize;
//    int maxSize;
//    int moveSequenceWorth;
//    Player copyBoard;
//
//// didn't set the constructor
//    MoveSequence()
//    {
//        currentSize = 0;
//        maxSize = 100;
//        moveSequenceWorth = 0;
//        moveArray = new Move[maxSize];
//
//
//    }
//    public void setMoveArray(Move x[])
//    {
//        for(int i =0;i<currentSize;i++)
//        {
//            moveArray[i]  = x[i];
//        }
//
//    }
//
//    public Move[] getMoveArray()
//    {
//        return moveArray;
//    }
//
//    public void setCurrentSize(int x)
//    {
//        currentSize = x;
//    }
//
//    public int getCurrentSize()
//    {
//        return currentSize;
//    }
//
//    public void setMaxSize(int x)
//    {
//        maxSize = x;
//    }
//
//    public int getMaxSize()
//    {
//        return maxSize;
//    }
//
//    public void setMoveSequenceWorth(int x)
//    {
//        moveSequenceWorth =x;
//    }
//
//    public int getMoveSequenceWorth()
//    {
//        return moveSequenceWorth;
//    }
//
//    public void extendArray()
//    {
//        maxSize = maxSize+100;
//        Move newArray[] = new Move[maxSize];
//
//        for(int i =0;i<currentSize;i++)
//        {
//            newArray[i] = moveArray[i];
//
//        }
//
//        moveArray = newArray;
//
//    }
//
//    public Player getCopyBoard()
//    {
//        return copyBoard;
//
//
//
//    }
//
//    void generateRandomAtIndex(int startIndex,Player board)
//    {
//        boolean gameEnded = false;
//        int counter = 0;
//        boolean thisPlayerTurn = board.checkEven(startIndex);
//        copyBoard = new Player ( board );
//        Random rand = new Random ();
//
//
//         currentSize = startIndex;
//        fillBoardWithMoves(startIndex,copyBoard);
//
//        while(!gameEnded) {
//            counter++;
//
//
////            copyBoard.computePossibleMoves ();
////
////            int sizeOfPossibleMoves = copyBoard.getPossibleMovesList ().getCurrentSize ();
////            if (sizeOfPossibleMoves == 0) {
////                copyBoard.printPlayerBoard ();
////                copyBoard.switchPlayer();
////                System.out.println ( "size cannot be zero, that means the list of possible moves is empty which means the game should've ended." );
////                break;
////               // copyBoard.printPlayerBoard();
////                //System.exit ( 1 );
////            }
////            int randomNumber = rand.nextInt ( sizeOfPossibleMoves ); // we've garaunteed that this is from 1 to maxSize.
////
////
////            Move tempMove = copyBoard.getPossibleMovesList ().getPossibleMovesList ()[randomNumber]; // this is the random move
////            moveArray[currentSize] = tempMove; // it is correct to generate the random move and replacing the starting index position
////            currentSize++;
////
////            if(currentSize == maxSize)
////                extendArray();
////
////            int i = tempMove.getMoveI ();
////            int j = tempMove.getMoveJ ();
////            char movement = tempMove.getMoveMovement ();
////
////            copyBoard.movePiece ( i, j, movement );
////            copyBoard.calculatePieceCount ();
////            //copyBoard.printPlayerBoard();
////            gameEnded =copyBoard.checkGameEnded ();
////            if(counter>10)
////                gameEnded = true;
////
//////            if(gameEnded)
//////                break;
////            copyBoard.switchPlayer(); // flips the board, swaps my piece with his pieces, calculates pieceCount
////
////        }
//
//        if(copyBoard.getMySoldier() =='O') // this may generate errors in the future because O don't mean anything
//            copyBoard.switchPlayer();
//
//        // calculate the worth of the list of moves.
////        if(thisPlayerTurn)
////            moveSequenceWorth =copyBoard.calculateMyPieces () - copyBoard.calculateEnemyPieces (); //.. in either case only one of them is considered...
////        else
////            moveSequenceWorth = copyBoard.calculateEnemyPieces () - copyBoard.calculateMyPieces();
//
//        moveSequenceWorth =copyBoard.calculateMyPieces () - copyBoard.calculateEnemyPieces ();
//
//
//    }
//
//    void fillBoardWithMoves(int startIndex,Player passedBoard) // its fine with the board, just keep it
//    {
//        // its possible that we don't start with the first index, it could be in the middle or the last index, in these cases, we need to fill the board with the current sequence of moves.
//        int i;
//        int j;
//        char movement;
//
//        for(int index =0;index<startIndex;index++)
//        {
//            i =moveArray[index].getMoveI();
//            j =moveArray[index].getMoveJ();
//            movement =moveArray[index].getMoveMovement();
//
//            passedBoard.movePiece(i,j,movement);
//       //     passedBoard.printPlayerBoard();
//            passedBoard.switchPlayer(); // watch out for this one, do we switch it back or keep it?
//
//        }
//
//
//
//
//    }
//
//    void copySequence( MoveSequence x)
//    {
//        currentSize = x.getCurrentSize ();
//        maxSize = x.getMaxSize ();
//        moveSequenceWorth = x.getMoveSequenceWorth ();
//        moveArray = new Move[maxSize];
//        setMoveArray (x.getMoveArray());
//        copyBoard = new Player(x.getCopyBoard());
//
//
//
//      //  copyBoard.copyPlayer(x.getCopyBoard ());
//
//
//
//    }
//
//    void printSequence(Player board)
//    {
//        Player newBoard = new Player(board);
//        fillBoardWithMoves (currentSize,newBoard );
//        newBoard.printPlayerBoard ();
//
//
//
//
//    }
//
//
//}
