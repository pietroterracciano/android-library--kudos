package it.pietroterracciano.kudos.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import it.pietroterracciano.kudos.Controllers.LayoutInflaterController;

public abstract class AFragment extends Fragment
{
    @Nullable
    private View _x_v;
    @LayoutRes
    @Nullable
    protected abstract Integer _onLayoutResourceIDInflate();

    @Override
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater li, @Nullable ViewGroup vg, @Nullable Bundle bnd)
    {
        super.onCreateView(li, vg, bnd);
        Integer ilrid = _onLayoutResourceIDInflate();
        if(ilrid != null)
            try { return (_x_v = li.inflate(ilrid, vg, false)); } catch (Exception ignored) {}
        return null;
    }

    @MainThread
    @Override
    public final void onViewCreated(@Nullable View v, @Nullable Bundle bnd)
    {
        if(v == null) return;
        super.onViewCreated(v, bnd);
        _onViewCreate(bnd);
    }

    protected abstract void _onViewCreate(@Nullable Bundle bnd);

    @Nullable
    public final <ViewType extends View> ViewType findViewById(@IdRes @NotNull int iResourceID)
    {
        return _x_v != null ? _x_v.findViewById(iResourceID) : null;
    }

    @Nullable
    public final <T extends Fragment> T inflate(@Nullable ViewGroup x_vg, @Nullable Class<T> cls)
    {
        return LayoutInflaterController.inflate(x_vg, cls, true);
    }

    @Nullable
    public final <T extends Fragment> T inflate(@IdRes @NotNull int ivg, @Nullable Class<T> cls)
    {
        ViewGroup x_vg = findViewById(ivg);
        return inflate(x_vg, cls);
    }
}
