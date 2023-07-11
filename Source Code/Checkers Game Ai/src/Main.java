

// You have to understand how the board is coordinated:
// from LEFT to RIGHT (0 -> 8)
// from TOP to BOTTOM (0 -> 8)
// 0  1  2  3  4  5  6  7
// 1
// 2
// 3
// 4
// 5
// 6
// 7

// NOTE: you didn't make the transition from normal solider to knight.
// and perhaps you didn't adjust the movement for knights.

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.util.Formatter;
public class Main {

    public static void main(String[] args)
    {
                       // in java you have to declare and define your input operator, this is it's declaration and definition.
        Player player1 = new Player('O','K','Q','J'); // creating player objects and initializing the pieces letters.
        Player player2 = new Player(player1); // the solider will be represented by 'Q' , while the Knight will be represented by 'J'
        player2.switchPlayer(); // i can legally move pl
        //player1.switchPlayer ();

        start(player1);
      //  startDebug(player1,player2);





    }

    public static void startDebug(Player player1,Player player2)
    {
        Scanner input = new Scanner(System.in);

        int rowIndex;                      // initialize the board with two players. NOTE: THE ORDER MATTERS.
        int columnIndex;
        String movementDirection;
        char movementDirectionLetter;






        boolean gameEnded = false;                                     // declare a boolean to decide whether to stop the game or not.
        boolean skip = false;
        boolean skip2 = false;
        int counter =0;
        int decision = 0;

        while(!gameEnded) {                                            // the game will loop until either: gameEnded is TRUE or BREAK from loop

            if(!skip2) {

                //     OUTPUT: Player 1

                System.out.println ( "Player 1's turn:" );
                //   player1.flipBoard ();
                player1.printPlayerBoard ();                                     // print the board for player 1 to see.
                System.out.println ( "move counter:" + counter );
                counter++;

                // Messages to instruct the player on what to do.
                //  System.out.println ( "enter position followed by the Direction like: 6 5 L." );        // Enter ROW INDEX followed by COLUMN INDEX
                //    System.out.println("enter -1 to stop the game.");
                //      System.out.println("NOTE: ROW POSITION FOLLOWED BY COLUMN POSITION! BE CAREFUL!!! not x , y coordinates!");
                //END OF OUTPUT.


                //INPUT:here to test the intelligence
//                rowIndex = input.nextInt ();                                  // Read rowIndex. if it's -1, then STOP the game.
//                if (rowIndex == -1) {
//                     break;
//                }
//                  columnIndex = input.nextInt ();                              // Read columnIndex
//                 movementDirection = input.next ();                         // Read movement direction as string
//               movementDirectionLetter = movementDirection.charAt ( 0 );   // take the first letter
//                 //END OF INPUT.

                //    Move Player1Move = player1.evaluateSA (4,1000.0,true,counter);


                Move Player1Move = player1.evaluateMinimax ( 4 );
                if(player1.calculatePieceCount ()> player2.calculatePieceCount ())
                    System.out.println("we are winning...");
                else if (player1.calculatePieceCount () == player2.calculatePieceCount ())
                    System.out.println("we are equally strong...");
                else
                {
                    System.out.println("we are losing...");
                }


////
                rowIndex = Player1Move.getMoveI ();
                columnIndex = Player1Move.getMoveJ ();
                movementDirectionLetter = Player1Move.getMoveMovement ();

                boolean legaly[] = new boolean[2];
                legaly = player1.checkMovementLegal ( rowIndex, columnIndex, movementDirectionLetter );
                if (!legaly[0]) {
                    System.out.println("movement is illegal problem with AI player 1");
                    try {
                        TimeUnit.MINUTES.sleep ( 2 );
                    }
                    catch(Exception ex)
                    {
//
                    }
                }


//

//
                if(movementDirectionLetter =='F')
                    decision = 1;


                if (player1.movePiece ( rowIndex, columnIndex, movementDirectionLetter ))
                    skip = true;
                else {
                    skip = false;// Register movement in Player 1's Board
                    player1.flipBoard ();                                                 // this function  flips the board for the other player to get it.
                    player2.setPositions ( player1.getPositions () );

                }

                if (player1.getPieceCount () == 0 || decision == 2)                                      // check if player 1 ate all player 2's pieces. The counter decrements every time a piece is eaten.
                {
                    gameEnded = true;
                    System.out.println ( "player 1 WON!" );
                    player1.printPlayerBoard ();
                    System.exit(0);
                }
            }                 // Register player 2's after player 1 made the changes.
            //                                                                              player1.getPositions() automatically flips the board for player 2.


            //INPUT:
            player2.printPlayerBoard();
//
//      //    Move Player2Move =player2.evaluatePlayerMinimax ( player2 ); // no matter what you do, the 2nd time, it gets fucked up.
//
//
//            //Processing Player 2's move.
            if(!skip) {
//
//                //INPUT:
                //    rowIndex = input.nextInt ();                                  // Read rowIndex. if it's -1, then STOP the game.
                //       if (rowIndex == -1) {
                //            break;
                //          }
                //                   columnIndex = input.nextInt ();                              // Read columnIndex
                //                  movementDirection = input.next ();                         // Read movement direction as string
//                movementDirectionLetter = movementDirection.charAt ( 0 );   // take the first letter
//                 //END OF INPUT.

                Move Player2Move = player2.evaluateSA (6,10000000.0,true,counter); // can it beat minimax if I it give it more intelligence and duration? if minimax had 8 intelligence? if it does, then the algorithm is working.
                // ***** lets first run this then talk ******
                //            Move Player2Move = player2.evaluateMinimax (4); // basically the reason the bug occured was because after you've won, you've tried to re-enter

                rowIndex =Player2Move.getMoveI();
                columnIndex = Player2Move.getMoveJ();
                movementDirectionLetter = Player2Move.getMoveMovement();



                char letter = 'X';
                if(movementDirectionLetter =='L')
                    letter='R';
                else if(movementDirectionLetter=='R')
                    letter = 'L';
                else if (movementDirectionLetter=='G')
                    letter ='H';
                else if(movementDirectionLetter=='H')
                    letter='G';

                int tempColumn = 7 -columnIndex;
                int tempRow = 7- rowIndex;

                System.out.println("the actual letter is:  "+ movementDirectionLetter);
                System.out.println();
                System.out.println();


                System.out.println("ROW: " +tempRow + "   Column:  " + tempColumn + " Letter: " + letter);





//
                System.out.println ( "player 2's turn" );
                player2.printPlayerBoard ();
                //      rowIndex = input.nextInt ();
                //     columnIndex = input.nextInt ();
                //     movementDirection = input.next ();
                //   movementDirectionLetter = movementDirection.charAt ( 0 );

                if(movementDirectionLetter =='F')
                    decision =2;
                boolean legal[] = new boolean[2];
                legal =  player2.checkMovementLegal ( rowIndex,columnIndex,movementDirectionLetter );
                if(!legal[0]) {
                    System.out.println("movement is illegal, problem with Ai player 2");
                    input.nextInt ();
                }


                if( player2.movePiece ( rowIndex, columnIndex, movementDirectionLetter ))
                    skip2 = true;
                else
                {
                    skip2=false;
                    player2.flipBoard ();
                    player1.setPositions ( player2.getPositions () );
                }


                if (player2.getPieceCount () == 0|| decision ==1) {
                    gameEnded = true;
                    System.out.println ( "Player 2 WON!" );

                    System.out.println("board after endgame:");
                    player2.printPlayerBoard ();
                    System.exit(0);
                }

                // End of processing Player 2's move.

            }

        }   // Loop again.









    }
    public static void start(Player player1)
    {

       // File aiFile = new File("C:\\Users\\Enzyme\\Desktop\\seniorTest\\aitype.txt");
        File aiFile = new File("aitype.txt");



      //  File file1 = new File("C:\\Users\\Enzyme\\Desktop\\seniorTest\\gameMovements.txt");
        File file1 = new File("gameMovements.txt");
        ListOfFileMoves  listOfFileMoves = new ListOfFileMoves ();
     //   player1.printPlayerBoard ();
        System.out.println("executing AI turn....");



        try {


            Scanner aiFileScanner = new Scanner(aiFile);
            boolean minimax;
            if(aiFileScanner.next().equals( "minimax"))
                minimax = true;
            else
                minimax = false;


            Scanner input = new Scanner ( file1 );

            int i;
            int j;
            char k;
            while(input.hasNext())
            {

                 i =input.nextInt();

                 j = input.nextInt();
                String str1 = input.next();
                 k = str1.charAt(0);
                listOfFileMoves.addMove(i,j,k);


            }

            int x, y;
            char z;
            File file2 = new File("lastMove.txt"); // OK, so this one only gets his last move from player 1, in player 2, he doesn't get the last move
            // but wait, we don't write the last move, because we've already written it?
            // ok, but what happens the next time we do this? we re-read the new move as even though its an old move if we would've taken it
            // ok here's the problem, when your player EATS a piece, he's allowed to run again right? and when he runs again what does he read?
            Scanner input2 = new Scanner(file2);
            while(input2.hasNext()) {
                int x1 = input2.nextInt ();
                if (x1 != 6976) { // suggested solution
                    int y1 = input2.nextInt ();
                    int x2 = input2.nextInt ();
                    int y2 = input2.nextInt ();

                    //  Point temp1 =flipToPlayer2 ( x1,y1 ); dont think we need this
                    //  Point temp2 = flipToPlayer2 ( x2,y2 );


                    Move tempMove2 = coordinatesToLetter ( x1, y1, x2, y2 );

                    i = tempMove2.getMoveI ();
                    j = tempMove2.getMoveJ ();
                    k = tempMove2.getMoveMovement ();

                    listOfFileMoves.addMove ( i, j, k );
                }
                else
                    break;
                // now we've added the last move that the last player made
                // all he has to do is just put it in c#, in its normal form and it will understand it

            }


            for( i =0;i<listOfFileMoves.currentSize;i++)
            {



                        x = listOfFileMoves.moveList[i].getMoveI ();
                        y = listOfFileMoves.moveList[i].getMoveJ ();
                        z = listOfFileMoves.moveList[i].getMoveMovement ();




                        if (!player1.movePiece ( x, y, z ))
                        {
                            player1.switchPlayer ();
                        }

                    }





            Move tempMove;
           if(minimax) {
               tempMove = player1.evaluateMinimax ( 7 );
               System.out.println("using minimax");
           }
           else {
               tempMove = player1.evaluateSA ( 6, 10000000.0, true, 0 );// got to be fixed
               System.out.println("using Simulated annealing");
           }

            Formatter output2 = new Formatter(file2);

            i = tempMove.getMoveI ();
            j = tempMove.getMoveJ();
            k = tempMove.getMoveMovement ();
            if(k =='F')
            {
                output2.format("6969 6969 6969");
                System.out.println("Game Over");
                System.exit(1);
            }


            listOfFileMoves.addMove ( i,j,k ); // must be added BEFORE CONVERSION
            // we have to convert the move into coordinate system,
           Point tempPoint[] =  letterToCoordinates ( i,j,k,player1 );



            // flip it for the other player
            tempPoint[0] = flipToPlayer2 ( (int)tempPoint[0].getPointI (),(int)tempPoint[0].getPointJ () );
            tempPoint[1] = flipToPlayer2 ( (int)tempPoint[1].getPointI (),(int)tempPoint[1].getPointJ () );

           // we put it in a file to be read by C#



           if(player1.movePiece(i,j,k))
               output2.format("6976 " +(int)tempPoint[0].getPointI() + " " + (int)tempPoint[0].getPointJ ()+ " " +(int) tempPoint[1].getPointI () + " " + (int)tempPoint[1].getPointJ () + " ");
           else
            output2.format((int)tempPoint[0].getPointI() + " " + (int)tempPoint[0].getPointJ ()+ " " +(int) tempPoint[1].getPointI () + " " + (int)tempPoint[1].getPointJ () + " "); // x1 y1 x2 y2

            output2.close();




            // finish
            Formatter output = new Formatter(file1);


            for( int h =0;h<listOfFileMoves.currentSize;h++)
            {
               i = listOfFileMoves.moveList[h].getMoveI ();
               j=  listOfFileMoves.moveList[h].getMoveJ ();
               k= listOfFileMoves.moveList[h].getMoveMovement ();

                output.format(i + " " + j  + " "  + k + " " );

            }



            output.close();




        }
        catch(Exception ex)
        {
            System.out.println("file not found");
            System.exit(1);

        }




    }
    // They all need testing ffs
    public static Point javaToC(int i, int j)
    {
        Point point = new Point();
        i = 7-i;
        int temp = i;
        i = j;
        j = temp;
        point.setPoint(i,j);
        return point;

    }

    public static Point cToJava(int i,int j)
    {
        int temp;
        temp = i;
        i = 7-j;
        j = temp;
        Point point = new Point();

        point.setPoint(i,j);
        return point;
    }

    public static Point flipToPlayer2(int i, int j) // needs to be used ffs
    {
        i = 7-i;
        j = 7-j;
        // assuming ^ is correct

        Point tempPoint = new Point();
        tempPoint.setPoint(i,j);
        return tempPoint;
    }
    public static Move coordinatesToLetter(int x1,int y1, int x2,int y2)
    {
        int i,j;

        i = x1;
        j = y1;
        char k;
        if(y2-y1 >0) // if it's R or L
        {
            if(x2-x1 >0)
                k ='R';
            else
            {
                k='L';
            }

        }
        else // either G or H
        {
            if(x2-x1>0)
            k='H';
            else
                k='G';

        }

       Point tempPoint = cToJava(i,j);
        int o = (int)tempPoint.getPointI ();
        int p = (int)tempPoint.getPointJ ();
        Move tempMove = new Move(o,p,k);

        return tempMove;

    }

    public static Point[] letterToCoordinates(int i,int j,char k,Player player1)
    {

        boolean legal[] = new boolean[2];
        int distance;

        legal =player1.checkMovementLegal (i,j,k  );
        if(legal[1])
            distance = 2;
        else
            distance = 1;

        Point tempPoint1 = javaToC(i,j);


        int x2,y2;
        x2 =0;
        y2 =0;


        i = (int)tempPoint1.getPointI ();
        j = (int)tempPoint1.getPointJ ();
        if(k =='L')
        {
            x2 = i - distance;
            y2 = j + distance;
        }
        else if(k=='R')
        {
            x2 = i + distance;
            y2 = j + distance;
        }
        else if(k=='G')
        {
            x2 = i - distance;
            y2 = j - distance;
        }
        else if (k =='H')
        {
            x2 = i + distance;
            y2 = j -distance;
        }

        int x1 = i;
        int y1 = j;


        x1 = (int) tempPoint1.getPointI ();
        y1 = (int) tempPoint1.getPointJ();



        Point point[] = new Point[2];
        point[0] = new Point();
        point[1] = new Point();
        point[0].setPoint(x1,y1);
        point[1].setPoint(x2,y2);

        return point;



    }

}



// code graveyard:

// *******************************************************************************************************************
//OUTPUT: Player 2                                                                                                   *
//     player2.printPlayerBoard();                                                                                   *
//    System.out.println("Player 2's turn:");                                                                        *
//       System.out.println ( "enter position followed by the Direction." );                                         *
//         System.out.println("enter -1 to stop the game.");                                                         *
//           System.out.println("NOTE: ROW POSITION FOLLOWED BY COLUMN POSITION! BE CAREFUL!!!");                    *
//END OF OUTPUT.                                                                                                     *
//                                                                                                                   *
//                                                                                                                   *
//                                                                                                                   *
// rowIndex = input.nextInt();                                                                                       *
//            if(rowIndex == -1)                                                                                     *
//            {                                                                                                      *
//                break;                                                                                             *
//            }                                                                                                      *
//           columnIndex = input.nextInt();                                                                          *
// movementDirection = input.next();                                                                                 *
// movementDirectionLetter = movementDirection.charAt(0);                                                            *
// END OF INPUT.                                                                                                     *
//********************************************************************************************************************

