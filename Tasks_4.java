import java.util.*;
import java.util.stream.IntStream;

public class Tasks_4 {
    public static void main(String[] args) {
        int[] Numbs = new int [] {2,55,60,97,86};
        System.out.println("Есть ли семерка: " + sevenBoom(Numbs));
        int[] Array = new int [] {5,1,4,3,2,8};
        System.out.println("Переупорядочить массив: " + cons(Array));
        System.out.println("Расшифровка фразы: "+ unmix("hTsii  s aimex dpus rtni.g"));
        System.out.println("Удаление знаков в конце: "+ noYelling("I just!!! can!!! not!!! believe!!! it!!!") );
        System.out.println("Замена X: " + xPronounce("Inside the  x box was a xylophone"));
        int[] arr = new int[] {9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5};
        System.out.println("Максимальная разница: " + largestGap(arr));
        System.out.println("Обратное кодирование 832: " + reversCoding(832));
        System.out.println("Обратное кодирование 51: " + reversCoding(51));
        System.out.println("Обратное кодирование 7977: " + reversCoding(7977));
        System.out.println("Обратное кодирование 1: " + reversCoding(0));
        System.out.println("Обратное кодирование 665: " + reversCoding(665));
        System.out.println("Обратное кодирование 149: " + reversCoding(0));
        System.out.println("Рапространенная последняя буква: " + commonLastVowel("Watch the characters dance!"));
        System.out.println("Сумма: " + memeSum(1222, 30277));
        System.out.println("Строка без повторений: " + unrepeated("teshahset"));
    }
    /* 1.	Создайте функцию, которая принимает массив чисел и возвращает "Бум!", 
    если в массиве появляется цифра 7. В противном случае верните "в массиве нет 7".*/

    public static String sevenBoom(int[] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i : arr) {
            builder.append(i);
        }
        String text = builder.toString();
        
        if (text.contains("7")) return "Boom!";
        else return "There is no 7 in the array";
    }

/*2.	Создайте функцию, которая определяет, могут ли элементы в массиве быть переупорядочены,
 чтобы сформировать последовательный список чисел, где каждое число появляется ровно один раз. */

    public static boolean cons(int[] arr) {
        Arrays.sort(arr);
        int i = 0;
        while ((i < (arr.length-1)) && (arr[i+1]-arr[i]==1))
            i++;
        return (i == arr.length);
    }

/**Каким-то образом все строки перепутались, каждая пара символов поменялась местами.
 *  Помоги отменить это, чтобы снова понять строки. */

    public static String unmix(String str) {
        char[] array = str.toCharArray();
        int i = 0;
        char temp;
        while (i< (array.length-1)){
            temp = array[i];
            array[i]=array[i+1];
            array[i+1] = temp;
            i+=2;
        }
        return new String(array);
        
    }

/**4.	Создать функцию, которая преобразует предложения, заканчивающиеся несколькими вопросительными знаками ? 
 * или восклицательными знаками ! в предложение, заканчивающееся только одним, без изменения пунктуации в середине предложений. */

    public static String noYelling(String str) {
        char[] array = str.toCharArray();
        char exc = 33;
        char quest = 63;
        int i = array.length - 1; 
        while ((i >= 0)&& ((array[i]==exc)||(array[i]==quest) ))
            i--;
        int m = i;
        if (m==array.length-1) return str;
        else{
        for (i = (array.length-1);i>m+1;i-- ){
            array[i] = 0;
        }
        }
        return new String(array);
        
    }

    /**Замените все x на "cks", ЕСЛИ ТОЛЬКО:
Слово начинается с "x", поэтому замените его на "z".
Слово-это просто буква "х", поэтому замените ее на " cks ".
 */

    public static String xPronounce(String str) {
        if (str.charAt(0)=='x')
            str = 'z'+ str.substring(1);
        str = str.replaceAll("(\s)x(\s)"," cks ");
        str = str.replaceAll("(\s)x", " z");
        str = str.replace("x", "cks");
        return str;
    }

    /**6.	Учитывая массив целых чисел, верните наибольший разрыв между отсортированными элементами массива. */

    public static int largestGap(int[] arr) {
        Arrays.sort(arr);
        int maxDiff = 0;
        for (int i = 0; i< arr.length-1; i++){
            if (arr[i+1]-arr[i]>maxDiff) maxDiff = arr[i+1]-arr[i];
        }
        return maxDiff;
    }

    /**Ваша задача состоит в том, чтобы создать функцию, 
     * которая при подаче входных данных ниже производит показанные примеры выходных данных. */

    public static int reversCoding(int x) {
        int[] arr = IntStream.iterate(x, i -> i / 10 > 0 || i % 10 > 0, i -> i / 10).map(i -> i % 10).toArray();
        Arrays.sort(arr);
        int result = 0;

    for (int i =arr.length -1 , n = 0; i >= 0; --i, n++) {
        int pos = (int)Math.pow(10, i);
        result += arr[n] * pos;
    }
    return (x-result);
        // начинаем с 'x', каждое последующее число -
        // это текущее число, делённое на 10
        // получаем [348597, 34859, 3485, 348, 34, 3]
        
        // получаем последнюю цифру
        
        // собираем в массив
        

        /*List<Integer> numbers = new ArrayList<>();
        int y;
        for (int i = x; i > 0; i /= 10)
            numbers.add(i % 10);
        numbers.stream().sorted();
        for (int i = 0; i<numbers.size();i++)
            y = y*10+numbers.get(i);
        
            return (x-y);*/

       
    }

    public static int isVowel(char c) {
        return "aeiou".indexOf(c);
        
    }

    /**8.	Создайте функцию, которая принимает предложение в качестве входных данных 
     * и возвращает наиболее распространенную последнюю гласную в предложении в виде одной символьной строки. */

    public static char commonLastVowel(String str) {
        int[] count = new int[5];
        String[] words = str.split(" ");
        for (int i = 0; i< words.length-1; i++){
            // Если последний символ слова это буква
            if (Character.isLetter(words[i].charAt(words[i].length()-1))){
                // Если последний символ это гласная
                if (isVowel(words[i].charAt(words[i].length()-1))!=-1){
                    // То прибавляю плюс один по индексу этой гласной в массив, где хранится количество каждой буквы
                    count[isVowel(words[i].charAt(words[i].length()-1))]+=1;
                }
            }
            else {
                // Если последний символ был не буквой то делаем тоже самое для предпоследнего
                if (isVowel(words[i].charAt(words[i].length()-2))!=-1){
                    count[isVowel(words[i].charAt(words[i].length()-2))]+=1;
                }         
            }    
            
        }
        // Нахожу максимум в массиве с количеством каждой встречающейся гласной
        int max = 0;
        int m = -1;
         for (int k : count){
             if (count[k]>max){
              max = count[k];
             m = k;
             }
         }
         // m - это индекс гласной в строке "aeiou"
        String vowels = "aeiou";
        return  vowels.charAt(m);
    }

    public static int memeSum(int x, int y) {
        StringBuilder builder = new StringBuilder();
        int len = Math.max(String.valueOf(x).length(), String.valueOf(y).length());
        for (int i = len -1; i>=0; i--){
            builder.append(Integer.toString(x%10+y%10));
            x/=10;
            y/=10;
        }
        builder.reverse();
        return Integer.parseInt(builder.toString());
    }

/**10.	Создайте функцию, которая удалит все повторяющиеся символы в слове, 
 * переданном этой функции. Не просто последовательные символы, а символы, повторяющиеся в любом месте строки. */

    public static String unrepeated(String str) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i< str.length(); i++){
            if (newStr.indexOf(String.valueOf(str.charAt(i)))==-1)
                newStr.append(str.charAt(i));
        }
        return newStr.toString();

    }
}

