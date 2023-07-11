public class PrintNodesList {

    int currentSize;
    int maxSize;
    PrintNodes printNodes[];

    PrintNodesList(int max)
    {
maxSize = max;
currentSize = 0;

printNodes = new PrintNodes[maxSize];


    }

    void addNode(int depth,int index, double value)
    {
        printNodes[currentSize].depth = depth;
        printNodes[currentSize].nodeIndex = index;
        printNodes[currentSize].nodeValue = value;
        currentSize++;



    }


}
