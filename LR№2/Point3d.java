public class Point3d {

    private double xCoord; // Координата X
    private double yCoord; // Координата Y
    private double zCoord; // Координата Z

    /** Конструктор инициализации **/
    public Point3d ( double x, double y, double z) { 
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    /** Конструктор по умолчанию. **/
    public Point3d () { 
        this(0.0, 0.0, 0.0);
    }
    /** Возвращение координаты X **/
    public double getX () { 
        return xCoord;
    }
    /** Возвращение координаты Y **/
    public double getY () { 
        return yCoord;
    }
    /** Возвращение координаты Z **/
    public double getZ () { 
        return zCoord;
    }
    /** Установка значения координаты X. **/
    public void setX ( double val) { 
        xCoord = val;
    }
    /** Установка значения координаты Y. **/
    public void setY ( double val) { 
        yCoord = val;
    }
    /** Установка значения координаты Z. **/
    public void setZ ( double val) { 
        zCoord = val;
    }
    public double vectorLength(double x, double y, double z) {
       double num = Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
       return num;
    }
    public boolean Compare (Point3d a){
       double num1 = vectorLength(xCoord, yCoord, zCoord);
       double num2 = vectorLength(a.xCoord, a.yCoord, a.zCoord);
        if (num1 == num2) return true;
        else return false;
    } 
    /* Метод для сравнения координат точек */   
    public boolean compareCoord(Point3d a){
        if ((a.xCoord==xCoord)&&(a.yCoord==yCoord)&&(a.zCoord==zCoord))
            return true;
        else return false;
    }
    /* Вычисление расстояния между двумя точками*/ 
    public double distanceTo(Point3d a){
        double distance = Math.sqrt(Math.pow((a.xCoord-xCoord),2)+Math.pow((a.yCoord-yCoord),2)+Math.pow((a.zCoord-zCoord),2));
        double scale = Math.pow(10, 2);
        distance = Math.ceil(distance * scale) / scale;
        return distance;
    }
}
