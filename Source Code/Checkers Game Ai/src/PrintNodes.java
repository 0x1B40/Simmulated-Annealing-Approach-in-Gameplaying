public class PrintNodes {

    int depth;
    double nodeValue;
    int nodeIndex;



    PrintNodes(int inputDepth, double inputNodeValue, int inputNodeIndex)
    {
            depth = inputDepth;
            nodeValue = inputNodeValue;
            nodeIndex = inputNodeIndex;
    }

    int getDepth()
    {
        return depth;
    }

    double getNodeValue()
    {
        return nodeValue;
    }

    int getNodeIndex()
    {
        return nodeIndex;
    }

}
