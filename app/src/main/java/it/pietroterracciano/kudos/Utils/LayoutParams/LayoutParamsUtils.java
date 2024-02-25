package it.pietroterracciano.kudos.Utils.LayoutParams;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.ELParam;
import it.pietroterracciano.kudos.Utils.TypesUtils.NumericUtils.IntegerUtils;
import it.pietroterracciano.kudos.Utils.TypesUtils.NumericUtils.intUtils;

public abstract class LayoutParamsUtils
{
    @NonNull
    public static int normalizeWidthHeight(@NonNull int i)
    {
        return i > ViewGroup.LayoutParams.MATCH_PARENT
            ?
                i
            :
            (
                i > ViewGroup.LayoutParams.WRAP_CONTENT
                    ? ViewGroup.LayoutParams.MATCH_PARENT
                    : ViewGroup.LayoutParams.WRAP_CONTENT
            );
    }

    @Nullable
    public static ViewGroup.LayoutParams clone(@Nullable ViewGroup.LayoutParams vglp)
    {
        if(vglp == null)
            return null;

        Integer i;
        Float f;
        ViewGroup.LayoutParams vglp0;

        if(vglp instanceof WindowManager.LayoutParams)
        {
            vglp0 = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            i = getInt(vglp, ELParam.WindowAnimations);
            if(i != null) set(vglp0, ELParam.WindowAnimations, i);
            i = getInt(vglp, ELParam.WindowRotationAnimation);
            if(i != null) set(vglp0, ELParam.WindowRotationAnimation, i);
            i = getInt(vglp, ELParam.WindowGravity);
            if(i != null) set(vglp0, ELParam.WindowGravity, i);
            i = getInt(vglp, ELParam.WindowX);
            if(i != null) set(vglp0, ELParam.WindowX, i);
            i = getInt(vglp, ELParam.WindowY);
            if(i != null) set(vglp0, ELParam.WindowY, i);
        }
        else if(vglp instanceof ViewGroup.MarginLayoutParams)
        {
            if(vglp instanceof LinearLayout.LayoutParams)
            {
                vglp0 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                i = getInt(vglp, ELParam.ViewGravity);
                if(i != null) set(vglp0, ELParam.ViewGravity, i);
                f = getFloat(vglp, ELParam.ViewWeight);
                if(f != null) set(vglp0, ELParam.ViewWeight, f);
            }
            else
                vglp0 = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            i = getInt(vglp, ELParam.ViewMarginBottom);
            if(i != null) set(vglp0, ELParam.ViewMarginBottom, i);
            i = getInt(vglp, ELParam.ViewMarginTop);
            if(i != null) set(vglp0, ELParam.ViewMarginTop, i);
            i = getInt(vglp, ELParam.ViewMarginStart);
            if(i != null) set(vglp0, ELParam.ViewMarginStart, i);
            i = getInt(vglp, ELParam.ViewMarginEnd);
            if(i != null) set(vglp0, ELParam.ViewMarginEnd, i);
            i = getInt(vglp, ELParam.ViewMarginLeft);
            if(i != null) set(vglp0, ELParam.ViewMarginLeft, i);
            i = getInt(vglp, ELParam.ViewMarginRight);
            if(i != null) set(vglp0, ELParam.ViewMarginRight, i);
        }
        else
            vglp0 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        i = getInt(vglp, ELParam.Width);
        if(i != null) set(vglp0, ELParam.Width, i);
        i = getInt(vglp, ELParam.Height);
        if(i != null) set(vglp0, ELParam.Height, i);

        return vglp0;
    }

    @NonNull
    public static boolean set(@Nullable ViewGroup.LayoutParams vglp, @Nullable ELParam e, @NonNull float f)
    {
        if(vglp != null && e != null)
        {
            int i;

            switch (e)
            {
                case Width:
                case Height:
                    i = intUtils.from(f);
                    i = normalizeWidthHeight(i);

                    switch (e)
                    {
                        case Width:
                            if(vglp.width == i) return false;
                            vglp.width = i; return true;
                        case Height:
                            if(vglp.height == i) return false;
                            vglp.height = i; return true;
                    }
                    break;

                case ViewMarginBottom:
                case ViewMarginEnd:
                case ViewMarginTop:
                case ViewMarginRight:
                case ViewMarginStart:
                case ViewMarginLeft:
                    if(!(vglp instanceof ViewGroup.MarginLayoutParams))
                        return false;

                    ViewGroup.MarginLayoutParams
                            vgmlp = (ViewGroup.MarginLayoutParams)vglp;

                    i = intUtils.from(f);

                    switch (e)
                    {
                        case ViewMarginBottom:
                            if(vgmlp.bottomMargin == i) return false;
                            vgmlp.bottomMargin = i; return true;
                        case ViewMarginEnd:
                            if(vgmlp.getMarginEnd() == i) return false;
                            vgmlp.setMarginEnd(i); return true;
                        case ViewMarginRight:
                            if(vgmlp.rightMargin == i) return false;
                            vgmlp.rightMargin = i; return true;
                        case ViewMarginStart:
                            if(vgmlp.getMarginStart() == i) return false;
                            vgmlp.setMarginStart(i); return true;
                        case ViewMarginLeft:
                            if(vgmlp.leftMargin == i) return false;
                            vgmlp.leftMargin = i; return true;
                        case ViewMarginTop:
                            if(vgmlp.topMargin == i) return false;
                            vgmlp.topMargin = i; return true;
                    }
                    break;
                case ViewWeight:
                case ViewGravity:
                    if(!(vglp instanceof LinearLayout.LayoutParams))
                        return false;

                    LinearLayout.LayoutParams
                        lllp = (LinearLayout.LayoutParams) vglp;

                    switch (e)
                    {
                        case ViewWeight:
                            if(lllp.weight == f) return false;
                            lllp.weight = f; return true;
                        case ViewGravity:
                            i = intUtils.from(f);
                            if(lllp.gravity == i) return false;
                            lllp.gravity = i; return true;
                    }
                    break;
                case WindowRotationAnimation:
                case WindowGravity:
                case WindowX:
                case WindowY:
                case WindowAnimations:
                    if(!(vglp instanceof WindowManager.LayoutParams))
                        return false;

                    WindowManager.LayoutParams
                        wmlp = (WindowManager.LayoutParams) vglp;

                    i = intUtils.from(f);

                    switch (e)
                    {
                        case WindowAnimations:
                            if(wmlp.windowAnimations == i) return false;
                            wmlp.windowAnimations = i; return true;
                        case WindowRotationAnimation:
                            if(wmlp.rotationAnimation == i) return false;
                            wmlp.rotationAnimation = i; return true;
                        case WindowGravity:
                            if(wmlp.gravity == i) return false;
                            wmlp.gravity = i; return true;
                        case WindowX:
                            if(wmlp.x == i) return false;
                            wmlp.x = i; return true;
                        case WindowY:
                            if(wmlp.y == i) return false;
                            wmlp.y = i; return true;
                    }
                    break;
            }
        }

        return false;
    }

    @Nullable
    public static Float getFloat(@Nullable ViewGroup.LayoutParams vglp, @Nullable ELParam e)
    {
        if(vglp != null && e != null)
            switch (e)
            {
                case Width:
                    return (float)vglp.width;
                case Height:
                    return (float)vglp.height;

                case ViewMarginBottom:
                case ViewMarginEnd:
                case ViewMarginRight:
                case ViewMarginStart:
                case ViewMarginLeft:
                case ViewMarginTop:
                    if(!(vglp instanceof ViewGroup.MarginLayoutParams))
                        return null;

                    ViewGroup.MarginLayoutParams
                            vgmlp = (ViewGroup.MarginLayoutParams)vglp;

                    switch (e)
                    {
                        case ViewMarginBottom:
                            return (float)vgmlp.bottomMargin;
                        case ViewMarginEnd:
                            return (float)vgmlp.getMarginEnd();
                        case ViewMarginRight:
                            return (float)vgmlp.rightMargin;
                        case ViewMarginStart:
                            return (float)vgmlp.getMarginStart();
                        case ViewMarginLeft:
                            return (float)vgmlp.leftMargin;
                        case ViewMarginTop:
                            return (float)vgmlp.topMargin;
                    }
                    break;
                case ViewWeight:
                case ViewGravity:
                    if(!(vglp instanceof LinearLayout.LayoutParams))
                        return null;

                    LinearLayout.LayoutParams
                            lllp = (LinearLayout.LayoutParams) vglp;

                    switch (e)
                    {
                        case ViewWeight:
                            return (float)lllp.weight;
                        case ViewGravity:
                            return (float)lllp.gravity;
                    }
                    break;
                case WindowRotationAnimation:
                case WindowGravity:
                case WindowX:
                case WindowY:
                case WindowAnimations:
                    if(!(vglp instanceof WindowManager.LayoutParams))
                        return null;

                    WindowManager.LayoutParams
                            wmlp = (WindowManager.LayoutParams) vglp;

                    switch (e)
                    {
                        case WindowAnimations:
                            return (float)wmlp.windowAnimations;
                        case WindowRotationAnimation:
                            return (float)wmlp.rotationAnimation;
                        case WindowGravity:
                            return (float)wmlp.gravity ;
                        case WindowX:
                            return (float)wmlp.x;
                        case WindowY:
                            return (float)wmlp.y;
                    }
                    break;
            }

        return null;
    }

    @Nullable
    public static Integer getInt(@Nullable ViewGroup.LayoutParams vglp, @Nullable ELParam e)
    {
        return IntegerUtils.from(getFloat(vglp, e));
    }
}