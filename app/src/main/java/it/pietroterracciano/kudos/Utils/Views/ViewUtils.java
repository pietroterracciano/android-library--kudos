package it.pietroterracciano.kudos.Utils.Views;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.ELayoutParam;
import it.pietroterracciano.kudos.Enums.EVLayoutParam;
import it.pietroterracciano.kudos.Enums.EWLayoutParam;
import it.pietroterracciano.kudos.Utils.LayoutParams.LayoutParamsUtils;

public abstract class ViewUtils
{
    @NonNull
    public static boolean setLayoutParam(@Nullable View v, @Nullable EVLayoutParam e, int i)
    {
        if(
            v == null
            || !LayoutParamsUtils.set(v.getLayoutParams(), parseLayoutParam(e), i)
        )
            return false;

        v.requestLayout();
        return true;
    }

    @Nullable
    public static Integer getLayoutParam(@Nullable View v, @Nullable EVLayoutParam e)
    {
        return v != null ? LayoutParamsUtils.get(v.getLayoutParams(), parseLayoutParam(e)) : null;
    }

    @Nullable
    private static ELayoutParam parseLayoutParam(@Nullable EVLayoutParam e)
    {
        if(e != null)
            switch (e)
            {
                case MarginBottom:
                    return ELayoutParam.ViewMarginBottom;
                case MarginRight:
                    return ELayoutParam.ViewMarginRight;
                case MarginStart:
                    return ELayoutParam.ViewMarginStart;
                case MarginTop:
                    return ELayoutParam.ViewMarginTop;
                case MarginEnd:
                    return ELayoutParam.ViewMarginEnd;
                case MarginLeft:
                    return ELayoutParam.ViewMarginLeft;
                case Weight:
                    return ELayoutParam.ViewWeight;
                case Width:
                    return ELayoutParam.Width;
                case Height:
                    return ELayoutParam.Height;
            }

        return null;
    }
}
