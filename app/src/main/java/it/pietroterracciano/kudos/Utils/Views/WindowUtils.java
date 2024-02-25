package it.pietroterracciano.kudos.Utils.Views;

import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.ELParam;
import it.pietroterracciano.kudos.Enums.EWLayoutParam;
import it.pietroterracciano.kudos.Utils.LayoutParams.LayoutParamsUtils;

public abstract class WindowUtils
{
    @Nullable
    public static boolean setLayout(@Nullable Window w, @NonNull int iW, @NonNull int iH)
    {
        if(w == null) return false;
        w.setLayout(LayoutParamsUtils.normalizeWidthHeight(iW), LayoutParamsUtils.normalizeWidthHeight(iH));
        return true;
    }

    @NonNull
    public static void clearFlags(@Nullable Window w, @NonNull int i)
    {
        if(w == null) return;
        w.clearFlags(i);
    }
    @NonNull
    public static void addFlags(@Nullable Window w, @NonNull int i)
    {
        if(w == null) return;
        w.addFlags(i);
    }

    @NonNull
    public static boolean setLayoutParam(@Nullable Window w, @Nullable EWLayoutParam e, int i)
    {
        if(w == null)
            return false;

        WindowManager.LayoutParams wmlp = w.getAttributes();

        if(!LayoutParamsUtils.set(wmlp, parseLayoutParam(e), i))
            return false;

        w.setAttributes(wmlp);
        return true;
    }

    @Nullable
    public static Integer getLayoutParam(@Nullable Window w, @Nullable EWLayoutParam e)
    {
        return w != null ? LayoutParamsUtils.getInt(w.getAttributes(), parseLayoutParam(e)) : null;
    }

    @Nullable
    private static ELParam parseLayoutParam(@Nullable EWLayoutParam e)
    {
        if(e != null)
            switch (e)
            {
                case X:
                    return ELParam.WindowX;
                case Y:
                    return ELParam.WindowY;
                case RotationAnimation:
                    return ELParam.WindowRotationAnimation;
                case Animations:
                    return ELParam.WindowAnimations;
                case Gravity:
                    return ELParam.WindowGravity;
                case Width:
                    return ELParam.Width;
                case Height:
                    return ELParam.Height;
            }

        return null;
    }
}
