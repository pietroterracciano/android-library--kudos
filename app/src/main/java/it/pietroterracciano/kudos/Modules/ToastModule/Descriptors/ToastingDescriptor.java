package it.pietroterracciano.kudos.Modules.ToastModule.Descriptors;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Modules.ToastModule.Enums.EToastDuration;

public final class ToastingDescriptor
{
    @Nullable
    public String Text;
    @Nullable
    public View View;
    @Nullable
    public Context Context;
    @Nullable
    public EToastDuration Duration;

    private ToastingDescriptor() {}

    @NonNull
    public ToastingDescriptor clone()
    {
        ToastingDescriptor dp = new ToastingDescriptor();
        dp.Text = Text;
        dp.View = View;
        dp.Duration = Duration;
        dp.Context = Context;
        return dp;
    }
}
