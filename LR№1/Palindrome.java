import java.util.Scanner; // Импорт класса Scaner для ввода с консоли 
public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i<args.length; i++){
            String s = args[i];
        }
        Scanner in = new Scanner (System.in); // Ввод слова с консоли
        System.out.print("Input a word:");
        String word = in.nextLine();
        if (isPalindrom(word)==true) System.out.println("It's a palindrom"); // Вызов функции, которая сравнивает исходное и перевернутое слова
        else System.out.println("It's not a palindrom");

    }
    public static String reverseString(String s){  // Функция, которая переворачивает слово
        String str = "";
        int len = s.length();  // Подсчет длины слова
        for (int i=len-1; i>=0; i--){ 
            str+=s.charAt(i); // Конкатенация символов слова с конца к началу
        }
        return str;
    }
    public static boolean isPalindrom (String s){ // Функция, которая сравнивает исходное и перевернутое слова
        String s2 = reverseString(s); // Вызов функции, которая переворачивает слово
        if (s.equals(s2)) return true;  // Сравнение строк
        else return false;
    }
}
