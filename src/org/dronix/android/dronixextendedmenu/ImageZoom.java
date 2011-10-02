package org.dronix.android.dronixextendedmenu;

/**
 * User: harlem88
 * Date: 9/29/11
 * Time: 12:52 AM
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class ImageZoom extends ImageView {

    public ImageZoom(Context context) {
        super(context);
    super.setClickable(true);
    }
 @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        bmWidth = bm.getWidth();
        bmHeight = bm.getHeight();
    }

    public void setMaxZoom(float x)
    {
     maxScale = x;
    }
private  float bmWidth, bmHeight;
private float maxScale = 2.5f;
}
