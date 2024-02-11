package it.pietroterracciano.kudos.Managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

import it.pietroterracciano.kudos.Interfaces.Action1;
import it.pietroterracciano.kudos.Interfaces.Action2;
import it.pietroterracciano.kudos.Listeners.IOnTabSelectedUnselectedListener;
import it.pietroterracciano.kudos.Utils.ObjectUtils;

public class TabLayoutManager
{
    @NonNull
    private final Object _oLock;
    @NonNull
    public final TabLayout x_oTabLayout;
    @NonNull
    private final HashMap<Class<? extends Fragment>, Integer> _hmClassFragments2Indexes;
    @NonNull
    private final HashMap<TabLayout.Tab, Class<? extends Fragment>> _hmTLTabs2ClassFragments;
    @Nullable
    private IOnTabSelectedUnselectedListener _lstOnTabSelectedUnselected;

    private TabLayoutManager(@NonNull TabLayout tl)
    {
        _oLock = new Object();
        _hmClassFragments2Indexes = new HashMap<>();
        _hmTLTabs2ClassFragments = new HashMap<>();
        x_oTabLayout = tl;
        x_oTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tlt)
            {
                onTabSelectedUnselected(tlt, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tlt)
            {
                onTabSelectedUnselected(tlt, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tlt)
            {
                onTabSelected(tlt);
            }
        });
    }

    private void onTabSelectedUnselected(@NonNull TabLayout.Tab tlt, boolean b)
    {
        Class<? extends Fragment> cls = getTabFragmentClass(tlt);
        if(cls == null) return;

        synchronized (_oLock)
        {
            if(_lstOnTabSelectedUnselected == null) return;
            else if(b) _lstOnTabSelectedUnselected.onTabSelected(cls, tlt);
            else _lstOnTabSelectedUnselected.onTabUnselected(cls, tlt);
        }
    }

    public void setOnTabSelectedUnselectedListener(@Nullable IOnTabSelectedUnselectedListener lst)
    {
        synchronized (_oLock)
        {
            _lstOnTabSelectedUnselected = lst;
        }
    }

    @Nullable
    public TabLayout.Tab adseTab(@Nullable Class<? extends Fragment> cls)
    {
        if(cls == null)
            return null;

        synchronized (_oLock)
        {
            Integer i = _hmClassFragments2Indexes.get(cls);

            if(i == null)
            {
                TabLayout.Tab tlt = x_oTabLayout.newTab();
                x_oTabLayout.addTab(tlt);
                i = x_oTabLayout.getTabCount() - 1;
                _hmClassFragments2Indexes.put(cls, i);
                _hmTLTabs2ClassFragments.put(tlt, cls);
            }

            return x_oTabLayout.getTabAt(i);
        }
    }

    public void selectTab(Class<? extends Fragment> cls)
    {
        x_oTabLayout.selectTab(getTab(cls));
    }

    @Nullable
    public TabLayout.Tab getTab(@Nullable Class<? extends Fragment> cls)
    {
        Integer i = getTabIndex(cls);
        return i != null ? x_oTabLayout.getTabAt(i) : null;
    }

    @Nullable
    public TabLayout.Tab getTab(@NonNull int i)
    {
        synchronized (_oLock)
        {
            return x_oTabLayout.getTabAt(i);
        }
    }

    @Nullable
    public Integer getTabIndex(@Nullable Class<? extends Fragment> cls)
    {
        synchronized (_oLock)
        {
            return _hmClassFragments2Indexes.get(cls);
        }
    }

    @Nullable
    public <T extends Fragment> Class<T> getTabFragmentClass(@Nullable TabLayout.Tab tlt)
    {
        synchronized (_oLock)
        {
            return ObjectUtils.cast(_hmTLTabs2ClassFragments.get(tlt));
        }
    }

    @Nullable
    public static TabLayoutManager build(@Nullable TabLayout tl)
    {
        return tl != null ? new TabLayoutManager(tl) : null;
    }
}
