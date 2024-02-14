package it.pietroterracciano.kudos.Utils.LayoutParams;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.ELayoutParam;

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

    @NonNull
    public static boolean set(@Nullable ViewGroup.LayoutParams vglp, @Nullable ELayoutParam e, @NonNull int i)
    {
        if(vglp != null && e != null)
            switch (e)
            {
                case Width:
                case Height:
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
                    if(!(vglp instanceof LinearLayout.LayoutParams))
                        return false;

                    LinearLayout.LayoutParams
                        lllp = (LinearLayout.LayoutParams) vglp;

                    switch (e)
                    {
                        case ViewWeight:
                            if(lllp.weight == i) return false;
                            lllp.weight = i; return true;
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

        return false;
    }

    @Nullable
    public static Integer get(@Nullable ViewGroup.LayoutParams vglp, @NonNull ELayoutParam e)
    {
        if(vglp != null)
            switch (e)
            {
                case Width:
                    return vglp.width;
                case Height:
                    return vglp.height;

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
                            return vgmlp.bottomMargin;
                        case ViewMarginEnd:
                            return vgmlp.getMarginEnd();
                        case ViewMarginRight:
                            return vgmlp.rightMargin;
                        case ViewMarginStart:
                            return vgmlp.getMarginStart();
                        case ViewMarginLeft:
                            return vgmlp.leftMargin;
                        case ViewMarginTop:
                            return vgmlp.topMargin;
                    }
                    break;
                case ViewWeight:
                    if(!(vglp instanceof LinearLayout.LayoutParams))
                        return null;

                    LinearLayout.LayoutParams
                            lllp = (LinearLayout.LayoutParams) vglp;

                    switch (e)
                    {
                        case ViewWeight:
                            return 0;// lllp.weight;
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
                            return wmlp.windowAnimations;
                        case WindowRotationAnimation:
                            return wmlp.rotationAnimation;
                        case WindowGravity:
                            return wmlp.gravity ;
                        case WindowX:
                            return wmlp.x;
                        case WindowY:
                            return wmlp.y ;
                    }
                    break;
            }

        return null;
    }
}