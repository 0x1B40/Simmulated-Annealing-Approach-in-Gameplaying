import java.util.Random;
public class Tree  // every board in evaluateBoard() should have a we'll call this boardTree
{

    int treeSize;
    int currentSize;
    Move treeList[];
    Move max;

    Tree() //  default constructor
    {
        treeSize =32;
        currentSize = 0;
        treeList = new Move[treeSize];
    }
    void setTreeSize(int x)
    {
        treeSize = x;
    }

    int getTreeSize()
    {
        return treeSize;
    }


    void addMove(int i,int j,char movement,int movementValue)
    {
        Move newMove = new Move(i,j,movement);
        newMove.setValue(movementValue);
        addTreeNode(newMove);

    }
    // correct
    void addTreeNode(Move move)
    {
        if(currentSize < treeSize)
        {
            treeList[currentSize] = move; // or moveValue
            currentSize++;
        }
        else
        {
            System.out.println("cannot add more to the tree, the tree has reached maxSize");
        }
    }
    // correct

    Move getTreeNode(int index)
    {
        return treeList[index];
    }
    // correct

    int evaluateTree()
    {
        int bestCounter = 0; // we did need u.
        int bestCounterList[] = new int[currentSize]; // idk why it needs +1
        max = treeList[0];
        for(int i =0;i<currentSize;i++)
        {
            if(treeList[i].getValue() > max.getValue())
                max = treeList[i];

        }

        for(int j =0;j<currentSize;j++)
        {
            if(treeList[j].getValue() == max.getValue())
            {
                bestCounterList[bestCounter] = j;
                bestCounter++;
            }
        }


        Random rand = new Random();
        if(bestCounter !=0) {
            bestCounter = rand.nextInt ( bestCounter );
            max = treeList[bestCounterList[bestCounter]]; // no need to deduct -1 because rand already deducts it.
            return max.getValue ();
        }
        else
        {
            System.out.println("best counter is equal to zero");
            return max.getValue();
        }





    }

    Move getMoveMax()
    {
        return max;
    }

    void printTree()
    {
        System.out.println("Printing tree:");
        for(int i =0;i<currentSize;i++)
        {

            System.out.print(String.valueOf(treeList[i].getValue())+ "\t");
        }
        System.out.println();

    }
}



