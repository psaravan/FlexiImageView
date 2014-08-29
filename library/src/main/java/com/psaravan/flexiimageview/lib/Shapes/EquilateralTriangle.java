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
package com.psaravan.flexiimageview.lib.Shapes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.view.View;

import com.psaravan.flexiimageview.lib.View.FlexiImageView;

/**
 * Contains methods to perform a triangle shape transformation.
 *
 * @author Saravan Pantham
 */
public class EquilateralTriangle extends BaseShape {

    private Context mContext;
    private FlexiImageView mView;

    public EquilateralTriangle(Context context, FlexiImageView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public Bitmap applyShape(Bitmap bitmap, float verticalRadiusFactor, float horizontalRadiusFactor) {
        //Not used for this shape.
        return null;
    }

    @Override
    public Bitmap applyShape(Bitmap bitmap) {

        //Calculate the width/height to use for the triangle
        int width;
        int height;

        if (bitmap.getHeight() > bitmap.getWidth()) {
            width = bitmap.getWidth();
            height = width;
        } else {
            width = bitmap.getHeight();
            height = width;
        }

        //Increase the size of the final bitmap to account for the shadow.
        int widthWithShadow = width;
        int heightWithShadow = height;
        if (mView.isShadowEnabled()) {
            widthWithShadow += (int) mView.getShadowRadius();
            heightWithShadow += (int) mView.getShadowRadius();

        }

        Bitmap output = Bitmap.createBitmap(widthWithShadow, heightWithShadow,
                                            Bitmap.Config.ARGB_8888);
        mView.setCanvas(new Canvas(output));

        //Calculate the coordinates of the triangle's vertices.
        int x1, x2, x3;
        int y1, y2, y3;

        //Vertex 1 (top).
        x1 = width/2;
        y1 = 0;

        //Vertex 2 (bottom left).
        x2 = 0;
        y2 = height;

        //Vertex 3 (bottom right).
        x3 = width;
        y3 = height;

        mView.setPaint(new Paint());
        mView.setPath(new Path());

        mView.setBitmapShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                              Shader.TileMode.CLAMP));
        mView.getPaint().setStyle(Paint.Style.FILL);
        mView.getPaint().setShader(mView.getBitmapShader());

        mView.getPath().reset();
        mView.getPath().setFillType(Path.FillType.EVEN_ODD);
        mView.getPath().moveTo(x1, y1);
        mView.getPath().lineTo(x2, y2);
        mView.getPath().lineTo(x3, y3);
        mView.getPath().close();

        //Check if we need to draw a shadow.
        if (mView.isShadowEnabled())
            applyShadow();

        //Draw a path cutout from the overall bitmap.
        mView.getCanvas().drawPath(mView.getPath(), mView.getPaint());

        return output;
    }

    /**
     * Draws a shadow under the primary image layer.
     */
    public void applyShadow() {
        mView.setShadowPaint(new Paint());
        mView.getShadowPaint().setShader(mView.getBitmapShader());
        mView.getShadowPaint().setColor(Color.WHITE);
        mView.getShadowPaint().setShadowLayer(mView.getShadowRadius(),
                                              mView.getShadowDx(),
                                              mView.getShadowDy(),
                                              mView.getShadowColor());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            mView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //Draw the shadow.
        mView.getCanvas().drawPath(mView.getPath(), mView.getShadowPaint());

    }

}
