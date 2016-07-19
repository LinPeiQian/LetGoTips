package com.chwings.letgotips.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AutoCompleteTextView;

import com.chwings.letgotips.R;


/**
 * 带删除键的EditText<br>
 * 右边的图片将被其替代
 * @author lpq
 */
public class WithDelEditText extends AutoCompleteTextView implements OnFocusChangeListener{
	
	private final int CLEAN_DRAWABLE_ID = R.drawable.ic_divider_list_checkbox_checked;
	
	private boolean isHasFocus;
	
	private boolean isAutoComplete ;
	
	private String[] allCompleteArr ;
	
	public WithDelEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public WithDelEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public WithDelEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	
	/**
	 * 初始化
	 */
	private void init(){
		setClearDrawableVisibility(getText().toString().length() > 0);
		addTextChangedListener(new TextWatcherApi());
	}
	
	/**
	 * 设置清空按钮的可见性
	 * @param visibility true 可见 false不可见
	 */
	private void setClearDrawableVisibility(boolean visibility){
		Drawable rightDrawable = null;
		if(visibility)
			rightDrawable = getResources().getDrawable(CLEAN_DRAWABLE_ID);
		setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[1], rightDrawable, getCompoundDrawables()[3]);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			boolean isClean = (event.getX() > (getWidth() - getTotalPaddingRight()))
					&& (event.getX() < (getWidth() - getPaddingRight()));
			if (isClean) {
				setText("");
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		isHasFocus = hasFocus;
		if (isHasFocus) {
			boolean isVisible = getText().toString().length() >= 1;
			setClearDrawableVisibility(isVisible);
		} else {
			setClearDrawableVisibility(false);
		}
	}
	
	private class TextWatcherApi implements TextWatcher{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			setClearDrawableVisibility(s.toString().length() > 0);
		}
		
	}

	
}
