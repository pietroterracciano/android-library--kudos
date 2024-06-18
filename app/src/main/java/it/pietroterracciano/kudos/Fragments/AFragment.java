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
    private View _v;
    @LayoutRes
    @Nullable
    protected abstract Integer _onLayoutResourceIDInflate();

    @Override
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater li, @Nullable ViewGroup vg, @Nullable Bundle bnd)
    {
        if(_v != null)
            return _v;

        Integer ilrid = _onLayoutResourceIDInflate();
        if(ilrid != null)
            try { _v = li.inflate(ilrid, vg, false); } catch (Exception ignored) {}

        if(_v != null)
            _onViewCreate(bnd);

        return _v;
    }

    @MainThread
    @Override
    public final void onViewCreated(@Nullable View v, @Nullable Bundle bnd)
    {
        if(v == null) return;
        _onViewBind(bnd);
    }

    protected abstract void _onViewCreate(@Nullable Bundle bnd);
    protected abstract void _onViewBind(@Nullable Bundle bnd);

    @Nullable
    public final <ViewType extends View> ViewType findViewById(@IdRes @NotNull int iResourceID)
    {
        return _v != null ? _v.findViewById(iResourceID) : null;
    }

    @Nullable
    public final <T extends Fragment> T inflate(@IdRes @NotNull int ivg, @Nullable Class<T> cls)
    {
        return inflate(findViewById(ivg), cls);
    }
    @Nullable
    public final <T extends Fragment> T inflate(@Nullable ViewGroup vg, @Nullable Class<T> cls)
    {
        return LayoutInflaterController.inflate(vg, cls, true);
    }
}
