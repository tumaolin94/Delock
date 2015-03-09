package com.example.delockset;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ClickableViewAccessibility")
public class SelectColor extends Activity {

	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Paint mPaint;
	int [] color=new int[1];

	private TextView textView;
	private int width;
	private int height;
	private int r;
	private float x1;
	private float x2;
	private float x3;
	private float y1;
	private float y2;
	private float y3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;//宽度
		height = dm.heightPixels ;//高度
		
		//Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.activity_select_color);
		ImageView imageView1 = (ImageView) this.findViewById(R.id.imageViewdc);
		 textView = (TextView) this.findViewById(R.id.textView1);
		mBitmap = Bitmap.createBitmap(width,  
				height, Bitmap.Config.ARGB_8888);  
		mCanvas = new Canvas(mBitmap);  
		mCanvas.drawColor(Color.TRANSPARENT); 
		
		mPaint = new Paint();
		/*九宫格简化*/
		r = width/9;
		 x1= (float) (width/2-2.5*r);
		 x2= (float)width/2;
		 x3= (float) (width/2+2.5*r);
		
		 y1= (float)(height/2-3.0*r);
		 y2= (float)(height/2);
		 y3= (float)(height/2+3.0*r);
		 
		 
		mPaint.setStrokeWidth(0);
//		mPaint.setColor(Color.BLUE);
		mPaint.setStyle(Style.FILL_AND_STROKE);
		mPaint.setColor(Color.GRAY);
		mPaint.setAlpha(0x77);//透明度
		mCanvas.drawCircle(x1, y1, r, mPaint);//1
		mPaint.setColor(Color.GREEN);
		mPaint.setAlpha(0x77);
		mCanvas.drawCircle(x2, y1, r, mPaint);//2
		mPaint.setColor(Color.RED);
		mPaint.setAlpha(0x77);
		mCanvas.drawCircle(x3, y1, r, mPaint);//3
		mPaint.setColor(Color.YELLOW);
		mPaint.setAlpha(0x77);
		mCanvas.drawCircle(x1, y2, r, mPaint);//4
		mPaint.setColor(Color.CYAN);
		mPaint.setAlpha(0x77);
		mCanvas.drawCircle(x2, y2, r, mPaint);//5
		mPaint.setColor(0xFF8C00);//orange
		mPaint.setAlpha(0x77);
		mCanvas.drawCircle(x3, y2, r, mPaint);//6
		mPaint.setColor(0x808000);//olive
		mPaint.setAlpha(0x77);
		mCanvas.drawCircle(x1, y3, r, mPaint);//7
		mPaint.setColor(Color.MAGENTA);
		mPaint.setAlpha(0x77);
		mCanvas.drawCircle(x2, y3, r, mPaint);//8
		mPaint.setColor(Color.BLUE);
		mPaint.setAlpha(0x77);
		mCanvas.drawCircle(x3, y3, r, mPaint);//9
	//	mCanvas.drawLine(0, 0, 720, 1133, mPaint);//测试
		imageView1.setImageBitmap(mBitmap);
		imageView1.setOnTouchListener(new OnTouchListener(){
			//处理点击位置对应的颜色
			public boolean onTouch(View v, MotionEvent event) {
				float x = 0,y = 0;
				
				Intent intent = getIntent();
				Bundle data = new Bundle();
				x=event.getX();y=event.getY();
				if(Math.sqrt(Math.pow((x-x1),2)+Math.pow((y-y1),2))<=r){
					color[0]=0xff888888;
					data.putIntArray("color", color);
					intent.putExtras(data);
					SelectColor.this.setResult(0,intent);
					SelectColor.this.finish();
					return false;
				}
				if(Math.sqrt(Math.pow((x-x2),2)+Math.pow((y-y1),2))<=r){
					color[0]=0xff00ff00;
					data.putIntArray("color", color);
					intent.putExtras(data);
					SelectColor.this.setResult(0,intent);
					SelectColor.this.finish();
					return false;
				}
					
				if(Math.sqrt(Math.pow((x-x3),2)+Math.pow((y-y1),2))<=r){
					color[0]=0xffff0000;
					data.putIntArray("color", color);
					intent.putExtras(data);
					SelectColor.this.setResult(0,intent);
					SelectColor.this.finish();
					return false;
				}
				if(Math.sqrt(Math.pow((x-x1),2)+Math.pow((y-y2),2))<=r){
					color[0]=0xffffff00;
					data.putIntArray("color", color);
					intent.putExtras(data);
					SelectColor.this.setResult(0,intent);
					SelectColor.this.finish();
					return false;
				}
				if(Math.sqrt(Math.pow((x-x2),2)+Math.pow((y-y2),2))<=r){
					color[0]=0xff00ffff;
					data.putIntArray("color", color);
					intent.putExtras(data);
					SelectColor.this.setResult(0,intent);
					SelectColor.this.finish();
					return false;
				}
				if(Math.sqrt(Math.pow((x-x3),2)+Math.pow((y-y2),2))<=r){
//					color[0]=0xffcccccc;
					color[0]=0xFF8C00;//orange
					data.putIntArray("color", color);
					intent.putExtras(data);
					SelectColor.this.setResult(0,intent);
					SelectColor.this.finish();
					return false;
				}
				if(Math.sqrt(Math.pow((x-x1),2)+Math.pow((y-y1),2))<=r){
					color[0]=0x808000;//olive
					data.putIntArray("color", color);
					intent.putExtras(data);
					SelectColor.this.setResult(0,intent);
					SelectColor.this.finish();
					return false;
				}
				if(Math.sqrt(Math.pow((x-x2),2)+Math.pow((y-y2),2))<=r){
					color[0]=0xffff00ff;
					data.putIntArray("color", color);
					intent.putExtras(data);
					SelectColor.this.setResult(0,intent);
					SelectColor.this.finish();
					return false;
				}
				if(Math.sqrt(Math.pow((x-x3),2)+Math.pow((y-y3),2))<=r){
					color[0]=0xff0000ff;
					data.putIntArray("color", color);
					intent.putExtras(data);
					SelectColor.this.setResult(0,intent);
					SelectColor.this.finish();
					return false;
				

				}
	    		return false;
			}

		});

		}
}


