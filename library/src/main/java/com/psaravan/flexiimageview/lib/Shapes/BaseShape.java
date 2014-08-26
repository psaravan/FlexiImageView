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

import android.graphics.Bitmap;

/**
 * Abstract class for shape transformations. Kinda useless for now, but
 * will most likely be expanded for future updates/feature additions.
 *
 * @author Saravan Pantham
 */
public abstract class BaseShape {

    /**
     * Applies the appropriate shape to the specified bitmap. Use this method signature to
     * draw ovals.
     *
     * @param bitmap The bitmap to apply the shape transformation on.
     * @param verticalRadiusFactor This value is multiplied by the height of the source image to
     *                             calculate the height of the image from the center of the image to
     *                             the top boundary.
     * @param horizontalRadiusFactor This value is multiplied by the width of the source image to
     *                               calculate the width of the image from the center of the image
     *                               to the side boundary.
     * @return The bitmap after applying the shape transformation.
     */
    public abstract Bitmap applyShape(Bitmap bitmap, float verticalRadiusFactor,
                                      float horizontalRadiusFactor);

    /**
     * Applies the appropriate shape to the specified bitmap. Use this method to prevent
     * the user from tampering with the corner radii.
     *
     * @param bitmap The bitmap to apply the shape transformation on.
     * @return The bitmap after applying the shape transformation.
     */
    public abstract Bitmap applyShape(Bitmap bitmap);

}
