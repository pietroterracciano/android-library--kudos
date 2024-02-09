package it.pietroterracciano.kudos.Utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.EVProperty;


public abstract class ViewUtils extends BaseViewUtils
{
    public static boolean setWidth(@Nullable View v, int i)
    {
        return setProperty(v, EVProperty.Width, i);
    }
    public static boolean setHeight(@Nullable View v, int i)
    {
        return setProperty(v, EVProperty.Height, i);
    }
    public static boolean setMarginBottom(@Nullable View v, int i)
    {
        return setProperty(v, EVProperty.MarginBottom, i);
    }
    public static boolean setMarginEnd(@Nullable View v, int i)
    {
        return setProperty(v, EVProperty.MarginEnd, i);
    }
    public static boolean setMarginLeft(@Nullable View v, int i)
    {
        return setProperty(v, EVProperty.MarginLeft, i);
    }
    public static boolean setMarginStart(@Nullable View v, int i)
    {
        return setProperty(v, EVProperty.MarginStart, i);
    }
    public static boolean setMarginRight(@Nullable View v, int i)
    {
        return setProperty(v, EVProperty.MarginRight, i);
    }
    private static boolean setProperty(@Nullable View v, EVProperty e, int i)
    {
        if(
            v == null
            || !setProperty(v.getLayoutParams(), e, i)
        )
            return false;

        v.requestLayout();
        return true;
    }

    @Nullable
    public static Integer getWidth(@Nullable View v)
    {
        return getProperty(v, EVProperty.Width);
    }
    @Nullable
    public static Integer getHeight(@Nullable View v)
    {
        return getProperty(v, EVProperty.Height);
    }
    @Nullable
    public static Integer getMarginBottom(@Nullable View v)
    {
        return getProperty(v, EVProperty.MarginBottom);
    }
    @Nullable
    public static Integer getMarginEnd(@Nullable View v)
    {
        return getProperty(v, EVProperty.MarginEnd);
    }
    @Nullable
    public static Integer getMarginLeft(@Nullable View v)
    {
        return getProperty(v, EVProperty.MarginLeft);
    }
    @Nullable
    public static Integer getMarginStart(@Nullable View v)
    {
        return getProperty(v, EVProperty.MarginStart);
    }
    @Nullable
    public static Integer getMarginRight(@Nullable View v)
    {
        return getProperty(v, EVProperty.MarginRight);
    }
    @Nullable
    private static Integer getProperty(@Nullable View v, EVProperty e)
    {
        return v != null ? getProperty(v.getLayoutParams(), e) : null;
    }
}