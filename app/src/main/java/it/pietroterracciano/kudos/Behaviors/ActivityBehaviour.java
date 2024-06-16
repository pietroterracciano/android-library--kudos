package it.pietroterracciano.kudos.Behaviors;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class ActivityBehaviour
{
    @Nullable
    private final Activity _act;
    @Nullable
    public final WindowBehaviour WindowBehaviour;

    public ActivityBehaviour(@Nullable Activity act)
    {
        _act = act;
        WindowBehaviour = new it.pietroterracciano.kudos.Behaviors.WindowBehaviour(_act != null ? act.getWindow() : null);
    }
}