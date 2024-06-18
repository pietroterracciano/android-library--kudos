package it.pietroterracciano.kudos.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;
import it.pietroterracciano.kudos.Utils.ListUtils;
import it.pietroterracciano.kudos.Utils.ObjectUtils;

public final class FragmentStateAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter
{
    @NonNull
    private List<Class<? extends Fragment>> _l;
    @NonNull
    private HashMap<Integer, Fragment> _hm;


    public FragmentStateAdapter() {this(Kudos.getFragmentManager(), Kudos.getLifecycle()); }
    public FragmentStateAdapter(@NonNull Lifecycle lifecycle) {this(Kudos.getFragmentManager(), lifecycle); _this(); }
    public FragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {super(fragmentActivity); _this(); }
    public FragmentStateAdapter(@NonNull Fragment fragment) { super(fragment); _this();  }
    public FragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) { super(fragmentManager, lifecycle); _this();  }

    private void _this()
    {
        _hm = new HashMap<>();
        _l = new ArrayList<>();
    }

    @NonNull
    public boolean adseItem(@Nullable Class<? extends Fragment> itm)
    {
        if(itm == null)
            return false;

        synchronized (_l)
        {
            int j = ListUtils.adse(_l, itm, false);
            if(j < -1) return false;
            else if(j < 0) notifyItemInserted(_l.size() -1);
            else notifyItemChanged(j);
            return true;
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(@NonNull int i)
    {
        synchronized (_l)
        {
            Fragment
                frg = _hm.get(i);

            if(frg != null)
                return frg;

            Class<? extends Fragment>
                cls = getItem(i);

            if(cls != null)
            {
                Constructor<? extends Fragment>
                    cns = ConstructorUtils.getDeclared(cls);

                frg = ConstructorUtils.createInstance(cns);
            }

            if(frg == null)
                frg = new Fragment();

            _hm.put(i, frg);

            return frg;
        }
    }

    @Nullable
    public <T extends Fragment> Class<T> getItem(@NonNull int i)
    {
        synchronized (_l)
        {
            return ObjectUtils.cast(ListUtils.get(_l, i));
        }
    }

    @Nullable
    public Integer getItemIndex(Class<? extends Fragment> cls)
    {
        synchronized (_l)
        {
            return _l.indexOf(cls);
        }
    }

    @Override
    @NonNull
    public int getItemCount()
    {
        synchronized (_l)
        {
            return _l.size();
        }
    }
}