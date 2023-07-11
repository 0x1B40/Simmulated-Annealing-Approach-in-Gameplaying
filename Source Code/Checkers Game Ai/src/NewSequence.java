public class NewSequence {

    int moveArrayIndex[];
    int currentSize;
    int maxSize;
    int moveSequenceWorth;
    //Player copyBoard;

    NewSequence(int maxDepth)
    {
        currentSize = 0;
        maxSize = maxDepth+1;
        moveSequenceWorth = 0; // not used yet
        moveArrayIndex = new int[maxSize];


    }

    NewSequence( NewSequence x) // it is indeed hard copying
    {
        maxSize = x.maxSize;
        currentSize = x.currentSize;

        moveSequenceWorth=x.moveSequenceWorth;
        moveArrayIndex = new int[maxSize]; // used to be there. correct unless creating new sequences is unnecessary

        for(int i =0;i<x.maxSize;i++) // how come it didnt run into this problem earlier? this is really fcked up, i need to relook it up
        {

            moveArrayIndex[i] = x.moveArrayIndex[i];

        }



    }

    void copySequence(NewSequence x)
    {
        maxSize = x.maxSize;
        currentSize = x.currentSize;
        moveSequenceWorth=x.moveSequenceWorth;
        moveArrayIndex = new int[maxSize];

        for(int i =0;i<x.maxSize;i++)
        {

            moveArrayIndex[i] = x.moveArrayIndex[i];

        }




    }



    void addIndex(int depth,int index)
    {

        moveArrayIndex[depth] = index;
        currentSize++;

    }

    int getIndexAtDepth(int depth)
    {
        return moveArrayIndex[depth];


    }

    boolean compareSequence(NewSequence tempSeq)
    {
        boolean same = true;
        if(currentSize == tempSeq.currentSize) // is the problem with their sizes being different? No and have been proven
        {
            for(int i =0;i<tempSeq.currentSize;i++)
            {
                if(moveArrayIndex[i]!=tempSeq.moveArrayIndex[i]) // a new optimization method to break from the loop once its false
                    same = false;


            }



        }
        else
            same = false;

         // this proves that the problem is not with size , to be removed when this algorithm is working

        return same;



    }


    void print()
    {


        for(int i =0;i<currentSize;i++)
        {
            System.out.print(moveArrayIndex[i] + "  ");
        }
        System.out.println();
    }






}
