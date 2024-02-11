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
import it.pietroterracciano.kudos.Controllers.ThreadController;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.R;
import it.pietroterracciano.kudos.Utils.ArrayUtils;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;
import it.pietroterracciano.kudos.Utils.LayoutManagerUtils;
import it.pietroterracciano.kudos.Utils.ListUtils;
import it.pietroterracciano.kudos.Utils.ObjectUtils;
import it.pietroterracciano.kudos.Utils.TypesUtils.NumericUtils.intUtils;
import it.pietroterracciano.kudos.Interfaces.IRVOnEndlessListener;
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
        __ilBlank = new RVItemLayout<>(Object.class, R.layout.recycler_view__item__blank, null);
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
    @Nullable
    private IRVOnEndlessListener _lsnOnEndless;
    @NonNull
    private int _iEndlessThreshold;
    @NonNull
    private RecyclerView x_oView;
    @NonNull
    private final RecyclerView.OnScrollListener _oOnScrollListener;

    public ARVAdapter()
    {
        TransientID = Kudos.newTransientID();
        _oLock = new Object();
        _lItems = new ArrayList<>();

        _oOnScrollListener = new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(@NonNull RecyclerView rv, int dx, int dy)
            {
                rv.removeOnScrollListener(this);

                super.onScrolled(rv, dx, dy);

                IRVOnEndlessListener lst;
                int iLastVisibleItemPosition, iItemCount, iEndlessThreshold;

                synchronized (_oLock)
                {
                    iLastVisibleItemPosition = intUtils.from(LayoutManagerUtils.findLastVisibleItemPosition(x_oView.getLayoutManager()));
                    iItemCount = getItemCount();
                    iEndlessThreshold = _iEndlessThreshold;
                    lst = _lsnOnEndless;
                }

                if(
                    iLastVisibleItemPosition < iItemCount - iEndlessThreshold
                    || lst == null
                )
                {
                    rv.addOnScrollListener(this);
                    return;
                }

                try  { lst.fg_onBeforeEndless(); } catch (Exception ignored){}

                ThreadController.runOnBackground(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try { lst.bg_onEndless(); } catch (Exception ignored) {}

                        ThreadController.runOnForeground(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                try { lst.fg_onAfterEndless(); } catch (Exception ignored) {}
                                rv.addOnScrollListener(_oOnScrollListener);
                            }
                        });
                    }
                });
            }
        };

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

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView v)
    {
        super.onAttachedToRecyclerView(v);
        x_oView = v;
        x_oView.addOnScrollListener(_oOnScrollListener);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView v)
    {
        x_oView.removeOnScrollListener(_oOnScrollListener);
        super.onDetachedFromRecyclerView(v);
    }

    public AdapterType setEndlessThreshold(@NonNull int i)
    {
        _iEndlessThreshold = i > 0 ? i : 0;
        return (AdapterType) this;
    }
    public AdapterType setOnEndlessListener(@Nullable IRVOnEndlessListener lst)
    {
        _lsnOnEndless = lst;
        return (AdapterType) this;
    }

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
    public boolean adseItem(@Nullable Object itm)
    {
        if(itm == null)
            return false;

        synchronized (_oLock)
        {
            int j = ListUtils.adse(_lItems, itm);
            if(j < -1) return false;
            else if(j < 0) notifyItemInserted(_lItems.size() -1);
            else notifyItemChanged(j);
            return true;
        }
    }

    @NonNull
    public boolean removeLastItem()
    {
        return removeItem(getItemCount()-1);
    }
    @NonNull
    public boolean removeItem(@Nullable Object itm)
    {
        return removeItem(indexOfItem(itm));
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
            int i = getItemCount();
            _lItems.clear();
            notifyItemRangeRemoved(0, i);
        }
    }

    @Override
    @NonNull
    public int getItemCount()
    {
        synchronized (_oLock)
        {
            return _lItems.size();
        }
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
        synchronized (_oLock)
        {
            return itm != null ? _hmItemClasses2LayoutResourceIDs.get(itm.getClass()) : null;
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
        synchronized (_oLock)
        {
            Object itm = getItem(i);
            if(itm == null || vh.ItemLayout.OnInvalidateItemXListener == null) return;
            vh.ItemLayout.OnInvalidateItemXListener.invoke(vh.itemView, i, ObjectUtils.cast(itm));
        }
    }
}