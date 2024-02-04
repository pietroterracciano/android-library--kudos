package it.pietroterracciano.kudos.Views.RecyclersView.ViewHolders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import it.pietroterracciano.kudos.Kudos;

public abstract class ARVViewHolder
<
    ViewHolderType extends ARVViewHolder<ViewHolderType, ItemType>,
    ItemType
>
extends
    RecyclerView.ViewHolder
{
    @NonNull
    public final int TransientID;
    @Nullable
    private ItemType _oItem;

    @NonNull
    public ViewHolderType injectItem(@Nullable ItemType oItem)
    {
        _oItem = oItem;
        invalidateX(_oItem);
        return (ViewHolderType)this;
    }

    public ARVViewHolder(View v)
    {
        super(v);
        TransientID = Kudos.newTransientID();
    }

    protected abstract void invalidateX(@Nullable ItemType oItem);

    @Override
    public boolean equals(Object o)
    {
        return
            (
                o instanceof ARVViewHolder
                && ((ARVViewHolder)o).TransientID == TransientID
            )
            || super.equals(o);
    }
}