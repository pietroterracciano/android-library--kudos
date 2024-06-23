package it.pietroterracciano.kudos.Modules.AndroidXModule.Behaviours;

import android.app.Activity;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import it.pietroterracciano.kudos.Behaviors.WindowBehaviour;
import it.pietroterracciano.kudos.Modules.AndroidXModule.Utils.ActionBarUtils;
import it.pietroterracciano.kudos.Utils.Views.WindowUtils;

public final class ActionBarBehaviour
{
    @Nullable
    private final ActionBar _ab;
    @NonNull
    public ActionBarBehaviour setDisplayShowHomeEnabled(@NonNull boolean b) { ActionBarUtils.setDisplayShowHomeEnabled(_ab, b); return this; }
    @NonNull
    public ActionBarBehaviour setDisplayUseLogoEnabled(@NonNull boolean b) { ActionBarUtils.setDisplayUseLogoEnabled(_ab, b); return this; }
    @NonNull
    public ActionBarBehaviour setDisplayShowTitleEnabled(@NonNull boolean b) { ActionBarUtils.setDisplayShowTitleEnabled(_ab, b); return this; }
    @NonNull
    public ActionBarBehaviour setDisplayShowCustomEnabled(@NonNull boolean b) { ActionBarUtils.setDisplayShowCustomEnabled(_ab, b); return this; }
    @NonNull
    public ActionBarBehaviour setCustomView(@NonNull @LayoutRes int i)
    {
        setDisplayShowCustomEnabled(true);
        ActionBarUtils.setCustomView(_ab, i);
        return this;
    }
    @NonNull
    public ActionBarBehaviour setCustomView(@NonNull @LayoutRes int i, @Nullable  ActionBar.LayoutParams lp)
    {
        setDisplayShowCustomEnabled(true);
        ActionBarUtils.setCustomView(_ab, i, lp);
        return this;
    }
    @NonNull
    public ActionBarBehaviour setCustomView(@Nullable View v)
    {
        setDisplayShowCustomEnabled(true);
        ActionBarUtils.setCustomView(_ab, v);
        return this;
    }
    @NonNull
    public ActionBarBehaviour setCustomView(@Nullable View v, @Nullable  ActionBar.LayoutParams lp)
    {
        setDisplayShowCustomEnabled(true);
        ActionBarUtils.setCustomView(_ab, v, lp);
        return this;
    }

    public ActionBarBehaviour(@Nullable ActionBar ab)
    {
        _ab = ab;
    }
}