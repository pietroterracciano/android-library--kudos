package it.pietroterracciano.kudos.Utils;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class DrawableUtils
{
    @Nullable
    public static Drawable getFromResources(@NonNull @DrawableRes int i)
    {
        return ResourcesUtils.getDrawable(i);
    }
    @Nullable
    public static Drawable getFromResources(@NonNull @DrawableRes int i, @Nullable Resources.Theme thm)
    {
        return ResourcesUtils.getDrawable(i, thm);
    }
    @Nullable
    public static Drawable getFromResources(@Nullable Resources r, @NonNull @DrawableRes int i)
    {
        return ResourcesUtils.getDrawable(r, i);
    }
    @Nullable
    public static Drawable getFromResources(@Nullable Resources r, @NonNull @DrawableRes int i, @Nullable Resources.Theme thm)
    {
        return ResourcesUtils.getDrawable(r, i, thm);
    }

    public static void setColorFilter(@Nullable Drawable drw, @NonNull @ColorInt int i)
    {
        setColorFilter(drw, i, null);
    }

    public static void setColorFilter(@Nullable Drawable drw, @NonNull @ColorInt int i, @Nullable PorterDuff.Mode e)
    {
        if(drw == null) return;
        drw.setColorFilter(i, e != null ? e : PorterDuff.Mode.SRC_IN);
    }
}
