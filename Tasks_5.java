import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TimeZone;
import java.time.*;

public class Tasks_5 {
        public static void main(String[] args) {
        System.out.println("Одинаковый шаблон: " + sameLetterPattern("FFGG", "CDCD"));
        System.out.println("Паук и муха: " + spiderVsFly("H3", "E2"));
        System.out.println("Количество цифр: " + digitsCount(4666));
        System.out.println("Количество очков: " + totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));
        System.out.println("Последовательный прогон: " + longestRun(new int[] {1, 2, 3, 5, 6, 7, 8, 9}));
        System.out.println("Балл за тест: " + takeDownAverage(new int[] {95, 83, 90, 87, 88, 93}));
        System.out.println("Перестановка слов: " + rearrange("Tesh3 th5e 1I lov2e way6 she7 j4ust i8s."));
        System.out.println("Наибольшее число: " + maxPossible(8732, 91255));
        System.out.println("Новая дата: " + timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println("Новое число: " + isNew(321));
     }

    public static boolean sameLetterPattern(String a, String b) {
        if (a.length() != b.length()) return false;
        HashMap<Character, Character> pattern = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            if (!pattern.containsKey(a.charAt(i)))
                pattern.put(a.charAt(i), b.charAt(i));
            if (b.charAt(i) != pattern.get(a.charAt(i)))
                return false;
        }
        return true;
    }
// расчет длины между двумя радиалами: l = (x*кольцо мухи * sin(45))/sin(67.5) = x*кольцо мухи*0,76
// x возьмем за единицу
    public static String spiderVsFly(String spider, String fly) {
        int sRadial = spider.charAt(0) - 65;
	    int sRing = spider.charAt(1) - 48;
	    int fRadial = fly.charAt(0) - 65;
	    int fRing = fly.charAt(1) - 48;
	    
	    double firstDist = sRing + fRing;
	    double secondDist = Math.abs(sRing - fRing) + ((sRadial + fRadial) % 8) * fRing * 0.76;
	    
	    String path = "";
	    
	    if (firstDist <= secondDist) {
	        for (int i = 0; i < sRing; i++) {
	            path += spider.charAt(0);
	            path += sRing - i;
	            path += '-';
	        }
	        path += "A0-";
	        for (int i = 0; i < fRing; i++) {
	            path += fly.charAt(0);
	            path += i + 1;
	            path += '-';
	        }
	    } else {
	        for (int i = 0; i < Math.abs(sRing - fRing); i++) {
	            path += spider.charAt(0);
	            if (sRing > fRing) path += sRing - i;
	            else path += sRing + i;
	            path += '-';
	        }
	        for (int i = 0; i <= (sRadial + fRadial) % 8; i++) {
	            path += (char)(65 + (sRadial + i) % 8);
	            path += fly.charAt(1);
	            path += '-';
	        }
	    }
	    
	    return path.substring(0, path.length() - 1);
    }

    public static int digitsCount(long x) {
        if(x < 10) return 1;
        return 1 + digitsCount(x / 10);
    }

    public static int[] getChars(String word) {
	    int[] charset = new int[122];
	    for (char c : word.toCharArray()) charset[c]++;
	    return charset;
	}
	
	public static int totalPoints(String[] words, String mainWord) {
	    int points = 0;
	    int[] mainCharset = getChars(mainWord);
	    for (int i = 0; i < words.length; i++) {
	        int[] wordCharset = getChars(words[i]);
	        boolean check = true;
	        for (int j = 0; j < 122; j++)
	            if (wordCharset[j] > mainCharset[j]) {
	                check = false;
	                break;
	            }
	        if (check) {
	            points += words[i].length() - 2;
	            if (words[i].length() == 6) points += 50;
	        }
	    }
	    return points;
	}

    public static int longestRun(int[] arr) {
        int max = 1;
        int count = 1;
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i+1] - arr[i] == 1 || arr[i+1] - arr[i] == -1) {
                count++;
                if (max < count) max = count;
            } 
            else count = 1;
        return max;
    }

    public static String takeDownAverage(int[] percents) {
        int average = 0;
        for (int i=0; i< percents.length; i++)
            average += percents[i];
        return (average / percents.length - percents.length*5 - 5) + "%";
    }

    public static String rearrange(String str) {
        String[] words = str.split(" ");
        String[] sentence = new String[words.length];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (Character.isDigit(word.charAt(i))) {
                    sentence[word.charAt(i) - 48 - 1] = word.substring(0, i) + word.substring(i+1);
                    break;
                }
            }
        }
        return String.join(" ", sentence);
    }

    public static int[] splitNumber(int num) {
        String temp = Integer.toString(num);
        int[] nums = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++)
        {
        nums[i] = temp.charAt(i) - '0';
        }
        return nums;
    }

    public static int[] reverse(int arr[])
    {
        int[] b = new int[arr.length];
        int j = arr.length;
        for (int i = 0; i < arr.length; i++) {
            b[j - 1] = arr[i];
            j = j - 1;
        }
        return b;
    }
    public static int maxPossible(int firstNum, int secondNum) {
        int[] x = splitNumber(firstNum);
        int[] y = splitNumber(secondNum);
        Arrays.sort(y);
        y = reverse(y);
        int yIndex = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] < y[yIndex]) {
                x[i] = y[yIndex];
                yIndex++;
            }
            if (yIndex == y.length)
                break;
        }
        x = reverse(x);
        firstNum = 0;
        int dec = 1;
        for (int i : x) {
            firstNum += i * dec;
            dec *= 10;
        }
        return firstNum;
    }

    public static SimpleDateFormat enteredDate = new SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH);
    public static SimpleDateFormat newDate = new SimpleDateFormat("yyyy-M-d HH:mm"); 
    
    public static String getGMT(String city) {
        if (city == "Los Angeles") return "GMT-08:00";
        if (city == "New York") return "GMT-05:00";
        if (city == "Caracas") return "GMT- 04:30";
        if (city == "Buenos Aires") return "GMT-03:00";
        if (city == "London") return "GMT00:00";
        if (city == "Rome") return "GMT+01:00";
        if (city == "Moscow") return "GMT+03:00";
        if (city == "Tehran") return "GMT+03:30";
        if (city == "New Delhi") return "GMT+05:30";
        if (city == "Beijing") return "GMT+08:00";
        if (city == "Canberra") return "GMT+10:00";
        return "GMT";
    }
    
    public static String timeDifference(
        String cityA, String timestamp, String cityB) {
            enteredDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityA)));
            Date date;
        try {
            date = enteredDate.parse(timestamp);
            newDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityB))); 
            return newDate.format(date);
        } 
        catch(ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isNew(int n) {
        int[] num = splitNumber(n);
        for (int i = 0; i < num.length - 1; i++)
            if (num[i] > 0 && num[i] > num[num.length - 1])
                return false;
        return true;
    }
}
