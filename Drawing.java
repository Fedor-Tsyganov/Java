package com.cs211s.myPaintWindow;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Drawing extends JPanel implements MouseListener, MouseMotionListener
{
    public static int RADIUS;
    public static Color myColor = Color.BLACK;
    public static ArrayList<Integer> aX = new ArrayList<>();
    public static ArrayList<Integer> aY = new ArrayList<>();
    public static ArrayList<Integer> aSize = new ArrayList<>();
    public static ArrayList <Color> aColor = new ArrayList<>();
    public static ArrayList<Integer> aShape = new ArrayList<>();
    public static ArrayList <String> txtToDraw = new ArrayList<>();
    public static int myCount = 0;
    public static boolean openFile = false;
    static int counter =0;
    private int mx=0, my=0;
    private boolean clear=false;
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private int WIDTH = (int) (dimension.getWidth()-100);
    private int HEIGHT = (int) (dimension.getHeight()-100);
  //****************************Drawing()***************************
    public Drawing(){}
  //****************************Drawing(int r)***************************
    public Drawing(int radius)
    {
        if (radius == -1)
        {
            RADIUS = 10;
        }
        else
        {
            RADIUS = radius;
        }
        addMouseMotionListener(this);
        addMouseListener(this);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.WHITE);
        setOpaque(true);
    }
  //****************************mouseDragged()***************************
    public void mouseDragged(MouseEvent e)
    {
        if (SwingUtilities.isRightMouseButton(e)) clear = true;
        mx = e.getX();
        my = e.getY();
        aX.add(mx);
        aY.add(my);
        aColor.add(myColor);
        aSize.add(RADIUS);
        aShape.add(Brush.drawingNumber);
        txtToDraw.add(Brush.textToDraw);
        update(getGraphics());
        myCount++;

    }
  //****************************mouseMoved()***************************
    public void mouseMoved(MouseEvent e)
    {
        mx = e.getX();
        my = e.getY();
    }
  //****************************mouseClicked()***************************
    public void mouseClicked(MouseEvent e)
    {
        if (SwingUtilities.isRightMouseButton(e)) clear = true;
        mx = e.getX();
        my = e.getY();
        aX.add(mx);
        aY.add(my);
        aColor.add(myColor);
        aSize.add(RADIUS);
        aShape.add(Brush.drawingNumber);
        txtToDraw.add(Brush.textToDraw);
        update(getGraphics());
        myCount++;
    }
  //****************************mouseEntered()***************************
    public void mouseEntered(MouseEvent e)
    {
        mx=-150;
        my=-150;
        update(getGraphics());
    }
  //****************************mouseExited()***************************
    public void mouseExited(MouseEvent e) {	}
  //****************************mouseReleased()***************************
    public void mouseReleased(MouseEvent e)
    {
        if (SwingUtilities.isRightMouseButton(e)) clear = false;

    }
  //****************************mousePressed()***************************
    public void mousePressed(MouseEvent e)
    {
        if (SwingUtilities.isRightMouseButton(e)) clear = true;
    }
  //****************************paint()***************************
    public void paint(Graphics g)
    {
        if (counter <1)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }
        else
        {
            if (clear)
            {
                g.setColor(Color.WHITE);
                g.fillOval(mx, my, 10, 10);
            }
            else if (openFile)
            {
                for (int i = 0; i < aX.size(); i++)
                {
                    g.setColor(aColor.get(i));
                    if (aShape.get(i) == 1)
                    {
                        g.fillOval(aX.get(i), aY.get(i), aSize.get(i), aSize.get(i));
                    }
                    if (aShape.get(i) == 2)
                    {
                        g.fillRect(aX.get(i), aY.get(i), aSize.get(i), aSize.get(i));
                    }
                    if (aShape.get(i) == 3)
                    {
                        g.setFont(new Font("Times New Roman", Font.BOLD, aSize.get(i)));
                        g.drawString(txtToDraw.get(i), aX.get(i), aY.get(i));
                    }
                }
                openFile = false;
            }
            else
            {
                g.setColor(myColor);
                if (Brush.drawingNumber == 1)
                    g.fillOval(mx-5, my-5, RADIUS, RADIUS);
                if (Brush.drawingNumber == 2)
                    g.fillRect(mx-5, my-5, RADIUS, RADIUS);
                if (Brush.drawingNumber == 3)
                {
                    g.setFont(new Font("Times New Roman", Font.BOLD, RADIUS));
                    g.drawString(Brush.textToDraw, mx, my);
                }
            }
            g.dispose();
        }
        counter++;
    }
  //****************************paint()***************************
    public void paint(Graphics g, int i)
    {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawOval(mx-5, my-5, RADIUS, RADIUS);
    }
  //****************************update()***************************
    public void update(Graphics g)
    {
        paint(g);
    }
  //****************************clear()***************************
    public static void clear()
    {
        counter = 0;
        aX.removeAll(aX);
        aY.removeAll(aY);
        aSize.removeAll(aSize);
        aColor.removeAll(aColor);
        aShape.removeAll(aShape);
        new Drawing().invalidate();
    }
}//end of class
