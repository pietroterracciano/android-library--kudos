package it.pietroterracciano.kudos.Listeners;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public interface IOnTabSelectedUnselectedListener
{
    void onTabSelected(Class<? extends Fragment> cls, TabLayout.Tab tlt);
    void onTabUnselected(Class<? extends Fragment> cls, TabLayout.Tab tlt);
}
