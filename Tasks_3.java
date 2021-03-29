import java.util.function.DoublePredicate;

public class Tasks_3 {
   public static class  Millions {
        private String cityName;
        private long peopleNumber;
    
        public Millions(String cityName, long peopleNumber){
            this.cityName = cityName;
            this.peopleNumber = peopleNumber;
        }   
    }
    public static void main(String[] args) {
        /* 1.	Учитывая массив городов и населения, верните массив, в котором все население округлено до ближайшего миллиона.*/
        System.out.println("Население городов: ");
        Millions[] arrMillions; 
        arrMillions= new Millions[] {new Millions("Izhevsk", 765000),new Millions("Moscow", 15600000)};
        millionsRounding(arrMillions);
        for (int i =0; i < arrMillions.length;i++ ) {
             System.out.println(arrMillions[i].cityName +" "+arrMillions[i].peopleNumber);
        }
        double[] arr = otherSides(1);
        
        System.out.println("Стороны треугольника: "+ arr[0] + ", " + arr[1]);
        System.out.println("КНБ: " + rps("rock", "scissors"));
        int[] Numbs = new int [] {7,8,2,5};
        System.out.println("Война чисел: " + warOfNumbers(Numbs));
        System.out.println("Изменение регистра: " + reverseCase("aBcD"));
        System.out.println("Инатор: " + inatorInator("Shrink"));
        System.out.println("Влезает ли кирпич: " + doesBrickFit(1, 2, 1, 1, 1));
        System.out.println("Расстояние: "+totalDistance(36.1, 8.6, 3, true));
        int[] mean = new int []{2,3,2,3};
        System.out.println("Среднее значение: " + mean(mean));
        System.out.println("Одинаковая четность: " + parityAnalysis(243));
    }
    
    public static Millions[] millionsRounding(Millions[] arr) {
        for (int i =0; i < arr.length;i++ ) {
            double x = (double) arr[i].peopleNumber;
            arr[i].peopleNumber = (long) Math.round(x/1000000) * 1000000;
        }
       return arr;
    }
    /* 2.	Учитывая самую короткую сторону треугольника 30° на 60° на 90°, 
        вы должны найти другие 2 стороны (верните самую длинную сторону, сторону средней длины).*/
    public static double[] otherSides(double low ) {
        double middle = Math.round(((Math.sin(Math.toRadians(60))*low)/Math.sin(Math.toRadians(30)))*100.0)/100.0;
        double high = Math.round(((Math.sin(Math.toRadians(90))*low)/Math.sin(Math.toRadians(30)))*100.0)/100.0;
        return new double[] {high, middle};
    }
    /* 3.	Создайте функцию, имитирующую игру "камень, ножницы, бумага". Функция принимает 
    входные данные обоих игроков (камень, ножницы или бумага), первый параметр от первого игрока, второй от второго игрока. */
    public static String rps(String str1, String str2) {
        final String paper = "paper";
        final String scissors = "scissors";
        final String rock = "rock";
        if (str1.equals(str2)) return "TIE";
        else if ((str1.equals(rock) && str2.equals(scissors))|| (str1.equals(scissors) && str2.equals(paper))|| (str1.equals(paper) && str2.equals(rock))) 
            return "Player 1 wins";
        return "Player 2 wins";     
    }
    /* Создайте функцию, которая берет массив целых чисел, 
    суммирует четные и нечетные числа отдельно, а затем возвращает разницу 
    между суммой четных и нечетных чисел. */
    public static int warOfNumbers(int[] arr){
        int even = 0;
        int odd = 0;
        for (int i = 0; i< arr.length; i++){
            if (arr[i]%2 ==0) even +=arr[i];
            else odd += arr[i];
        }
        if (odd>=even) return odd-even;
        else return even -odd;
    }

    /*5.	Учитывая строку, создайте функцию для обратного обращения. 
    Все буквы в нижнем регистре должны быть прописными, и наоборот.*/
     public static String reverseCase(String str) {
        char[] array = str.toCharArray();
        for (int i = 0; i<array.length;i++){
            String x = Character.toString(array[i]); 
            if (x.equals(x.toLowerCase())) x = x.toUpperCase() ;
            else x = x.toLowerCase() ;
            array[i] = x.charAt(0);
        }
        return new String(array);
     }
     /* 6.	Создайте функцию, которая принимает строку из одного слова и выполняет следующие действия:

Конкатенирует inator до конца, если слово заканчивается согласным, в противном случае вместо него конкатенирует  -inator
Добавляет длину слова исходного слова в конец, снабженный '000'.*/

     public static String inatorInator(String str) {
        String vowels = "aeiou";
         int f = str.length();
         String letter = str.substring(f);
         if (letter.contains(vowels)) return str+"-inator" +" "+ f+"000";
         else return str+"inator" + " "+f+"000";
     }
     /* 7.	Напишите функцию, которая принимает три измерения кирпича: высоту(a), 
     ширину(b) и глубину(c) и возвращает true, если этот кирпич может поместиться в отверстие с шириной(w) и высотой(h).*/
     public static boolean doesBrickFit(int a, int b, int c, int w, int h) {
         if ((c<=h &&b<=w)||(c<=w&&b<=h)) return true;
         if ((c<=h && a<=w)||(c<=w&&a<=h)) return true;
         if ((b<=h&& a<=w)||(b<=w&&a<=h)) return true;
         return false;
     }
     /* 8.	Напишите функцию, которая принимает топливо (литры), расход топлива (литры/100 км), пассажиров, 
     кондиционер (логическое значение) и возвращает максимальное расстояние, которое может проехать автомобиль.*/
     public static double totalDistance(double volume, double cons, int number, boolean cond) {
         if (cond) {
            double temp = cons + cons* number*0.05;
           return Math.round((volume/(temp+temp*0.1) *100)*100.0)/100.0 ;
        }        
         else return Math.round((volume/(cons+cons*number*0.05) *100)*100.0)/100.0 ;
     }
     /* 9.	Создайте функцию, которая принимает массив чисел и возвращает среднее значение (average) всех этих чисел.*/
     public static double mean(int[] arr) {
         double sum = 0;
        for (int i = 0; i<arr.length;i++){
            sum+= arr[i];
        }
        return sum/arr.length;
    }
  /*10.	Создайте функцию, которая принимает число в качестве входных данных 
  и возвращает true, если сумма его цифр имеет ту же четность, что и все число. В противном случае верните false.*/
    public static boolean parityAnalysis(int x) {
        int sum = 0;
        int a = x;
        while( a>0) {
            sum += x%10;
            a = a / 10;
       }
       return ((x%2==0 && sum%2==0) || (x%2 ==1 && sum%2==1));
    }
}
