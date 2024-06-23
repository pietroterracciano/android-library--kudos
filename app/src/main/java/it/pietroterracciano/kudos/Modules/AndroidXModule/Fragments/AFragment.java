package it.pietroterracciano.kudos.Modules.AndroidXModule.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.jetbrains.annotations.NotNull;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Modules.AndroidXModule.Activities.AAppCompatActivity;
import it.pietroterracciano.kudos.Modules.AndroidXModule.Behaviours.ActionBarBehaviour;
import it.pietroterracciano.kudos.Modules.AndroidXModule.Behaviours.FragmentBehaviour;
import it.pietroterracciano.kudos.Utils.LayoutInflaterUtils;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public abstract class AFragment extends Fragment
{
    @Nullable
    private View _v;
    @NonNull
    private final FragmentBehaviour
        _fb;

    @LayoutRes
    @Nullable
    protected abstract Integer _onLayoutResourceToInflate();
    @IdRes
    @Nullable
    protected abstract Integer _onToolbarIDToInflate();

    public AFragment()
    {
        _onFragmentBehaviourReceive(_fb = new FragmentBehaviour(this));
    }

    protected abstract void _onFragmentBehaviourReceive(@NonNull FragmentBehaviour fb);

    @Override
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater li, @Nullable ViewGroup vg, @Nullable Bundle bnd)
    {
        if(_v != null && !_fb.isNeedToRecreateView())
            return _v;

        Integer ilr2i = _onLayoutResourceToInflate();

        if(ilr2i == null)
            return null;

        try { _v = li.inflate(ilr2i, vg, false); } catch (Exception ignored) {}

        if(_v == null)
            return null;

        _onViewCreate(bnd);

        FragmentActivity fa = getActivity();
        if(!(fa instanceof AAppCompatActivity))
            return _v;

        AAppCompatActivity aca = (AAppCompatActivity)fa;
        Integer itid2i = _onToolbarIDToInflate();
        if(itid2i == null) return _v;
        Toolbar tlb = findViewById(itid2i);
        aca.setSupportActionBar(tlb);
        _onActionBarBehaviourReceive(new ActionBarBehaviour(aca.getSupportActionBar()));

        return _v;
    }

    protected abstract void _onActionBarBehaviourReceive(@NonNull ActionBarBehaviour abb);

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
    public final <ViewType extends View> ViewType findViewById(@IdRes @NotNull int i)
    {
        return ViewUtils.findViewById(_v, i);
    }

    @Nullable
    public final <T extends Fragment> T inflate(@IdRes @NotNull int ivg, @Nullable Class<T> cls)
    {
        return inflate(findViewById(ivg), cls);
    }
    @Nullable
    public final <T extends Fragment> T inflate(@Nullable ViewGroup vg, @Nullable Class<T> cls)
    {
        return LayoutInflaterUtils.inflate(vg, cls, true);
    }
}
