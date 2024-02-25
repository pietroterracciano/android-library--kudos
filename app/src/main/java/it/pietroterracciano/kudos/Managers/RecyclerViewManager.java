package it.pietroterracciano.kudos.Managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewManager
{
    @NonNull
    private RecyclerView x_o;

    public RecyclerViewManager(@NonNull RecyclerView x_o)
    {
        this.x_o = x_o;
    }

    @Nullable
    public static RecyclerViewManager build(@Nullable RecyclerView x_o)
    {
        return x_o != null ? new RecyclerViewManager(x_o) : null;
    }
}
