/**
* двумерный класс точки.
*/
public class Point2d {
    public static void main(String[] args) {
        Point2d firstPoint = new Point2d(19,5);
        double x = firstPoint.getX();
        double y = firstPoint.getY();
        System.out.printf("x=%.0f; y=%.0f \n", x, y);

        
    }
/* координата X */

private double xCoord;
/** координата Y **/
private double yCoord;
/** Конструктор инициализации **/
public Point2d ( double x, double y) { // метод
xCoord = x;
yCoord = y;
}
/** Конструктор по умолчанию. **/
public Point2d () { // метод
//Вызовите конструктор с двумя параметрами и определите источник.
this(0, 0);
}
/** Возвращение координаты X **/
public double getX () { // функция
return xCoord;
}
/** Возвращение координаты Y **/
public double getY () { // функция
return yCoord;
}
/** Установка значения координаты X. **/
public void setX ( double val) { // функция
xCoord = val;
}
/** Установка значения координаты Y. **/
public void setY ( double val) { // функция
yCoord = val;
}
}
