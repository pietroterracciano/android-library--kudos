package it.pietroterracciano.kudos.ViewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.pietroterracciano.kudos.Controllers.LayoutInflaterController;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Layouts.RVItemLayout;

public final class RVViewHolder extends RecyclerView.ViewHolder
{
    @NonNull
    public final int TransientID;
    @NonNull
    public final RVItemLayout<?> ItemLayout;

    private RVViewHolder(@NonNull ViewGroup vg, @NonNull RVItemLayout<?> il)
    {
        super(LayoutInflaterController.inflate(il.LayoutResourceID, vg, false));
        TransientID = Kudos.newTransientID();
        ItemLayout = il;
    }

    @Override
    public boolean equals(Object o)
    {
        return
            (
                o instanceof RVViewHolder
                && ((RVViewHolder)o).TransientID == TransientID
            )
            || super.equals(o);
    }
}