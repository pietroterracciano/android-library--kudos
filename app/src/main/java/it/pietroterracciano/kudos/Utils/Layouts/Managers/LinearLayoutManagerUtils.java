package it.pietroterracciano.kudos.Utils.Layouts.Managers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import it.pietroterracciano.kudos.Enums.ELMItemPosition;
import it.pietroterracciano.kudos.Enums.EVVisibilityRect;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public abstract class LinearLayoutManagerUtils extends LayoutManagerUtils
{
    @NonNull
    public static int findItemPosition(@Nullable LinearLayoutManager lm, @Nullable ELMItemPosition e)
    {
        if(lm != null && e != null)
            switch (e)
            {
                case First:
                    return lm.findFirstVisibleItemPosition();
                case FirstCompletelyVisible:
                    return lm.findFirstCompletelyVisibleItemPosition();
                case Last:
                    return lm.findLastVisibleItemPosition();
                case LastCompletelyVisible:
                    return lm.findLastCompletelyVisibleItemPosition();
                case MoreVisible:
                case LessVisible:
                    int
                        i0 = lm.findFirstVisibleItemPosition(),
                        i1 = lm.findLastVisibleItemPosition();

                    View
                        v0 = lm.findViewByPosition(i0),
                        v1 = lm.findViewByPosition(i1),
                        v2 =
                            ViewUtils.find
                            (
                                parseOrientation2Enum(lm.getOrientation()),
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
    public static View findViewByPosition(@Nullable LinearLayoutManager lm, @Nullable ELMItemPosition e)
    {
        int i = findItemPosition(lm, e);
        return i != RecyclerView.NO_POSITION ? lm.findViewByPosition(i) : null;
    }
}
