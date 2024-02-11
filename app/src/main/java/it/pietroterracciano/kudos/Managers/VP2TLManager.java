package it.pietroterracciano.kudos.Managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import it.pietroterracciano.kudos.Interfaces.Action2;
import it.pietroterracciano.kudos.Listeners.IOnTabSelectedUnselectedListener;

public class VP2TLManager
{
    @NonNull
    private TabLayoutManager _mTabLayout;
    @NonNull
    private ViewPager2Manager _mViewPager2;

    private VP2TLManager(@NonNull ViewPager2 vp2, @NonNull TabLayout tl)
    {
        _mTabLayout = TabLayoutManager.build(tl);
        _mViewPager2 = ViewPager2Manager.build(vp2);

        _mTabLayout.x_oTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tlt)
            {
                _mViewPager2.setCurrentItem( _mTabLayout.getTabFragmentClass(tlt) );
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tlt)
            {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tlt)
            {
                _mViewPager2.setCurrentItem( _mTabLayout.getTabFragmentClass(tlt) );
            }
        });

        _mViewPager2.x_oViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
        {
            @Override
            public void onPageSelected(int i)
            {
                _mTabLayout.selectTab( _mViewPager2.getFragmentClass(i) );
            }
        });
    }

    public void setOnTabSelectedUnselectedListener(@Nullable IOnTabSelectedUnselectedListener lst)
    {
        _mTabLayout.setOnTabSelectedUnselectedListener(lst);
    }
    @Nullable
    public TabLayout.Tab adseFragmentClass(Class<? extends Fragment> cls)
    {
        boolean b = _mViewPager2.adseFragmentClass(cls);
        TabLayout.Tab tlt = _mTabLayout.adseTab(cls);
        return b && tlt != null ? tlt : null;
    }

    @Nullable
    public static VP2TLManager build(@Nullable ViewPager2 vp2, @Nullable TabLayout tl)
    {
        return vp2 != null && tl != null ? new VP2TLManager(vp2, tl) : null;
    }
}
