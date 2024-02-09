package it.pietroterracciano.kudos.Utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class DrawableUtils
{
    @Nullable
    public static Drawable getFromResources(@NonNull @DrawableRes int iResourceID)
    {
        return ResourcesUtils.getDrawable(iResourceID);
    }
    @Nullable
    public static Drawable getFromResources(@Nullable Resources oResources, @NonNull @DrawableRes int iRIdentifier)
    {
        return ResourcesUtils.getDrawable(oResources, iRIdentifier);
    }
}
