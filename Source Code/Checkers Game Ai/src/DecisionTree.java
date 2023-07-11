// new clue: use the full board in the nodes
import java.util.concurrent.TimeUnit;
import java.util.Random;
public class DecisionTree {
    int currentDepth = 0;
    int maxDepth = 6;
    PrintNodesList printNodesList;

    class Node
    {
        Node nodeList[];
        Move nodeMove;
        int maxSize;
        int currentSize;
        double nodeValue;
        boolean explored = false;
        boolean gameEnded = false;
        NewSequence nodeSeq;
        boolean myPlayer = true;
        // debug suggestion



        // minimax chit:
        Player board;

        Node()
        {
            maxSize = 64;
            currentSize = 0;
            nodeList = new Node[maxSize]; //if we wanted to create nodes for this specific node we'll have to specify that.
            nodeSeq = new NewSequence(maxDepth);// what is the value of maxDepth
            nodeMove = new Move();
            // there is still a problem, maybe nodeValue by it being by default 0, it might create a problem

        }

        void copyBoard(Player tempboard)
        {
            board = new Player(tempboard);

        }

        // functions for class node

        void setNodeMove(Move x)
        {
            nodeMove = x;
        }

        void setNodeMove(int i,int j,char x)
        {
            nodeMove.setMove(i,j,x);

        }

        void setNodeValue(double x)
        {
            nodeValue = x;
        }

        Move getNodeMove()
        {
            return nodeMove;

        }

        Double getNodeValue()
        {
            return nodeValue;
        }

        boolean checkExplored()
        {
            return explored;
        }


//        void addPossibleMoves(Player board)
//        {
//            // u can add the checking of exploration either here or:
//            // do we need a temp board? No.
//            board.computePossibleMoves();
//            for(currentSize =0;currentSize<board.getPossibleMovesList ().getCurrentSize ();currentSize++)
//            {
//                nodeList[currentSize].setNodeMove(board.getPossibleMovesList ().listOfPossibleMoves[currentSize]);
//
//            }
//
//            // now for every possible move, it has been added to the node.
//
//            explored = true;
//
//
//
//
//        }

        void setGameEnded(Boolean x)
        {
            gameEnded = x;

        }

        boolean checkGameEnded()
        {
            return gameEnded;
        }



    }   // end of class node



    Node root;
    DecisionTree()
    {
        root = new Node();
        printNodesList = new PrintNodesList ( 35^(maxDepth) );
    }

    // functions:

    void generateRandomAtIndex(int index, Player Board,NewSequence currentSeq) {
//        // my greatest worry, is that the main core of my idea is wrong and far off
        root.copyBoard(Board);
        Node tempNode;

        if(Board.getMySoldier () =='O') // this one too
        root.myPlayer = false;
        else
            root.myPlayer = true;

        boolean legal2[] = new boolean[2];
//        boolean gameEnded = false;
//        boolean repeat;
//        currentDepth = 0;

        Player tempBoard = new Player(Board);
        Move tempMove;


//        int tempCounter;
//        // End of Declarations.
//
//
//
        tempNode = fillBoardAndTree ( index, tempBoard,currentSeq ); // this thang updates currentDepth
//
   //     if(true) { //tempNode.checkExplored ()

//            System.out.println("tempNode board:");
//            if(tempNode.board.checkIdentical(tempBoard))
 //               System.out.println("they're identical");
 //           else
 //               System.out.println("they're not identical");

//
//            tempNode.board.printPlayerBoard (); // debugging portion
//
//            System.out.println("tempBoard board:");
     //       tempBoard.printPlayerBoard ();
      //  }
        while (currentDepth < maxDepth && !tempNode.gameEnded) {
            if (!tempNode.checkExplored ()) {

                tempNode.explored = true;
                boolean legal[];
                int tempNodeSizeCounter = 0;

                for (int i = 0; i < tempBoard.boardSize; i++) {
                    for (int j = 0; j < tempBoard.boardSize; j++) {

                        legal = tempBoard.checkMovementLegal ( i, j, 'L' );
                        if (legal[0]) {
                            tempNode.nodeList[tempNodeSizeCounter] = new Node ();
                            tempNode.nodeList[tempNodeSizeCounter].setNodeMove ( i, j, 'L' );
                            tempNode.nodeList[tempNodeSizeCounter].copyBoard ( tempBoard );
                            if (tempNode.nodeList[tempNodeSizeCounter].board.movePiece ( i, j, 'L' )) {
                                tempNode.nodeList[tempNodeSizeCounter].myPlayer = tempNode.myPlayer;
                            } else {
                                tempNode.nodeList[tempNodeSizeCounter].myPlayer = !tempNode.myPlayer;
                                tempNode.nodeList[tempNodeSizeCounter].board.switchPlayer ();
                            }

                            tempNode.nodeList[tempNodeSizeCounter].gameEnded = tempNode.nodeList[tempNodeSizeCounter].checkGameEnded ();


                            tempNodeSizeCounter++;
                        }
                        legal = tempBoard.checkMovementLegal ( i, j, 'R' );
                        if (legal[0]) {
                            tempNode.nodeList[tempNodeSizeCounter] = new Node ();
                            tempNode.nodeList[tempNodeSizeCounter].setNodeMove ( i, j, 'R' );
                            tempNode.nodeList[tempNodeSizeCounter].copyBoard ( tempBoard );
                            if (tempNode.nodeList[tempNodeSizeCounter].board.movePiece ( i, j, 'R' ))
                                tempNode.nodeList[tempNodeSizeCounter].myPlayer = tempNode.myPlayer;
                            else {
                                tempNode.nodeList[tempNodeSizeCounter].myPlayer = !tempNode.myPlayer;
                                tempNode.nodeList[tempNodeSizeCounter].board.switchPlayer ();
                            }

                            tempNode.nodeList[tempNodeSizeCounter].gameEnded = tempNode.nodeList[tempNodeSizeCounter].checkGameEnded ();

                            tempNodeSizeCounter++;
                        }
                        legal = tempBoard.checkMovementLegal ( i, j, 'G' );
                        if (legal[0]) {
                            tempNode.nodeList[tempNodeSizeCounter] = new Node ();
                            tempNode.nodeList[tempNodeSizeCounter].setNodeMove ( i, j, 'G' );
                            tempNode.nodeList[tempNodeSizeCounter].copyBoard ( tempBoard );
                            if (tempNode.nodeList[tempNodeSizeCounter].board.movePiece ( i, j, 'G' ))
                                tempNode.nodeList[tempNodeSizeCounter].myPlayer = tempNode.myPlayer;
                            else {
                                tempNode.nodeList[tempNodeSizeCounter].myPlayer = !tempNode.myPlayer;
                                tempNode.nodeList[tempNodeSizeCounter].board.switchPlayer ();
                            }
                            tempNode.nodeList[tempNodeSizeCounter].gameEnded = tempNode.nodeList[tempNodeSizeCounter].checkGameEnded ();

                            tempNodeSizeCounter++;
                        }
                        legal = tempBoard.checkMovementLegal ( i, j, 'H' );
                        if (legal[0]) {
                            tempNode.nodeList[tempNodeSizeCounter] = new Node ();
                            tempNode.nodeList[tempNodeSizeCounter].setNodeMove ( i, j, 'H' );
                            tempNode.nodeList[tempNodeSizeCounter].copyBoard ( tempBoard );
                            if (tempNode.nodeList[tempNodeSizeCounter].board.movePiece ( i, j, 'H' ))
                                tempNode.nodeList[tempNodeSizeCounter].myPlayer = tempNode.myPlayer;
                            else {
                                tempNode.nodeList[tempNodeSizeCounter].myPlayer = !tempNode.myPlayer;
                                tempNode.nodeList[tempNodeSizeCounter].board.switchPlayer ();
                            }

                            tempNode.nodeList[tempNodeSizeCounter].gameEnded = tempNode.nodeList[tempNodeSizeCounter].checkGameEnded ();

                            tempNodeSizeCounter++;
                        }
                    } // engineering mistake: what is the purpose of node.board it can be removed because its redundant, so needs re-engineering


                }
                tempNode.currentSize = tempNodeSizeCounter;


            } // END OF CHECKING IF ITS EXPLORED OR NOT (IF IT WERE WE FILLED IT WITH NODES AND BOARDS OF ALL THE POSSIBLE MOVES). This Whole thing can be simplified into a function


            if (tempNode.currentSize == 0) {
                tempNode.gameEnded = true;
                currentSeq.moveArrayIndex[currentDepth] = -1;

            }// this node may have a nodeValue, but it has no possible nodeList moves left, all the other possible moves are illegal
            else  // because we cannot keep going if it's gameEnded, it must stop
            {
                Random rand = new Random (); // in order to pick a random index from nodeList
                int tempIndex = rand.nextInt ( tempNode.currentSize ); // generated a random index from the list of possible moves stored in nodeList
                currentSeq.moveArrayIndex[currentDepth] = tempIndex;
                currentDepth++;
                currentSeq.currentSize = currentDepth;
              tempMove=  tempNode.nodeList[tempIndex].getNodeMove();
              //** debugging
//  //              System.out.println("tempIndex = " + tempIndex);
// //             System.out.println(" chosen move:" + "i: " + tempMove.getMoveI () + "   j: " + tempMove.getMoveJ () + "   letter: " + tempMove.getMoveMovement ());
//                if(tempIndex == 0)
//                {
//                    System.out.println("tempIndex == 0");
//                    System.out.println(" chosen move:" + "i: " + tempMove.getMoveI () + "   j: " + tempMove.getMoveJ () + "   letter: " + tempMove.getMoveMovement ());
//                tempBoard.printPlayerBoard();
//                }
//                else if(tempIndex == 1)
//                {
//                    System.out.println("tempIndex == 1");
//                    System.out.println(" chosen move:" + "i: " + tempMove.getMoveI () + "   j: " + tempMove.getMoveJ () + "   letter: " + tempMove.getMoveMovement ());
//                    tempBoard.printPlayerBoard();
//
//                }

//              if(tempNode.board.checkIdentical(tempBoard)) {
//                  System.out.println ( "they're identical" );
//
//              }
//              else {
//                  System.out.println ( "they're not identical" );
//                  try {
//                      TimeUnit.MINUTES.sleep ( 2 );
//                  }
//                  catch(Exception ex)
//                  {
//
//                  }
//
//              }

                //if(tempBoard.mySoldier =='O' )
                //{
              //      if(!tempNode.myPlayer)
                //        System.out.println("something is wrong here!");
                //}

                //if(tempBoard.mySoldier =='Q')
                //{
                  //  if(tempNode.myPlayer)
                    //    System.out.println("something is wrong here!");
                //}

             //  debugging **

              legal2 = tempBoard.checkMovementLegal ( tempMove.getMoveI (),tempMove.getMoveJ (),tempMove.getMoveMovement () ); // debug purpose only
              if(!legal2[0]) {
                  System.out.println ( "we've got a real problem, it's that tempBoard not making legal moves" ); // debug purpose only
                  try {
                      TimeUnit.MINUTES.sleep ( 2 );
                  }
                  catch(Exception ex)
                  {

                  }
              }
              if( !tempBoard.movePiece(tempMove.getMoveI (),tempMove.getMoveJ (),tempMove.getMoveMovement ()))
                  tempBoard.switchPlayer();

                tempNode = tempNode.nodeList[tempIndex];





            }





        }
        if(!tempNode.explored)
        {
            //System.out.println("the final node was not explored");
            tempNode.explored = true; /////// OHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH!, the next time what will happen to it?

        }
       // System.out.println(" last node value:   "  + tempNode.board.specialEvaluateBoard ());
//        if(currentDepth != maxDepth)
//            System.out.println("max Depth not Reached");

        currentDepth = 0;
//        while(currentDepth<=maxDepth && !gameEnded) { // we may be able to replace gameEnded with tempNode.gameEnded , diff? the first assumes that the first iteration is not ended , it should be when
//            // generateRandomAtIndex as in, this function shouldn't be called when the gameEnded
//
//            if (!tempNode.checkExplored ()) {
//                tempCounter = 0;
//         //       tempNode.nodeList = new Node[64]; // i am not sure if we need this
//                tempNode.explored = true;
//                boolean legal[];
//
//                for (int i = 0; i < tempBoard.boardSize; i++) {
//                    for (int j = 0; j < tempBoard.boardSize; j++)
//                    {
//                        legal =tempBoard.checkMovementLegal ( i, j, 'L' );
//                        if (legal[0]) {
//                            tempNode.nodeList[tempCounter] = new Node();
//                            tempNode.nodeList[tempCounter].setNodeMove ( i, j, 'L' );
//                            tempCounter++;
//                        }
//                        legal =tempBoard.checkMovementLegal ( i, j, 'R' );
//                        if (legal[0]) {
//                            tempNode.nodeList[tempCounter] = new Node();
//                            tempNode.nodeList[tempCounter].setNodeMove ( i, j, 'R' );
//                            tempCounter++;
//                        }
//                        legal = tempBoard.checkMovementLegal ( i, j, 'G' );
//                        if (legal[0]) {
//                            tempNode.nodeList[tempCounter] = new Node();
//                            tempNode.nodeList[tempCounter].setNodeMove ( i, j, 'G' );
//                            tempCounter++;
//                        }
//                        legal =tempBoard.checkMovementLegal ( i, j, 'H' );
//                        if (legal[0]) {
//                            tempNode.nodeList[tempCounter] = new Node();
//                            tempNode.nodeList[tempCounter].setNodeMove ( i, j, 'H' );
//                            tempCounter++;
//                        }
//
//
//                    }
//
//
//                }
//                tempNode.currentSize = tempCounter;
//
//
//            } // End of IF-STATEMENT
//
//            if(tempNode.currentSize == 0)
//            {
//             tempNode.gameEnded = true; // interesting case
//            // System.out.println("we're here, gameEnded is true at generateRandomIndex");
//             gameEnded = true;
//            }
//            else {
//
//                Random rand = new Random ();
//                int tempIndex = rand.nextInt ( tempNode.currentSize );
//                Move tempMove = tempNode.nodeList[tempIndex].getNodeMove ();
//           //     tempBoard.printPlayerBoard ();
//                if (tempBoard.movePiece ( tempMove.getMoveI (), tempMove.getMoveJ (), tempMove.getMoveMovement () )) // why this approach? because i didnt want to keep a copy of the board on every node, it would take too much memory
//                {
//                    repeat = true;
//                    tempNode.nodeList[tempIndex].myPlayer = tempNode.myPlayer;
//                 //   tempBoard.printPlayerBoard ();
//                }
//                else {
//                    repeat = false;
//                    tempNode.nodeList[tempIndex].myPlayer = !tempNode.myPlayer;
//                }
//
//                // ^ why not from checkMovementLegal?
//
//                tempNode.nodeList[tempIndex].setGameEnded ( tempBoard.checkGameEnded () );
//                gameEnded = tempNode.nodeList[tempIndex].gameEnded; // this may be redundant
//
//                tempNode = tempNode.nodeList[tempIndex];
//                currentSeq.moveArrayIndex[currentDepth] = tempIndex; // this may cause an error
//                currentDepth++;
//                currentSeq.currentSize = currentDepth;
//                if (!repeat) // if you're not gonna repeat then switch player
//                {
//                    tempBoard.switchPlayer ();
//                }
//            }
//
//
//        }// END OF WHILE LOOP
//
//
//       // basically this section is used to indicate that we are at maxDepth or gameEnded
//        ;
//
//        tempNode.nodeValue = tempBoard.specialEvaluateBoard(); // usually, when we evaluate the board, it has to be specific to a certain player always.
//        tempNode.explored = true;
//    //    tempNode.explored = true; // why does this needs to be explored?
//        currentDepth = 0;

    }



    Node fillBoardAndTree(int index,Player board,NewSequence currentSeq) // I think board is passed by reference so it will keep the value, Maaz, be careful of by reference passing
    {
        int i,j;
        char l;
//
         Node tempNode = root;
          // optimizable , also you could change its location, instead of here, put it in the beginning, by copying the board into root.
        // should be outside the process ^
         while(currentDepth <index)
         {
             tempNode = tempNode.nodeList[currentSeq.moveArrayIndex[currentDepth]];
             i = tempNode.nodeMove.getMoveI (); // does ROOT have a move? if it  has a list of possible moves, then it has a move. In order to make that move.
             j = tempNode.nodeMove.getMoveJ (); // check when the first time we're filling, what are their values? for the first iteration? is it always 0, 0?
             l = tempNode.nodeMove.getMoveMovement ();
             if(!board.movePiece ( i,j,l))
                 board.switchPlayer();


             currentDepth++;

         }

         return tempNode;
//        Move tempMove;
//
//        for(int i =0;i<index;i++) // you could rewrite this code,for it to be a while loop, remove the index I, and replace it with currentDepth but there is no significant performance benefit
//        {
//            tempMove = tempNode.nodeList[currentSeq.moveArrayIndex[i]].getNodeMove (); //  I indicating the depth
//           if(! board.movePiece(tempMove.getMoveI (),tempMove.getMoveJ (),tempMove.getMoveMovement ()))
//           {
//               board.switchPlayer ();
//               //tempNode.nodeList[currentSeq.moveArrayIndex[i]].myPlayer = !tempNode.myPlayer; // not needed
//           }
//           else
//           {
//             //  tempNode.nodeList[currentSeq.moveArrayIndex[i]].myPlayer = tempNode.myPlayer; // not needed
//
//           }
//
//
//            tempNode = tempNode.nodeList[currentSeq.moveArrayIndex[i]];
//            currentDepth++; // this is for purposes outside this function hence why i wanted it to rewrite it
//        }
//
//        return tempNode;



    }

    // the solution is making node.expored = true for when reaching maxDepth as well. reason: because at maxDepth you're not ever going to go even more deeper than maxdepth
    // so its okay.

    double traverseTree(Node node) { // * for some reason, traverseTree keeps returning different *best* sequences, even though, there should be only the same sequence for newly generated branches of the tree
//        // what is the role of each inputs
        double array[] = new double[node.currentSize]; // an issue perhaps with how its starting with array[] with zero values initialized
        int bestIndex;
//        //currentDepth++; // this is partially what caused te problem
//
        if (currentDepth < maxDepth && !node.checkGameEnded () && node.explored ) // node.explored isn't node.explored a tautological statement to currentDepth<maxDepth? because it would
            // have to be explored
        {
//          //  currentDepth++;
//            // here's a problem with this approach, this kind of assumes that some of the moves do not have repetition
//
//        //    int tempIndex;

            for (int i = 0; i < node.currentSize; i++) {
               // if (node.nodeList[i].checkExplored ()) // ok it makes sense, when currentDepth == maxDepth -1, then we dont care if its explored or not, we just want the value in it
                {


                    currentDepth++;
                    array[i] = traverseTree ( node.nodeList[i] ); // there is a real problem here betwen checkExplored() and if(currentDepth<maxDepth) and its whats causing al the trouble, the last index cant get traversed
                    // because its not explored, and it cannot be explored
                    currentDepth--; // automatically resets currentDepth
                }

            }

            bestIndex = indexMaxOrMin ( array, node.myPlayer, node.currentSize );
            node.nodeSeq.copySequence ( node.nodeList[bestIndex].nodeSeq );
            node.nodeSeq.addIndex ( currentDepth, bestIndex );
            node.setNodeValue ( node.nodeList[bestIndex].getNodeValue () );

            return node.getNodeValue ();


        }
        else {
             if(!node.explored)
                return 6976.666; // maybe we should also set node.setValue(6976.666)
            else if( (node.gameEnded || currentDepth==maxDepth)) // if we did it this way then everytime a node gets into max depth it will return this
             {
                 node.setNodeValue (node.board.specialEvaluateBoard ()  ); // optimizable
                 return node.board.specialEvaluateBoard ();
             }
            else
            {
                System.out.println("this statement should never be reached");
                return 5000;
            }

        }

    }
//
//            if(node.currentSize == 0) // is it possible to go  through the tree and at some point reach a node that is  empty?
//                for(int x =0;x<10;x++)
//                System.out.println("help something fucked up happened");
//
//
//                // suppose we fixed the problem
//
//
//
//            bestIndex = indexMaxOrMin(array,node.myPlayer,node.currentSize); // this part will totally rely on whether or not node.myPlayer value was correctly set or not
//            node.nodeSeq.copySequence( node.nodeList[bestIndex].nodeSeq);
//            node.nodeSeq.addIndex(currentDepth,bestIndex);
//            node.setNodeValue ( node.nodeList[bestIndex].getNodeValue () );
//
//            return node.getNodeValue ();
//
//
//
//        }
//        else { // currentDepth == maxDepth
//
//           // it used to be the case that, when the depth was 5, we would be here, there, the depth was 5, but you were there, so there's no way you get from there to here so you got the problem
//            // here the depth is 5, and now we return the value
//            return node.getNodeValue ();
//        }
//
//
//




 //   }

    int indexMaxOrMin(double[] array, boolean playerTurn, int maxIndex)
    {
        Random rand = new Random();
        int temp = 0;
        while(temp<maxIndex)
        {
            if(array[temp]!=6976.666)
                break;
            else
                temp++;


        }
        int[] topBestArray = new int[maxIndex]; // the name needs to be changed
        int topBestCounter = 0;
        double best = array[temp];
        int bestIndex = temp; // this removed
        topBestArray[topBestCounter] = temp;
        topBestCounter++;


        if(playerTurn)
        {
            for(int i =temp+1;i<maxIndex;i++) // just increment temp and let it start from temp
            {

                if(best<array[i] && array[i]!=6976.666)
                {
                    best=array[i];bestIndex=i; // bestIndex is removable
                    topBestCounter = 0;
                    topBestArray[topBestCounter] = i;
                    topBestCounter++;
                }
                else if(best ==array[i] && array[i]!=6976.666)
                {
                    topBestArray[topBestCounter] =i;
                    topBestCounter++;
                }

            }


        }
        else
        {

            for(int i =temp+1;i<maxIndex;i++)
            {

                if(best>array[i] && array[i]!=6976.666)
                {
                    best = array[i];
                    bestIndex =i;
                    topBestCounter=0;
                    topBestArray[topBestCounter] =i;
                    topBestCounter++;
                }
                else if(best==array[i] && array[i] !=6976.666)
                {
                    topBestArray[topBestCounter] = i;
                    topBestCounter++;
                }

            }

        }

        int randomIndex = rand.nextInt(topBestCounter);

        return topBestArray[randomIndex];




    }

    double evaluatMinimax(Player board,int localdepth,boolean maximizingPlayer,Node currentNode, double alpha,double beta)
    {
       // currentNode = new Node(); no need, just pass a newly generated root
        boolean gameOver = board.checkGameEnded (); // check if this actually is correct
        double decoyAlpha = alpha; // because Java's pointer nature
        double decoyBeta = beta;
        int tempLocalDepth = localdepth;
         // okay...

        if(currentDepth >= maxDepth||gameOver) // or >=
        {
           // if(board.mySoldier =='Q')
          //      board.switchPlayer(); //this is un-necessairy? what if at max depth its the other player
            return board.specialEvaluateBoard ();
        }
        // not certain but not the problem

        if(maximizingPlayer) // don't forget to change the other case, for any changes made here since its the kernel
        {
            double maxEval = -10000; // correct
            calculateListOfPossibleMoves(board,currentNode); // 95% certain its correct
            if(currentNode.currentSize == 0) {
                 // either this or redefine what it means for it to be game over
                currentNode.gameEnded = true;
                gameOver = true;
                return maxEval;
            }
            Move tempMove; // correct
            for(int i =0;i<currentNode.currentSize;i++) // after calculating each possible move, we're going through each one of them
            {

                tempMove = currentNode.nodeList[i].nodeMove;
                if(! currentNode.nodeList[i].board.movePiece(tempMove.getMoveI (),tempMove.getMoveJ (),tempMove.getMoveMovement ())) // ok, i took the node that i've discovered to be possible, took its move and applied
                currentNode.nodeList[i].board.switchPlayer (); //  it to its board
                // it will return true, if you ate and could eat more
                // it will return false, if you can't eat more.
                // correct so far till here


                currentDepth++;
                currentNode.nodeList[i].nodeValue = evaluatMinimax ( currentNode.nodeList[i].board,++tempLocalDepth,!maximizingPlayer,currentNode.nodeList[i],decoyAlpha,decoyBeta ); // watchout for local depth // ??!  i think nvm.
                currentDepth--;
                if(currentNode.nodeList[i].nodeValue > maxEval)
                    maxEval =currentNode.nodeList[i].nodeValue; // ok! but then what will happen to it?

                if(decoyAlpha<currentNode.nodeList[i].nodeValue)
                    decoyAlpha =currentNode.nodeList[i].nodeValue;
                if(decoyBeta<=decoyAlpha)
                    break;

            }

            return maxEval;


        }
        else
        {
            double minEval = 10000;
            calculateListOfPossibleMoves(board,currentNode);
            if(currentNode.currentSize == 0) {
                // either this or redefine what it means for it to be game over
                currentNode.gameEnded = true;
                gameOver = true;
                return minEval;
            }


            Move tempMove;
            for(int i =0;i<currentNode.currentSize;i++)
            {

                tempMove = currentNode.nodeList[i].nodeMove;
                if(! currentNode.nodeList[i].board.movePiece(tempMove.getMoveI (),tempMove.getMoveJ (),tempMove.getMoveMovement ()))
                currentNode.nodeList[i].board.switchPlayer ();
                currentDepth++;
                currentNode.nodeList[i].nodeValue = evaluatMinimax ( currentNode.nodeList[i].board,++tempLocalDepth,!maximizingPlayer,currentNode.nodeList[i],decoyAlpha,decoyBeta );
                currentDepth--;
                if(currentNode.nodeList[i].nodeValue < minEval)
                    minEval =currentNode.nodeList[i].nodeValue;

                if(decoyBeta>currentNode.nodeList[i].nodeValue)
                    decoyBeta =currentNode.nodeList[i].nodeValue;
                if(decoyBeta<=decoyAlpha)
                    break;

            }

            return minEval;


        }



    }

    void calculateListOfPossibleMoves(Player board,Node node)
    {
        int tempCounter = 0;
    //    node.nodeList = new Node[64];
        boolean legal[];

        for (int i = 0; i < board.boardSize; i++) {
            for (int j = 0; j < board.boardSize; j++) {
                legal =board.checkMovementLegal ( i, j, 'L' );

                if (legal[0]) {
                    node.nodeList[tempCounter] = new Node();
                    node.nodeList[tempCounter].setNodeMove ( i, j, 'L' );
                    node.nodeList[tempCounter].copyBoard(board);
                    tempCounter++;
                }
                legal =board.checkMovementLegal ( i, j, 'R' );
                if (legal[0]) {
                    node.nodeList[tempCounter] = new Node();
                    node.nodeList[tempCounter].setNodeMove ( i, j, 'R' );
                    node.nodeList[tempCounter].copyBoard(board);
                    tempCounter++;
                }
                legal =board.checkMovementLegal ( i, j, 'G' );
                if (legal[0]) {
                    node.nodeList[tempCounter] = new Node();
                    node.nodeList[tempCounter].setNodeMove ( i, j, 'G' );
                    node.nodeList[tempCounter].copyBoard(board);
                    tempCounter++;
                }
                legal =board.checkMovementLegal ( i, j, 'H' );
                if (legal[0]) {
                    node.nodeList[tempCounter] = new Node();
                    node.nodeList[tempCounter].setNodeMove ( i, j, 'H' );
                    node.nodeList[tempCounter].copyBoard(board);
                    tempCounter++;
                }


            }


        }
        node.currentSize = tempCounter;
        // if no move was possible, node.currentSize would be 0






    }

    double fillPrint(Node node)
    {

        double array[] = new double[node.currentSize];
        int bestIndex;
//
//
        if (currentDepth < maxDepth && !node.checkGameEnded () && node.explored )

        {


            for (int i = 0; i < node.currentSize; i++) {

                {


                    currentDepth++;
                    array[i] = traverseTree ( node.nodeList[i] );

                    currentDepth--;
                }

            }

            bestIndex = indexMaxOrMin ( array, node.myPlayer, node.currentSize );

            for(int i = 0;i<node.currentSize;i++)
            {
                printNodesList.addNode(currentDepth,i,array[i]);

            }


            node.setNodeValue ( node.nodeList[bestIndex].getNodeValue () );

            return node.getNodeValue ();


        }
        else {
            if(!node.explored)
                return 6976.666;
            else if( (node.gameEnded || currentDepth==maxDepth))
            {
                printNodesList.addNode(currentDepth,0,node.board.specialEvaluateBoard ());
                return node.board.specialEvaluateBoard ();
            }
            else
            {
                System.out.println("this statement should never be reached");
                return 5000;
            }

        }

    }



    void print()
    {
//        fillPrint(root);
//        // idea is: if the depth is matched, then , if the node Index is matched then, print the node index and value
//        // Question: how many node indecies per depth are there?
//        // Answer: depends on the depth itself, it could be more, it could be less
//        // Idea: go through each depth, and go through each printnodeList, if depth is matched,
//        // problem: we don't know how the maximum number of indecies per depth
//        // its even a bit more complicated;
//
//
//        for(int i =0;i<maxDepth;i++)
//        {
//            System.out.println("Depth: " + i);
//
//
//            for(int j =0;j<printNodesList.currentSize;j++)
//            {
//
//
//                        if(printNodesList.printNodes[j].getDepth () == i)
//
//
//
//
//
//
//            }
//
//
//
//        }
//
    }


}

