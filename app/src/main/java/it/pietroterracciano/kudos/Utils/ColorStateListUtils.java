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
    @Nullable
    public static ColorStateList from(@ColorRes @NonNull int i)
    {
        return from(Kudos.getResources(), i);
    }
    @Nullable
    public static ColorStateList from(@Nullable Resources r, @ColorRes @NonNull int i)
    {
        Integer j = ColorUtils.getFromResources(r, i);
        if(j == null) return null;
        return ColorStateList.valueOf(j);
    }
}
