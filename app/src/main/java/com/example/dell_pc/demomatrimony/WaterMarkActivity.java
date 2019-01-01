package com.example.dell_pc.demomatrimony;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;

import static android.graphics.Typeface.*;

public class WaterMarkActivity extends AppCompatActivity {

    private AppCompatImageView watermark;
    private int color;
    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_mark);
        watermark=(AppCompatImageView)findViewById(R.id.watermarkImag);
        color=R.color.colorPrimaryDark;
        Point point=new Point();
        point.set(300,900);

     //    tf =Typeface.createFromAsset(getAssets(),"fonts/aller_bdlt.ttf");


       Bitmap bitmap= mark(BitmapFactory.decodeResource(getResources(), R.drawable.jacket_male1),"MY SSK",point,Color.DKGRAY,90,120);
       watermark.setImageBitmap(bitmap);
    }

    public Bitmap mark(Bitmap src, String watermark, Point location, int color, int alpha, int size) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);

        Typeface tf = createFromAsset(getAssets(),"fonts/specialelite.ttf");


        Paint paint = new Paint();
        paint.setTypeface(tf);

      //  paint.setTypeface(Typeface.create("Arial",Typeface.ITALIC));
        paint.setColor(color);
        paint.setAlpha(alpha);
        paint.setTextSize(size);
        paint.setFakeBoldText(true);
        paint.setAntiAlias(true);

        canvas.drawText(watermark, location.x, location.y, paint);


        return result;
    }
}
