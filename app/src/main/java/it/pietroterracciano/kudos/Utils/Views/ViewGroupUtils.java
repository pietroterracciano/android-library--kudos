package it.pietroterracciano.kudos.Utils.Views;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class ViewGroupUtils
{
    @NonNull
    public static boolean removeAllViews(@Nullable ViewGroup vg)
    {
        if(vg != null)
            try { vg.removeAllViews(); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean addView(@Nullable ViewGroup vg, @Nullable View v)
    {
        if(vg != null && v != null)
            try { vg.addView(v); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static View getChildAt(@Nullable ViewGroup vg, @NonNull @IntRange(from = 0) int i)
    {
        if(vg != null)
            try { return vg.getChildAt(i); } catch (Exception ignored) {}

        return null;
    }
}