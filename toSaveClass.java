package com.cs211s.myPaintWindow;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

public class toSaveClass implements Serializable
{
    ArrayList<Integer> aX = new ArrayList<>();
    ArrayList<Integer> aY = new ArrayList<>();
    ArrayList<Integer> aSize = new ArrayList<>();
    ArrayList <Color> aColor = new ArrayList<>();
    ArrayList<Integer> aShape = new ArrayList<>();
    ArrayList <String> txtToDraw = new ArrayList<>();
//****************************toSaveClass()***************************
    public toSaveClass()
    {
        aX.addAll(Drawing.aX);
        aY.addAll(Drawing.aY);
        aSize.addAll(Drawing.aSize);
        aShape.addAll(Drawing.aShape);
        aColor.addAll(Drawing.aColor);
        txtToDraw.addAll(Drawing.txtToDraw);
    }
}//end of the class
