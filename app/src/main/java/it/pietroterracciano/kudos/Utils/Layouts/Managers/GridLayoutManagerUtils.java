package it.pietroterracciano.kudos.Utils.Layouts.Managers;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import it.pietroterracciano.kudos.Enums.ELMItemPosition;
import it.pietroterracciano.kudos.Enums.EVVisibilityBound;
import it.pietroterracciano.kudos.Enums.EVVisibilityOrientation;
import it.pietroterracciano.kudos.Enums.EVVisibilityRect;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public abstract class GridLayoutManagerUtils extends LayoutManagerUtils
{
    @NonNull
    public static int findItemPosition(@Nullable GridLayoutManager glm, @Nullable ELMItemPosition e)
    {
        if(glm != null && e != null)
            switch (e)
            {
                case First:
                    return glm.findFirstVisibleItemPosition();
                case FirstCompletelyVisible:
                    return glm.findFirstCompletelyVisibleItemPosition();
                case Last:
                    return glm.findLastVisibleItemPosition();
                case LastCompletelyVisible:
                    return glm.findLastCompletelyVisibleItemPosition();
                case MoreVisible:
                case LessVisible:
                    int
                        i0 = glm.findFirstVisibleItemPosition(),
                        i1 = glm.findLastVisibleItemPosition();

                    View
                        v0 = glm.findViewByPosition(i0),
                        v1 = glm.findViewByPosition(i1),
                        v2 =
                            ViewUtils.find
                            (
                                parseOrientation2Enum(glm.getOrientation()),
                                EVVisibilityRect.Local,
                                parseItemPositionEnum2Enum(e),
                                v0,
                                v1
                            );

                    if(v2 != null)
                        return v2 == v0 ? i0 : i1;
            }

        return RecyclerView.NO_POSITION;
    }

    @Nullable
    public static View findViewByPosition(@Nullable GridLayoutManager glm, @Nullable ELMItemPosition e)
    {
        int i = findItemPosition(glm, e);
        return i != RecyclerView.NO_POSITION ? glm.findViewByPosition(i) : null;
    }
}