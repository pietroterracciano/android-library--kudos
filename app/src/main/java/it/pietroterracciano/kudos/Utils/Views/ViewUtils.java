package it.pietroterracciano.kudos.Utils.Views;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import it.pietroterracciano.kudos.Enums.ELParam;
import it.pietroterracciano.kudos.Enums.EVLayoutParam;
import it.pietroterracciano.kudos.Enums.EVVisibilityBound;
import it.pietroterracciano.kudos.Enums.EVVisibilityOrientation;
import it.pietroterracciano.kudos.Enums.EVVisibilityRect;
import it.pietroterracciano.kudos.Utils.Collections.ArrayUtils;
import it.pietroterracciano.kudos.Utils.LayoutParams.LayoutParamsUtils;

public abstract class ViewUtils
{
    @NonNull
    public static void addFlagsOnSystemUiVisibility(@Nullable View v, @NonNull int i) { _setFlagsOnSystemUiVisibility(v, i, i); }
    @NonNull
    public static void clearFlagsOnSystemUiVisibility(@Nullable View v, @NonNull int i) { _setFlagsOnSystemUiVisibility(v, 0, i); }
    @NonNull
    private static void _setFlagsOnSystemUiVisibility(@Nullable View v, @NonNull int iValue, @NonNull int iMask)
    {
        if(v == null) return;
        int suiv = v.getSystemUiVisibility();
        suiv = (suiv&~iMask) | (iValue&iMask);
        v.setSystemUiVisibility(suiv);
    }

    /*@NonNull
    public static boolean setLayoutParams(@Nullable View vFrom, @Nullable View vTo)
    {
        return setLayoutParams(vTo, vFrom, false);
    }*/
    @NonNull
    public static boolean setLayoutParams(@Nullable View vFrom, @Nullable View vTo, boolean bClone)
    {
        return setLayoutParams(vTo, getLayoutParams(vFrom), bClone);
    }
    /*@NonNull
    public static boolean setLayoutParams(@Nullable View v, @Nullable ViewGroup.LayoutParams vglp)
    {
        return setLayoutParams(v, vglp, false);
    }*/
    @NonNull
    public static boolean setLayoutParams(@Nullable View v, @Nullable ViewGroup.LayoutParams vglp, boolean bClone)
    {
        if(v == null) return false;
        if(bClone) vglp = LayoutParamsUtils.clone(vglp);
        if(vglp == null) return false;
        v.setLayoutParams(vglp);
        return true;
    }

    @Nullable
    public static ViewGroup.LayoutParams getLayoutParams(@Nullable View v)
    {
        return getLayoutParams(v, false);
    }
    @Nullable
    public static ViewGroup.LayoutParams getLayoutParams(@Nullable View v, boolean bClone)
    {
        return v != null
            ?
            (
                bClone
                    ? LayoutParamsUtils.clone(v.getLayoutParams())
                    : v.getLayoutParams()
            )
            :
                null;
    }

    @NonNull
    public static boolean setLayoutParam(@Nullable View v, @Nullable EVLayoutParam e, float f)
    {
        if(
            v == null
            || !LayoutParamsUtils.set(v.getLayoutParams(), parseLayoutParam(e), f)
        )
            return false;

        v.requestLayout();
        return true;
    }

    @Nullable
    public static Integer getLayoutParamInt(@Nullable View v, @Nullable EVLayoutParam e)
    {
        return v != null ? LayoutParamsUtils.getInt(v.getLayoutParams(), parseLayoutParam(e)) : null;
    }

    @Nullable
    public static Float getLayoutParamFloat(@Nullable View v, @Nullable EVLayoutParam e)
    {
        return v != null ? LayoutParamsUtils.getFloat(v.getLayoutParams(), parseLayoutParam(e)) : null;
    }

    @Nullable
    private static ELParam parseLayoutParam(@Nullable EVLayoutParam e)
    {
        if(e != null)
            switch (e)
            {
                case MarginBottom:
                    return ELParam.ViewMarginBottom;
                case MarginRight:
                    return ELParam.ViewMarginRight;
                case MarginStart:
                    return ELParam.ViewMarginStart;
                case MarginTop:
                    return ELParam.ViewMarginTop;
                case MarginEnd:
                    return ELParam.ViewMarginEnd;
                case MarginLeft:
                    return ELParam.ViewMarginLeft;
                case Weight:
                    return ELParam.ViewWeight;
                case Width:
                    return ELParam.Width;
                case Height:
                    return ELParam.Height;
                case Gravity:
                    return ELParam.ViewGravity;
            }

        return null;
    }

    @Nullable
    public static View find
    (
        @Nullable EVVisibilityOrientation evo,
        @Nullable EVVisibilityRect evr,
        @Nullable EVVisibilityBound evb,
        @Nullable View... a
    )
    {
        if
        (
            evo == null
            || evr == null
            || evb == null
            || !ArrayUtils.isValidIndex(a, 0)
        )
            return null;
        else if(a.length < 2)
            return a[0];

        int j;

        Rect
            rv = null,
            ri = new Rect();

        View
            v = null;

        for(int i=0; i<a.length; i++)
        {
            if(a[i] == null)
                continue;

            switch (evr)
            {
                case Local:
                    a[i].getLocalVisibleRect(ri);
                    break;
                case Global:
                    a[i].getGlobalVisibleRect(ri);
                    break;
                default:
                    continue;
            }

            if(rv == null)
            {
                v = a[i];
                rv = new Rect(ri);
            }

            switch (evo)
            {
                case Horizontal:
                    j = rv.width() - ri.width();
                    break;
                case Vertical:
                    j = rv.height() - ri.height();
                    break;
                default:
                    continue;
            }

            switch (evb)
            {
                case More:
                    if(j < 0)
                    {
                        rv = new Rect(ri);
                        v = a[i];
                    }
                    break;
                case Less:
                    if(j > 0)
                    {
                        rv = new Rect(ri);
                        v = a[i];
                    }
                    break;
                default:
                    continue;
            }
        }

        return v;
    }

    @NonNull
    public static boolean addChild(@Nullable ViewGroup x_vg, @Nullable View x_v)
    {
        if(x_vg != null && x_v != null)
            try { x_vg.addView(x_v); return true; } catch (Exception ignored) {}

        return false;
    }
}
