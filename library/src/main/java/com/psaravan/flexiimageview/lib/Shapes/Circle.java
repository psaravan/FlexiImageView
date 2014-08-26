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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

/**
 * Contains methods to perform a circle shape transformation.
 *
 * @author Saravan Pantham
 */
public class Circle extends BaseShape {

    private Context mContext;

    public Circle(Context context) {
        mContext = context;
    }

    @Override
    public Bitmap applyShape(Bitmap bitmap, float verticalRadiusFactor,
                             float horizontalRadiusFactor) {
        //Not used for circles. See applyShape(Bitmap bitmap).
        return null;
    }

    @Override
    public Bitmap applyShape(Bitmap bitmap) {

        //Calculate the center coordinates/width/height to use for the circle.
        int centerCoordinates;
        int width;
        int height;

        if (bitmap.getHeight() > bitmap.getWidth()) {
            centerCoordinates = bitmap.getWidth()/2;
            width = bitmap.getWidth();
            height = width;
        } else {
            centerCoordinates = bitmap.getHeight()/2;
            width = bitmap.getHeight();
            height = width;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(centerCoordinates, centerCoordinates, centerCoordinates, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

}
