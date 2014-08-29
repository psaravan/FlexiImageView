/*
 * Copyright (C) 2014 Saravan Pantham
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.psaravan.flexiimageview.lib.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.psaravan.flexiimageview.lib.Shapes.ShapeHelper;

/**
 * View class for FlexiImageView
 *
 * @author Saravan Pantham
 */
public class FlexiImageView extends ImageView {

    //Context.
    private Context mContext;

    //Image bitmap properties.
    private Bitmap mBitmap;
    private Paint mPaint;
    private Paint mShadowPaint;
    private Path mPath;
    private Canvas mCanvas;
    private BitmapShader mBitmapShader;
    private int mRectWidth;
    private int mRectHeight;

    //Feature flags.
    private int mShape = -1;
    private boolean mBlur = false;
    private boolean mBorder = false;
    private boolean mMultiImage = false;
    private boolean mShadow = false;

    //Shape constants.
    public static final int SHAPE_RECTANGLE = 0;
    public static final int SHAPE_EQUILATERAL_TRIANGLE = 1;
    public static final int SHAPE_CIRCLE = 2;
    public static final int SHAPE_OVAL = 3;

    //Shape parameters.
    private float mShapeCornerRadiusFactor = 0;
    private float mOvalVerticalRadiusFactor = 0;
    private float mOvalHorizontalRadiusFactor = 0;

    //Shadow parameters.
    private float mShadowRadius = 0.0f;
    private float mShadowDx = 0.0f;
    private float mShadowDy = 0.0f;
    private int mShadowColor = 0x00000000;

    public FlexiImageView(Context context) {
        super(context);
        mContext = context;

    }

    public FlexiImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mContext = context;

    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        //Check to make sure a valid bitmap was passed in.
        if (bitmap==null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            super.setImageBitmap(null);
            return;
        }

        mBitmap = bitmap;
    }

    @Override
    public void setImageResource(int resourceId) {
        //Check to make sure a valid resource id was passed in.
        if (resourceId <= 0) {
            super.setImageBitmap(null);
            return;
        }

        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), resourceId);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {

        /*
         * Need to call the superclass implementation because
         * setImageBitmap() indirectly relies on this method.
         */
        super.setImageDrawable(drawable);

        //Check to make sure a valid drawable was passed in.
        if (drawable==null) {
            super.setImageDrawable(drawable);
            return;
        }

        mBitmap = ((BitmapDrawable) drawable).getBitmap();
    }

    /**
     * Determines which effects have been enabled by the user, applies those effects to the
     * view's image, and then displays the image.
     *
     * @throws java.lang.IllegalStateException Exception thrown if
     *                                         {@link #setImageBitmap(android.graphics.Bitmap)},
     *                                         {@link #setImageDrawable(android.graphics.drawable.Drawable)},
     *                                         or {@link #setImageResource(int)} is not called before
     *                                         calling this method.
     */
    public void draw() throws IllegalStateException {

        if (mBitmap==null)
            throw new IllegalStateException("You must set an image source (bitmap, drawable, or " +
                                            "drawable resource before calling draw().");

        //Apply the correct shape transformation to the bitmap.
        if (mShape > -1) {
            ShapeHelper shapeHelper = new ShapeHelper(mContext, this);
            mBitmap = shapeHelper.applyShape(mBitmap, mShape, mShapeCornerRadiusFactor,
                                             mOvalVerticalRadiusFactor, mOvalHorizontalRadiusFactor);

        }

        //Allow the superclass implementation to display the final bitmap.
        super.setImageBitmap(mBitmap);
    }

    /**
     * Sets the shape of the ImageView.
     *
     * @param shape Use one of the following shapes: {@link #SHAPE_RECTANGLE}, {@link #SHAPE_CIRCLE},
     *              {@link #SHAPE_EQUILATERAL_TRIANGLE}, {@link #SHAPE_OVAL}.
     *
     * @throws IllegalArgumentException Exception thrown if an invalid shape is passed in.
     * @return Returns this instance to allow method chaining.
     */
    public FlexiImageView setShape(int shape) throws IllegalArgumentException {
        if (shape > 3 || shape < 0)
            throw new IllegalArgumentException("Invalid shape passed in. Must use one of the " +
                                               "predefined, static shape constants.");

        mShape = shape;
        return this;
    }

    /**
     * Sets whether the ImageView's image should be blurred or not.
     *
     * @param blur Pass true to blur the ImageView's image. False otherwise.
     * @return Returns this instance to allow method chaining.
     */
    public FlexiImageView setBlur(boolean blur) {
        mBlur = blur;
        return this;
    }

    /**
     * Sets whether the ImageView should have a border drawn around it. Make sure you call
     * {@link #setBorderDrawable(Drawable drawable)} or this method will have no effect.
     *
     * @param border Pass true to draw a border around the ImageView. False otherwise.
     * @return Returns this instance to allow method chaining.
     */
    public FlexiImageView setBorder(boolean border) {
        mBorder = border;
        return this;
    }

    /**
     * Sets whether the ImageView will have multiple images drawn inside it. Make sure you call
     * {@link #setMultiImageBitmaps(android.graphics.Bitmap[])} or this method will have no effect.
     *
     * @param multiImage Pass true to enable a multi-image view (à la Facebook chat heads).
     * @return Returns this instance to allow method chaining.
     */
    public FlexiImageView setMultiImage(boolean multiImage) {
        mMultiImage = multiImage;
        return this;
    }

    /**
     * Sets whether the ImageView will have multiple images drawn inside it. Make sure you call
     * {@link #setMultiImageBitmaps(android.graphics.Bitmap[])} or this method will have no effect.
     *
     * @param shadow Pass true to show a shadow under the View.
     * @param shadowRadius The radius/size of the drop shadow. Pass {@code 0.0f} if you passed
     *                     false for dropShadow.
     * @param dx The horizontal offset of the shadow behind the view.
     * @param dy The vertical offset of the shadow behind the view.
     * @param shadowColor The color of the drop shadow. Pass {@code 0} if you passed false for
     *                    dropShadow.
     *
     * @return Returns this instance to allow method chaining.
     *
     */
    public FlexiImageView setShadow(boolean shadow, float shadowRadius,
                                    float dx, float dy, int shadowColor) {
        mShadow = shadow;
        mShadowRadius = shadowRadius;
        mShadowColor = shadowColor;
        mShadowDx = dx;
        mShadowDy = dy;

        return this;
    }

    /**
     * Sets the drawable to use as the ImageView's border.
     *
     * @param drawable The drawable to use for the ImageView's border.
     * @throws java.lang.IllegalStateException Exception thrown if {@code setBorder(true)} is
     *                                         not called before calling this method.
     * @return Returns this instance to allow method chaining.
     */
    public FlexiImageView setBorderDrawable(Drawable drawable) {
        if (mBorder==false)
            throw new IllegalStateException("setBorder() was not called or was set to false! " +
                                            "Call setBorder(true) before calling this method.");


        return this;
    }

    /**
     * Sets the image bitmaps to display within the ImageView.
     *
     * @param bitmaps An array of bitmaps that will be displayed by this ImageView.
     * @throws java.lang.IllegalStateException Exception thrown if {@code setMultiImage(true)}
     *                                         is not called before calling this method.
     * @return Returns this instance to allow method chaining.
     */
    public FlexiImageView setMultiImageBitmaps(Bitmap[] bitmaps) {
        if (mMultiImage==false)
            throw new IllegalStateException("setMultiImage(true) was not called or was set to false! " +
                                            "Call setMultiImage(true) before calling this method.");


        return this;
    }

    /**
     * Sets the vertical radius of the oval shape. This method has no effect unless
     * {@link #setShape(int)} is called and has {@link #SHAPE_OVAL} passed in as a parameter.
     *
     * @param verticalRadiusFactor Used to calculate the curvature above and below the center.
     *                             Higher values will result in a larger curvature, while smaller
     *                             values will create a linear/sharp curve.
     *
     * @return This FlexiImageView instance to allow method chaining.
     */
    public FlexiImageView setOvalVerticalRadius(float verticalRadiusFactor) {
        if (getShape()==SHAPE_OVAL)
            mOvalVerticalRadiusFactor = verticalRadiusFactor;

        return this;
    }

    /**
     * Sets the horizontal radius of the oval shape. This method has no effect unless
     * {@link #setShape(int)} is called and has {@link #SHAPE_OVAL} passed in as a parameter.
     *
     * @param horizontalRadiusFactor Used to calculate the curvature to the sides of the center.
     *                               Higher values will result in a larger curvature, while smaller
     *                               values will create a linear/sharp curve.
     *
     * @return This FlexiImageView instance to allow method chaining.
     */
    public FlexiImageView setOvalHorizontalRadius(float horizontalRadiusFactor) {
        if (getShape()==SHAPE_OVAL)
            mOvalHorizontalRadiusFactor = horizontalRadiusFactor;

        return this;
    }


    /**
     * Public getter methods.
     */
    public float getOvalVerticalRadiusFactor() {
        return mOvalVerticalRadiusFactor;
    }

    public float getOvalHorizontalRadiusFactor() {
        return mOvalHorizontalRadiusFactor;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public int getShape() {
        return mShape;
    }

    public Paint getPaint() {
        return mPaint;
    }

    public Paint getShadowPaint() {
        return mShadowPaint;
    }

    public Canvas getCanvas() {
        return mCanvas;
    }

    public Path getPath() {
        return mPath;
    }

    public BitmapShader getBitmapShader() {
        return mBitmapShader;
    }

    public float getShadowDx() {
        return mShadowDx;
    }

    public float getShadowDy() {
        return mShadowDy;
    }

    public float getShadowRadius() {
        return mShadowRadius;
    }

    public int getShadowColor() {
        return mShadowColor;
    }

    public int getRectWidth() {
        return mRectWidth;
    }

    public int getRectHeight() {
        return mRectHeight;
    }

    public boolean isBlurEnabled() {
        return mBlur;
    }

    public boolean isBorderEnabled() {
        return mBorder;
    }

    public boolean isMultiImage() {
        return mMultiImage;
    }

    public boolean isShadowEnabled() {
        return mShadow;
    }

    /**
     * Public setter methods.
     */
    public void setRectWidth(int rectWidth) {
        mRectWidth = rectWidth;
    }

    public void setRectHeight(int rectHeight) {
        mRectHeight = rectHeight;
    }

    public void setCanvas(Canvas canvas) {
        mCanvas = canvas;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public void setShadowPaint(Paint shadowPaint) {
        mShadowPaint = shadowPaint;
    }

    public void setPath(Path path) {
        mPath = path;
    }

    public void setBitmapShader(BitmapShader bitmapShader) {
        mBitmapShader = bitmapShader;
    }

}
