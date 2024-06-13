package it.pietroterracciano.kudos.Behaviors;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DialogBehavior extends ADialogBehavior<DialogBehavior>
{
    protected DialogBehavior(@NonNull Dialog dlg)
    {
        super(dlg);
    }

    @Override
    @Nullable
    protected ViewGroup onFindRootViewGroup()
    {
        return findViewById(android.R.id.content);
    }

    @Override
    @Nullable
    protected View onFindRootView(@NonNull ViewGroup x_vgRoot)
    {
        return x_vgRoot.getChildAt(0);
    }

    @Override
    @Nullable
    protected View onFindInflatedView(@NonNull ViewGroup x_vgRoot)
    {
        return x_vgRoot.getChildAt(0);
    }

    @Nullable
    public static DialogBehavior from(@Nullable Dialog dlg)
    {
        return dlg != null ? new DialogBehavior(dlg) : null;
    }
}