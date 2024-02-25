package it.pietroterracciano.kudos.Constants;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

import it.pietroterracciano.kudos.Models.MotionEventModel;

public abstract class CClass
{
    @NonNull
    public static final Class<Object> Object = Object.class;
    @NonNull
    public static final Class<View> View = View.class;
    @NonNull
    public static final Class<ViewGroup> ViewGroup = ViewGroup.class;
    @NonNull
    public static final Class<HashMap> HashMap = HashMap.class;
    @NonNull
    public static final Class<Integer> Int = int.class;
    @NonNull
    public static final Class<Integer> Integer = Integer.class;
    @NonNull
    public static final Class<ViewPager2> ViewPager2 = ViewPager2.class;
    @NonNull
    public static final Class<TabLayout> TabLayout = TabLayout.class;
    @NonNull
    public static final Class<MotionEventModel> MotionEventModel = MotionEventModel.class;
}
