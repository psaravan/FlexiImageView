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
package com.psaravan.flexiimageview.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Base View implementation class for FlexiImageView
 *
 * @author Saravan Pantham
 */
public class FlexiImageView extends ImageView {

    //Context.
    private Context mContext;

    //Feature flags.
    private int mShape = -1;
    private boolean mBlur = false;
    private boolean mBorder = false;
    private boolean mMultiImage = false;

    //Shape constants.
    public static final int SHAPE_RECTANGLE = 0;
    public static final int SHAPE_TRIANGLE = 1;
    public static final int SHAPE_CIRCLE = 2;
    public static final int SHAPE_OVAL = 3;

    public FlexiImageView(Context context) {
        super(context);
        mContext = context;
        setShape(SHAPE_RECTANGLE);

    }

    public FlexiImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mContext = context;
        setShape(SHAPE_RECTANGLE);

    }

    /**
     * Sets the shape of the ImageView.
     *
     * @param shape Use one of the following shapes: {@link #SHAPE_RECTANGLE}, {@link #SHAPE_CIRCLE},
     *              {@link #SHAPE_TRIANGLE}, {@link #SHAPE_OVAL}.
     *
     * @throws IllegalArgumentException Exception thrown if an invalid shape is passed in.
     */
    public void setShape(int shape) throws IllegalArgumentException {
        if (shape > 3 || shape < 0)
            throw new IllegalArgumentException("Invalid shape passed in. Must use one of the " +
                                               "predefined, static shape constants.");

        mShape = shape;

    }

    /**
     * Sets whether the ImageView's image should be blurred or not.
     *
     * @param blur Pass true to blur the ImageView's image. False otherwise.
     */
    public void setBlur(boolean blur) {

    }

    /**
     * Sets whether the ImageView should have a border drawn around it. Make sure you call
     * {@link #setBorderDrawable(Drawable drawable)} or this method will have no effect.
     *
     * @param border Pass true to draw a border around the ImageView. False otherwise.
     */
    public void setBorder(boolean border) {

    }

    /**
     * Sets whether the ImageView will have multiple images drawn inside it. Make sure you call
     * {@link #setMultiImageBitmaps(android.graphics.Bitmap[])} or this method will have no effect.
     *
     * @param multiImage Pass true to indicate that
     */
    public void setMultiImage(boolean multiImage) {

    }

    /**
     * Sets the drawable to use as the ImageView's border.
     *
     * @param drawable The drawable to use for the ImageView's border.
     * @throws java.lang.IllegalStateException Exception thrown if {@code setBorder(true)} is
     *                                         not called before calling this method.
     */
    public void setBorderDrawable(Drawable drawable) {
        if (mBorder==false)
            throw new IllegalStateException("setBorder() was not called or was set to false! " +
                                            "Call setBorder(true) before calling this method.");

    }

    /**
     * Sets the image bitmaps to display within the ImageView.
     *
     * @param bitmaps An array of bitmaps that will be displayed by this ImageView.
     * @throws java.lang.IllegalStateException Exception thrown if {@code setMultiImage(true)}
     *                                         is not called before calling this method.
     */
    public void setMultiImageBitmaps(Bitmap[] bitmaps) {
        if (mMultiImage==false)
            throw new IllegalStateException("setMultiImage() was not called or was set to false! " +
                                            "Call setMultiImage(true) before calling this method.");

    }



}
