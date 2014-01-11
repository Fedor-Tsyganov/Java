package com.cs211s.myPaintWindow;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorSelection extends JFrame
{
    public JFrame frame;
    public static Color c = Color.BLUE;
    public static boolean isPressed = false;
    public static int counter =0;
    public static boolean flag = false;
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//****************************ColorSelection()***************************
    public ColorSelection()
    {
        WindowListener listener = new WindowAdapter()
        {
            public void windowClosing(WindowEvent w)
            {
                counter=0;
            }
        };
        if (counter < 1)
        {
            int x = (int) ((dimension.getWidth() - 600)/2);
            int y = (int) ((dimension.getHeight() - 800)/2);
            Something bp = new Something();
            frame = new JFrame();
            frame.setTitle("Color chooser");
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.add(bp);
            frame.pack();
            frame.setSize(600, 800);
            frame.setLocation(x, y);
            frame.setVisible(true);
            //user cannot resize the window
            frame.setResizable(false);
            frame.addWindowListener(listener);
        }
        counter++;
    }
//****************************getColor()***************************
    public static Color getColor(Color col)
    {
        return col;
    }
//****************************
    //Inner class Something
    public class Something extends JPanel implements  ChangeListener
    {
        public Color theColor;
        private JLabel Decimal;
        private JButton selectColor;
        private JPanel main_panel, slider_panel,
                buttons_panel, oval_panel, color_panel;
        private JSlider sliderRed, sliderGreen, sliderBlue;
        private JTextField tRed, tGreen, tBlue;
        private DrawRects drRED, drGREEN,drBLUE;
        int blue, green, red;
        DrawCircle dc;
        boolean decSelected, hexSelected, binSelected, octSelected;
        String toShow1="", toShow2="", toShow3="";

        //***************************CONSTRUCTOR********************************
        //*************************BuildPanel()*************************
        public Something()
        {
            super(new GridBagLayout());
            buildButtonsPanel(); //buttons
            buildSliderPanel(); //slider
            buildColorPanel();  //colored rectangular
            buildOvalPanel();   //color oval
            buildMainPanel();  //main panel
            add(main_panel);
        }
        //*********************buildButtonsPanel()*****************************
        private void buildButtonsPanel()
        {
            //building panel with radio buttons
            buttons_panel = new JPanel(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            gc.weightx =0.5;
            gc.weighty =0.5;
            gc.gridx=1;
            gc.gridy=0;
            //labels
            Decimal = new JLabel("Decimal Value");
            buttons_panel.add(Decimal,gc);
            gc.weightx =0.1;
            gc.weighty =0.1;

            gc.gridx=0;
            gc.gridy=1;
            JLabel red = new JLabel("Red: ");
            buttons_panel.add(red,gc);
            gc.gridx=1;
            gc.gridy=1;
            tRed = new JTextField();
            tRed.setEditable(false);
            buttons_panel.add(tRed, gc);
            gc.gridx=0;
            gc.gridy=2;
            JLabel green = new JLabel("Green: ");
            buttons_panel.add(green,gc);

            gc.gridx=1;
            gc.gridy=2;
            tGreen = new JTextField();
            tGreen.setEditable(false);
            buttons_panel.add(tGreen,gc);

            gc.gridx=0;
            gc.gridy=3;
            JLabel blue = new JLabel("Blue: ");
            buttons_panel.add(blue,gc);
            gc.gridx=1;
            gc.gridy=3;
            tBlue = new JTextField();
            tBlue.setEditable(false);
            buttons_panel.add(tBlue,gc);
            //setting size of text field
            tBlue.setPreferredSize(new Dimension(70,30));
            tGreen.setPreferredSize(new Dimension(70,30));
            tRed.setPreferredSize(new Dimension(70,30));
            //adding buttons and text
            gc.weightx=2;
            gc.weighty=2;
            gc.gridx=1;
            gc.gridy=4;
            selectColor = new JButton("Select");
            selectColor.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    isPressed = true;
                    c = new Color((int)sliderRed.getValue(),(int)sliderGreen.getValue(),(int)sliderBlue.getValue());
                    Drawing.myColor = getColor(c);
                    frame.setVisible(false);
                    counter=0;
                    //frame.dispose();
                }
            });
            buttons_panel.add(selectColor,gc);
        }

        //***********************buildSliderPanel()*************************
        private void buildSliderPanel()
        {
            //building slider panel
            slider_panel = new JPanel(new GridLayout(0,3));
            slider_panel.setPreferredSize(new Dimension(100,100));

            sliderRed = new JSlider(SwingConstants.VERTICAL,0,255,0);
            sliderGreen = new JSlider(SwingConstants.VERTICAL,0,255,0);
            sliderBlue = new JSlider(SwingConstants.VERTICAL,0,255,0);
            //setting slider for red color
            sliderRed.setBorder(BorderFactory.createTitledBorder("Red"));
            sliderRed.setMajorTickSpacing(51);
            sliderRed.setMinorTickSpacing(6);
            sliderRed.setPaintTicks(true);
            sliderRed.setPaintLabels(true);
            //setting slider for green color
            sliderGreen.setBorder(BorderFactory.createTitledBorder("Green")); //setting Border
            sliderGreen.setMajorTickSpacing(51);
            sliderGreen.setMinorTickSpacing(6);
            sliderGreen.setPaintTicks(true);
            sliderGreen.setPaintLabels(true);
            //setting slider for blue color
            sliderBlue.setBorder(BorderFactory.createTitledBorder("Blue"));
            sliderBlue.setMajorTickSpacing(51);
            sliderBlue.setMinorTickSpacing(6);
            sliderBlue.setPaintTicks(true);
            sliderBlue.setPaintLabels(true);
            //adding sliders to the panel
            slider_panel.add(sliderRed);
            slider_panel.add(sliderGreen);
            slider_panel.add(sliderBlue);
            //slider listener
            sliderRed.addChangeListener(this);
            sliderBlue.addChangeListener(this);
            sliderGreen.addChangeListener(this);
        }
        //**********************buildColorPanel()**************************
        private void buildColorPanel()
        {
            //panel with color rectangular
            color_panel = new JPanel(new GridLayout(0, 3, 1, 1));
            color_panel.setPreferredSize(new Dimension(100,100));
            //drawing rectangular
            drRED = new DrawRects(1);
            drGREEN = new DrawRects(2);
            drBLUE = new DrawRects(3);
            //adding rectangular to the panel
            color_panel.add(drRED);
            color_panel.add(drGREEN);
            color_panel.add(drBLUE);
        }
        //******************************buildOvalPanel()*********************
        private void buildOvalPanel()
        {
            //build panel where our oval going to be
            oval_panel = new JPanel(new BorderLayout());
            oval_panel.setPreferredSize(new Dimension(100,100));
            dc = new DrawCircle(red, green, blue);
            dc.repaint();
            oval_panel.add(dc);
        }
        //**************************inner class******************************
        //***************************DrawRects****************************
        private class DrawRects extends JPanel
        {
            protected int height = 1;
            protected int colorNum;
            protected int colorVal;
            int y = 255;
            //**************constructor**************
            DrawRects() { }
            //*************DrawRects****************
            //overriding constructor
            DrawRects(int colorNumber){ colorNum = colorNumber; }
            //**************DrawRects****************
            //overriding constructor 
            DrawRects(int height, int colorValue)
            {
                this.height = height;
                colorVal = colorValue;
            }
            //*****************setHeight*************
            void setHeight(int height)
            {
                //setting height of the rectangular
                //that will change size of rect
                this.height = height+1;
                y = 255-height;
            }
            //******************paintComponent*********
            //drawing rectangulars
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                if (colorNum == 1) g.setColor(Color.RED);
                if (colorNum == 2) g.setColor(Color.GREEN);
                if (colorNum == 3)g.setColor(Color.BLUE);
                g.fillRect(0, y, 30, height);
            }
        }
        //end of inner class
        //**************************another inner class************************
        //****************************DrawCircle************************
        private class DrawCircle extends JPanel
        {
            int r, gr, b;
            Color col;
            //***********************DrawCircle********************
            public DrawCircle(int red, int green, int blue)
            {
                //constructor for drawing circle
                r = red;
                gr = green;
                b = blue;
            }
            //*******************paintComponent()*****************
            public void paintComponent(Graphics g)
            {
                //drawing our circle
                super.paintComponent(g);
                col = new Color(r, gr, b);
                g.setColor(col);
                g.fillOval(10, 60, 200, 150); //fixed size
            }
        }
        //end of inner class

        //*****************buildMainPanel()********************************
        private void buildMainPanel()
        {
            //building main panel
            main_panel = new JPanel(new GridLayout(2,2));
            main_panel.setPreferredSize(new Dimension(500,770));
            //adding all panels to main panel
            main_panel.add(color_panel);
            main_panel.add(buttons_panel);
            main_panel.add(oval_panel);
            main_panel.add(slider_panel);
        }
        //************************stateChanged()*******************
        public void stateChanged(ChangeEvent ce)
        {
            //getting value from sliders
            blue = sliderBlue.getValue();
            green = sliderGreen.getValue();
            red = sliderRed.getValue();
            //based on selection displaying value of the color
            displayDecimal();
            drRED.setHeight(red);
            drRED.repaint();
            drGREEN.setHeight(green);
            drGREEN.repaint();
            drBLUE.setHeight(blue);
            drBLUE.repaint();
            //repainting oval
            dc.b = blue;
            dc.r = red;
            dc.gr = green;
            oval_panel.repaint();
        }
        //**********************displayDecimal()*********************
        protected void displayDecimal()
        {
            toShow1 = ""+red;
            toShow2 = ""+green;
            toShow3 = ""+blue;
            //displaying decimal value of the color
            tRed.setText(toShow1);
            tGreen.setText(toShow2);
            tBlue.setText(toShow3);
        }

    } //end of inner class
}//end of class