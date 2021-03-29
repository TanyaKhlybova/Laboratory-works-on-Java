public class Lab2 {
    public static void main(String[] args) {
        Point3d firstPoint = new Point3d(0,6,-7);
        Point3d secondPoint = new Point3d(0,6,-7);
        Point3d thirdPoint = new Point3d(9,2,6);
        if ((firstPoint.compareCoord(secondPoint) == true) || (firstPoint.compareCoord(thirdPoint)==true)||(secondPoint.compareCoord(thirdPoint)))
            System.out.println("Одна из точек равна другой, невозможно посчитать площадь");
        else {
            double Square = computeArea(firstPoint, secondPoint, thirdPoint);
            System.out.println("Площадь равна: " + Square);
        }    
    }
    public static double computeArea(Point3d firstPoint, Point3d secondPoint, Point3d thirdPoint){
       double a = firstPoint.distanceTo(secondPoint);
       double b = secondPoint.distanceTo(thirdPoint);
       double c = firstPoint.distanceTo(thirdPoint);
       double p = 0.5*(a+b+c);
       double s = Math.sqrt(p*(p-a)*(p-b)*(p-c));
       double scale = Math.pow(10, 2);
        s = Math.ceil(s * scale) / scale;
       return s;      
    }
}
