package it.pietroterracciano.kudos.Utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.EVProperty;

public abstract class BaseViewUtils
{
    @NonNull
    protected static int normalizeWidthHeight(@NonNull int i)
    {
        return i > ViewGroup.LayoutParams.MATCH_PARENT
                ? i
                :
                (
                        i > ViewGroup.LayoutParams.WRAP_CONTENT
                                ? ViewGroup.LayoutParams.MATCH_PARENT
                                : ViewGroup.LayoutParams.WRAP_CONTENT
                );
    }

    @NonNull
    protected static boolean setProperty(@Nullable ViewGroup.LayoutParams lp, @NonNull EVProperty e, @NonNull int i)
    {
        if(lp != null)
            switch (e)
            {
                case Width:
                    i = normalizeWidthHeight(i);
                    if(lp.width == i)
                        break;
                    lp.width = i;
                    return true;
                case Height:
                    i = normalizeWidthHeight(i);
                    if(lp.height == i)
                        break;
                    lp.height = i;
                    return true;
                case MarginBottom:
                case MarginEnd:
                case MarginTop:
                case MarginLeft:
                case MarginRight:
                case MarginStart:
                    if(!(lp instanceof ViewGroup.MarginLayoutParams))
                        break;

                    ViewGroup.MarginLayoutParams
                            mlp = (ViewGroup.MarginLayoutParams)lp;

                    switch (e)
                    {
                        case MarginBottom:
                            if(mlp.bottomMargin == i)
                                break;
                            mlp.bottomMargin = i;
                            return true;
                        case MarginEnd:
                            if(mlp.getMarginEnd() == i)
                                break;
                            mlp.setMarginEnd(i);
                            return true;
                        case MarginRight:
                            if(mlp.rightMargin == i)
                                break;
                            mlp.rightMargin = i;
                            return true;
                        case MarginStart:
                            if(mlp.getMarginStart() == i)
                                break;
                            mlp.setMarginStart(i);
                            return true;
                        case MarginLeft:
                            if(mlp.leftMargin == i)
                                break;
                            mlp.leftMargin = i;
                            return true;
                        case MarginTop:
                            if(mlp.topMargin == i)
                                break;
                            mlp.topMargin = i;
                            return true;
                    }
            }

        return false;
    }
    @Nullable
    protected static Integer getProperty(@Nullable ViewGroup.LayoutParams lp, @NonNull EVProperty e)
    {
        if(lp != null)
            switch (e)
            {
                case Width:
                    return lp.width;
                case Height:
                    return lp.height;
                case MarginBottom:
                case MarginEnd:
                case MarginTop:
                case MarginLeft:
                case MarginRight:
                case MarginStart:
                    if(!(lp instanceof ViewGroup.MarginLayoutParams))
                        break;

                    ViewGroup.MarginLayoutParams
                            mlp = (ViewGroup.MarginLayoutParams)lp;

                    switch (e)
                    {
                        case MarginBottom:
                            return mlp.bottomMargin;
                        case MarginEnd:
                            return mlp.getMarginEnd();
                        case MarginRight:
                            return mlp.rightMargin;
                        case MarginStart:
                            return mlp.getMarginStart();
                        case MarginLeft:
                            return mlp.leftMargin;
                        case MarginTop:
                            return mlp.topMargin;
                    }
            }

        return null;
    }
}
