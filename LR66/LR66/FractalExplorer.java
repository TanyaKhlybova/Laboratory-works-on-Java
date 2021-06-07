package LR66;

import java.awt.*;
import javax.swing.*;

import java.awt.geom.Rectangle2D;
import java.io.FileFilter;
import java.awt.event.*;

import javax.swing.JFileChooser.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO;
import javax.imageio.ImageIO.*;
import java.awt.image.*;

public class FractalExplorer {

     /** Размер экрана в пикселях **/
     private int displaySize;

     private int rowsRemaining;
    
     /**
      * Ссылка JImageDisplay для обновления отображения в разных
методах в процессе вычисления фрактала.
      */
     private JImageDisplay display;
     
     /**
      * Объект FractalGenerator. Будет использоваться ссылка на базовый
      * класс для отображения других видов фракталов в будущем.
      */
     private FractalGenerator fractals;
     
     /**
      * Объект Rectangle2D.Double, указывающий диапазона комплексной
      *  плоскости, которая выводится на экран.
      */
     private Rectangle2D.Double range;

    private JComboBox comboBox = new JComboBox();
    private JButton saveButton = new JButton("Save");
    private JButton resetButton = new JButton("Reset");

     /**
      * Конструктор принимает значение
      * размера отображения в качестве аргумента, затем сохраняет это значение в 
      * соответствующем поле, а также инициализирует объекты диапазона и 
      * фрактального генератора.
      */
    public FractalExplorer(int size) {
    
        displaySize = size;
    
        fractals = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractals.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    
    }

    /**который инициализирует 
     * графический интерфейс Swing: JFrame, содержащий объект JimageDisplay, и 
     * кнопку для сброса отображения. */

    public void createAndShowGUI(){
        JFrame frame = new JFrame("Fractal Explorer");
        frame.add(display, BorderLayout.CENTER);
    
        buttonClick reset = new buttonClick();
        resetButton.addActionListener(reset);
        mouseClick mouseClick = new mouseClick();
        frame.addMouseListener(mouseClick);
        //операция закрытия окна по умолчанию
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        buttonClick chooseFractal = new buttonClick();
        comboBox.addActionListener(chooseFractal);
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Type of fractal");
        topPanel.add(label);
        topPanel.add(comboBox);
        frame.add(topPanel, BorderLayout.NORTH);
        
        
        buttonClick save = new buttonClick();
        saveButton.addActionListener(save);
        JPanel lowPanel = new JPanel();
        lowPanel.add(saveButton);
        lowPanel.add(resetButton);
        frame.add(lowPanel, BorderLayout.SOUTH);


/**Данные операции правильно разметят содержимое окна, сделают его
видимым (окна первоначально не отображаются при их создании для того,
чтобы можно было сконфигурировать их прежде, чем выводить на экран), и
затем запретят изменение размеров окна */
        frame.pack ();
        frame.setVisible (true);
        frame.setResizable (false);
    }

    private void enableUI(boolean val) {
        resetButton.setEnabled(val);
        comboBox.setEnabled(val);
        saveButton.setEnabled(val);
    }
    
    // метод для вывода на экран фрактала
    private void drawFractal(){
        enableUI(false);
        rowsRemaining = displaySize;
        for (int x = 0; x < displaySize; x++) {
            FractalWorker drawRow = new FractalWorker(x);
            drawRow.execute();
        }   
        display.repaint();             
    }

    private class buttonClick implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                fractals = (FractalGenerator) mySource.getSelectedItem();
                fractals.getInitialRange(range);
                drawFractal();
            }
            /**обработка событий 
     * java.awt.event.ActionListener от кнопки сброса. Обработчик должен сбросить 
     * диапазон к начальному, определенному генератором, а затем перерисовать 
     * фрактал. */
            else if (e.getActionCommand().equals("Reset")){
                fractals.getInitialRange(range);
                drawFractal();
            }
            else if (e.getActionCommand().equals("Save")){
                JFileChooser chooser = new JFileChooser();
                javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileNameExtensionFilter("PNG Images","png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(display) == JFileChooser.APPROVE_OPTION){
                    java.io.File file = chooser.getSelectedFile();
                    try {
                        BufferedImage displayImage = display.getImage();
                        ImageIO.write(displayImage, "png", file);
                    }
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(
                        display, exception.getMessage(),
                        "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    /**внутренний класс для обработки событий 
     * java.awt.event.ActionListener от кнопки сброса. Обработчик должен сбросить 
     * диапазон к начальному, определенному генератором, а затем перерисовать 
     * фрактал. */
    
// внутренний класс для обработки событий java.awt.event.MouseListener с дисплея. 
    private class mouseClick extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            if (rowsRemaining == 0){
            /**
             * Получение координат пикселя от щелчка мыщью 
             * перевод координат пикселя x и у к соответствующим координатам xCoord и yCoord в области фрактала на экране*/
            int x = e.getX();
            double xCoord = fractals.getCoord(range.x, range.x + range.width, displaySize, x);
            
            
            int y = e.getY();
            double yCoord = fractals.getCoord(range.y, range.y + range.height, displaySize, y);
            
            /**
             * вызвов 
             * метода генератора recenterAndZoomRange() с координатами, по которым 
             * щелкнули, и масштабом 0.5.
             */
            fractals.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            
            /**
             * перерисовка фрактал после изменения области фрактала.
             */
            drawFractal();
            }
        }
    }
    private class FractalWorker extends SwingWorker<Object, Object>{
        int yCoordinate;

        int[] RGBValues;

        private FractalWorker(int row) {
            yCoordinate = row;
        }
        @Override
        public Object doInBackground(){
            RGBValues = new int[displaySize];
            for (int i = 0; i<RGBValues.length; i++){
                double xCoord = fractals.getCoord(range.x, range.x + range.width, displaySize, i);
                double yCoord = fractals.getCoord(range.y, range.y + range.height, displaySize, yCoordinate);
                /**Вычисление количества итераций для соответствующих координат в
                области отображения фрактала. */
                    int iteration = fractals.numIterations(xCoord, yCoord);
                
                /** Если количество итерация -1, то закрашиваем пиксель в черный. */
                if (iteration == -1) {
                    RGBValues[i] = 0;
                }
                else {
                    /**
                     * иначе выбераем значение цвета, основанное на количестве итераций.  
                     */
                    float hue = 0.7f + (float) iteration / 200f;
                    RGBValues[i] = Color.HSBtoRGB(hue, 1f, 1f);
                
                    /** Обновляем отображение в соответсвии с цветом для каждого пикселя.*/
                    
                }
            }
            return null;
        }
        public void done() {
            for (int i = 0; i < RGBValues.length; i++) {
                display.drawPixel(i, yCoordinate, RGBValues[i]);
            }
             /**
              * После того, как строка будет вычислена, нужно перерисовать часть изображения, которая была изменена
              */
            display.repaint(0, 0, yCoordinate, displaySize, 1);
            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }
    /**
     * метод main() для FractalExplorer так, чтобы можно было его запустить. В main:
     * Инициализизация нового экземпляра класса FractalExplorer с размером отображения 700. 
     * Вызов метода createAndShowGUI () класса FractalExplorer. 
     * Вызов метода drawFractal() класса FractalExplorer для отображения начального представления.
     */
    public static void main(String[] args) {
        FractalExplorer displayExplorer = new FractalExplorer(700);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}

