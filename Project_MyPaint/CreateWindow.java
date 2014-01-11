package com.cs211s.myPaintWindow;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CreateWindow extends JFrame
{
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    ColorSelection colsect;
    //getting screen size
    private int WIDTH = (int) (dimension.getWidth()-100);
    private int HEIGHT = (int) (dimension.getHeight()-100);
    public Color myColor = Color.BLACK;
    public int RADIUS =-1;
    public int xPosition = 1700;
    Timer t;
    int strSize;
//****************************CreateWindow()************************
    public CreateWindow()
    {
        try
        {
    	    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	    SwingUtilities.updateComponentTreeUI(this);
        } //changing style of window.
    	catch(Exception e){}
        //placing window in the center of a screen
        int x = (int) ((dimension.getWidth() - WIDTH)/2);
        int y = (int) ((dimension.getHeight() - HEIGHT)/2);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new StatusBar(), BorderLayout.NORTH);
        add(new Buttons(), BorderLayout.SOUTH);
        add(new Drawing(RADIUS), BorderLayout.CENTER);
        setTitle("Drawing for beginers");
        setBackground(Color.WHITE);
        setLocation(x,y);
        setVisible(true);
    }
 //****************************setRadius()***************************
    public int setRadius(int r)
    {
        return RADIUS = r; //setting radius
    }
//****************************CreateWindow()***************************
    public CreateWindow(Color c)
    {
        myColor=c;
        repaint();
    }
//****************************CreateWindow()***************************
    public CreateWindow(int radius)
    {
        RADIUS=radius;
    }
//****************************    
    //inner class where i place moving line
    class StatusBar extends JPanel
    {
        public String statist="You can place your ad here";
//*****************************StatusBar()***************************
        public StatusBar()
        {
            super(new BorderLayout());
            JPanel stats = new JPanel();
            stats.setPreferredSize(new Dimension(1800,30));
            add(stats);
            changePosition();
            repaint();
        }
//****************************paint()***************************
        public void paint(Graphics g)
        {
        	super.paint(g); //drawing moving string
        	g.setFont(new Font("Times New Roman",Font.BOLD, 24));
        	FontMetrics fm = getFontMetrics(g.getFont());
        	strSize = fm.stringWidth(statist);
        	g.drawString(statist, xPosition+strSize, 20);
        }
//****************************changePosition()***************************
        public void changePosition()
        {
        	t = new Timer(1, new ActionListener() 
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    t.setDelay(10); //setting delay time
                    if (xPosition < (0-strSize*2)) {xPosition = 1700;}
                    xPosition--; //changing position of the string
                    repaint();
                }
            });
            t.start();
        }
    }
}//end of class
