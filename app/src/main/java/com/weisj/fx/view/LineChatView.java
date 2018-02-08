package com.weisj.fx.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.weisj.fx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 线框图
 * 
 * @author Administrator
 * 
 */
public class LineChatView extends View {

	private List<Integer> milliliter;
	private float tb;
	private float interval_left_right;

	private float left_pot;

	Bitmap icBitmap;

	private Paint paint_date, paint_brokenLine, paint_dottedline,
			paint_brokenline_big,drawCircle,drawCircle2,drawDot, framPanint,paint_text;

	private Bitmap bitmap_point;
	private Path path;

	String[] mothStrings;


	private int textColor = 0xff999999;
	private int fineLineColor = 0xffe6e6e6; // 灰色
	private int CircleColor = 0xffe0e0e0; // 蓝色
	private int WhiteColor = 0xffFFFFFF; // 蓝色
	private int lineColor;


	private List<String> dFloats  = new ArrayList<String>();
	private float xV = 0,yV = 0;
	int  tagValue;


	int []PainFrameColor ;


	int whichCharLine;

	String CompanyString  ; // 标示CompanyString位


	int AllValueMax;

	public LineChatView(Context context, List<Integer> milliliter, int lineColor, int whichCharLine, String[] mothStrings, int AllValueMax) {
		super(context);

		this.lineColor = lineColor;
		this.whichCharLine = whichCharLine;
		this.mothStrings = mothStrings;
		this.AllValueMax = AllValueMax;
		init(milliliter);
		switch (whichCharLine){
			case 1:
				PainFrameColor = new int[]{0xff61AEFF, 0x3361AEFF, 0x00A7D1FD};
				icBitmap = BitmapFactory.decodeResource(getResources(),
						R.mipmap.greed_charline);

				CompanyString = "人";
				break;
			case 2:
				PainFrameColor = new int[]{0xff1AE190, 0x331AE190, 0x00F0FDF8};
				icBitmap = BitmapFactory.decodeResource(getResources(),
						R.mipmap.greed_charline);

				CompanyString = "%";
				break;
			case 3:
				PainFrameColor = new int[]{	0xffF44341, 0x33F44341, 0x00FFECEB};

				icBitmap = BitmapFactory.decodeResource(getResources(),
						R.mipmap.greed_charline);
				CompanyString = "元";
				break;
		}
	}

	public void init(List<Integer> milliliter) {
		if (null == milliliter || milliliter.size() == 0)
			return;
		// this.milliliter = delZero(milliliter);
		this.milliliter = milliliter;
		Resources res = getResources();
		tb = res.getDimension(R.dimen.index_bar_view_margin);


		 left_pot = tb*2f;


		paint_date = new Paint();
		paint_date.setStrokeWidth(tb * 0.05f);
		paint_date.setTextSize(tb * 1.2f);
		paint_date.setColor(textColor);
		paint_date.setAntiAlias(true);


		paint_text = new Paint();
		paint_text.setStrokeWidth(tb * 0.5f);
		paint_text.setTextSize(tb * 1.2f);
		paint_text.setColor(WhiteColor);
		paint_text.setAntiAlias(true);
		// 曲线
		paint_brokenLine = new Paint();
		paint_brokenLine.setStrokeWidth(tb * 0.15f);
		paint_brokenLine.setColor(fineLineColor);
		paint_brokenLine.setAntiAlias(true);

		// 绘制竖线
		paint_dottedline = new Paint();
//		paint_dottedline.setStrokeWidth(tb * 0.03f);
		paint_dottedline.setStyle(Paint.Style.STROKE);
		paint_dottedline.setColor(fineLineColor);

		// 最底线
		paint_brokenline_big = new Paint();
		paint_brokenline_big.setStrokeWidth(tb * 0.2f);
		paint_brokenline_big.setColor(lineColor);
		paint_brokenline_big.setAntiAlias(true);

		framPanint = new Paint();
		framPanint.setAntiAlias(true);
		framPanint.setStrokeWidth(2f);


		drawCircle = new Paint(); // 圆点
		drawCircle.setAntiAlias(true);
		drawCircle.setColor(lineColor);
		drawCircle.setStyle(Paint.Style.FILL);
		drawCircle.setStrokeWidth(3);
		drawCircle.setAntiAlias(true);


		drawCircle2 = new Paint(); // 圆点
		drawCircle2.setAntiAlias(true);
		drawCircle2.setColor(Color.rgb(255, 255, 255));
		drawCircle2.setStyle(Paint.Style.FILL);
		drawCircle2.setStrokeWidth(3);
		drawCircle2.setAntiAlias(true);



		drawDot = new Paint(); // 圆点
		drawDot.setAntiAlias(true);
		drawDot.setColor(CircleColor);
		drawDot.setStyle(Paint.Style.FILL);
		drawDot.setStrokeWidth(6);
		drawDot.setAntiAlias(true);


		path = new Path();
		bitmap_point = BitmapFactory.decodeResource(getResources(),
				R.mipmap.status_join);



		interval_left_right = tb * 7.5f;
		setLayoutParams(new LayoutParams((int) (interval_left_right
				* mothStrings.length * 1.1f), LayoutParams.MATCH_PARENT));

//		setLayoutParams(new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

	}



	protected void onDraw(Canvas c) {
		if (null == milliliter || milliliter.size() == 0)
			return;

		dFloats  = new ArrayList<String>();
		drawStrokiLine(c);
		drawBrokenLine(c);



		float numFloat = tb*1.0f;

		if (xV>0){
			c.drawBitmap(icBitmap,
					xV - icBitmap.getWidth() / 2,
					yV - icBitmap.getHeight() / 2 - 50, null);

			if (tagValue>=1000  ){
				numFloat = numFloat*1.5f;
			}else if (tagValue>=100 ){
				numFloat = numFloat*1.5f;
			}else if (tagValue>=10 ){
				numFloat = numFloat*1.1f;
			}
			c.drawText(tagValue + CompanyString, xV - numFloat, yV - 45, paint_text);

		}
	}


	/**
	 * 绘制横x线
	 *
	 * @param c
	 */

	public void drawStrokiLine(Canvas c){
		paint_dottedline.setColor(fineLineColor);
		for (int i = 0; i <7; i++) {
			if (i!= 0) {

			Path path = new Path();
			path.moveTo(left_pot, (getHeight() - (tb * 1.5f)) / 7 * i);
			path.lineTo((int) (interval_left_right
					* mothStrings.length * 1.1f), (getHeight() - (tb * 1.5f)) / 7 * i);
			c.drawPath(path, paint_dottedline);
			}
		}


		for (int i = 0 ; i< milliliter.size() ; i ++ ){

			paint_brokenLine.setColor(fineLineColor);
			float x1 = interval_left_right * i - left_pot/2.0f*0.01f;
			float y1 = (getHeight() - (tb * 1.5f));

			float x2 = interval_left_right * (i + 1)- left_pot/2.0f*0.01f ;
			float y2 = (getHeight() - (tb * 1.5f));

			float xx = (interval_left_right-2) * i - left_pot/2.0f*0.1f;

			c.drawLine(x1, y1, x2, y2, paint_brokenLine);

			c.drawCircle(x1 - bitmap_point.getWidth() / 2+tb*2.7f, y1
					- bitmap_point.getHeight() / 2+tb*0.6f,8, drawDot);
			c.drawText(mothStrings[i], (x1 - bitmap_point.getWidth() / 2+tb*2.7f)-mothStrings[i].length() , getHeight() - tb * 0.5f+12, paint_date);
		}

	}


	/**
	 * 绘制折线
	 * @param c
	 */
	public void drawBrokenLine(Canvas c) {
		int index = 0;
		float temp_x = 0;
		float temp_y = 0;


		float heig;
		if (AllValueMax == 1){
			heig = 100*1.3f;
		}else{
			heig = AllValueMax*10.3f;
		}

		float base =  (getHeight() - tb * 1.5f) / heig;

		Shader mShader = new LinearGradient(0, 0, 0, getHeight(), PainFrameColor, null,
				Shader.TileMode.CLAMP);
		framPanint.setShader(mShader);

		drawCircle.setColor(lineColor);
		drawCircle2.setColor(Color.rgb(255,255,255));
		paint_brokenline_big.setColor(lineColor);


		int MaxValue=milliliter.get(0)-1 ,MinValue=milliliter.get(0)-1 ,middValue;

		int MaxNum = 0 ,MinNum = 0;

		float numFloat = tb*1.0f;

		for (int  i = 0;i<milliliter.size();i++){
			middValue = milliliter.get(i)-1;


			if (MaxValue<middValue){
				MaxValue = middValue;
				MaxNum = i;
			}

			if (MinValue>middValue){
				MinValue = middValue;
				MinNum = i;
			}

		}




		for (int i = 0; i < milliliter.size() - 1; i++) {
			float x1 = interval_left_right * i+left_pot*1.1f ;
			float y1 = (getHeight() - tb * 1.5f) - (base * ((milliliter.get(i))*0.85f));

			float x2 = interval_left_right * (i + 1)+left_pot*1.1f;
			float y2 = (getHeight() - tb * 1.5f) - (base * ((milliliter.get(i+1))*0.85f));


			if ((int) (base * (milliliter.get(i + 1) )) == 0 && index == 0) {
				index++;
				temp_x = x1;
				temp_y = y1;
			}
			if ((int) (base * (milliliter.get(i + 1))) != 0 && index != 0) {
				index = 0;
				x1 = temp_x;
				y1 = temp_y;
			}
			if (index == 0) {
				c.drawLine(x1, y1, x2, y2, paint_brokenline_big);
				if (i == 0) {
					path.moveTo(x1, y1);
				} else {
					path.lineTo(x1, y1);
				}
				c.drawCircle(x1 - bitmap_point.getWidth() / 2 + 16,
						y1- bitmap_point.getHeight() / 2 + left_pot*0.27f, 10, drawCircle);

				c.drawCircle(x1 - bitmap_point.getWidth() / 2 + 16,
						y1- bitmap_point.getHeight() / 2 + left_pot*0.27f, 7, drawCircle2);



				if (MaxNum == i || MinNum==i){
					c.drawBitmap(icBitmap,
							x1 - icBitmap.getWidth() / 2,
							y1 - icBitmap.getHeight() / 2 - 50, null);

					if (MaxNum == i){

						if (MaxValue>=1000 || MinValue>=1000 ){
							  numFloat = numFloat*1.5f;
						}else if (MaxValue>=100 || MinValue>=100 ){
							numFloat = numFloat*1.5f;
						}else if (MaxValue>=10 || MinValue>=10){
							numFloat = numFloat*1.1f;
						}
						c.drawText(MaxValue+CompanyString, x1-numFloat , y1  - 45, paint_text);
					}else{
						c.drawText(MinValue+CompanyString, x1-numFloat, y1  - 45, paint_text);
					}

				}else{
					String varString = ((x1 - bitmap_point.getWidth() / 2 + 11)+","+(y1- bitmap_point.getHeight() / 2 + left_pot*0.3f)+","+(milliliter.get(i)-1));

					dFloats.add(varString);
				}


				if (i == milliliter.size() - 2 && i > 0) {
					path.lineTo(x2, y2);
					path.lineTo(x2, getHeight() - tb * 1.5f);
					path.lineTo(0 + left_pot/2.0f, getHeight() - tb * 1.5f);
					path.close();
					c.drawPath(path, framPanint);


					c.drawCircle(x2 - bitmap_point.getWidth() / 2 + 16,
							y2 - bitmap_point.getHeight() / 2 + left_pot*0.27f, 10, drawCircle);

					c.drawCircle(x2 - bitmap_point.getWidth() / 2 + 16,
							y2 - bitmap_point.getHeight() / 2 + left_pot * 0.27f, 7, drawCircle2);



					if (MaxNum == milliliter.size() - 1 || MinNum == milliliter.size() - 1){
						c.drawBitmap(icBitmap,
								x2 - icBitmap.getWidth() / 2,
								y2 - icBitmap.getHeight() / 2 - 50, null);


						if (MaxNum == milliliter.size() -1){
							if (MaxValue>=100 || MinValue>100 ){
								numFloat = 32;
							}else if (MaxValue>=1000 || MinValue>1000) {
								numFloat = 42;
							}
							c.drawText(MaxValue+CompanyString, x2-numFloat , y2  - 45, paint_text);
						}else{
							c.drawText(MinValue+CompanyString, x2-numFloat , y2 - 45, paint_text);
						}
					}else{
						String varString = ((x2 - bitmap_point.getWidth() / 2 + 11)+","+(y2- bitmap_point.getHeight() / 2 + left_pot*0.3f)+","+(milliliter.get(milliliter.size() - 1)-1));

						dFloats.add(varString);
					}






				}
			}
		}

	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {

			case MotionEvent.ACTION_DOWN:



				for(int i=0;i<dFloats.size();i++){
					String[] split = dFloats.get(i).toString().split(",");

					if (Math.abs(Float.valueOf(split[0])-x)<50   &&Math.abs(Double.valueOf(split[1])-y)<50 ) {


						xV = Float.valueOf(split[0]);
						yV = Float.valueOf(split[1]);

						tagValue =Integer.valueOf(split[2]) ;
						invalidate();
						return false;
					}

				}
				break;





		}

		return true;
	}
}
