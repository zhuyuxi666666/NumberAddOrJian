package com.zhuyuxi.beicai.lenovo.numberaddorjian;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lenovo on 2016/9/26.
 */
public class NumberAddSuby extends LinearLayout implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Button mAddBtn;
    private Button mSubBtn;
    private TextView mNumTv;

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getValue() {
        String val = mNumTv.getText().toString();
        if (val != null && !"".equals(val)) {
            this.value = Integer.parseInt(val);
        }
        return value;
    }

    public void setValue(int value) {
        mNumTv.setText(value + "");
        this.value = value;
    }

    private int value;
    private int minValue;
    private int maxValue;

    private OnButtonClickListener mOnButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.mOnButtonClickListener = onButtonClickListener;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn) {//点击+
            numAdd();
            if (mOnButtonClickListener != null) {
                mOnButtonClickListener.onButtonAddClick(v, value);
            }
        } else if (v.getId() == R.id.sub_btn) {//点击-
            numSub();
            if (mOnButtonClickListener != null) {
                mOnButtonClickListener.onButtonSubClick(v, value);
            }
        }
    }

    //加的方法
    private void numAdd() {
        if (value < maxValue) {
            value++;
        }
        mNumTv.setText(value + "");
    }

    //减的方法
    private void numSub() {
        if (value > minValue) {
            value--;
        }
        mNumTv.setText(value + "");
    }

    public interface OnButtonClickListener {
        void onButtonAddClick(View view, int value);

        void onButtonSubClick(View view, int value);

    }

    public NumberAddSuby(Context context) {
        this(context, null);
    }

    public NumberAddSuby(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberAddSuby(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        initView();
        if (attrs != null) {
            TintTypedArray tta = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.NumberAddSuby, defStyleAttr, 0);
            int val = tta.getInt(R.styleable.NumberAddSuby_value, 0);
            setValue(val);
            int minValue = tta.getInt(R.styleable.NumberAddSuby_minValue, 0);
            setMinValue(minValue);
            int maxValue = tta.getInt(R.styleable.NumberAddSuby_maxValue, 0);
            setMaxValue(maxValue);

            Drawable addBtnDrawable = tta.getDrawable(R.styleable.NumberAddSuby_addBtnBg);
            setAddBtnBackground(addBtnDrawable);

            Drawable subBtnDrawable = tta.getDrawable(R.styleable.NumberAddSuby_subBtnBg);
            setSubBtnBackground(subBtnDrawable);

            int numTvDrawable = tta.getResourceId(R.styleable.NumberAddSuby_numTvBg, android.R.color.white);
            setNumTvBackground(numTvDrawable);


            tta.recycle();
        }
    }

    private void setNumTvBackground(int numTvDrawable) {
        this.mNumTv.setBackgroundResource(numTvDrawable);
    }

    private void setSubBtnBackground(Drawable subBtnDrawable) {
        this.mSubBtn.setBackground(subBtnDrawable);
    }

    private void setAddBtnBackground(Drawable addBtnDrawable) {
        this.mAddBtn.setBackground(addBtnDrawable);
    }

    private void initView() {
        View view = mInflater.inflate(R.layout.wieght_number_add_sub, this, true);
        mAddBtn = (Button) findViewById(R.id.add_btn);
        mSubBtn = (Button) findViewById(R.id.sub_btn);
        mNumTv = (TextView) findViewById(R.id.num_tv);


        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);

    }
}
