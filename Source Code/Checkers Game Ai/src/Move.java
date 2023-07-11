public class Move {

    int i;
    int j;
    char movement;
    int value;
    Move()
    {

//System.out.println("movement is created");

    }

    Move(int x,int y,char z)
    {
        i =x;
        j =y;
        movement=z;
    }


    void setMove(int x, int y, char z)
    {
        i = x;
        j = y;
        movement=z;
    }

    int getMoveI()
    {
        return i;
    }

    int getMoveJ()
    {
        return j;
    }

    char getMoveMovement()
    {
        return movement;
    }

    void setValue(int x)
    {

        value = x;
    }

    int getValue()
    {
        return value;
    }

    void copyMove(Move x)
    {
        i =x.getMoveI();
        j =x.getMoveJ();
        movement = x.getMoveMovement ();



    }





}
