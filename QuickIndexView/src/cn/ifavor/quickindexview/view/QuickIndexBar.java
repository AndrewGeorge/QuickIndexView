package cn.ifavor.quickindexview.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View{

	private OnLetterUpdateListener onLetterUpdateListener;
	public interface OnLetterUpdateListener {
		void onUpdate(String letter);
	}
	
	private Paint mPaint;
	
	private int mWidth;
	private int mHeight;
	private float mCellWidth;
	private float mCellHeight;
	
	private List<String> mLetters;
	
	public QuickIndexBar(Context context) {
		this(context, null);
	}
	public QuickIndexBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// 初始化组件
		initView();
	}
	
	/* 初始化组件 */
	private void initView() {
		// 参数：反锯齿
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(24);
		// 加粗
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);
		
		mLetters = new ArrayList<String>();
		for (char i = 'A'; i <= 'Z'; i++){
			String s = String.valueOf(i);
			mLetters.add(s);
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mWidth = getMeasuredWidth();
		mHeight = getMeasuredHeight();
		
		mCellWidth = mWidth;
		mCellHeight = mHeight / mLetters.size();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < mLetters.size(); i++){
			
			Rect bounds = new Rect();
			mPaint.getTextBounds(mLetters.get(i), 0, 1, bounds);
			int x = (int) (mCellWidth / 2.0f - mPaint.measureText(mLetters.get(i)) / 2.0f);
			int y = (int) (mCellHeight - bounds.height() / 2.0f + i * mCellHeight);
			
			// 当选中字母时，设置画笔的颜色为黑色，否则为白色
			mPaint.setColor( currentIndex == i ? Color.BLACK : Color.WHITE );
			
			canvas.drawText(mLetters.get(i), x, y, mPaint);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		
		switch (MotionEventCompat.getActionMasked(event)) {
		case MotionEvent.ACTION_DOWN:
			dispathTouchAction(event);
			break;
		case MotionEvent.ACTION_MOVE:
			dispathTouchAction(event);
			break;
		case MotionEvent.ACTION_UP:
			
			break;
		}
		
		// 只有返回true，才能监听到move
		return true;
	}

	/* 最好初始为-1, 刚好是字母表没有的 */
	int preIndex = -1;
	int currentIndex = -1;

	private void dispathTouchAction(MotionEvent event) {
		currentIndex = (int) (event.getY() / mCellHeight);
		// 容错，防数组越界
		if (!( currentIndex >= 0 && currentIndex <= mLetters.size() - 1) ){
			return;
		}
		
		// 当前显示的index和上次是否一样，避免重复
		if (preIndex != currentIndex){
			preIndex = currentIndex;

			// 通知监听
			if (onLetterUpdateListener != null){
				onLetterUpdateListener.onUpdate(mLetters.get(currentIndex));
			}
		}
		
		// 重绘，让画笔设置到颜色起作用（通知系统调用onDraw）
		invalidate();
		// 兼容部分手机
		ViewCompat.postInvalidateOnAnimation(this);
	}
	public void setOnLetterUpdateListener(
			OnLetterUpdateListener onLetterUpdateListener) {
		this.onLetterUpdateListener = onLetterUpdateListener;
	}

}
