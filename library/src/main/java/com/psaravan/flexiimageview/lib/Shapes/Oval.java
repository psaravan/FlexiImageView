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
import android.graphics.RectF;

/**
 * Contains methods to perform an oval shape transformation.
 *
 * @author Saravan Pantham
 */
public class Oval extends BaseShape {

    private Context mContext;

    public Oval(Context context) {
        mContext = context;
    }

    @Override
    public Bitmap applyShape(Bitmap bitmap, float verticalRadiusFactor,
                             float horizontalRadiusFactor) {

        //Calculate the radii based on the input factors.
        float verticalRadius = (bitmap.getHeight()/2) * verticalRadiusFactor;
        float horizontalRadius = (bitmap.getWidth()/2) * horizontalRadiusFactor;

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                                            Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, verticalRadius, horizontalRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;

    }

    @Override
    public Bitmap applyShape(Bitmap bitmap) {
        //Not used for ovals (we need a corner radius).
        return null;
    }

}
