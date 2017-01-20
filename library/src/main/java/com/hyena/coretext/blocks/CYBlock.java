package com.hyena.coretext.blocks;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.hyena.coretext.TextEnv;
import com.hyena.coretext.event.CYEventDispatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/8.
 */
public abstract class CYBlock<T> {

    private static final String TAG = "CYBlock";
    //当前块横坐标
    private int x;
    //当前行上边界
    private int lineY;
    //当前行高度
    private int lineHeight;
    //是否存在焦点
    private boolean mFocus = false;
    //内容范围
    private Rect mRect = new Rect();
    //所有子节点
    private List<T> mChildren = new ArrayList<T>();
    private TextEnv mTextEnv;

    public CYBlock(TextEnv textEnv, String content) {
        this.mTextEnv = textEnv;
    }

    public TextEnv getTextEnv() {
        return mTextEnv;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * @param lineY top y
     */
    public void setLineY(int lineY) {
        this.lineY = lineY;
    }

    /**
     * @return current line top y
     */
    public int getLineY() {
        return lineY;
    }

    /**
     * @param lineHeight current line height
     */
    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    /**
     * 获得前行高度
     * @return 高度
     */
    public int getLineHeight() {
        return lineHeight;
    }

    /**
     * @return width of block
     */
    public abstract int getWidth();

    /**
     * @return height of block
     */
    public abstract int getHeight();

    /**
     * draw block
     * @param canvas canvas
     */
    public abstract void draw(Canvas canvas);

    /**
     * add child
     * @param child child block
     */
    public void addChild(T child) {
        if (mChildren == null)
            mChildren = new ArrayList<T>();
        mChildren.add(child);
    }

    /**
     * @return child of block
     */
    public List<T> getChildren() {
        return mChildren;
    }

    /**
     * @return rect of block
     */
    public Rect getContentRect() {
        mRect.set(x, lineY, x + getWidth(), lineY + getHeight());
        return mRect;
    }

    public void onTouchEvent(int event, float x, float y) {
        debug("onEvent: " + event);
    }

    /**
     * @param focus mark force or not
     */
    public void setFocus(boolean focus) {
        mFocus = focus;
        debug("rect: " + getContentRect().toString() + ", focus: " + focus);
    }

    /**
     * @return force or not
     */
    public boolean isFocus(){
        return mFocus;
    }

    /**
     * relayout
     */
    public void requestLayout() {
        CYEventDispatcher.getEventDispatcher().requestLayout();
    }

    /**
     * force relayout
     * @param force force or not
     */
    public void requestLayout(boolean force) {
        CYEventDispatcher.getEventDispatcher().requestLayout(force);
    }

    /**
     * reDraw
     */
    public void postInvalidate() {
        CYEventDispatcher.getEventDispatcher().postInvalidate();
    }

    protected void debug(String msg) {
        Log.v(TAG, msg);
    }
}
