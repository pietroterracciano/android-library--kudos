package it.pietroterracciano.kudos.Utils.Dialogs;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Utils.Views.ViewGroupUtils;

public final class DialogUtils
{
    @Nullable
    public static Window getWindow(@Nullable Dialog dlg)
    {
        if(dlg != null)
            try { return dlg.getWindow(); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static <T extends View> T findViewById(@Nullable Dialog dlg, @IdRes @NonNull int i)
    {
        if(dlg != null)
            try { return dlg.findViewById(i); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static <T extends View> T getRootView(@Nullable Dialog dlg)
    {
        if(dlg != null)
            try { return dlg.findViewById(android.R.id.content); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static View getInflatedView(@Nullable Dialog dlg)
    {
        return ViewGroupUtils.getChildAt(getRootView(dlg), 0);
    }
}