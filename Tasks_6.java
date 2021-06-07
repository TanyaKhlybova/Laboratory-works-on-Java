import java.util.*;

public class Tasks_6 {
    public static void main(String[] args) {
        System.out.println("Спрятанная анаграма: " + hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println("Срезы: ");
        for (String s : collect("intercontinentalisat", 6)){
            System.out.println(s);
        }
        System.out.println("Шифр: " + nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println("Два делителя: ");
        for (int i : twoProduct(new int[] {1, 2, 3, 9, 4, 5, 15, 3}, 45))
            System.out.println(i);
        System.out.println("Факториал: ");
        for (int i : isExact(24))
            System.out.println(i);

        System.out.println("Дроби: " + fractions("0.19(2367)"));
        System.out.println("Строка Пилиша: " +pilish_string("CANIMAKEAGUESSNOW"));
        System.out.println("Двоичные строки: " + generateNonconsecutive(2));
        System.out.println("Действительная строка: " + isValid("abccc"));
        System.out.println("Сумма пар до 8: ");
        for (int[] i : sumsUp(new int[] {1, 2, 3, 7, 9}))
            for (int k = 0; k< i.length;k++)
                System.out.println(i[k]);
    }

    public static int[] getChars(String str) {
        int[] set = new int[26];
        for (char c : str.toCharArray())
            set[c - 97]++;
        return set;
    }

    public static String getLetters(String str) {
        str = str.toLowerCase();
        String letters = "";
        for (char c : str.toCharArray())
            if (97 <= c && c <= 122)
                letters += c;
        return letters;
    }
    
    public static String hiddenAnagram(String a, String b) {
        a = getLetters(a);
        b = getLetters(b);
        int[] setB = getChars(b);
        for (int i = 0; i <= a.length() - b.length(); i++) {
            String substr = a.substring(i, i+b.length());
            int[] setA = getChars(substr);
            if (Arrays.equals(setA, setB)) {
                return substr;
            }
        }
        return "noutfond";
    }

    public static String[] collect(String s, int n) {
        if (s.length() < n)
            return new String[] {};
        String[] res = collect(s.substring(n), n);
        String head = s.substring(0, n);
        String[] newRes = new String[res.length+1];
        int index = 0;
        while (index < res.length && res[index].compareTo(head) < 0) index++;
       System.arraycopy(res, 0, newRes, 0, index);
        System.arraycopy(res, index, newRes, index+1, res.length - index);
        newRes[index] = head;
        return newRes;
    }

    public static int[] _getChars(String word) {
	    int[] charset = new int[122];
	    for (char c : word.toCharArray()) charset[c]++;
	    return charset;
	}

    public static String nicoCipher(String message, String key) {
        for (int i = 0; i < (message.length()+key.length()) % key.length(); i++)
            message += ' ';
        int[] set = _getChars(key);
        
        int counter = 1;
        for (int i = 0; i < set.length; i++)
            if (set[i] != 0) {
                if (set[i] > 1)
                    counter += set[i] - 1;
            set[i] = counter++;
            }
        
        int[] offsets = new int[key.length()];
        for (int i = 0; i < key.length(); i++)
            offsets[set[key.charAt(i)]-1] = i; 
        String res = "";
        for (int i = 0; i < message.length(); i++) {
            int index = (i / offsets.length) * offsets.length + offsets[i % offsets.length];
            res += message.charAt(index);
        }
 
        return res;
    }

    public static int[] twoProduct(int[] arr, int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int m : arr) {
            if (n % m == 0 && set.contains(n / m))
                return new int[] {n/m, m};
            set.add(m);
        }
        return new int[] {};
    }

    public static int[] isExact(int factorial, int k, int n) {
        if (factorial < n)
            return isExact(factorial*(k+1), k+1, n);
        return new int[] {factorial, k};
    }
       
    public static int[] isExact(int n) {
        int[] res = isExact(1, 1, n);
        if (res[0] == n) return res;
        return new int[] {};
    }
    
    
    public static String fractions(String frac) {
        int bracket = frac.indexOf('(');
        if (bracket != -1) {
            String f = frac.substring(bracket+1, frac.length()-1);
            frac = frac.substring(0, bracket);
            for (int i = 0; i < 9 - f.length(); i++)
                frac += f;
        }
        double a = Double.parseDouble(frac);
        int coeff = 2;
        while (Math.ceil(a * coeff) - a * coeff > 0.00001) coeff++;

        return "" + (int)Math.ceil(a * coeff) + "/" + coeff;
    }

    public static String pilish_string(String str) {
        String res = "";
        String pi = String.valueOf(Math.PI).replace(".", "");
        int piIndex = 0;
        while (str.length() > 0) {
            int p = pi.charAt(piIndex) - 48;
            int n = Math.min(p, str.length());
            res += str.substring(0, n);
            str = str.substring(n);
            piIndex++;
            if (str.length() > 0) res += ' ';
            else if (p > n)
                for (int i = 0; i < p - n; i++)
                    res += res.charAt(res.length() - 1);
        }
        return res;
    }

    public static String generateNonconsecutive(int n) {
        String res = "";
        String format = "%" + n + 's';
        int count = 2 << (n-1);
        nextNumber:
        for (int i = 0; i < count; i++) {
            String num = String.format(format, Integer.toBinaryString(i)).replace(' ', '0');
            for (int j = 0; j < n - 1; j++)
                if (num.charAt(j) == '1' && num.charAt(j+1) == '1')
                    continue nextNumber;
            res += num + ' ';
        }
        return res;
    }

    public static String isValid(String str) {
        int[] set = getChars(str);
        int[] temp = new int[str.length()];
        for (int i = 0; i < set.length; i++)
            if (set[i] != 0) temp[set[i]]++;
        int cur = 0;
        int max = 0;
        for (int i = 0; i < temp.length; i++)
            if (temp[i] > cur) {
                cur = temp[i];
                max = i;
            }
        boolean index = false;
        for (int i = 0; i < set.length; i++)
            if (set[i] != 0 && max - set[i] != 0) {
                index = true;
                if (index) return "NO";
                
            }
        return "YES";
    }

    public static int[][] sumsUp(int[] arr) {
        ArrayList<int[]> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(8 - arr[i])) {
                int a = arr[i];
                int b = 8 - a;
                if (a > b) {
                    b = a;
                    a = 8 - b;
                }
                res.add(new int[] { a, b});
            }
            map.put(arr[i], i);
        }
        Collections.sort(res, (o1, o2) -> o1[0]-o2[0]);
        int[][] newRes = new int[res.size()][];
        for (int i = 0; i < res.size(); i++)
            newRes[i] = new int[] {res.get(i)[0], res.get(i)[1]};
            
        return newRes;
    }
}
