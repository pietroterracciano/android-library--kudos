package it.pietroterracciano.kudos.Behaviors;

import android.app.Dialog;
import android.view.View;

import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Utils.Dialogs.DialogUtils;

public final class DialogBehaviour extends ADialogBehavior<DialogBehaviour>
{
    @Nullable
    private final Dialog
        _dlg;

    private DialogBehaviour(@Nullable Dialog dlg)
    {
        super(dlg);
        _dlg = dlg;
    }

    @Override
    protected void _onContentChangeReceive() { }

    @Override
    @Nullable
    protected View _onFindRootView()
    {
        return DialogUtils.getRootView(_dlg);
    }

    @Override
    @Nullable
    protected View _onFindInflatedView()
    {
        return DialogUtils.getInflatedView(_dlg);
    }
}