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
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;

/**
 * Contains methods to perform a triangle shape transformation.
 *
 * @author Saravan Pantham
 */
public class EquilateralTriangle extends BaseShape {

    private Context mContext;

    public EquilateralTriangle(Context context) {
        mContext = context;
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

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

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

        Paint paint = new Paint();
        Path path = new Path();

        BitmapShader bms = new BitmapShader(bitmap, Shader.TileMode.REPEAT , Shader.TileMode.REPEAT  );
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(bms);

        path.reset();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.close();

        canvas.drawPath(path, paint);
        paint.setShader(null);

        return output;
    }

}
