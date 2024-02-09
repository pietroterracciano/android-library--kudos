package it.pietroterracciano.kudos.Utils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class LayoutManagerUtils
{
    @Nullable
    public static Integer findLastVisibleItemPosition(@Nullable RecyclerView.LayoutManager lm)
    {
        if(lm instanceof GridLayoutManager)
        {
            GridLayoutManager glm = (GridLayoutManager) lm;
            return glm.findLastVisibleItemPosition();
        }
        else if(lm instanceof LinearLayoutManager)
        {
            LinearLayoutManager llm = (LinearLayoutManager) lm;
            return llm.findLastVisibleItemPosition();
        }
        /*else if(lm instanceof StaggeredGridLayoutManager)
        {
            StaggeredGridLayoutManager sglm = (StaggeredGridLayoutManager) lm;
            return sglm.findLastVisibleItemPositions();
        }*/

        return null;
    }
}
