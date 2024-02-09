package it.pietroterracciano.kudos.Utils;

import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import it.pietroterracciano.kudos.Enums.EVProperty;

public abstract class WindowUtils extends BaseViewUtils
{
    @Nullable
    public static boolean setLayout(@Nullable Window w, @NonNull int iW, @NonNull int iH)
    {
        if(w == null) return false;
        w.setLayout(normalizeWidthHeight(iW), normalizeWidthHeight(iH));
        return true;
    }

    @NonNull
    public static boolean setGravity(@Nullable Window w, @GravityInt @NonNull int i)
    {
        return setProperty(w, EVProperty.Gravity, i);
    }
    @Nullable
    public static Integer getGravity(@Nullable Window w)
    {
        return getProperty(w, EVProperty.Gravity);
    }
    @NonNull
    public static boolean setWindowAnimations(@Nullable Window w, @StyleRes @NonNull int i)
    {
        return setProperty(w, EVProperty.WindowAnimations, i);
    }
    @Nullable
    public static Integer getWindowAnimations(@Nullable Window w)
    {
        return getProperty(w, EVProperty.WindowAnimations);
    }
    @NonNull
    public static boolean setRotationAnimation(@Nullable Window w, @StyleRes @NonNull int i)
    {
        return setProperty(w, EVProperty.RotationAnimation, i);
    }
    @Nullable
    public static Integer getRotationAnimations(@Nullable Window w)
    {
        return getProperty(w, EVProperty.RotationAnimation);
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
    public static boolean setX(@Nullable Window w, @NonNull int i)
    {
        return setProperty(w, EVProperty.X, i);
    }
    @Nullable
    public static Integer getX(@Nullable Window w)
    {
        return getProperty(w, EVProperty.X);
    }
    @NonNull
    public static boolean setY(@Nullable Window w, @NonNull int i)
    {
        return setProperty(w, EVProperty.Y, i);
    }
    @Nullable
    public static Integer getY(@Nullable Window w)
    {
        return getProperty(w, EVProperty.Y);
    }

    @NonNull
    private static boolean setProperty(@Nullable Window w, @NonNull EVProperty e, @NonNull int i)
    {
        if( w == null )
            return false;

        WindowManager.LayoutParams
            lp = w.getAttributes();

        if(!setProperty(lp, e, i))
            return false;

        w.setAttributes(lp);
        return true;
    }
    @NonNull
    private static boolean setProperty(@Nullable WindowManager.LayoutParams lp, EVProperty e, int i)
    {
        if(lp == null)
            return false;

        switch (e)
        {
            case WindowAnimations:
                if(lp.windowAnimations == i)
                    break;
                lp.windowAnimations = i;
                return true;
            case RotationAnimation:
                if(lp.rotationAnimation == i)
                    break;
                lp.rotationAnimation = i;
                return true;
            case Gravity:
                if(lp.gravity == i)
                    break;
                lp.gravity = i;
                return true;
            case X:
                if(lp.x == i)
                    break;
                lp.x = i;
                return true;
            case Y:
                if(lp.y == i)
                    break;
                lp.y = i;
                return true;
            default:
                return ViewUtils.setProperty(lp, e, i);
        }

        return false;
    }

    @Nullable
    private static Integer getProperty(@Nullable Window w, @NonNull EVProperty e)
    {
        return w != null ? getProperty(w.getAttributes(), e) : null;
    }
    @Nullable
    private static Integer getProperty(@Nullable WindowManager.LayoutParams lp, EVProperty e)
    {
        if(lp != null)
            switch (e)
            {
                case WindowAnimations:
                    return lp.windowAnimations;
                case RotationAnimation:
                    return lp.rotationAnimation;
                case Gravity:
                    return lp.gravity;
                case X:
                    return lp.x;
                case Y:
                    return lp.y;
            }

        return null;
    }
}
