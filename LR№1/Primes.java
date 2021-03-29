/**
 * Primes
 */
public class Primes {

    public static void main(String[] args) { 
        int n = 100;                         // До какого числа искать просыте числа 
        for (int i = 2; i<=n; i++)
        {
        if (isPrime(i) == true)              // Вызов функции, которая определяет является ли аргумент простым числом
            System.out.println(i);           // Вывод найденных простых чисел
            
        }
        
    }
    public static boolean isPrime(int m)   // Функция, которая определяет является ли аргумент простым числом
    {
            for (int i=2; i<m;i++)
            {
              if (m%i==0) return false;   // Нахождение чисел, которые имеют какие-либо делители
            }
            return true;                  // Если делителей нет, число простое 
                
    }
}   
