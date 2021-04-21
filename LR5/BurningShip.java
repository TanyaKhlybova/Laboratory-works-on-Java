package LR5;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;

    /**методу в качестве
аргумента передается прямоугольный объект, и метод должен изменить поля
прямоугольника для отображения правильного начального диапазона для
фрактала. */
    public void getInitialRange(Rectangle2D.Double range){

        range.x = -2;
        range.y = -2.5;
        range.width = 4;
        range.height = 4;

    }

    /**реализует итеративную
функцию для фрактала Мандельброта. В качестве аргументов принимает два числа
 для действительной и мнимой части комплексного числа и возвращает количество итераций для соответсвующего числа */
    public int numIterations(double x, double y)
    {
        
        int iterations = 0;
        /** Initialize zreal and zimaginary. */
        double realNumber = 0;
        double imaginaryNumber = 0;
        
        /**
         * Вычисляет Zn = Zn-1^2 + c, где все значения это комплексные числа, Z0=0, и c это определенная точка фрактала,
         * отображаемая на экране (координаты x и y). Вычисления повторяются пока Z^2 > 4 или пока не достигнуто максимальное 
         * количество итераций.
         */
        while (iterations < MAX_ITERATIONS &&
               realNumber * realNumber + imaginaryNumber * imaginaryNumber < 4)
        {
            double newReal = realNumber * realNumber - imaginaryNumber * imaginaryNumber + x;
            double newImaginary = Math.abs(2 * realNumber * imaginaryNumber) + y;
            realNumber = newReal;
            imaginaryNumber = newImaginary;
            iterations += 1;
        }
        
        /**
         * если достигнуто максимальное количество итераций, возвращаем -1
         * чтобы показать что точка не вышла за пределы границ.
         */
        if (iterations == MAX_ITERATIONS)
        {
            return -1;
        }
        
        return iterations;
    }
    public String toString(){
        return "BurningShip";
    }
}


