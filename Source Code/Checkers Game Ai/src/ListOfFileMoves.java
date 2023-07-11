public class ListOfFileMoves {

    Move moveList[];
    int maxSize;
    int currentSize;

    ListOfFileMoves()
    {

        maxSize = 1000;
        currentSize = 0;
        moveList = new Move[maxSize];
    }

    void addMove(int i, int j, char k)
    {
        moveList[currentSize] = new Move(i,j,k);
        currentSize++;

    }

}
