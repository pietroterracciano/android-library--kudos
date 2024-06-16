package it.pietroterracciano.kudos.Utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Kudos;

public abstract class ResourcesUtils
{
    @NonNull
    private static final Object _lck = new Object();
    @NonNull
    private static final HashMap<Integer, Integer> __hmIDs2Colors = new HashMap<>();
    @NonNull
    private static final HashMap<Integer, Drawable> __hmIDs2Drawables = new HashMap<>();

    @Nullable
    public static Integer getColor(@NonNull @ColorRes int iResourceID)
    {
        return getColor(Kudos.getResources(), iResourceID);
    }
    @Nullable
    public static Integer getColor(@Nullable Resources r, @NonNull @ColorRes int i)
    {
        synchronized (_lck)
        {
            Integer
                j = __hmIDs2Colors.get(i);

            if(j == null && r != null)
            {
                try { j = r.getColor(i, null); } catch (Exception ignored){ }
                __hmIDs2Colors.put(i, j);
            }

            return j;
        }
    }

    @Nullable
    public static Drawable getDrawable(@NonNull @DrawableRes int iResourceID)
    {
        return getDrawable(Kudos.getResources(), iResourceID);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    public static Drawable getDrawable(@Nullable Resources r, @NonNull @DrawableRes int i)
    {
        synchronized (_lck)
        {
            Drawable
                j = __hmIDs2Drawables.get(i);

            if(j == null && r != null)
            {
                try { j = r.getDrawable(i, null); } catch (Exception ignored){ }
                __hmIDs2Drawables.put(i, j);
            }

            return j;
        }
    }
}
