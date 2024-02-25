package it.pietroterracciano.kudos.Utils.Layouts.Managers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import it.pietroterracciano.kudos.Enums.ELMItemPosition;
import it.pietroterracciano.kudos.Enums.ELParam;
import it.pietroterracciano.kudos.Enums.EVVisibilityBound;
import it.pietroterracciano.kudos.Enums.EVVisibilityOrientation;

public abstract class LayoutManagerUtils
{
    @NonNull
    public static int findItemPosition(@Nullable RecyclerView.LayoutManager lm, @Nullable ELMItemPosition e)
    {
        if(lm instanceof GridLayoutManager)
            return GridLayoutManagerUtils.findItemPosition((GridLayoutManager) lm, e);
        else if(lm instanceof LinearLayoutManager)
            return LinearLayoutManagerUtils.findItemPosition((LinearLayoutManager) lm, e);
        else
            return RecyclerView.NO_POSITION;
    }

    @Nullable
    public static View findViewByPosition(@Nullable RecyclerView.LayoutManager lm, @Nullable ELMItemPosition e)
    {
        if(lm instanceof GridLayoutManager)
            return GridLayoutManagerUtils.findViewByPosition((GridLayoutManager) lm, e);
        else if(lm instanceof LinearLayoutManager)
            return LinearLayoutManagerUtils.findViewByPosition((LinearLayoutManager) lm, e);
        else
            return null;
    }

    @Nullable
    protected static EVVisibilityOrientation parseOrientation2Enum(@NonNull int i)
    {
        switch (i)
        {
            case RecyclerView.HORIZONTAL:
                return EVVisibilityOrientation.Horizontal;
            case RecyclerView.VERTICAL:
                return EVVisibilityOrientation.Vertical;
        }

        return null;
    }

    @Nullable
    protected static EVVisibilityBound parseItemPositionEnum2Enum(@Nullable ELMItemPosition e)
    {
        if(e != null)
            switch (e)
            {
                case MoreVisible:
                    return EVVisibilityBound.More;
                case LessVisible:
                    return EVVisibilityBound.Less;
            }

        return null;
    }
}