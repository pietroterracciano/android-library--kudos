package it.pietroterracciano.kudos.Behaviors;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.EVLayoutParam;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public final class DecorViewBehaviour
{
    @Nullable
    private final View _v;

    @NonNull
    public DecorViewBehaviour addSystemUiFlagFullscreen() { return addFlagsOnSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); }
    @NonNull
    public DecorViewBehaviour addSystemUiFlagLayoutStable() { return addFlagsOnSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE); }
    @NonNull
    public DecorViewBehaviour addSystemUiFlagLightStatusBar() { return addFlagsOnSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); }
    @NonNull
    public DecorViewBehaviour addSystemUiFlagLightNavigationBar() { return addFlagsOnSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR); }
    @NonNull
    public DecorViewBehaviour addFlagsOnSystemUiVisibility(@NonNull int i) { ViewUtils.addFlagsOnSystemUiVisibility(_v, i); return this; }

    @NonNull
    public DecorViewBehaviour clearSystemUiFlagFullscreen() { return clearFlagsOnSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); }
    @NonNull
    public DecorViewBehaviour clearSystemUiFlagLayoutStable() { return clearFlagsOnSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE); }
    @NonNull
    public DecorViewBehaviour clearSystemUiFlagLightStatusBar() { return clearFlagsOnSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); }
    @NonNull
    public DecorViewBehaviour clearSystemUiFlagLightNavigationBar() { return clearFlagsOnSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR); }
    @NonNull
    public DecorViewBehaviour clearFlagsOnSystemUiVisibility(@NonNull int i) { ViewUtils.clearFlagsOnSystemUiVisibility(_v, i); return this; }

    public DecorViewBehaviour(@Nullable View v) { _v = v; }
}
