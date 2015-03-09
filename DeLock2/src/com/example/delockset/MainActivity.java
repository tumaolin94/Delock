package com.example.delockset;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;








import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Paint.Style;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

private ImageView imageView;
	
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Paint mPaint;
	private int[] colors = new int[1];   //��¼ѡ�����ɫ(int��ֵ)
	private float Dx=360,Dy=560;   //��¼ͼƬ��λ��
	private ImageView imageView1;
	private static int RESULT_LOAD_IMAGE = 1;  // ����ͼ���¼��Ĳ���
	String picturePath=null;//��¼ͼƬ��·��
	private int width,height;
	String colors1;//����Ļ�Ϸ�����ɫ
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//����Ϊȫ��ģʽ
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;//���
		height = dm.heightPixels ;//�߶�
//		Dx+=width/2;Dy+=height/2;
		//Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        
		setContentView(R.layout.activity_main);
		imageView = (ImageView) this.findViewById(R.id.imageView1);
		imageView.setOnTouchListener(new TouchListener());
		imageView1 = (ImageView) this.findViewById(R.id.imageView2);
		mBitmap = Bitmap.createBitmap(width,  
				height, Bitmap.Config.ARGB_8888);  
		mCanvas = new Canvas(mBitmap);  
		mCanvas.drawColor(Color.TRANSPARENT); 
		mPaint = new Paint();
		mPaint.setStrokeWidth(5);
		mPaint.setColor(0x00000000);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setAlpha(0x77);//͸����		
		mCanvas.drawCircle(width/2, height/2, 80, mPaint);//�������ĵ�Բ	
		mPaint.setColor(Color.BLACK);
//		mCanvas.drawLine(0, 0, 720, 1133, mPaint);//����
		imageView1.setImageBitmap(mBitmap);
		Button bt1 = (Button)findViewById(R.id.button1);//ѡ����ɫ��ť
		Button bt2 = (Button)findViewById(R.id.button2);//ѡ��ͼƬ��ť
		Button bt3 = (Button)findViewById(R.id.button3);//������ð�ť
		bt1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//setContentView(R.layout.activity_draw_circle);
				Intent intent = new Intent(MainActivity.this,SelectColor.class);//��ѡ����ɫActivity
				startActivityForResult(intent,0);
			}});
		bt2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//����ͼ��ѡ��ͼƬ
				Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
  
                startActivityForResult(intent, RESULT_LOAD_IMAGE);

				
			}});
		bt3.setOnClickListener(new OnClickListener(){

			

			@Override
			public void onClick(View v) {
//				try{
//				FileOutputStream fos = openFileOutput("data.txt",MODE_PRIVATE);
//				PrintStream ps = new PrintStream(fos);
//				ps.println(colors[0]);
//				ps.println(picturePath);
//				ps.println(Dx);
//				ps.print(Dy);
//				}catch(Exception e){e.printStackTrace();}
//				//�����Ի�����ʾ����ɹ�
//				new AlertDialog.Builder(MainActivity.this)    
//				.setTitle("��Ϣ")  
//			    .setMessage("�ѱ�������")  
//			    .setPositiveButton("ȷ��", null)  
//			    .show(); 
				OutputStream os = null;
				 File file = new File(Environment.getExternalStorageDirectory(), "eboy.txt");
		         try {
		             os = new FileOutputStream(file);
		         } catch (FileNotFoundException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }
		         
		         try {
		        	 
		             os.write(String.valueOf(colors[0]).getBytes( ));
		             os.write(" ".getBytes());
		             os.write(picturePath.getBytes());
		             os.write(" ".getBytes());
		             os.write(String.valueOf(Dx).getBytes());
		             os.write(" ".getBytes());
		             os.write(String.valueOf(Dy).getBytes());
		        	 
		         } catch (IOException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }
		         
		         
		         try {
		             os.flush();
		         } catch (IOException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }
		         try {
		             os.close();
		         } catch (IOException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }    
		         Toast toast = Toast.makeText(MainActivity.this, "���óɹ�", Toast.LENGTH_SHORT); 
		         toast.show();
			}
			
		});
	}
	
	//�����ص���ɫ����ת��Ϊ��ɫֵ
//	private int IntColor(String color){
//		switch(color){
//		case "GRAY":return 0xff888888;
//		case "GREEN":return 0xff00ff00;
//		case "RED":return 0xffff0000;
//		case "YELLOW":return 0xffffff00;
//		case "CYAN":return 0xff00ffff;
//		case "LTGRAY":return 0xffcccccc;
//		case "DKGRAY":return 0xff444444;
//		case "MAGENTA":return 0xffff00ff;
//		case "BLUE":return 0xff0000ff;
//		default:return 0;
//		}
//	}
	
	void StringColor(int color){
		switch(color)
		{
			case 0xff888888:colors1="GRAY";break;
			case 0xff00ff00:colors1="GREEN";break;
			case 0xffff0000:colors1="RED";break;
			case 0xffffff00:colors1="YELLOW";break;
			case 0xff00ffff:colors1="CYAN";break;
			case 0xFF8C00:colors1="ORANGE";break;
			case 0x808000:colors1="OLIVE";break;
			case 0xffff00ff:colors1="MAGENTA";break;
			case 0xff0000ff:colors1="BLUE";break;
			default:colors1=" ";break;
		}
		}
	public void onActivityResult(int requestCode,int resultCode,Intent intent){
		//ѡ����ɫ�Ļص�����
//		if(requestCode==0&&resultCode==0){
//			Bundle data = intent.getExtras();
//			int color = Integer.parseInt(data.getString("color"));
//			TextView tv = (TextView)findViewById(R.id.textView1);
//			tv.setText("ѡ����ɫ��"+color);
////			colors=IntColor(color);
//			colors=color;
//		}
		if(requestCode==0&&resultCode==0){
			Bundle data = intent.getExtras();
			int[] color = data.getIntArray("color");
			TextView tv = (TextView)findViewById(R.id.textView1);
			
			colors=color;//���colors��һ������д��txt����������
//			if(colors==-7829368)
			StringColor(color[0]);
		//	tv.setText("ѡ����ɫ��"+color[0]+"    "+colors1);
			tv.setText("��ѡ��ɫ��"+colors1);
		}
		//����ͼ��Ļص�����
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != intent) {
			super.onActivityResult(requestCode, resultCode, intent);
            Uri selectedImage = intent.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
  
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
  
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
  
            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
  
        }

	}
	//ͼƬ�ƶ�������
	private final class TouchListener implements OnTouchListener {
		
		float[] values = new float[9]; //һ������������ȡmatrix�����ֵ
		/** ���ڼ�¼��ʼʱ�������λ�� */
		private PointF startPoint = new PointF();
		/** ���ڼ�¼����ͼƬ�ƶ�������λ�� */
		private Matrix matrix = new Matrix();
		/** ���ڼ�¼ͼƬҪ��������ʱ�������λ�� */
		private Matrix currentMatrix = new Matrix();
	
		TextView tv2 = (TextView)findViewById(R.id.textView2);

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			/** ͨ�������㱣������λ MotionEvent.ACTION_MASK = 255 */
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			// ��ָѹ����Ļ
			case MotionEvent.ACTION_DOWN:
				// ��¼ImageView��ǰ��λ��
				currentMatrix.set(imageView.getImageMatrix());
				startPoint.set(event.getX(), event.getY());
				break;
			// ��ָ����Ļ���ƶ������¼��ᱻ���ϴ���
			case MotionEvent.ACTION_MOVE:
				// ����ͼƬ
					float dx = event.getX() - startPoint.x; // �õ�x����ƶ�����
					
					float dy = event.getY() - startPoint.y; // �õ�x����ƶ�����
					
					// ��û���ƶ�֮ǰ��λ���Ͻ����ƶ�
					matrix.getValues(values);
					Dx=width/2-values[2];
					Dy=height/2-values[5];
					//tv2.setText(values[2]+","+values[5]+" Dx: "+Dx+"Dy: "+Dy);
					matrix.set(currentMatrix);
					matrix.postTranslate(dx, dy);
					matrix.getValues(values);
				break;
			}
			imageView.setImageMatrix(matrix);
			return true;
		}

	}
	

}