package it.pietroterracciano.kudos.Utils;

import android.content.res.Resources;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Kudos;

public abstract class ColorUtils
{
    @Nullable
    public static Integer getFromResources(@NonNull @ColorRes int i)
    {
        return ResourcesUtils.getColor(i);
    }
    @Nullable
    public static Integer getFromResources(@NonNull @ColorRes int i, @Nullable Resources.Theme thm)
    {
        return ResourcesUtils.getColor(i, thm);
    }
    @Nullable
    public static Integer getFromResources(@Nullable Resources r, @NonNull @ColorRes int i)
    {
        return ResourcesUtils.getColor(r, i);
    }
    @Nullable
    public static Integer getFromResources(@Nullable Resources r, @NonNull @ColorRes int i, @Nullable Resources.Theme thm)
    {
        return ResourcesUtils.getColor(r, i, thm);
    }
}