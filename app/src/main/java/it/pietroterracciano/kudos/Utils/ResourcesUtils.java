package it.pietroterracciano.kudos.Utils;

import android.content.res.Resources;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Kudos;

public abstract class ResourcesUtils
{
    @Nullable
    public static Integer getColor(@NonNull @ColorRes int iResourceID)
    {
        return getColor(Kudos.getResources(), iResourceID);
    }
    @Nullable
    public static Integer getColor(@Nullable Resources r, @NonNull @ColorRes int iResourceID)
    {
        if(r != null)
            try
            {
                return r.getColor(iResourceID);
            }
            catch (Exception ignored)
            {
            }

        return null;
    }
}
