package it.pietroterracciano.kudos.Utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Constants.CCharacter;
import it.pietroterracciano.kudos.Kudos;

public abstract class ResourcesUtils
{
    /*
    @NonNull
    private static final StringBuilder __sb0;
    @NonNull
    private static final HashMap<String, Integer> __hmhks2clrs;*/
    @NonNull
    private static final HashMap<Integer, Drawable> __hmrids2drws;

    static
    {
        //__sb0 = new StringBuilder();
        //__hmhks2clrs = new HashMap<>();
        __hmrids2drws = new HashMap<>();
    }

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

        /*
        synchronized (__hmhks2clrs)
        {
            String shk = _calculateHashKey(i, thm);
            Integer j = __hmhks2clrs.get(shk);

            if(j == null && r != null)
            {
                try { j = r.getColor(i, thm); } catch (Exception ignored){ }
                __hmhks2clrs.put(shk, j);
            }

            return j;
        }*/
    }

    /*@NonNull
    private static String _calculateHashKey(@NonNull @ColorRes int i, @Nullable Resources.Theme thm)
    {
        synchronized (__sb0)
        {
            __sb0.setLength(0);

            if(thm != null)
                __sb0.append("thmn").append(CCharacter.DoubleDot).append(thm).append(CCharacter.Pipe);

            __sb0.append("crid").append(CCharacter.DoubleDot).append(i);

            return __sb0.toString();
        }
    }*/

    /*
    @Nullable
    public static Drawable getDrawable(@NonNull @DrawableRes int iResourceID)
    {
        return getDrawable(Kudos.getResources(), iResourceID);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    public static Drawable getDrawable(@Nullable Resources r, @NonNull @DrawableRes int i)
    {
        synchronized (__hmrids2drws)
        {
            Drawable
                j = __hmrids2drws.get(i);

            if(j == null && r != null)
            {
                try { j = r.getDrawable(i, null); } catch (Exception ignored){ }
                __hmrids2drws.put(i, j);
            }

            return j;
        }
    }*/

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
}
