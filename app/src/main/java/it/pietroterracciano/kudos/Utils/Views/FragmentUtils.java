package it.pietroterracciano.kudos.Utils.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public final class FragmentUtils
{
    @Nullable
    public static <T extends Fragment> T createInstance(Class<T> cls)
    {
        return ConstructorUtils.createInstance(ConstructorUtils.getDeclared(cls));
    }
}