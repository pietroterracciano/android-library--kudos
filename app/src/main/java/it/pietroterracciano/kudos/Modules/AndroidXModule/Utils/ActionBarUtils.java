package it.pietroterracciano.kudos.Modules.AndroidXModule.Utils;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import it.pietroterracciano.kudos.Utils.LayoutInflaterUtils;

public final class ActionBarUtils
{
    @NonNull
    public static boolean setCustomView(@Nullable ActionBar ab, @NonNull @LayoutRes int i)
    {
        if(ab != null)
            try { ab.setCustomView(i); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean setCustomView(@Nullable ActionBar ab, @Nullable View v)
    {
        if(ab != null && v != null)
            try { ab.setCustomView(v); return true; } catch (Exception ignored) {}

        return false;
    }
    @NonNull
    public static boolean setCustomView(@Nullable ActionBar ab, @NonNull @LayoutRes int i, @Nullable ActionBar.LayoutParams lp)
    {
        return setCustomView(ab, LayoutInflaterUtils.inflate(i), lp);
    }
    @NonNull
    public static boolean setCustomView(@Nullable ActionBar ab, @Nullable View v, @Nullable ActionBar.LayoutParams lp)
    {
        if(ab != null && v != null && lp != null)
            try { ab.setCustomView(v, lp); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean setDisplayShowHomeEnabled(@Nullable ActionBar ab, @NonNull boolean b)
    {
        if(ab != null)
            try { ab.setDisplayShowHomeEnabled(b); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean setDisplayUseLogoEnabled(@Nullable ActionBar ab, @NonNull boolean b)
    {
        if(ab != null)
            try { ab.setDisplayUseLogoEnabled(b); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean setDisplayShowCustomEnabled(@Nullable ActionBar ab, @NonNull boolean b)
    {
        if(ab != null)
            try { ab.setDisplayShowCustomEnabled(b); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean setDisplayShowTitleEnabled(@Nullable ActionBar ab, @NonNull boolean b)
    {
        if(ab != null)
            try { ab.setDisplayShowTitleEnabled(b); return true; } catch (Exception ignored) {}

        return false;
    }
}
