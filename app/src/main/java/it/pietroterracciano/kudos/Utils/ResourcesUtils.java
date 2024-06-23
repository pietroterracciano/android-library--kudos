package it.pietroterracciano.kudos.Utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import java.io.InputStream;
import java.util.HashMap;

import it.pietroterracciano.kudos.Constants.CCharacter;
import it.pietroterracciano.kudos.Kudos;

public abstract class ResourcesUtils
{
    @Nullable
    public static Integer getColor(@NonNull @ColorRes int i) { return getColor(Kudos.getResources(), i, Kudos.getTheme()); }
    @Nullable
    public static Integer getColor(@NonNull @ColorRes int i, @Nullable Resources.Theme thm) { return getColor(Kudos.getResources(), i, thm); }
    @Nullable
    public static Integer getColor(@Nullable Resources r, @NonNull @ColorRes int i) { return getColor(r, i, Kudos.getTheme()); }
    @Nullable
    public static Integer getColor(@Nullable Resources r, @NonNull @ColorRes int i, @Nullable Resources.Theme thm)
    {
        if(r != null)
            try { return r.getColor(i, thm); } catch (Exception ignored){ }

        return null;
    }

    @Nullable
    public static Drawable getDrawable(@NonNull @DrawableRes int i) { return getDrawable(Kudos.getResources(), i, Kudos.getTheme()); }
    @Nullable
    public static Drawable getDrawable(@NonNull @DrawableRes int i, @Nullable Resources.Theme thm) { return getDrawable(Kudos.getResources(), i, thm); }
    @Nullable
    public static Drawable getDrawable(@Nullable Resources r, @NonNull @DrawableRes int i) { return getDrawable(r, i, Kudos.getTheme()); }
    @Nullable
    public static Drawable getDrawable(@Nullable Resources r, @NonNull @DrawableRes int i, @Nullable Resources.Theme thm)
    {
        if(r != null)
            try { return r.getDrawable(i, thm); } catch (Exception ignored){ }

        return null;
    }


    @Nullable
    public static InputStream openRaw(@RawRes @NonNull int i)
    {
        return openRaw(Kudos.getResources(), i);
    }
    @Nullable
    public static InputStream openRaw(@Nullable Resources r, @RawRes @NonNull int i)
    {
        if(r != null)
            try { return r.openRawResource(i); } catch (Exception ignored) {}

        return null;
    }
}
