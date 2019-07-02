package com.example.dynamic_recyclerview;


import android.graphics.Bitmap;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class Model {


    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;
    public static final int DESCRIPTIONTYPE=2;

    public int type;
    public Bitmap data;
    public String text1,text2;



    public Model(int type, String text1, String text2, Bitmap data)
    {
        this.type=type;
        this.data=data;
        this.text1=text1;
        this.text2=text2;

    }

}
