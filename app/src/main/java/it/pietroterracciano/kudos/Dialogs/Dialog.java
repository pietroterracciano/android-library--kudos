package it.pietroterracciano.kudos.Dialogs;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import it.pietroterracciano.kudos.Behaviors.ADialogBehavior;
import it.pietroterracciano.kudos.Behaviors.DialogBehavior;
import it.pietroterracciano.kudos.Interfaces.IDialog;
import it.pietroterracciano.kudos.Kudos;

public class Dialog
    extends android.app.Dialog
    implements IDialog
{
    @NonNull
    private DialogBehavior _dlgBehavior;

    @NonNull
    public DialogBehavior getBaseBehavior()
    {
        return _dlgBehavior;
    }

    public Dialog()
    {
        this(Kudos.getContext());
    }
    public Dialog(@NonNull Context context)
    {
        super(context);
    }
    public Dialog(@StyleRes @NonNull int iStyleResourceID)
    {
        this(Kudos.getContext(), iStyleResourceID);
    }
    public Dialog(@NonNull Context context, @StyleRes @NonNull int themeResId)
    {
        super(context, themeResId);
    }

    protected Dialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener)
    {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onContentChanged()
    {
        super.onContentChanged();
        _dlgBehavior = DialogBehavior.from(this);
    }
}