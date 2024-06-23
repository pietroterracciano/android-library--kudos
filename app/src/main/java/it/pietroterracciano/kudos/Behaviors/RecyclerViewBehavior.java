package it.pietroterracciano.kudos.Behaviors;

import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import it.pietroterracciano.kudos.Utils.ThreadUtils;
import it.pietroterracciano.kudos.Enums.ELMItemPosition;
import it.pietroterracciano.kudos.Enums.EVLayoutParam;
import it.pietroterracciano.kudos.Interfaces.IRVOnEndlessListener;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.Layouts.Managers.LayoutManagerUtils;
import it.pietroterracciano.kudos.Utils.RunnableUtils;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public class RecyclerViewBehavior
{
    @NonNull
    public final int TransientID;

    @NonNull
    private final Object _oLock;
    @Nullable
    private SwipeRefreshLayout x_oSwipeRefreshLayout;
    @Nullable
    private IRVOnEndlessListener _lsnOnUpperEndless, _lsnOnLowerEndless;
    @NonNull
    private int _iLowerEndlessThreshold;
    @NonNull
    private RecyclerView x_o;
    @Nullable
    private ViewGroup x_vgParent;

    @NonNull
    private final RecyclerView.OnScrollListener _oOnScrollListener;
    @NonNull
    private final SwipeRefreshLayout.OnRefreshListener _srlOnRefreshListener;
    @NonNull
    private final Runnable _rOnLowerEndlessListenerFinish, _rOnUpperEndlessListenerFinish;

    private RecyclerViewBehavior(@NonNull RecyclerView x_o)
    {
        TransientID = Kudos.newTransientID();
        _oLock = new Object();

        this.x_o = x_o;

        ViewParent x_oViewParent = x_o.getParent();
        if(x_oViewParent instanceof ViewGroup)
            x_vgParent = (ViewGroup) x_oViewParent;

        _rOnLowerEndlessListenerFinish = new Runnable()
        {
            @Override
            public void run()
            {
                x_o.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        x_o.scrollBy(0, -1);
                        x_o.addOnScrollListener(_oOnScrollListener);
                    }
                });
            }
        };

        _rOnUpperEndlessListenerFinish = new Runnable()
        {
            @Override
            public void run()
            {
                x_o.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        x_o.scrollBy(0, +1);
                    }
                });

                x_oSwipeRefreshLayout.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        x_oSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        };

        _oOnScrollListener = new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(@NonNull RecyclerView x_o, @NonNull int dx, @NonNull int dy)
            {
                x_o.removeOnScrollListener(this);

                super.onScrolled(x_o, dx, dy);

                int iLastVisibleItemPosition, iItemCount, iEndlessThreshold;
                RecyclerView.Adapter oAdapter;

                iLastVisibleItemPosition = LayoutManagerUtils.findItemPosition(x_o.getLayoutManager(), ELMItemPosition.Last);
                oAdapter = x_o.getAdapter();
                iItemCount = oAdapter != null ? oAdapter.getItemCount() : 0;

                synchronized (_oLock)
                {
                    iEndlessThreshold = _iLowerEndlessThreshold;
                }

                if
                (
                    iLastVisibleItemPosition < iItemCount - iEndlessThreshold
                )
                {
                    x_o.addOnScrollListener(this);
                    return;
                }

                runEndlessListener(
                    _lsnOnLowerEndless,
                    _rOnLowerEndlessListenerFinish
                );
            }
        };

        _srlOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                runEndlessListener(
                    _lsnOnUpperEndless,
                    _rOnUpperEndlessListenerFinish
                );
            }
        };

    }


    private void runEndlessListener(@Nullable IRVOnEndlessListener lst, @Nullable Runnable rOnFinish)
    {
        if(lst == null)
        {
            RunnableUtils.run(rOnFinish);
            return;
        }

        try { lst.fg_onBeforeEndless(); } catch (Exception ignored){}

        ThreadUtils.runOnBackground(new Runnable()
        {
            @Override
            public void run()
            {
                try { lst.bg_onEndless(); } catch (Exception ignored) {}

                ThreadUtils.runOnForeground(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try { lst.fg_onAfterEndless(); } catch (Exception ignored) {}
                        RunnableUtils.run(rOnFinish);
                    }
                });
            }
        });
    }

    public RecyclerViewBehavior setOnUpperEndlessListener(@Nullable IRVOnEndlessListener lst)
    {
        synchronized (_oLock)
        {
            _lsnOnUpperEndless = lst;

            if(x_oSwipeRefreshLayout == null)
            {
                if(_lsnOnUpperEndless == null || x_vgParent == null)
                    return this;

                if(!(x_vgParent instanceof SwipeRefreshLayout))
                {
                    int j = x_vgParent.getChildCount(), k = -1;

                    for(int i=0; i<j; i++)
                    {
                        if(x_vgParent.getChildAt(i) != x_o) continue;
                        k = i; break;
                    }

                    if(k < 0)
                        return this;

                    x_vgParent.removeViewAt(k);

                    x_oSwipeRefreshLayout = new SwipeRefreshLayout(x_o.getContext());
                    ViewUtils.setLayoutParams(x_o, x_oSwipeRefreshLayout, true);
                    ViewUtils.setLayoutParam(x_o, EVLayoutParam.Width, ViewGroup.LayoutParams.MATCH_PARENT);
                    ViewUtils.setLayoutParam(x_o, EVLayoutParam.Height, ViewGroup.LayoutParams.MATCH_PARENT);
                    ViewUtils.setLayoutParam(x_o, EVLayoutParam.Weight, 0);
                    x_oSwipeRefreshLayout.addView(x_o);

                    x_vgParent.addView(x_oSwipeRefreshLayout, k);
                }
                else
                    x_oSwipeRefreshLayout = (SwipeRefreshLayout)x_vgParent;
            }

            if(_lsnOnUpperEndless != null)
            {
                x_oSwipeRefreshLayout.setEnabled(true);
                x_oSwipeRefreshLayout.setOnRefreshListener(_srlOnRefreshListener);
            }
            else
            {
                x_oSwipeRefreshLayout.setEnabled(false);
                x_oSwipeRefreshLayout.setOnRefreshListener(null);
            }
        }
        return this;
    }

    public RecyclerViewBehavior setLowerEndlessThreshold(@NonNull int i)
    {
        synchronized (_oLock)
        {
            _iLowerEndlessThreshold = i > 0 ? i : 0;
        }
        return this;
    }
    public RecyclerViewBehavior setOnLowerEndlessListener(@Nullable IRVOnEndlessListener lst)
    {
        synchronized (_oLock)
        {
            _lsnOnLowerEndless = lst;

            if(_lsnOnLowerEndless != null)
                x_o.addOnScrollListener(_oOnScrollListener);
            else
                x_o.removeOnScrollListener(_oOnScrollListener);
        }

        return this;
    }

    @Nullable
    public static RecyclerViewBehavior from(@Nullable RecyclerView x_o)
    {
        return x_o != null ? new RecyclerViewBehavior(x_o) : null;
    }
}