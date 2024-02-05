package it.pietroterracciano.kudos.Views.RecyclersView.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import it.pietroterracciano.kudos.Controllers.LayoutController;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ArrayUtils;
import it.pietroterracciano.kudos.Utils.ListUtils;
import it.pietroterracciano.kudos.Utils.ObjectUtils;
import it.pietroterracciano.kudos.Views.RecyclersView.ViewHolders.ARVViewHolder;

public abstract class ARVAdapter
<
    RVAdapterType extends ARVAdapter<RVAdapterType, ItemType, ViewHolderType>,
    ItemType,
    ViewHolderType extends ARVViewHolder<ViewHolderType, ItemType>
>
extends
    RecyclerView.Adapter<ViewHolderType>
{
    @NonNull
    public final int TransientID;
    @NonNull
    private final Object _oLock;
    @NonNull
    private final List<ItemType> _lItems;
    @NonNull
    private boolean _bIsItemValidationEnabled;
    @NonNull @LayoutRes
    private final int _iLayoutResourceID;
    @Nullable
    private final Constructor<ViewHolderType> _cnsViewHolderType;
    @NonNull
    private final Class<ItemType> _clsItemType;

    @NonNull
    public RVAdapterType isItemValidationEnabled(@NonNull boolean bIsItemValidationEnabled)
    {
        _bIsItemValidationEnabled = bIsItemValidationEnabled;
        return (RVAdapterType)this;
    }
    @NonNull
    public boolean isItemValidationEnabled()
    {
        return _bIsItemValidationEnabled;
    }

    public ARVAdapter
    (
        @NonNull Class<ViewHolderType> clsViewHolder,
        @NonNull Class<ItemType> clsItemType,
        @NonNull @LayoutRes int iLayoutResourceID
    )
    {
        TransientID = Kudos.newTransientID();
        _oLock = new Object();
        _lItems = new ArrayList<>();
        _cnsViewHolderType = ObjectUtils.getDeclaredConstructor(clsViewHolder, View.class);
        _clsItemType = clsItemType;
        _iLayoutResourceID = iLayoutResourceID;
    }

    @NonNull
    public int indexOfItem(@Nullable ItemType oItem)
    {
        synchronized (_oLock)
        {
            return ListUtils.indexOf(_lItems, oItem);
        }
    }

    @NonNull
    public int lastIndexOfItem(@Nullable ItemType oItem)
    {
        synchronized (_oLock)
        {
            return ListUtils.lastIndexOf(_lItems, oItem);
        }
    }

    @NonNull
    public boolean adseItem(@Nullable ItemType oItem)
    {
        synchronized (_oLock)
        {
            int i = ListUtils.adse(_lItems, oItem);
            if(i < 0) notifyItemInserted(_lItems.size());
            else notifyItemChanged(i);
            return i > -2;
        }
    }

    @NonNull
    public boolean removeItem(@Nullable ItemType oItem)
    {
        return removeItem(indexOfItem(oItem));
    }
    @NonNull
    public boolean removeItem(@NonNull int i)
    {
        synchronized (_oLock)
        {
            boolean b = ListUtils.remove(_lItems, i);
            if(b) notifyItemRemoved(i);
            return b;
        }
    }

    public void removeItems()
    {
        synchronized (_oLock)
        {
            int iItemsSize = getItemsSize();
            _lItems.clear();
            notifyItemRangeRemoved(0, iItemsSize);
        }
    }

    @Override
    @NonNull
    public int getItemCount()
    {
        return _lItems.size();
    }
    @NonNull
    public int getItemsSize()
    {
        return _lItems.size();
    }

    @Nullable
    public ItemType[] getItems()
    {
        synchronized (_oLock)
        {
            return ArrayUtils.from(_lItems, _clsItemType);
        }
    }

    @Nullable
    public ItemType getItem(@NonNull int i)
    {
        synchronized (_oLock)
        {
            return ListUtils.get(_lItems, i);
        }
    }

    @NonNull
    public ViewHolderType onCreateViewHolder(@NonNull ViewGroup vg, @NonNull int i)
    {
        return ObjectUtils.newInstance(_cnsViewHolderType, LayoutController.inflate(_iLayoutResourceID, vg, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderType vh, @NonNull int i)
    {
        vh.injectItem(getItem(i));
    }
}