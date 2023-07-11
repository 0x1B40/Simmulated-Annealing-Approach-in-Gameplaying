public class Point {

    double i;
    double j;

    Point()
    {
        i = 0.0;
        j = 0.0;
    }

    Point(double x,double y)
    {
        i = x;
        j =y;
    }

    double getPointI()
    {
        return i;
    }

    double getPointJ()
    {
        return j;
    }

void setPoint(double x,double y)
    {
        i = x;
        j = y;

    }
}
