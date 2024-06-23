package it.pietroterracciano.kudos.Utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Kudos;

public abstract class ScreenUtils
{
    @Nullable
    public static Point getSize()
    {
        return getSize(Kudos.getContext());
    }
    @Nullable
    public static Point getSize(@Nullable Context cnt)
    {
        if(cnt == null)
            return  null;

        WindowManager wm = ServiceUtils.getFromSystem(cnt, Context.WINDOW_SERVICE);
        if(wm == null)
            return null;

        Display dsp = wm.getDefaultDisplay();
        if(dsp == null)
            return null;

        Point p = new Point();
        dsp.getSize(p);
        return p;
    }

    public static int getWidth()
    {
        return getWidth(Kudos.getContext());
    }
    public static int getWidth(@Nullable Context cnt)
    {
        if(cnt == null)
            return 0;

        Point p = getSize(cnt);

        if(p == null)
            return 0;

        return p.x;
    }

    public static int getHeight()
    {
        return getHeight(Kudos.getContext());
    }
    public static int getHeight(@Nullable Context cnt)
    {
        if(cnt == null)
            return 0;

        Point p = getSize(cnt);

        if(p == null)
            return 0;

        return p.y;
    }
}
