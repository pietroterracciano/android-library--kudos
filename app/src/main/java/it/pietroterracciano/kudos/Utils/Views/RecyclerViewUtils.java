package it.pietroterracciano.kudos.Utils.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


public abstract class RecyclerViewUtils
{
    public static boolean smoothScrollToPosition(@Nullable RecyclerView o, @NonNull int i)
    {
        if(o == null ||  i < 0)
            return false;

        RecyclerView.LayoutManager
            lm = o.getLayoutManager();

        if(lm == null)
            return false;

        o.post(new Runnable()
        {
            @Override
            public void run()
            {
                if(i >= lm.getItemCount()) return;
                try { o.smoothScrollToPosition(i); } catch (Exception ignored) {}
            }
        });

        return true;
    }
}
