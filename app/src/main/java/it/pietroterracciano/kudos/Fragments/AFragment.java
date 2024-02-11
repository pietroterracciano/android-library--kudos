package it.pietroterracciano.kudos.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class AFragment extends Fragment
{
    protected abstract int onLayoutResourceIDRequest();

    @Override
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater li, @Nullable ViewGroup vg, @Nullable Bundle bnd)
    {
        super.onCreateView(li, vg, bnd);
        try { return li.inflate(onLayoutResourceIDRequest(), vg, false); } catch (Exception ignored) {}
        return null;
    }

    @MainThread
    @Override
    public final void onViewCreated(@NonNull View v, @Nullable Bundle bnd)
    {
        super.onViewCreated(v, bnd);
        onBindView(v, bnd);
    }

    protected abstract void onBindView(@NonNull View v, @Nullable Bundle bnd);
}
