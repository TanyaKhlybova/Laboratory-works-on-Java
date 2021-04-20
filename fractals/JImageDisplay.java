package fractals;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

class JImageDisplay extends JComponent {
    private BufferedImage image;

    /** Конструктор принимает целочисленные
значения ширины и высоты, и инициализирует объект BufferedImage новым
изображением с этой шириной и высотой, и типом изображения
TYPE_INT_RGB. */
    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
        
        /**вызвов метода setPreferredSize()
родительского класса с указанной шириной и высотой. он
отобразит на экране все изображение.  */
        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension);
    }
/**код для отрисовки, переопределяя защищенный метод JComponent
paintComponent */

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }    
/**устанавливает все пиксели
изображения в черный цвет */
    public void clearImage()
    {
        for (int y = 0; y<getHeight();y++ ){
            for (int x = 0; x< getWidth();x++)
                image.setRGB(x,y,0);
        }
    }
/**устанавливает пиксель в определенный цвет */
    public void drawPixel(int x, int y, int rgbColor)
    {
        image.setRGB(x, y, rgbColor);
    }
}
