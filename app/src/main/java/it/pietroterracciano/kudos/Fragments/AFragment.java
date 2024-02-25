package it.pietroterracciano.kudos.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public abstract class AFragment extends Fragment
{
    @Nullable
    private View x_oView;
    protected abstract int onLayoutResourceIDRequest();

    @Override
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater li, @Nullable ViewGroup vg, @Nullable Bundle bnd)
    {
        super.onCreateView(li, vg, bnd);
        try { return (x_oView = li.inflate(onLayoutResourceIDRequest(), vg, false)); } catch (Exception ignored) {}
        return null;
    }

    @MainThread
    @Override
    public final void onViewCreated(@NonNull View v, @Nullable Bundle bnd)
    {
        super.onViewCreated(v, bnd);
        onBindView(bnd);
    }

    protected abstract void onBindView(@Nullable Bundle bnd);

    @Nullable
    public final <ViewType extends View> ViewType findViewById(@IdRes @NotNull int iResourceID)
    {
        return x_oView != null ? x_oView.findViewById(iResourceID) : null;
    }
}
