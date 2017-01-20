package com.hyena.coretext.blocks;

import android.graphics.Canvas;

/**
 * Created by yangzc on 16/4/8.
 */
public class CYLineBlock extends CYBlock<CYBlock> {

    private int mLineHeight;

    public CYLineBlock() {
        super(null, "");
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return getLineHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        if (getChildren() != null) {
            for (int i = 0; i < getChildren().size(); i++) {
                getChildren().get(i).draw(canvas);
            }
        }
    }

    @Override
    public int getLineHeight() {
        if (mLineHeight <= 0) {
            measure();
        }
        if (mLineHeight <= 0) {
            mLineHeight = 100;
        }
        return mLineHeight;
    }

    public void measure() {
        measureLineHeight();
        syncBlocksHeight();
    }

    public void updateLineY(int lineY){
        if (getChildren() != null) {
            for (int i = 0; i < getChildren().size(); i++) {
                getChildren().get(i).setLineY(lineY);
            }
        }
    }

    private void measureLineHeight(){
        if (getChildren() != null) {
            mLineHeight = 0;
            for (int i = 0; i < getChildren().size(); i++) {
                CYBlock block = getChildren().get(i);
                if (block instanceof CYTextBlock || (block instanceof CYPlaceHolderBlock
                        && ((CYPlaceHolderBlock)block).getAlignStyle() == CYPlaceHolderBlock
                        .AlignStyle.Style_Single_Line)) {
                    if (block.getHeight() > mLineHeight) {
                        mLineHeight = block.getHeight();
                    }
                }
            }
            if (mLineHeight <= 0) {
                mLineHeight = getMaxBlockHeightInLine();
            }
        }
    }

    public int getMaxBlockHeightInLine(){
        int maxHeight = 0;
        if (getChildren() != null) {
            for (int i = 0; i < getChildren().size(); i++) {
                CYBlock block = getChildren().get(i);
                if (block.getHeight() > maxHeight) {
                    maxHeight = block.getHeight();
                }
            }
        }
        return maxHeight;
    }

    private void syncBlocksHeight(){
        if (getChildren() != null) {
            for (int i = 0; i < getChildren().size(); i++) {
                CYBlock block = getChildren().get(i);
                block.setLineHeight(mLineHeight);
            }
        }
    }

}
