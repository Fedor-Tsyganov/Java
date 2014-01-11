package com.cs211s.myPaintWindow;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class Buttons extends JPanel
{
    public int RADIUS = 10;
    Drawing drawing = new Drawing(RADIUS);
    public static Color selectedColor = Color.BLACK;
    private JButton bOpen, bSave, bClear, bExit, bColorChoser, bSizeChoser, bBrush;
    toSaveClass ts = null;
    public Color colorito;
//****************************Buttons()***************************
    public Buttons()
    {
    	try 
    	{
			System.setErr(new PrintStream("C:/Users/Fedor/Desktop/MyPaintWindowLog.txt"));
		} 
    	catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
        setPreferredSize(new Dimension(1800, 40));
        bOpen= new JButton("Open");
        bOpen.setPreferredSize(new Dimension(90, 30));
        bOpen.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser jfc = new JFileChooser();
                jfc.setCurrentDirectory(new File("C:/Users/Fedor/Desktop"));
                int returnVal = jfc.showOpenDialog(Buttons.this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File f = jfc.getSelectedFile();
                    if (!f.exists()) die ("File does not exist");
                    if (!f.isFile()) die ("Not a regular file");
                    if (f.isDirectory()) die ("It is a directory");
                    if (!f.canRead()) die ("File is not readable");
                    try
                    {
                        FileInputStream fis = new FileInputStream(f);
                        ObjectInputStream ois = new ObjectInputStream(fis);;
                        ts = (toSaveClass) ois.readObject();
                        Drawing.openFile = true;
                        Drawing.aX = ts.aX;
                        Drawing.aY=ts.aY;
                        Drawing.aSize=ts.aSize;
                        Drawing.aColor=ts.aColor;
                        Drawing.aShape = ts.aShape;
                        Drawing.txtToDraw = ts.txtToDraw;
                        drawing.invalidate();
                        ois.close();
                        fis.close();
                    }
                    catch (FileNotFoundException e1){Drawing.openFile = false;}
                    catch (IOException e1) {Drawing.openFile = false;}
                    catch (ClassNotFoundException e1) {e1.printStackTrace();}
                }
            }
        });

        bSave = new JButton("Save"); //inst circle button
        bSave.setPreferredSize(new Dimension(90, 30));
        bSave.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                File f;
                JFileChooser jfc2 = new JFileChooser();
                jfc2.setCurrentDirectory(new File("C:/Users/Fedor/Desktop"));
                jfc2.setLocation(600, 600);
                @SuppressWarnings("all")
                int returnVal2 = jfc2.showSaveDialog(Buttons.this);
                if (returnVal2 == JFileChooser.APPROVE_OPTION)
                {
                    toSaveClass tsc = new toSaveClass();
                    f = jfc2.getSelectedFile();
                    try
                    {
                        FileOutputStream fos = new FileOutputStream(f+".ser");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(tsc);
                        oos.close();
                        fos.close();
                    }
                    catch (FileNotFoundException e1) {e1.printStackTrace();}
                    catch (IOException e1) {e1.printStackTrace();}
                }
            }
        });

        bClear = new JButton("Clear"); //inst erazer button
        bClear.setPreferredSize(new Dimension(90, 30));
        bClear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Drawing.clear();
            }
        });

        bExit = new JButton("Exit"); //inst line shape button
        bExit.setPreferredSize(new Dimension(90, 30)); //change it to triangular button
        bExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        bBrush = new JButton("Brush");
        bBrush.setPreferredSize(new Dimension(90, 30));
        bBrush.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                new Brush();
                Brush.counter++;
            }
        });

        bColorChoser = new JButton("Color"); //inst color choser button
        bColorChoser.setPreferredSize(new Dimension(90, 30));
        bColorChoser.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new ColorSelection();
                if (ColorSelection.isPressed) {Drawing.myColor = selectedColor;}
                drawing.repaint();
                ColorSelection.counter++;
            }
        });

        bSizeChoser = new JButton("Size"); //inst size button
        bSizeChoser.setPreferredSize(new Dimension(90, 30));
        bSizeChoser.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new Resize(RADIUS);
                int size = Resize.getSliderSize();
                Drawing.RADIUS = size;
                Resize.counter++;
            }
        });
        bOpen.setToolTipText("Open a file");
        bSave.setToolTipText("Save to the file");
        bClear.setToolTipText("Delete everything in your drawing area");
        bExit.setToolTipText("Close the program");
        bColorChoser.setToolTipText("Select a color to draw");
        bSizeChoser.setToolTipText("Change size of the brush");
        bBrush.setToolTipText("Change your brush");
        add(bOpen);
        add(bSave);
        add(bClear);
        add(bExit);
        add(bBrush);
        add(bColorChoser);
        add(bSizeChoser);
        setBackground(Color.gray);
    }
//****************************die()***************************
    public void die (String str)
    {
    	JOptionPane.showMessageDialog(null, str);
    	System.err.println(str);
    }
}//end of class
