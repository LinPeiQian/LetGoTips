package com.chwings.letgotips.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.brianLin.utils.DensityUtils;
import com.chwings.letgotips.bean.LabelBean;
import com.chwings.letgotips.bean.LabelEnum;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 标签控件
 */
public class LabelView extends ViewGroup implements Serializable {

    /**
     * 只有一条线的样式变化
     */
    public final int ONE_LEFT_STYLE = 101;
    public final int ONE_RIGHT_STYLE = 102;
    public final int ONE_LEFT_BOTTOM_SPAN_STYLE = 103;
    public final int ONE_RIGHT_BOTTOM_SPAN_STYLE = 104;

    /**
     * 两条线的样式变化
     */
    public final int TWO_LEFT_STYLE = 105;
    public final int TWO_RIGHT_STYLE = 106;
    public final int TWO_LEFT_SPAN_STYLE = 107;
    public final int TWO_RIGHT_SPAN_STYLE = 108;

    /**
     * 三条线的样式变化
     */
    public static final int THREE_LEFT_STYLE = 109;
    public final int THREE_RIGHT_STYLE = 110;
    public final int THREE_LEFT_TWO_SPAN_RIGHT_SPAN_STYLE = 111;
    public final int THREE_LEFT_SPAN_RIGHT_TWO_SPAN_STYLE = 112;

    /**
     * 斜线的方向
     */
    public final int TOP = 200;
    public final int BOTTOM = 201;
    public final int LEFT_TOP = 202;
    public final int LEFT_BOTTOM = 203;
    public final int RIGHT_TOP = 204;
    public final int RIGHT_BOTTOM = 205;
    public final int LEFT = 206;
    public final int RIGHT = 207;

    /**
     * 优先级 (左上1 左下2 右下3) (右上1 右下2 左下3)
     */
    private final LabelEnum FIRST = LabelEnum.PRICE;
    private final LabelEnum SECOND = LabelEnum.NAME;
    private final LabelEnum THIRD = LabelEnum.ADDRESS;

    private List<LabelEnum> mPriorityList;

    /**
     * 直线动画
     */
    private ValueAnimator mLeftTopAnim, mLeftBottomAnim, mLeftAnim, mRightTopAnim, mRightBottomAnim, mRightAnim;

    private boolean mDrawLeftTop, mDrawLeftBottom, mDrawRightTop, mDrawRightBottom, mDrawLeft, mDrawRight;

    /**
     * 当前的样式
     */
    private int mCurrentStyle;

    /**
     * 当前线条数量
     */
    private int mCurrentLineNum;

    /**
     * 样式键值 key 线条数目 value 样式
     */
    private Map<Integer, List<Integer>> mStyleMap;

    /**
     * 一条线的样式集合
     */
    private List<Integer> mOneLineStyleList;

    /**
     * 两条线的样式集合
     */
    private List<Integer> mTwoLineStyleList;

    /**
     * 三条线的样式集合
     */
    private List<Integer> mThreeLineStyleList;

    /**
     * 标签数据 集合
     */
    private List<LabelBean> mDataList;

    /**
     * 最大线条数
     */
    private final int mMaxLineNum = LabelEnum.values().length;

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 文字边框 用于点击时的判断
     */
    private Rect mTextRect;

    /**
     * 线的颜色
     */
    private int mLineColor = Color.WHITE;

    /**
     * 线条的宽度
     */
    private int mLineWidth = 2;

    /**
     * 文字的颜色
     */
    private int mTextColor = Color.WHITE;

    /**
     * 文字的大小
     */
    private float mTextSize = DensityUtils.sp2px(getContext(), 15);

    /**
     * 中心小圆的颜色
     */
    private int mCenterInsideCircleColor = Color.WHITE;

    /**
     * 中心大圆的颜色
     */
    private int mCenterOutsideCircleColor = Color.parseColor("#30000000");

    /**
     * 中心动画圆的颜色
     */
    private int mCenterAnimCircleColor = Color.parseColor("#DE5448");

    /**
     * 跨度动画
     */
    private ValueAnimator mSpanLineAnim;

    /**
     * 线条终点的坐标
     */
    private Point mLeftLineEndPoint, mRightLineEndPoint, mLeftTopLineEndPoint, mLeftBottonEndPoint, mRightTopEndPoint, mRightBottonEndPoint;

    /**
     * 中心圆的坐标
     */
    private Point mCenterPoint;

    /**
     * view的宽
     */
    private int mViewWidth;

    /**
     * view的高
     */
    private int mViewHeight;

    /**
     * label在pointAt的x坐标(父容器中的百分比)
     */
    public PointF mPointAtPc;

    /**
     * label在pointAt的y坐标(父容器中的像素值)
     */
    public PointF mPointAtPx;

    /**
     * 中心小圆的半径
     */
    private int mInsideCircleRadius = DensityUtils.dp2px(getContext(), 6);

    /**
     * 中心外圈的圆半径
     */
    private int mOutSideCircleRadius = (int) (1.6 * mInsideCircleRadius);

    /**
     * 斜线的长度是
     */
    private int mSpanLength = 5 * mInsideCircleRadius;

    /**
     * 斜线在XY轴上的投影长度
     */
    private int mSpanLength2XY = (int) (mSpanLength / Math.sqrt(2));

    /**
     * 父容器
     */
    private RelativeLayout mParentView;

    /**
     * 在文字宽度上再加一点
     */
    private int mTextWidthAddValue = 40;

    /**
     * 绘制的文字距离地下的线条的距离
     */
    private int mTextMarginBottomLine = 8;

    /**
     * 圆心的边框 用于点击判断
     */
    private Rect mCenterRect;

    /**
     * 内间距
     */
    private int mPadding = 20;

    /**
     * 绘制斜线的时间
     */
    private long mAnimSpanDuration = 500L;

    /**
     * 绘制斜线是否完成
     */
    private boolean mIsDrawSpanLineFinish;

    /**
     * 动态记录斜线画到哪一点
     */
    private Point mCurrentSpanPoint;

    /**
     * 是否展示
     */
    private boolean mIsShow = false;

    /**
     * 中心圆的动画集
     */
    private ValueAnimator mCenterScaleAnim;

    private float mCurrentCenterScaleValue;

    private long mCenterAnimDuration = 700L;

    private ImageView iv_anim ;

    private final String TAG = getClass().getSimpleName();

    public LabelView(Context context) {
        super(context);
        init();
    }

    private boolean mIsAdd;

    /**
     * 初始化
     */
    private void init() {
        setWillNotDraw(false);
        setClickable(true);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        initStyleMap();
        initDot();
    }

    private void initDot() {
        iv_anim = new ImageView(getContext());
        iv_anim.setLayoutParams(new LayoutParams(2 * mInsideCircleRadius, 2 * mInsideCircleRadius));

        Bitmap bm = Bitmap.createBitmap(mInsideCircleRadius * 2, mInsideCircleRadius * 2, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStrokeWidth(mInsideCircleRadius);
        p.setColor(Color.WHITE);
        c.drawCircle(mInsideCircleRadius, mInsideCircleRadius, mInsideCircleRadius, p);

        iv_anim.setImageBitmap(bm);
        this.addView(iv_anim);
    }

    /**
     * 初始化绘制线条的画笔属性
     */
    private void initLinePaint() {
        if (mPaint != null) {
            mPaint.setStrokeWidth(mLineWidth);
            mPaint.setColor(mLineColor);
        }
    }

    /**
     * 初始化绘制文字的画笔属性
     */
    private void initTextPaint() {
        if (mPaint != null) {
            mPaint.setStrokeWidth(0);
            mPaint.setColor(mTextColor);
            mPaint.setTextSize(mTextSize);
        }
    }

    /**
     * 加入标签数据
     */
    public void addLabelBean(LabelBean bean) {
        //为空或者没有内容的不加入
        if (bean != null && (!TextUtils.isEmpty(bean.obj.toString()) || !TextUtils.isEmpty(bean.type))) {
            if (mDataList == null) {
                mDataList = new ArrayList<>();
            }
            if (mDataList.size() >= mMaxLineNum) {
                //超出线条数目的限制
                return;
            }
            mDataList.add(bean);
            setCurrentStyle();
            setViewSize();
        }
    }

    /**
     * 根据传入的参数设置样式
     */
    private void setCurrentStyle() {
        int lineNum = mDataList.size();
        if (mCurrentStyle == 0) {
            //使用默认
            mCurrentStyle = mStyleMap.get(lineNum).get(0);
        } else if (mCurrentLineNum > lineNum) {
            //减少了数据 采用一样的样式 只是线条数目不同
            int index = mStyleMap.get(mCurrentLineNum).indexOf(mCurrentStyle);
            mCurrentStyle = lineNum == 0 ? 0 :
                    mStyleMap.get(lineNum).get(index);
        } else if (mCurrentLineNum < lineNum) {
            //增加了数据
            int index = mStyleMap.get(mCurrentLineNum).indexOf(mCurrentStyle);
            mCurrentStyle = mStyleMap.get(lineNum).get(index);
        }
        mCurrentLineNum = lineNum;
    }

    public List<LabelBean> getDatas(){
        return mDataList;
    }

    /**
     * 设置空间尺寸
     */
    private void setViewSize() {
        if (mCenterPoint == null) mCenterPoint = new Point();
        setPadding(mPadding, mPadding, mPadding, mPadding);
        setTextInfoList();
        switch (mCurrentStyle) {
            case ONE_LEFT_STYLE:
                mCenterPoint.x = getPaddingLeft() + mTextInfoList.get(0).lineWidth + mOutSideCircleRadius;
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight;
                mViewWidth = mCenterPoint.x + mOutSideCircleRadius + getPaddingRight();
                mViewHeight = mCenterPoint.y + mOutSideCircleRadius + getPaddingBottom();
                break;
            case ONE_RIGHT_STYLE:
                mCenterPoint.x = getPaddingLeft() + mOutSideCircleRadius;
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight;
                mViewWidth = mCenterPoint.x + mOutSideCircleRadius + mTextInfoList.get(0).lineWidth + getPaddingRight();
                mViewHeight = mCenterPoint.y + mOutSideCircleRadius + getPaddingBottom();
                break;
            case ONE_LEFT_BOTTOM_SPAN_STYLE:
                mCenterPoint.x = getPaddingLeft() + mTextInfoList.get(0).lineWidth + mSpanLength2XY;
                mCenterPoint.y = getPaddingTop() + mOutSideCircleRadius;
                mViewWidth = getPaddingLeft() + mTextInfoList.get(0).lineWidth + mSpanLength2XY + mOutSideCircleRadius + getPaddingRight();
                mViewHeight = getPaddingTop() + mOutSideCircleRadius + mSpanLength2XY + getPaddingBottom();
                break;
            case ONE_RIGHT_BOTTOM_SPAN_STYLE:
                mCenterPoint.x = getPaddingLeft() + mOutSideCircleRadius;
                mCenterPoint.y = getPaddingTop() + mOutSideCircleRadius;
                mViewWidth = getPaddingLeft() + mTextInfoList.get(0).lineWidth + mSpanLength2XY + mOutSideCircleRadius + getPaddingRight();
                mViewHeight = getPaddingTop() + mOutSideCircleRadius + mSpanLength2XY + getPaddingBottom();
                break;
            case TWO_LEFT_STYLE:
                mCenterPoint.x = getPaddingLeft() + Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth);
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength;
                mViewWidth = getPaddingLeft() + Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth) + mOutSideCircleRadius + getPaddingRight();
                mViewHeight = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength * 2 + getPaddingBottom();
                break;
            case TWO_RIGHT_STYLE:
                mCenterPoint.x = getPaddingLeft() + mOutSideCircleRadius;
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength;
                mViewWidth = getPaddingLeft() + Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth) + mOutSideCircleRadius + getPaddingRight();
                mViewHeight = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength * 2 + getPaddingBottom();
                break;
            case TWO_LEFT_SPAN_STYLE:
                mCenterPoint.x = getPaddingLeft() + Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth) + mSpanLength2XY;
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength2XY;
                mViewWidth = getPaddingLeft() + Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth) + mSpanLength2XY + mOutSideCircleRadius + getPaddingRight();
                mViewHeight = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength2XY * 2 + getPaddingBottom();
                break;
            case TWO_RIGHT_SPAN_STYLE:
                mCenterPoint.x = getPaddingLeft() + mOutSideCircleRadius;
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength2XY;
                mViewWidth = getPaddingLeft() + Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth) + mSpanLength2XY + mOutSideCircleRadius + getPaddingRight();
                mViewHeight = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength2XY * 2 + getPaddingBottom();
                break;
            case THREE_LEFT_STYLE:
                mCenterPoint.x = getPaddingLeft() + Math.max(mTextInfoList.get(2).lineWidth, Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth));
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength;
                mViewWidth = getPaddingLeft() + Math.max(mTextInfoList.get(2).lineWidth, Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth)) + mOutSideCircleRadius + getPaddingRight();
                mViewHeight = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength * 2 + getPaddingBottom();
                break;
            case THREE_RIGHT_STYLE:
                mCenterPoint.x = getPaddingLeft() + mOutSideCircleRadius;
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength;
                mViewWidth = getPaddingLeft() + Math.max(mTextInfoList.get(2).lineWidth, Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth)) + mOutSideCircleRadius + getPaddingRight();
                mViewHeight = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength * 2 + getPaddingBottom();
                break;
            case THREE_LEFT_TWO_SPAN_RIGHT_SPAN_STYLE:
                mCenterPoint.x = getPaddingLeft() + Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth) + mSpanLength2XY;
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength2XY;
                mViewWidth = getPaddingLeft() + Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth) + mSpanLength2XY * 2 + mTextInfoList.get(2).lineWidth + getPaddingRight();
                mViewHeight = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength2XY * 2 + getPaddingBottom();
                break;
            case THREE_LEFT_SPAN_RIGHT_TWO_SPAN_STYLE:
                mCenterPoint.x = getPaddingLeft() + mTextInfoList.get(2).lineWidth + mSpanLength2XY;
                mCenterPoint.y = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength2XY;
                mViewWidth = getPaddingLeft() + Math.max(mTextInfoList.get(0).lineWidth, mTextInfoList.get(1).lineWidth) + mSpanLength2XY * 2 + mTextInfoList.get(2).lineWidth + getPaddingRight();
                mViewHeight = getPaddingTop() + mTextInfoList.get(0).textHeight + mSpanLength2XY * 2 + getPaddingBottom();
                break;
        }
        setCenterRect(mCenterPoint.x, mCenterPoint.y);
        setMeasuredDimension(mViewWidth, mViewHeight);
        //保持point的位置
        if (mPointAtPc == null) mPointAtPc = new PointF();
        setPointAt(mPointAtPc.x, mPointAtPc.y);
        //在改变view大小后,调用parent的layout,改变view在视图中的大小
        if (getLayoutParams() != null) {
            getLayoutParams().width = mViewWidth;
            getLayoutParams().height = mViewHeight;
        }
        if (getParent() != null) {
            getParent().requestLayout();
        }
        invalidate();
    }

    private void setCenterRect(int centerX, int centerY) {
        //中心范围int left, int top, int right, int bottom
        mCenterRect = new Rect(centerX - mOutSideCircleRadius, centerY - mOutSideCircleRadius, centerX + mOutSideCircleRadius, centerY + mOutSideCircleRadius);
    }

    private void reset() {
        mDrawLeftTop = false;
        mDrawLeftBottom = false;
        mDrawRightTop = false;
        mDrawRightBottom = false;
        mDrawLeft = false;
        mDrawRight = false;
        mIsDrawSpanLineFinish = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.GREEN);
        drawCircle(canvas);
        switch (mCurrentStyle) {
            case ONE_LEFT_STYLE:
                initLinePaint();
                if (mLeftAnim == null || !mDrawLeft) {
                    if (mLeftLineEndPoint == null) mLeftLineEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x - mTextInfoList.get(0).lineWidth, mCenterPoint.y, mLeftLineEndPoint, LEFT);
                    mDrawLeft = true;
                }
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mLeftLineEndPoint.x, mLeftLineEndPoint.y, mPaint);
                drawText(mLeftLineEndPoint.x, mLeftLineEndPoint.y, mLeftAnim, canvas, mTextInfoList.get(0));
                break;
            case ONE_RIGHT_STYLE:
                initLinePaint();
                //右
                if (mRightAnim == null || !mDrawRight) {
                    if (mRightLineEndPoint == null) mRightLineEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x - mTextInfoList.get(0).lineWidth, mCenterPoint.y, mRightLineEndPoint, RIGHT);
                    mDrawRight = true;
                }
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mRightLineEndPoint.x, mRightLineEndPoint.y, mPaint);
                drawText(mCenterPoint.x, mRightLineEndPoint.y, mRightAnim, canvas, mTextInfoList.get(0));
                break;
            case ONE_LEFT_BOTTOM_SPAN_STYLE:
                drawSpanLine(canvas, LEFT_BOTTOM);
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mLeftBottomAnim == null || !mDrawLeftBottom) {
                    if (mLeftBottonEndPoint == null) mLeftBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x - mSpanLength2XY - mTextInfoList.get(0).lineWidth, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint, LEFT_BOTTOM);
                    mDrawLeftBottom = true;
                }
                canvas.drawLine(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mPaint);
                drawText(mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mLeftBottomAnim, canvas, mTextInfoList.get(0));
                break;
            case ONE_RIGHT_BOTTOM_SPAN_STYLE:
                drawSpanLine(canvas, RIGHT_BOTTOM);
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mRightBottomAnim == null || !mDrawRightBottom) {
                    if (mRightBottonEndPoint == null) mRightBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x + mSpanLength2XY + mTextInfoList.get(0).lineWidth, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint, RIGHT_BOTTOM);
                    mDrawRightBottom = true;
                }
                canvas.drawLine(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint.x, mRightBottonEndPoint.y, mPaint);
                drawText(mCenterPoint.x + mSpanLength2XY, mRightBottonEndPoint.y, mRightBottomAnim, canvas, mTextInfoList.get(0));
                break;
            case TWO_LEFT_STYLE:
                drawSpanLine(canvas, new int[]{TOP, BOTTOM});
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mLeftTopAnim == null || !mDrawLeftTop) {
                    if (mLeftTopLineEndPoint == null) mLeftTopLineEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y - mSpanLength2XY, mCenterPoint.x + mTextInfoList.get(0).lineWidth, mCenterPoint.y - mSpanLength2XY, mLeftTopLineEndPoint, LEFT_TOP);
                    mDrawLeftTop = true;
                }
                if (mLeftBottomAnim == null || !mDrawLeftBottom) {
                    if (mLeftBottonEndPoint == null) mLeftBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x + mTextInfoList.get(1).lineWidth, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint, LEFT_BOTTOM);
                    mDrawLeftBottom = true;
                }
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y - mSpanLength2XY, mLeftTopLineEndPoint.x, mLeftTopLineEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mPaint);
                drawText(mLeftTopLineEndPoint.x, mLeftTopLineEndPoint.y, mLeftTopAnim, canvas, mTextInfoList.get(0));
                drawText(mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mLeftBottomAnim, canvas, mTextInfoList.get(1));
                break;
            case TWO_RIGHT_STYLE:
                drawSpanLine(canvas, new int[]{TOP, BOTTOM});
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mRightTopAnim == null || !mDrawRightTop) {
                    if (mRightTopEndPoint == null) mRightTopEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y - mSpanLength2XY, mCenterPoint.x + mTextInfoList.get(0).lineWidth, mCenterPoint.y - mSpanLength2XY, mRightTopEndPoint, RIGHT_TOP);
                    mDrawRightTop = true;
                }
                if (mRightBottomAnim == null || !mDrawRightBottom) {
                    if (mRightBottonEndPoint == null) mRightBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x + mTextInfoList.get(1).lineWidth, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint, RIGHT_BOTTOM);
                    mDrawRightBottom = true;
                }
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y - mSpanLength2XY, mRightTopEndPoint.x, mRightTopEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint.x, mRightBottonEndPoint.y, mPaint);
                drawText(mCenterPoint.x, mLeftTopLineEndPoint.y, mRightTopAnim, canvas, mTextInfoList.get(0));
                drawText(mCenterPoint.x, mLeftBottonEndPoint.y, mRightBottomAnim, canvas, mTextInfoList.get(1));
                break;
            case TWO_LEFT_SPAN_STYLE:
                drawSpanLine(canvas, new int[]{LEFT_TOP, LEFT_BOTTOM});
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mLeftTopAnim == null || !mDrawLeftTop) {
                    if (mLeftTopLineEndPoint == null) mLeftTopLineEndPoint = new Point();
                    startLineAnim(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y - mSpanLength2XY, mCenterPoint.x - mSpanLength2XY + mTextInfoList.get(0).lineWidth, mCenterPoint.y - mSpanLength2XY, mLeftTopLineEndPoint, LEFT_TOP);
                    mDrawLeftTop = true;
                }
                if (mLeftBottomAnim == null || !mDrawLeftBottom) {
                    if (mLeftBottonEndPoint == null) mLeftBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x - mSpanLength2XY - mTextInfoList.get(1).lineWidth, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint, LEFT_BOTTOM);
                    mDrawLeftBottom = true;
                }
                canvas.drawLine(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y - mSpanLength2XY, mLeftTopLineEndPoint.x, mLeftTopLineEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mPaint);
                drawText(mLeftTopLineEndPoint.x, mLeftTopLineEndPoint.y, mLeftTopAnim, canvas, mTextInfoList.get(0));
                drawText(mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mLeftBottomAnim, canvas, mTextInfoList.get(1));
                break;
            case TWO_RIGHT_SPAN_STYLE:
                drawSpanLine(canvas, new int[]{RIGHT_TOP, RIGHT_BOTTOM});
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mRightTopAnim == null || !mDrawRightTop) {
                    if (mRightTopEndPoint == null) mRightTopEndPoint = new Point();
                    startLineAnim(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y - mSpanLength2XY, mCenterPoint.x + mSpanLength2XY + mTextInfoList.get(0).lineWidth, mCenterPoint.y - mSpanLength2XY, mRightTopEndPoint, RIGHT_TOP);
                    mDrawRightTop = true;
                }
                if (mRightBottomAnim == null || !mDrawRightBottom) {
                    if (mRightBottonEndPoint == null) mRightBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x + mSpanLength2XY + mTextInfoList.get(1).lineWidth, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint, RIGHT_BOTTOM);
                    mDrawRightBottom = true;
                }
                canvas.drawLine(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y - mSpanLength2XY, mRightTopEndPoint.x, mRightTopEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint.x, mRightBottonEndPoint.y, mPaint);
                drawText(mCenterPoint.x + mSpanLength2XY, mRightTopEndPoint.y, mRightTopAnim, canvas, mTextInfoList.get(0));
                drawText(mCenterPoint.x + mSpanLength2XY, mRightBottonEndPoint.y, mRightBottomAnim, canvas, mTextInfoList.get(1));
                break;
            case THREE_LEFT_STYLE:
                drawSpanLine(canvas, new int[]{TOP, BOTTOM});
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mLeftTopAnim == null || !mDrawLeftTop) {
                    if (mLeftTopLineEndPoint == null) mLeftTopLineEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y - mSpanLength2XY, mCenterPoint.x - mTextInfoList.get(0).lineWidth, mCenterPoint.y - mSpanLength2XY, mLeftTopLineEndPoint, LEFT_TOP);
                    mDrawLeftTop = true;
                }
                if (mLeftAnim == null || !mDrawLeft) {
                    if (mLeftLineEndPoint == null) mLeftLineEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x - mTextInfoList.get(1).lineWidth, mCenterPoint.y, mLeftLineEndPoint, LEFT);
                    mDrawLeft = true;
                }
                if (mLeftBottomAnim == null || !mDrawLeftBottom) {
                    if (mLeftBottonEndPoint == null) mLeftBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x - mTextInfoList.get(2).lineWidth, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint, LEFT_BOTTOM);
                    mDrawLeftBottom = true;
                }
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y - mSpanLength2XY, mLeftTopLineEndPoint.x, mLeftTopLineEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mLeftLineEndPoint.x, mLeftLineEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mPaint);
                drawText(mLeftTopLineEndPoint.x, mLeftTopLineEndPoint.y, mLeftTopAnim, canvas, mTextInfoList.get(0));
                drawText(mLeftLineEndPoint.x, mLeftLineEndPoint.y, mLeftAnim, canvas, mTextInfoList.get(1));
                drawText(mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mLeftBottomAnim, canvas, mTextInfoList.get(2));
                break;
            case THREE_RIGHT_STYLE:
                drawSpanLine(canvas, new int[]{TOP, BOTTOM});
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mRightTopAnim == null || !mDrawRightTop) {
                    if (mRightTopEndPoint == null) mRightTopEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y - mSpanLength2XY, mCenterPoint.x + mTextInfoList.get(0).lineWidth, mCenterPoint.y - mSpanLength2XY, mRightTopEndPoint, RIGHT_TOP);
                    mDrawRightTop = true;
                }
                if (mRightAnim == null || !mDrawRight) {
                    if (mRightLineEndPoint == null) mRightLineEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x + mTextInfoList.get(1).lineWidth, mCenterPoint.y, mRightLineEndPoint, RIGHT);
                    mDrawRight = true;
                }
                if (mRightBottomAnim == null || !mDrawRightBottom) {
                    if (mRightBottonEndPoint == null) mRightBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x + mSpanLength2XY + mTextInfoList.get(2).lineWidth, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint, RIGHT_BOTTOM);
                    mDrawRightBottom = true;
                }
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y - mSpanLength2XY, mRightTopEndPoint.x, mRightTopEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mRightLineEndPoint.x, mRightLineEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint.x, mRightBottonEndPoint.y, mPaint);
                drawText(mCenterPoint.x, mRightTopEndPoint.y, mRightTopAnim, canvas, mTextInfoList.get(0));
                drawText(mCenterPoint.x, mLeftLineEndPoint.y, mRightAnim, canvas, mTextInfoList.get(1));
                drawText(mCenterPoint.x, mRightBottonEndPoint.y, mRightBottomAnim, canvas, mTextInfoList.get(2));
                break;
            case THREE_LEFT_TWO_SPAN_RIGHT_SPAN_STYLE:
                drawSpanLine(canvas, new int[]{LEFT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM});
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mLeftTopAnim == null || !mDrawLeftTop) {
                    if (mLeftTopLineEndPoint == null) mLeftTopLineEndPoint = new Point();
                    startLineAnim(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y - mSpanLength2XY, mCenterPoint.x - mSpanLength2XY - mTextInfoList.get(0).lineWidth, mCenterPoint.y - mSpanLength2XY, mLeftTopLineEndPoint, LEFT_TOP);
                    mDrawLeftTop = true;
                }
                if (mLeftBottomAnim == null || !mDrawLeftBottom) {
                    if (mLeftBottonEndPoint == null) mLeftBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x - mSpanLength2XY - mTextInfoList.get(1).lineWidth, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint, LEFT_BOTTOM);
                    mDrawLeftBottom = true;
                }
                if (mRightBottomAnim == null || !mDrawRightBottom) {
                    if (mRightBottonEndPoint == null) mRightBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x + mSpanLength2XY + mTextInfoList.get(2).lineWidth, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint, RIGHT_BOTTOM);
                    mDrawRightBottom = true;
                }
                canvas.drawLine(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y - mSpanLength2XY, mLeftTopLineEndPoint.x, mLeftTopLineEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint.x, mRightBottonEndPoint.y, mPaint);
                drawText(mLeftTopLineEndPoint.x, mLeftTopLineEndPoint.y, mLeftTopAnim, canvas, mTextInfoList.get(0));
                drawText(mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mLeftBottomAnim, canvas, mTextInfoList.get(1));
                drawText(mCenterPoint.x + mSpanLength2XY, mRightBottonEndPoint.y, mRightBottomAnim, canvas, mTextInfoList.get(2));
                break;
            case THREE_LEFT_SPAN_RIGHT_TWO_SPAN_STYLE:
                drawSpanLine(canvas, new int[]{RIGHT_TOP, RIGHT_BOTTOM, LEFT_BOTTOM});
                if (mSpanLineAnim.isRunning()) {
                    //先画完斜线部分
                    return;
                }
                if (mRightTopAnim == null || !mDrawRightTop) {
                    if (mRightTopEndPoint == null) mRightTopEndPoint = new Point();
                    startLineAnim(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y - mSpanLength2XY, mCenterPoint.x + mSpanLength2XY + mTextInfoList.get(0).lineWidth, mCenterPoint.y - mSpanLength2XY, mRightTopEndPoint, RIGHT_TOP);
                    mDrawRightTop = true;
                }
                if (mRightAnim == null || !mDrawRight) {
                    if (mRightBottonEndPoint == null) mRightBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x + mSpanLength2XY + mTextInfoList.get(1).lineWidth, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint, RIGHT);
                    mDrawRight = true;
                }
                if (mLeftBottomAnim == null || !mDrawLeftTop) {
                    if (mLeftBottonEndPoint == null) mLeftBottonEndPoint = new Point();
                    startLineAnim(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mCenterPoint.x - mSpanLength2XY - mTextInfoList.get(2).lineWidth, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint, LEFT_BOTTOM);
                    mDrawLeftTop = true;
                }
                canvas.drawLine(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y - mSpanLength2XY, mRightTopEndPoint.x, mRightTopEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x + mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mRightBottonEndPoint.x, mRightBottonEndPoint.y, mPaint);
                canvas.drawLine(mCenterPoint.x - mSpanLength2XY, mCenterPoint.y + mSpanLength2XY, mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mPaint);
                drawText(mCenterPoint.x + mSpanLength2XY, mRightTopEndPoint.y, mRightTopAnim, canvas, mTextInfoList.get(0));
                drawText(mCenterPoint.x + mSpanLength2XY, mRightBottonEndPoint.y, mRightBottomAnim, canvas, mTextInfoList.get(1));
                drawText(mLeftBottonEndPoint.x, mLeftBottonEndPoint.y, mLeftBottomAnim, canvas, mTextInfoList.get(2));
                break;
        }
    }

    /**
     * 绘制文字
     *
     * @param lineEndY 结束的y点
     * @param animator 线条的动画  需要线条绘制完成才绘制文字
     * @param canvas   画笔
     */
    private void drawText(float x, float lineEndY, ValueAnimator animator, Canvas canvas, TextInfo info) {
        if (animator == null || animator.isRunning() || info == null || canvas == null) {
            return;
        }
        float textX = info.lineWidth / 2 - info.textWidth / 2 + x;

        float textY = lineEndY - mTextMarginBottomLine;
        if (info.rectF == null) {
            info.rectF = new RectF();
        }
        info.rectF.set(textX, textY - info.textHeight, textX + info.textWidth, textY);
        canvas.drawText(info.content, textX, textY, mPaint);
    }

    /**
     * 绘制中心圆
     */
    private void drawCircle(Canvas canvas) {
        if (mPaint == null) {
            mPaint = new Paint();
        }
        mPaint.setColor(mCenterOutsideCircleColor);
        mPaint.setStrokeWidth(mInsideCircleRadius);
        canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mOutSideCircleRadius, mPaint);
        mPaint.setColor(mCenterInsideCircleColor);
        canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mInsideCircleRadius, mPaint);
    }

    /**
     * 画动画圆
     */
    private void drawAnimCircle(Canvas canvas) {

    }

    /**
     * 绘制斜线
     *
     * @param orientation 方向
     */
    private void drawSpanLine(Canvas canvas, int... orientation) {
        if (orientation == null || orientation.length < 1) {
            return;
        }
        if (mSpanLineAnim == null || (!mIsDrawSpanLineFinish && !mSpanLineAnim.isRunning())) {
            //默认计算左上的斜线
            startAnim(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x - mSpanLength2XY, mCenterPoint.y - mSpanLength2XY, mAnimSpanDuration);
        }
        initLinePaint();
        for (int i : orientation) {
            switch (i) {
                case TOP:
                    canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x, mCenterPoint.y - mCurrentSpanPoint.y, mPaint);
                    break;
                case BOTTOM:
                    canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x, mCenterPoint.y + mCurrentSpanPoint.y, mPaint);
                    break;
                case LEFT_TOP:
                    canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x - mCurrentSpanPoint.x, mCenterPoint.y - mCurrentSpanPoint.y, mPaint);
                    break;
                case LEFT_BOTTOM:
                    canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x - mCurrentSpanPoint.x, mCenterPoint.y + mCurrentSpanPoint.y, mPaint);
                    break;
                case RIGHT_TOP:
                    canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x + mCurrentSpanPoint.x, mCenterPoint.y - mCurrentSpanPoint.y, mPaint);
                    break;
                case RIGHT_BOTTOM:
                    canvas.drawLine(mCenterPoint.x, mCenterPoint.y, mCenterPoint.x + mCurrentSpanPoint.x, mCenterPoint.y + mCurrentSpanPoint.y, mPaint);
                    break;
            }
        }
    }

    /**
     * 拼接内容
     */
    private String combineStr(LabelBean bean) {
        String result = "";
        if (bean != null) {
            if (!(bean.obj == null) && !TextUtils.isEmpty(bean.obj.toString())) {
                result = bean.obj.toString() + " ";
            }
            if (!TextUtils.isEmpty(bean.type)) {
                result += bean.type;
            }
        }
        return result;
    }

    /**
     * 设置标签中心圆在父控件中的百分百位置
     */
    public void setPercentXY(float x, float y) {
        post(new PointAtPercentRunnable(x, y, false));
    }

    /**
     * 加入标签数据数组 并指定在父容器的位置 默认不响应手势
     */
    public void setLabelBean(float parentX, float parentY, int style, LabelBean... beans) {
        if (beans != null && beans.length > 0 && parentX > 0 && parentY > 0 && beans.length <= mMaxLineNum) {
            mDataList = Arrays.asList(beans);
            //根据数据数量设置默认的样式
            mCurrentStyle = style;
            //将标签放置于用户点击的父容器相应的点上
            setViewSize();
            setShow(true);
            post(new PointAtPercentRunnable(parentX, parentY, true));
        }
    }

    /**
     * 移除指定的标签数据
     */
    public void removeLabelBean(LabelEnum tag) {
        if (mDataList != null) {
            LabelBean bean = null;
            for (int i = 0; i < mDataList.size(); i++) {
                bean = mDataList.get(i);
                if (bean.tag == tag) {
                    mDataList.remove(i);
                    invalidate();
                    break;
                }
            }
        }
    }

    /**
     * 获取中心圆的坐标
     */
    public Point geCenterPoint() {
        return mCenterPoint;
    }

    /**
     * 获取控件在父容器中的坐标
     */
    public PointF getPointAt() {
        return mPointAtPc;
    }

    /**
     * 获取当前样式
     */
    public int getCurrentStyle() {
        return mCurrentStyle;
    }

    /**
     * 设置是否展示
     */
    public void setShow(boolean is) {
        mIsShow = is;
    }

    /**
     * 设置当前样式
     */
    public void setCurrentStyle(int style) {
        mCurrentStyle = style;
        setViewSize();
    }

    /**
     * 更改指定的标签数据
     */
    public void changeLabelBean(LabelEnum tag, Objects obj) {
        if (mDataList != null) {
            for (int i = 0; i < mDataList.size(); i++) {
                if (mDataList.get(i).tag == tag) {
                    mDataList.get(i).obj = obj;
                    invalidate();
                    break;
                }
            }
        }
    }

    /**
     * 线条数对应的样式 加入键值对
     */
    private void initStyleMap() {
        if (mOneLineStyleList == null) {
            mOneLineStyleList = new ArrayList<>();
            mOneLineStyleList.add(ONE_LEFT_STYLE);
            mOneLineStyleList.add(ONE_RIGHT_STYLE);
            mOneLineStyleList.add(ONE_LEFT_BOTTOM_SPAN_STYLE);
            mOneLineStyleList.add(ONE_RIGHT_BOTTOM_SPAN_STYLE);
        }
        if (mTwoLineStyleList == null) {
            mTwoLineStyleList = new ArrayList<>();
            mTwoLineStyleList.add(TWO_LEFT_STYLE);
            mTwoLineStyleList.add(TWO_RIGHT_STYLE);
            mTwoLineStyleList.add(TWO_LEFT_SPAN_STYLE);
            mTwoLineStyleList.add(TWO_RIGHT_SPAN_STYLE);
        }
        if (mThreeLineStyleList == null) {
            mThreeLineStyleList = new ArrayList<>();
            mThreeLineStyleList.add(THREE_LEFT_STYLE);
            mThreeLineStyleList.add(THREE_RIGHT_STYLE);
            mThreeLineStyleList.add(THREE_LEFT_TWO_SPAN_RIGHT_SPAN_STYLE);
            mThreeLineStyleList.add(THREE_LEFT_SPAN_RIGHT_TWO_SPAN_STYLE);
        }
        if (mStyleMap == null) {
            mStyleMap = new HashMap<>();
            mStyleMap.put(1, mOneLineStyleList);
            mStyleMap.put(2, mTwoLineStyleList);
            mStyleMap.put(3, mThreeLineStyleList);
        }
        //优先级
        if (mPriorityList == null) {
            mPriorityList = new ArrayList<>();
            mPriorityList.add(FIRST);
            mPriorityList.add(SECOND);
            mPriorityList.add(THIRD);
        }
    }

    /**
     * 改变样式
     */
    private void changeStyle() {
        List<Integer> list = mStyleMap.get(mDataList.size());
        int index = list.indexOf(mCurrentStyle);
        if (index == list.size() - 1) {
            index = 0;
        } else {
            index++;
        }
        int style = list.get(index);
        mCurrentStyle = style;
        reset();
        setViewSize();
        invalidate();
    }

    /**
     * 设置标签在父容器中的位置
     */
    public void setPointAt(float x, float y) {
        if (mCenterPoint == null) mCenterPoint = new Point();


//        int centerXinParent = (int) getX() + mCenterPoint.x;
//        int centerYinParent = (int) getY() + mCenterPoint.y;
        int centerXinParent = (int) ViewCompat.getX(this) + mCenterPoint.x;
        int centerYinParent = (int) ViewCompat.getY(this) + mCenterPoint.y;

        //因为添加了pcX, pcY,更新位置后需要更新
        updatePcXandPcY(centerXinParent, centerYinParent);

//        float oldX = getX();
//        float oldY = getY();
//        float setX = getX() + x - centerXinParent;
//        float setY = getY() + y - centerYinParent;
        float oldX = ViewCompat.getX(this);
        float oldY = ViewCompat.getY(this);
        float setX = ViewCompat.getX(this) + x - centerXinParent;
        float setY = ViewCompat.getY(this) + y - centerYinParent;
        float x2 = 0;
        x2 = mIsAdd ? setX : setX + getWidth() ;
        if (setX <= 0 || x2 >= mParentView.getWidth()) {
            setX = oldX;
        } else {
            mPointAtPc.x = (int) x;
            mPointAtPx.x = x;
        }
        float y2 = 0;
        y2 = mIsAdd ? setY : setY + getHeight() ;
        if (setY <= 0 || y2 >= mParentView.getHeight()) {
            setY = oldY;
        } else {
            mPointAtPc.y = (int) y;
            mPointAtPx.y = y;
        }

//        setX(setX);
//        setY(setY);
        ViewCompat.setX(this , setX);
        ViewCompat.setY(this , setY);
    }

    /**
     * 因为添加了pcX, pcY,更新位置后需要更新
     *
     * @param pxX
     * @param pxY
     */
    private void updatePcXandPcY(float pxX, float pxY) {
        if (getParent() != null) {
            float pw = ((ViewGroup) getParent()).getWidth();
            float ph = ((ViewGroup) getParent()).getHeight();

            if (pw > 0 && ph > 0) {
                if (mPointAtPx == null) mPointAtPx = new PointF();
                mPointAtPx.x = pxX / pw;
                mPointAtPx.y = pxY / ph;
            }
        }
    }

    class PointAtPercentRunnable implements Runnable {

        private float parentX, parentY;
        private boolean pointAt;

        public PointAtPercentRunnable(float x, float y, boolean pointAt) {
            parentX = x;
            parentY = y;
            this.pointAt = pointAt;
        }

        @Override
        public void run() {
            if (getParent() == null) {
                return;
            }
            if (getParent() instanceof RelativeLayout) {
                RelativeLayout p = (RelativeLayout) getParent();
                mParentView = p;
                float pxX, pxY;
                if (pointAt) {
                    pxX = parentX;
                    pxY = parentY;
                } else {
                    pxX = p.getWidth() * parentX;
                    pxY = p.getHeight() * parentY;
                }
                mIsAdd = true ;
                setPointAt(pxX, pxY);
            }
        }
    }

    private void startAnim(final float startX, final float startY, float endX, float endY, final long duration) {
        mSpanLineAnim = ValueAnimator.ofObject(new SpanEvaluator(), new Point((int) startX, (int) startY), new Point((int) endX, (int) endY));
        mSpanLineAnim.setDuration(duration);
        mSpanLineAnim.setRepeatCount(0);
        mSpanLineAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                Point pointF = (Point) animation.getAnimatedValue();
                if (mCurrentSpanPoint == null) mCurrentSpanPoint = new Point();
                mCurrentSpanPoint.x = Math.abs(pointF.x);
                mCurrentSpanPoint.y = Math.abs(pointF.y);
                invalidate();
            }
        });
        mSpanLineAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsDrawSpanLineFinish = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mIsDrawSpanLineFinish = true;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mSpanLineAnim.start();
    }

    class SpanEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue,
                              Point endValue) {
            Point point = new Point();
            point.x = (int) Math.abs(fraction * (startValue.x - endValue.x));
            point.y = (int) Math.abs(fraction * (startValue.y - endValue.y));
            return point;
        }
    }

    private int lastX;
    private int lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mIsShow) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = mPointAtPc.x + event.getX() - lastX;
                float y = mPointAtPc.y + event.getY() - lastY;
                mIsAdd = false;
                setPointAt(x, y);

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                float upX = event.getX();
                float upY = event.getY();
                double distance = Math.sqrt(Math.abs(upX - lastX)
                        * Math.abs(upX - lastX)
                        + Math.abs(upY - lastY)
                        * Math.abs(upY - lastY));//两点之间的距离
                if (distance < 15 && isCenterClick(lastX, lastY)) { // 距离较小，当作click事件来处理
                    changeStyle();
                } else if (mTextInfoList != null) {
                    for (TextInfo info : mTextInfoList) {
                        if (info.rectF != null && info.rectF.contains(lastX, lastY)) {
                            Toast.makeText(getContext(), "" + info.content + " tag = " + info.tag, Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int dotLeft = mCenterPoint.x - mInsideCircleRadius;
        int dotTop = mCenterPoint.y - mInsideCircleRadius;
        int dotRight = dotLeft + 2 * mInsideCircleRadius;
        int dotBottom = dotTop + 2 * mInsideCircleRadius;
        iv_anim.layout(dotLeft, dotTop, dotRight, dotBottom);
        startCenterScaleAnim();
    }

    /**
     * 判断是否点击中心
     */
    public boolean isCenterClick(float xInParent, float yInParent) {
        return mCenterRect.contains((int) (xInParent), (int) (yInParent));
    }

    /**
     * 开始绘制直线动画
     */
    private void startLineAnim(final float startX, float startY, final float endX, final float endY, final Point point, final int style) {
        ValueAnimator animator = null;
        switch (style) {
            case LEFT:
                mLeftAnim = ValueAnimator.ofObject(new LineEvaluator(), new Point((int) startX, (int) startY), new Point((int) endX, (int) endY));
                animator = mLeftAnim;
                break;
            case RIGHT:
                mRightAnim = ValueAnimator.ofObject(new LineEvaluator(), new Point((int) startX, (int) startY), new Point((int) endX, (int) endY));
                animator = mRightAnim;
                break;
            case LEFT_TOP:
                mLeftTopAnim = ValueAnimator.ofObject(new LineEvaluator(), new Point((int) startX, (int) startY), new Point((int) endX, (int) endY));
                animator = mLeftTopAnim;
                break;
            case LEFT_BOTTOM:
                mLeftBottomAnim = ValueAnimator.ofObject(new LineEvaluator(), new Point((int) startX, (int) startY), new Point((int) endX, (int) endY));
                animator = mLeftBottomAnim;
                break;
            case RIGHT_TOP:
                mRightTopAnim = ValueAnimator.ofObject(new LineEvaluator(), new Point((int) startX, (int) startY), new Point((int) endX, (int) endY));
                animator = mRightTopAnim;
                break;
            case RIGHT_BOTTOM:
                mRightBottomAnim = ValueAnimator.ofObject(new LineEvaluator(), new Point((int) startX, (int) startY), new Point((int) endX, (int) endY));
                animator = mRightBottomAnim;
                break;
            default:
                return;
        }
        if (animator == null) return;
        final int duration = ((int) Math.abs(startX - endX)) * 3;
        animator.setDuration(duration);
        animator.setRepeatCount(0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                Point pointF = (Point) animation.getAnimatedValue();
                point.x = (pointF.x);
                point.y = (pointF.y);
                switch (style) {
                    case LEFT:
                    case LEFT_TOP:
                    case LEFT_BOTTOM:
                        point.x = (int) (startX - pointF.x);
                        break;
                    case RIGHT:
                    case RIGHT_TOP:
                    case RIGHT_BOTTOM:
                        point.x = (int) (startX + pointF.x);
                        break;
                    default:
                        return;
                }
                invalidate();
            }
        });
        animator.start();
    }

    class LineEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue,
                              Point endValue) {
            Point point = new Point();
            point.x = (int) (fraction * Math.abs(startValue.x - endValue.x));
            point.y = endValue.y;

            return point;
        }
    }

    private void setTextInfoList() {
        if (mTextInfoList == null) {
            mTextInfoList = new ArrayList<>();
        }
        mTextInfoList.clear();
        for (LabelEnum tag : mPriorityList) {
            for (LabelBean bean : mDataList) {
                if (bean.tag == tag) {
                    TextInfo info = new TextInfo();
                    info.tag = tag;
                    if (mTextRect == null) mTextRect = new Rect();
                    initTextPaint();
                    String str = combineStr(bean);
                    mPaint.getTextBounds(str, 0, str.length(), mTextRect);
                    info.lineWidth = mTextRect.width() + mTextWidthAddValue + mOutSideCircleRadius;
                    info.textWidth = mTextRect.width();
                    info.textHeight = mTextRect.height();
                    info.content = str;
                    mTextInfoList.add(info);
                    break;
                }
            }
        }
    }

    public List<LabelBean> getDataForList(){
        return mDataList;
    }

    public LabelBean[] getDataForArr(){
        if(mDataList != null && mDataList.size() > 0){
            return (LabelBean[])mDataList.toArray(new LabelBean[mDataList.size()]);
        }
        return null;
    }


    private static final int ANIMATION_EACH_OFFSET = 400;
    private static final float ANIMATION_SCALE_MULTIPLE = 2.0f;

    /**
     * 开始中心圆的动画
     */
    private void startCenterScaleAnim() {
        AnimationSet as = new AnimationSet(true);
        ScaleAnimation sa = new ScaleAnimation(1f, ANIMATION_SCALE_MULTIPLE, 1f, ANIMATION_SCALE_MULTIPLE, ScaleAnimation.RELATIVE_TO_SELF,
                0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(ANIMATION_EACH_OFFSET * 3);
        sa.setRepeatCount(Animation.INFINITE);// 设置循环
        AlphaAnimation aniAlp = new AlphaAnimation(1, 0.1f);
        aniAlp.setRepeatCount(Animation.INFINITE);// 设置循环
        as.setDuration(ANIMATION_EACH_OFFSET * 3);
        as.addAnimation(sa);
        as.addAnimation(aniAlp);
        iv_anim.startAnimation(as);
    }

    private List<TextInfo> mTextInfoList;

    private class TextInfo {
        String content;
        int textWidth;
        int textHeight;
        int lineWidth;
        LabelEnum tag;
        RectF rectF;
    }
}
