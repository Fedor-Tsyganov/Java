package com.cs211s.myPaintWindow;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Resize extends JFrame
{
    public static int radius;
    public static int counter=0;
    public final int WIDTH = 300, HEIGHT = 300;
    public JPanel resizePanel, drawing;
    public static JSlider slider;
    public JButton selectSize;
    Draw c;
    Drawing size;
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
  //****************************Resize()***************************
    public Resize(int rad)
    {
        WindowListener listener = new WindowAdapter()
        {
            public void windowClosing(WindowEvent w)
            {
                counter = 0;
            }
        };
        assert counter > 0 :"counter: "+counter;
        if(counter < 1)
        {

            int x = (int) ((dimension.getWidth() - WIDTH)/2);
            int y = (int) ((dimension.getHeight() - HEIGHT)/2);
            setLocation(x+200, y);
            radius = rad;
            c = new Draw(Drawing.RADIUS);
            setSize(WIDTH, HEIGHT);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setTitle("Size Selector");
            setResizable(false);
            drawingPan();
            funcionsPanel();
            add(resizePanel, BorderLayout.SOUTH);
            add(drawing, BorderLayout.CENTER);
            setVisible(true);
            invalidate();
            addWindowListener(listener);
        }
        counter++;
    }
  //****************************funcionsPanel()***************************
    public void funcionsPanel()
    {
        resizePanel = new JPanel(new FlowLayout());
        selectSize = new JButton("Select");
        selectSize.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                size = new Drawing(slider.getValue());
                setVisible(false);
                counter=0;
            }
        });
        slider = new JSlider(SwingConstants.HORIZONTAL,0,150,Drawing.RADIUS);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                radius = slider.getValue();
                c.setRadius(radius);
                c.repaint();
            }

        });
        resizePanel.add(slider);
        resizePanel.add(selectSize);

    }
  //****************************getSliderSize()***************************
    public static int getSliderSize()
    {
        return slider.getValue();
    }
  //****************************drawingPan()***************************
    public void drawingPan()
    {
        drawing = new JPanel(new GridLayout());
        drawing.setPreferredSize(new Dimension(200,200));
        drawing.add(c);
    }
  //****************************
    //inner class Draw
    class Draw extends JPanel
    {
        int radius;
      //****************************Draw()***************************
        public Draw(int rad)
        {
            radius = rad;
        }
      //****************************setRadius()***************************
        public int setRadius(int radius)
        {
            this.radius=radius;
            return radius;
        }
      //****************************paint()***************************
        public void paint(Graphics g)
        {
            super.paint(g);
            g.setColor(Color.BLACK);
            if (Brush.drawingNumber==1)
                g.fillOval(0, 0, radius, radius);
            if (Brush.drawingNumber==2)
                g.fillRect(5, 10, radius, radius);
            if (Brush.drawingNumber==3)
            {
                g.setFont(new Font("Times New Roman", Font.BOLD,radius));
                g.drawString("H", 0, radius);
            }
        }
    }
}