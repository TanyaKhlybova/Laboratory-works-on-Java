public class Tasks_1{
public static void main(String[] args) {
    System.out.println("Остаток от деления: "+remainder(2, 3));
    System.out.println("Площадь треугольника: "+triArea(5, 5));
    System.out.println("Количество ног: "+animals(2, 3, 5));
    System.out.println("Профит: "+ profitableGamble(8, 2, 3));
    System.out.println("Операция: "+operation(2, 3, 5));
    System.out.println("Значение ASCII: "+ctoa('B'));
    System.out.println("Сумма чисел до числа: " + addUpTo(100));
    System.out.println("Максимальная длина ребра: "+nextEdge(6, 3));
    int[] arr = {1,2,3};
    System.out.println("Сумма кубов элементов массива: "+sumOfCubes(arr));
    System.out.println("ABSMath: " + abcmath(1, 3, 8));
}
public static int remainder(int x,int y){
    int c = x%y;
    return c;
}
public static double triArea(int x, int y){
    double c = 0.5*(x*y);
    return c;
}
public static int animals(int chickens, int cows, int pigs){
   int c = chickens*2+cows*4+pigs*4;
    return c;
}
public static boolean profitableGamble (double prob, double prize, double pay){
    if (prob*prize > pay) return true;
    else return false;

}
public static String operation(int x, int y, int z){
    String c;
    if (x+y ==z) c = "added";
    if (x-y==z) c = "subtracted";
    if (x*y==z) c= "multiplied";
    if (x/y==z) c="divided";
    else c="none";
    return c;
} 
public static int ctoa(char a){
    int ascii = (int) a;
    return ascii;
}  
public static double addUpTo(int x){
    double c = 0.5*(1+x)*x;
    return c;
}  
public static int nextEdge(int x, int y){
   int c = x+y-1;
   return c;
}
public static int sumOfCubes(int[] arr){
    int sum = 0;
    for (int i = 0; i < arr.length; i++){
        sum += Math.pow(arr[i], 3);
    }
    return sum;
}
public static boolean abcmath(int x, int y, int z){
        for (int i = 1; i<=y; i++){
            x+=x;
        }
        if (x==z) return true;
        else return false;
}
}