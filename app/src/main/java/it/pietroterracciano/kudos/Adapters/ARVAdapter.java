package it.pietroterracciano.kudos.Adapters;

import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.pietroterracciano.kudos.Constants.CClass;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.R;
import it.pietroterracciano.kudos.Utils.Collections.ArrayUtils;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;
import it.pietroterracciano.kudos.Utils.ListUtils;
import it.pietroterracciano.kudos.Utils.ObjectUtils;
import it.pietroterracciano.kudos.Layouts.RVItemLayout;
import it.pietroterracciano.kudos.ViewHolders.RVViewHolder;

public abstract class ARVAdapter
<
    AdapterType extends ARVAdapter<AdapterType>
>
extends
    RecyclerView.Adapter<RVViewHolder>
{
    @NonNull
    private static final RVItemLayout
        __ilBlank = new RVItemLayout<>(Object.class, R.layout.recycler_view__item__blank);
    @NonNull
    private static final Constructor<RVViewHolder>
        __cnsViewHolder = ConstructorUtils.getDeclared(RVViewHolder.class, CClass.ViewGroup, RVItemLayout.class);

    @NonNull
    public final int TransientID;

    @NonNull
    private final Object _oLock;
    @NonNull
    private final List<Object> _lItems;
    @NonNull
    private final HashMap<Class<?>, Integer> _hmItemClasses2LayoutResourceIDs;
    @NonNull
    private final HashMap<Integer, RVItemLayout<?>> _hmLayoutResourceIDs2ItemLayouts;

    public ARVAdapter()
    {
        TransientID = Kudos.newTransientID();
        _oLock = new Object();
        _lItems = new ArrayList<>();

        _hmItemClasses2LayoutResourceIDs = new HashMap<>();
        _hmLayoutResourceIDs2ItemLayouts = new HashMap<>();

        List<RVItemLayout<?>> l = new ArrayList<>();
        onItemLayoutsRegister(l);

        RVItemLayout<?> ili;

        int j = l.size();
        for(int i=0; i<j; i++)
        {
            ili = l.get(i);
            if(ili == null) continue;
            _hmItemClasses2LayoutResourceIDs.put(ili.ItemClass, ili.LayoutResourceID);
            _hmLayoutResourceIDs2ItemLayouts.put(ili.LayoutResourceID, ili);
        }
    }

    protected abstract void onItemLayoutsRegister(@NonNull List<RVItemLayout<?>> l);

    @NonNull
    public int indexOfItem(@Nullable Object oItem)
    {
        synchronized (_oLock)
        {
            return ListUtils.indexOf(_lItems, oItem);
        }
    }

    @NonNull
    public int lastIndexOfItem(@Nullable Object oItem)
    {
        synchronized (_oLock)
        {
            return ListUtils.lastIndexOf(_lItems, oItem);
        }
    }

    @NonNull
    public boolean prependItem(@Nullable Object itm) { return apprItem(itm, true); }
    @NonNull
    public boolean appendItem(@Nullable Object itm) { return apprItem(itm, false); }
    @NonNull
    private boolean apprItem(@Nullable Object itm, boolean bPrepend)
    {
        if(itm == null)
            return false;

        synchronized (_oLock)
        {
            int j = ListUtils.adse(_lItems, itm, bPrepend);
            if(j < -1) return false;
            else if(j < 0) notifyItemInserted(bPrepend ? 0 : getItemCount()-1);
            else notifyItemChanged(j);
            return true;
        }
    }

    @NonNull
    public boolean removeFirstItem() { return removeItem(0); }
    @NonNull
    public boolean removeLastItem() { return removeItem(getItemCount()-1); }
    @NonNull
    public boolean removeItem(@Nullable Object itm) { return removeItem(indexOfItem(itm));}
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
            int i = getItemCount();
            _lItems.clear();
            notifyItemRangeRemoved(0, i);
        }
    }

    @Override
    @NonNull
    public int getItemCount()
    {
        return _lItems.size();
    }

    @Nullable
    public Object[] getItems()
    {
        synchronized (_oLock)
        {
            return ArrayUtils.from(_lItems, CClass.Object);
        }
    }

    @Nullable
    public <ItemType> ItemType getFirstItem() { return getItem(0); }
    @Nullable
    public <ItemType> ItemType getLastItem() { return getItem(getItemCount()-1); }
    @Nullable
    public <ItemType> ItemType getItem(@NonNull int i)
    {
        synchronized (_oLock)
        {
            return ObjectUtils.cast(ListUtils.get(_lItems, i));
        }
    }

    @Nullable
    private Integer getItemViewType(@Nullable Object itm)
    {
        if(itm == null)
            return null;

        synchronized (_oLock)
        {
            return _hmItemClasses2LayoutResourceIDs.get(itm.getClass());
        }
    }
    @Override
    @NonNull
    public int getItemViewType(@NonNull int i)
    {
        Integer j = getItemViewType(getItem(i));
        return j != null ? j : R.layout.recycler_view__item__blank;
    }

    @Nullable
    private RVItemLayout<?> getItemLayout(@NonNull @LayoutRes int i)
    {
        synchronized (_oLock)
        {
            return _hmLayoutResourceIDs2ItemLayouts.get(i);
        }
    }

    @NonNull
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup vg, @NonNull @LayoutRes int i)
    {
        RVItemLayout<?> il = getItemLayout(i);
        return ConstructorUtils.newInstance(__cnsViewHolder, vg, il != null ? il : __ilBlank);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder vh, @NonNull int i)
    {
        Object itm = getItem(i);
        if(vh.ItemLayout.OnInvalidateItemXListener == null) return;
        vh.ItemLayout.OnInvalidateItemXListener.invoke(vh.itemView, i, ObjectUtils.cast(itm));
    }

    //public abstract void onInvalidateItemLayoutX(View v, int i, Object o);
}