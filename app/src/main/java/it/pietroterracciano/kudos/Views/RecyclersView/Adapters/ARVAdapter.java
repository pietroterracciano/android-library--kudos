package it.pietroterracciano.kudos.Views.RecyclersView.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.pietroterracciano.kudos.Constants.CClass;
import it.pietroterracciano.kudos.Controllers.LayoutController;
import it.pietroterracciano.kudos.Controllers.ThreadController;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ArrayUtils;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;
import it.pietroterracciano.kudos.Utils.LayoutManagerUtils;
import it.pietroterracciano.kudos.Utils.ListUtils;
import it.pietroterracciano.kudos.Utils.MethodUtils;
import it.pietroterracciano.kudos.Utils.ObjectUtils;
import it.pietroterracciano.kudos.Utils.TypesUtils.NumericUtils.intUtils;
import it.pietroterracciano.kudos.Views.RecyclersView.Enums.ERVEvent;
import it.pietroterracciano.kudos.Views.RecyclersView.Interfaces.IRVOnEndlessListener;
import it.pietroterracciano.kudos.Views.RecyclersView.Interfaces.IRVOnItemClickListener;
import it.pietroterracciano.kudos.Views.RecyclersView.Interfaces.IRVOnItemTouchListener;
import it.pietroterracciano.kudos.Views.RecyclersView.Interfaces.IRVOnItemEventListener;
import it.pietroterracciano.kudos.Views.RecyclersView.Layouts.RVItemLayout;
import it.pietroterracciano.kudos.Views.RecyclersView.ViewHolders.RVViewHolder;

public abstract class ARVAdapter
<
    AdapterType extends ARVAdapter<AdapterType>
>
extends
    RecyclerView.Adapter<RVViewHolder>
{
    @NonNull
    private static final Class<ARVAdapter> __clsAAdapter = ARVAdapter.class;
    @NonNull
    private static final Class<RVViewHolder> __clsViewHolder = RVViewHolder.class;
    @NonNull
    private static final Method
        __mthVHinjectOnItemEventListeners = MethodUtils.getDeclared(__clsViewHolder, "injectOnItemEventListeners", CClass.HashMap);
    @NonNull
    private static final Constructor<RVViewHolder> __cnsViewHolder = ConstructorUtils.getDeclared(__clsViewHolder, __clsAAdapter, CClass.View);

    @NonNull
    private final HashMap<Integer, HashMap<ERVEvent, IRVOnItemEventListener>> _hmResourceIDs2Events2OnItemEventListeners;
    @NonNull
    public final int TransientID;
    @NonNull
    private final Object _oLock;

    @NonNull
    private final List<Object> _lItems;
    @NonNull
    private final HashMap<Class<?>, RVItemLayout<?>> _hmItemClasses2ItemLayouts;
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

        _hmItemClasses2ItemLayouts = new HashMap<>();
        _hmResourceIDs2Events2OnItemEventListeners = new HashMap<>();

        RVItemLayout<?>[] a = onRegisterItemsLayouts();

        for(int i=0; i<a.length; i++)
            _hmItemClasses2ItemLayouts.put(a[i].Class, a[i]);
    }

    @NonNull
    protected abstract RVItemLayout<?>[] onRegisterItemsLayouts();

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

    public AdapterType setOnItemClickListener(@IdRes @NonNull int i, @Nullable IRVOnItemClickListener lst)
    {
        return setOnItemEventListener(i, ERVEvent.OnClick, lst);
    }

    public AdapterType setOnItemTouchListener(@IdRes @NonNull int i, @Nullable IRVOnItemTouchListener lst)
    {
        return setOnItemEventListener(i, ERVEvent.OnTouch, lst);
    }



    private AdapterType setOnItemEventListener
    (
        @IdRes @NonNull int i,
        @NonNull ERVEvent e,
        @Nullable IRVOnItemEventListener lst
    )
    {
        synchronized (_oLock)
        {
            HashMap<ERVEvent, IRVOnItemEventListener> hm;
            hm = _hmResourceIDs2Events2OnItemEventListeners.computeIfAbsent(i, j -> new HashMap<>());
            hm.put(e, lst);
        }

        return (AdapterType) this;
    }

    @NonNull
    public <ItemType> int indexOfItem(@Nullable ItemType oItem)
    {
        synchronized (_oLock)
        {
            return ListUtils.indexOf(_lItems, oItem);
        }
    }

    @NonNull
    public <ItemType> int lastIndexOfItem(@Nullable ItemType oItem)
    {
        synchronized (_oLock)
        {
            return ListUtils.lastIndexOf(_lItems, oItem);
        }
    }

    @NonNull
    public <ItemType> boolean adseItem(@Nullable ItemType itm)
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
    public <ItemType> boolean removeItem(@Nullable ItemType itm)
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

    @Override
    public int getItemViewType(int i)
    {
        synchronized (_oLock)
        {
            return _hmItemClasses2ItemLayouts.get(getItem(i).getClass()).LayoutResourceID;
        }
    }

    @NonNull
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup vg, @NonNull int i)
    {
        return ConstructorUtils.newInstance(__cnsViewHolder, this, LayoutController.inflate(i, vg, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder vh, @NonNull int i)
    {
        synchronized (_oLock)
        {
            Object itm = getItem(i);
            RVItemLayout il = _hmItemClasses2ItemLayouts.get(itm.getClass());
            if(il.OnItemInvalidateX != null) il.OnItemInvalidateX.accept(vh.View, itm);
            MethodUtils.invoke(vh, __mthVHinjectOnItemEventListeners, _hmResourceIDs2Events2OnItemEventListeners);
        }
    }
}