package it.pietroterracciano.kudos.Utils.Dialogs;
import android.app.Dialog;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import it.pietroterracciano.kudos.Utils.Views.ViewGroupUtils;

public abstract class BottomSheetDialogUtils
{
    public static <T extends View> T getRootView(@Nullable BottomSheetDialog bsd)
    {
        if(bsd != null)
            try { return bsd.findViewById(com.google.android.material.R.id.design_bottom_sheet); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static View getInflatedView(@Nullable BottomSheetDialog bsd)
    {
        return ViewGroupUtils.getChildAt(getRootView(bsd), 0);
    }

    @Nullable
    public static <T extends View> T findViewById(@Nullable BottomSheetDialog bsd, @IdRes @NonNull int i)
    {
        if(bsd != null)
            try { return bsd.findViewById(i); } catch (Exception ignored) {}

        return null;
    }
}
