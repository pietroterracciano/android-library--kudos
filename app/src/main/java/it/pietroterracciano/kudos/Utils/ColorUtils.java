package it.pietroterracciano.kudos.Utils;

import android.content.res.Resources;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class ColorUtils
{
    @Nullable
    public static Integer getFromResources(@NonNull @ColorRes int iResourceID)
    {
        return ResourcesUtils.getColor(iResourceID);
    }
    @Nullable
    public static Integer getFromResources(@Nullable Resources oResources, @NonNull @ColorRes int iRIdentifier)
    {
        return ResourcesUtils.getColor(oResources, iRIdentifier);
    }
}
