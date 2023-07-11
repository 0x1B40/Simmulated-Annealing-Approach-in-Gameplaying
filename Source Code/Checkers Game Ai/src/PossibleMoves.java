public class PossibleMoves {

    Move listOfPossibleMoves[];
    int maxSize;
    int currentSize;
    Move bestMove;



    PossibleMoves()
{
    maxSize = 64;
    currentSize = 0;
    listOfPossibleMoves = new Move[maxSize];


}

PossibleMoves(PossibleMoves x)
{
    maxSize = 64;
    currentSize = x.getCurrentSize();
    for(int i = 0;i<currentSize;i++)
    {
        listOfPossibleMoves[i] = x.listOfPossibleMoves[i];
    }


}

    void setCurrentSize(int x)
    {
        currentSize = x;
    }

    int getCurrentSize()
    {
        return currentSize;
    }



void setBestMove(Move x)
{
    bestMove = x;
}

Move getBestMove()
{
    return bestMove;
}

void addPossibleMove(Move x)
{
    Move tempMove = new Move();
    tempMove.copyMove(x);
    if(currentSize<maxSize) {
        listOfPossibleMoves[currentSize] = tempMove;
        currentSize++;
    }
    else
    {
        System.out.println("maximum possible moves reached and exceeded, error in the algorithm");
        System.exit(1);
    }
}

    void addPossibleMove(int i,int j,char k)
    {
        Move tempMove = new Move(i,j,k);
        if(currentSize<maxSize) {
            listOfPossibleMoves[currentSize] = tempMove;
            currentSize++;
        }
        else
        {
            System.out.println("maximum possible moves reached and exceeded, error in the algorithm");
            System.exit(1);
        }
    }


Move[] getPossibleMovesList()
{
    return listOfPossibleMoves;
}

boolean compareMove(int x,int y,char z)
{

    for(int i =0;i<currentSize;i++)
    {
        if(listOfPossibleMoves[i].getMoveI () == x &&listOfPossibleMoves[i].getMoveJ () == y && listOfPossibleMoves[i].getMoveMovement () == z)
            return true;
    }

    return false;

}

}
