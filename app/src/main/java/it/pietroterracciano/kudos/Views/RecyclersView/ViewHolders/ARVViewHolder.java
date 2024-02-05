package it.pietroterracciano.kudos.Views.RecyclersView.ViewHolders;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ObjectUtils;
import it.pietroterracciano.kudos.Views.Handlers.FastViewHandler;

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

    public ARVViewHolder(@NonNull View oView)
    {
        super(oView);
        TransientID = Kudos.newTransientID();
    }

    @NonNull
    public ViewHolderType injectItem(@Nullable ItemType oItem)
    {
        _oItem = oItem;
        invalidateX(_oItem);
        return (ViewHolderType)this;
    }

    protected abstract void invalidateX(@Nullable ItemType oItem);

    @Override
    public boolean equals(Object o)
    {
        return
            (
                o instanceof ARVViewHolder<?,?>
                && ((ARVViewHolder<?,?>)o).TransientID == TransientID
            )
            || super.equals(o);
    }
}