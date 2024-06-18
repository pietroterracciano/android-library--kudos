package it.pietroterracciano.kudos.Utils;

import android.content.res.ColorStateList;
import android.content.res.Resources;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Kudos;

public abstract class ColorStateListUtils
{
    public static ColorStateList from(@ColorRes @NonNull int i)
    {
        return from(Kudos.getResources(), i, Kudos.getTheme());
    }
    @Nullable
    public static ColorStateList from(@ColorRes @NonNull int i, @Nullable Resources.Theme thm)
    {
        return from(Kudos.getResources(), i, thm);
    }
    @Nullable
    public static ColorStateList from(@Nullable Resources r, @ColorRes @NonNull int i)
    {
        return from(r, i, Kudos.getTheme());
    }
    @Nullable
    public static ColorStateList from(@Nullable Resources r, @ColorRes @NonNull int i, @Nullable Resources.Theme thm)
    {
        Integer j = ColorUtils.getFromResources(r, i, thm);
        if(j == null) return null;
        return ColorStateList.valueOf(j);
    }
}
