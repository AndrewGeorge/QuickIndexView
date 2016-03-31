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
		// ��ʼ�����
		initView();
	}
	
	/* ��ʼ����� */
	private void initView() {
		// �����������
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(24);
		// �Ӵ�
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
			
			// ��ѡ����ĸʱ�����û��ʵ���ɫΪ��ɫ������Ϊ��ɫ
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
		
		// ֻ�з���true�����ܼ�����move
		return true;
	}

	/* ��ó�ʼΪ-1, �պ�����ĸ��û�е� */
	int preIndex = -1;
	int currentIndex = -1;

	private void dispathTouchAction(MotionEvent event) {
		currentIndex = (int) (event.getY() / mCellHeight);
		// �ݴ�������Խ��
		if (!( currentIndex >= 0 && currentIndex <= mLetters.size() - 1) ){
			return;
		}
		
		// ��ǰ��ʾ��index���ϴ��Ƿ�һ���������ظ�
		if (preIndex != currentIndex){
			preIndex = currentIndex;

			// ֪ͨ����
			if (onLetterUpdateListener != null){
				onLetterUpdateListener.onUpdate(mLetters.get(currentIndex));
			}
		}
		
		// �ػ棬�û������õ���ɫ�����ã�֪ͨϵͳ����onDraw��
		invalidate();
		// ���ݲ����ֻ�
		ViewCompat.postInvalidateOnAnimation(this);
	}
	public void setOnLetterUpdateListener(
			OnLetterUpdateListener onLetterUpdateListener) {
		this.onLetterUpdateListener = onLetterUpdateListener;
	}

}
