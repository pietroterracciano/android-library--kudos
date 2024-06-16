package it.pietroterracciano.kudos.Behaviors;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Utils.Views.WindowUtils;

public final class WindowBehaviour
{
    @Nullable
    private final Window _w;
    @Nullable
    public final DecorViewBehaviour DecorViewBehaviour;

    @NonNull
    public WindowBehaviour addFlagDrawsSystemBarBackgrounds() { return addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); }
    @NonNull
    public WindowBehaviour addFlagTranslucentStatus() { return addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); }
    @NonNull
    public WindowBehaviour addFlagTranslucentNavigation() { return addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); }
    @NonNull
    public WindowBehaviour addFlags(@NonNull int i) { WindowUtils.addFlags(_w, i); return this; }

    @NonNull
    public WindowBehaviour clearFlagDrawsSystemBarBackgrounds() { return clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); }
    @NonNull
    public WindowBehaviour clearFlagTranslucentStatus(){ return clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); }
    @NonNull
    public WindowBehaviour clearFlagTranslucentNavigation() { return clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); }
    @NonNull
    public WindowBehaviour clearFlags(@NonNull int i) { WindowUtils.clearFlags(_w, i); return this; }

    @NonNull
    public WindowBehaviour setStatusBarColor(@NonNull @ColorInt int i) { WindowUtils.setStatusBarColor(_w, i); return this; }
    @NonNull
    public WindowBehaviour setNavigationBarColor(@NonNull @ColorInt  int i) { WindowUtils.setNavigationBarColor(_w, i); return this; }

    public WindowBehaviour(@Nullable Window w)
    {
        _w = w;
        DecorViewBehaviour = new it.pietroterracciano.kudos.Behaviors.DecorViewBehaviour(w != null ? w.getDecorView() : null);
    }
}