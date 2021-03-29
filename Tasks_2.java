import java.util.Locale;
public class Tasks_2 {
    public static void main(String[] args) {
        System.out.println("Номер дома напротив: "+oppositeHouse(1, 4));
        System.out.println("Развернуть имя: "+ nameShuffle("Donald Trump"));
        System.out.println("Цена после скидки: "+ discount(89, 20));
        int[] arr = {10, 4, 1, 4, -10, -50, 32, 21};
        System.out.println("Разница между max и min: "+differenceMaxMin(arr));
        System.out.println("Количество одинаоквых значений: " + equal(3, 0, 3));
        System.out.println("Развернуть строку: "+reverse("The quick brown fox."));
        System.out.println("Разница зарплат: "+programmers(147, 33, 526));
        System.out.println("Количество Х и О: "+getXO("ooxXm"));
        System.out.println("Бомба: "+bomb("Hey, did you think there is a BOMB?"));
        System.out.println("Сумма значений по ASCII: "+sameAscii("AA", "B@"));
    }
    public static int oppositeHouse(int x, int n) {
        int c = (n*2 +1)-x;
        return c;
    }
    public static String nameShuffle(String x) {
        String[] words = x.split("\\s");
        String c = words[1]+" "+words[0];
        return c;
    }
    public static double discount(double x, double y) {
        double c = x - (x*y/100);
        return c;
    }
    public static int differenceMaxMin(int[] arr) {
        int max = -1000;
        int min =1000;
        for (int i = 0; i<arr.length; i++){
            if (arr[i]>max) {
                max = arr[i];
            }
            if (arr[i]< min){
                min = arr[i];
            }
        }
        int diff = max - min ;
        return diff; 
    }
    public static int equal(int x, int y, int z) {
        if (x==y && x==z){
            return 3;
        }
        if ((x==y)||(x==z)||(y==z)){
            return 2;
        }
        return 0;
    }
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    public static int programmers(int x, int y, int z) {
        int min = x;
        int max = x;
    if (y < min)  min = y;
    if (z < min)  min = z;
    if (y > max) max = y;
    if (z > max) max = z;
    return max-min;       
    }
    public static boolean getXO(String str) {
        char[] Array = str.toCharArray();
        int countX= 0;
        String letterX = "x";
        char x = letterX.charAt(0);
        String letterXX = "X";
        char XX = letterXX.charAt(0);
        int countO = 0;
        String letterO = "o";
        char o = letterO.charAt(0);
        String letterOO = "O";
        char OO = letterOO.charAt(0);
        for (int i = 0; i<Array.length; i++){
            if (Array[i]== x || Array[i]== XX) countX++;
            if (Array[i]== o|| Array[i]== OO) countO++;

        }
        if (countX==countO|| (countX==0&&countO==0)) return true;
        else return false;
    }
    public static String bomb(String str) {
        str = str.toLowerCase(Locale.ROOT);
        String subsrting = "bomb";
        if (str.contains(subsrting)) return "DUCK!";
        else return "Relax, there's no bomb.";
              
    }
    public static boolean sameAscii(String str1, String str2) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i<str1.length(); i++){
            sum1 += (int)str1.charAt(i);
        }
        for (int i = 0; i<str2.length(); i++){
            sum2 += (int)str2.charAt(i);
        }
        if (sum1==sum2) return true;
        else return false;
    }
}
