package it.pietroterracciano.kudos.Utils.Views;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public abstract class ImageViewUtils
{
    public static boolean setDrawable(ImageView iv, Drawable drw)
    {
        if(iv != null && drw != null)
            try { iv.setImageDrawable(drw); return true; } catch (Exception ignored) {}

        return false;
    }
}
