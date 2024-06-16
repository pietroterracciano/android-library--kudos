package it.pietroterracciano.kudos.Constants;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.material.tabs.TabLayout;

import java.nio.charset.Charset;
import java.util.HashMap;

import it.pietroterracciano.kudos.Activities.AAppCompatActivity;
import it.pietroterracciano.kudos.Models.MotionEventModel;

public abstract class CClass
{
    @NonNull
    public static final Class<MaterialRippleLayout> MaterialRippleLayout = MaterialRippleLayout.class;
    @NonNull
    public static final Class<Window> Window = Window.class;
    @NonNull
    public static final Class<AAppCompatActivity> AAppCompatActivity = AAppCompatActivity.class;
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
    @NonNull
    public static final Class<Byte> Byte = Byte.class;
    @NonNull
    public static final Class<Byte[]> BytesArray = Byte[].class;
    @NonNull
    public static final Class<Charset> Charset = Charset.class;
    @NonNull
    public static final Class<String> String = String.class;
}
