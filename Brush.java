package com.cs211s.myPaintWindow;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Brush extends JFrame
{
    public static int counter =0;
    public final int WIDTH = 280, HEIGHT = 350;

    public JRadioButton bCircle, bSquare, bString;
    public JButton bSelect;
    private JPanel buttonPanel, textPanel, panel;
    private JLabel lCircle, lSquare, lString;
    public static int drawingNumber=1;
    public static String textToDraw="H";
    private static boolean cirChecked=true, sqareChecked=false, txtCheched=false;
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
  //****************************Brush()***************************
    public Brush()
    {
        WindowListener listener = new WindowAdapter()
        {
            public void windowClosing(WindowEvent w)
            {
                counter=0;
            }
        };
        if (counter < 1) //no more than 1 window
        {
            int x = (int) ((dimension.getWidth() - WIDTH)/2);
            int y = (int) ((dimension.getHeight() - HEIGHT)/2);
            setLocation(x-200, y);
            setSize(WIDTH, HEIGHT);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            textPanel();
            buttonPanel();
            brushPanel();
            add(textPanel, BorderLayout.NORTH);
            add(panel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
            setVisible(true);
            addWindowListener(listener);
        }
        counter++;
    }
  //****************************brushPanel()***************************
    public void brushPanel()
    {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0.1;
        gc.weighty = 0.1;

        gc.anchor=GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        bCircle = new JRadioButton();
        bCircle.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Brush.drawingNumber =1;
                cirChecked = true;
                sqareChecked = false;
                txtCheched = false;
                bCircle.setSelected(cirChecked);
                bSquare.setSelected(sqareChecked);
                bString.setSelected(txtCheched);
            }
        });
        panel.add(bCircle,gc);

        gc.anchor=GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 1;
        bSquare = new JRadioButton();
        bSquare.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Brush.drawingNumber =2;
                cirChecked = false;
                sqareChecked = true;
                txtCheched = false;
                bCircle.setSelected(cirChecked);
                bSquare.setSelected(sqareChecked);
                bString.setSelected(txtCheched);
            }
        });
        panel.add(bSquare,gc);

        gc.anchor=GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 2;
        bString = new JRadioButton();
        bString.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Brush.drawingNumber =3;
                cirChecked = false;
                sqareChecked = false;
                txtCheched = true;
                bCircle.setSelected(cirChecked);
                bSquare.setSelected(sqareChecked);
                bString.setSelected(txtCheched);
                textToDraw = JOptionPane.showInputDialog("Enter text to draw: ");
            }
        });
        panel.add(bString,gc);
        bCircle.setSelected(cirChecked);
        bSquare.setSelected(sqareChecked);
        bString.setSelected(txtCheched);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.anchor=GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        lCircle = new JLabel("Circle");
        panel.add(lCircle,gc);

        gc.anchor=GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 1;
        lSquare = new JLabel("Square");
        panel.add(lSquare,gc);

        gc.anchor=GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 2;
        lString = new JLabel("Text");
        panel.add(lString,gc);
        repaint();
    }
  //****************************buttonsPanel()***************************
    public void buttonPanel()
    {
        buttonPanel = new JPanel();
        bSelect = new JButton("Select");
        bSelect.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                setVisible(false);
                counter=0;
            }
        });
        buttonPanel.add(bSelect);
    }
  //****************************textPanel()***************************
    public void textPanel()
    {
        textPanel = new JPanel(new FlowLayout());
        JLabel jl = new JLabel("Select your brush");
        textPanel.add(jl);
    }
//****************************paint()***************************
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.BLACK);

        g.fillOval(120,85, 30, 30);

        g.fillRect(120, 168, 30, 30);
        g.setFont(new Font("Times New Roman", Font.BOLD, 36));
        g.drawString("H", 120, 277);
    }
}//end of class
