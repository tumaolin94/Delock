package com.example.delockset;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;
import android.widget.ImageView;
//画出九个圆
public class DrawCircle extends View {

	private ImageView imageViewdc;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Paint mPaint;

	public DrawCircle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void onDraw(){
		
		
////		Canvas mCanvas = new Canvas();
////		Paint mPaint = new Paint();
//		mCanvas = new Canvas(mBitmap);  
//		mCanvas.drawColor(Color.TRANSPARENT); 
//		
//		mPaint = new Paint();
//		mPaint.setStyle(Style.FILL_AND_STROKE);
////		mPaint.setAlpha(0x77);//透明度
////		mPaint.setStyle(Style.FILL);
//		mPaint.setColor(Color.GRAY);
//		mCanvas.drawCircle(160, 280, 80, mPaint);//1
//		mPaint.setColor(Color.GREEN);
//		mCanvas.drawCircle(360, 280, 80, mPaint);//2
//		mPaint.setColor(Color.RED);
//		mCanvas.drawCircle(560, 280, 80, mPaint);//3
//		mPaint.setColor(Color.YELLOW);
//		mCanvas.drawCircle(160, 560, 80, mPaint);//4
//		mPaint.setColor(Color.CYAN);
//		mCanvas.drawCircle(360, 560, 80, mPaint);//5
//		mPaint.setColor(Color.LTGRAY);
//		mCanvas.drawCircle(560, 560, 80, mPaint);//6
//		mPaint.setColor(Color.DKGRAY);
//		mCanvas.drawCircle(160, 840, 80, mPaint);//7
//		mPaint.setColor(Color.MAGENTA);
//		mCanvas.drawCircle(360, 840, 80, mPaint);//8
//		mPaint.setColor(Color.BLUE);
//		mCanvas.drawCircle(560, 840, 80, mPaint);//9
//		imageViewdc.setImageBitmap(mBitmap);
		imageViewdc = (ImageView) this.findViewById(R.id.imageViewdc);
		mBitmap = Bitmap.createBitmap(720,  
				1133, Bitmap.Config.ARGB_8888);  
		mCanvas = new Canvas(mBitmap);  
		mCanvas.drawColor(Color.TRANSPARENT); 
		mPaint = new Paint();
		mPaint.setStrokeWidth(0);
		mPaint.setStyle(Style.FILL_AND_STROKE);
		mPaint.setColor(Color.WHITE);
		mPaint.setAlpha(0x77);//透明度		
		mCanvas.drawCircle(360, 560, 80, mPaint);//画出中心的圆	
		imageViewdc.setImageBitmap(mBitmap);
	}

}
