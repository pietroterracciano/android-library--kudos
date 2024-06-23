package it.pietroterracciano.kudos.Modules.AndroidXModule.Behaviours;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import it.pietroterracciano.kudos.Modules.AndroidXModule.Utils.ActionBarUtils;

public final class FragmentBehaviour
{
    @Nullable
    private Fragment _frg;
    @NonNull
    private boolean _bIsNeedToRecreateView;
    @NonNull
    public FragmentBehaviour isNeedToRecreateView(@NonNull boolean b) { _bIsNeedToRecreateView = b; return this; }
    @NonNull
    public boolean isNeedToRecreateView() { return _bIsNeedToRecreateView; }

    public FragmentBehaviour(@Nullable Fragment frg)
    {
        _frg = frg;
    }
}
