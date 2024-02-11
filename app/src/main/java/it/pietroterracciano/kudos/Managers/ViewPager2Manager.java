package it.pietroterracciano.kudos.Managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import it.pietroterracciano.kudos.Adapters.FragmentStateAdapter;

public class ViewPager2Manager
{
    @NonNull
    private final FragmentStateAdapter _adpFragmentState;
    @NonNull
    public final ViewPager2 x_oViewPager2;

    private ViewPager2Manager(@NonNull ViewPager2 vp2)
    {
        x_oViewPager2 = vp2;
        _adpFragmentState = new FragmentStateAdapter();
        x_oViewPager2.setAdapter(_adpFragmentState);
    }

    @NonNull
    public boolean adseFragmentClass(@Nullable Class<? extends Fragment> cls)
    {
        return _adpFragmentState.adseItem(cls);
    }

    public void setCurrentItem(@Nullable Class<? extends Fragment> cls)
    {
        Integer i = getFragmentClassIndex(cls);
        if(i == null) return;
        x_oViewPager2.setCurrentItem(i);
    }

    @Nullable
    public Integer getFragmentClassIndex(@Nullable Class<? extends Fragment> cls)
    {
        return _adpFragmentState.getItemIndex(cls);
    }

    @Nullable
    public <T extends Fragment> Class<T> getFragmentClass(@NonNull int i)
    {
        return _adpFragmentState.getItem(i);
    }

    @Nullable
    public static ViewPager2Manager build(@Nullable ViewPager2 vp2)
    {
        return vp2 != null ? new ViewPager2Manager(vp2) : null;
    }
}
