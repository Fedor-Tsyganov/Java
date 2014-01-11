package com.cs211s.myPaintWindow;
import javax.swing.SwingUtilities;

public class MainPaintWindow
{
//****************************main()***************************
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new CreateWindow();
            }
        });
    }
}//end of main
