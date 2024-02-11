package it.pietroterracciano.kudos.Dialogs;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import it.pietroterracciano.kudos.Behaviors.DialogBaseBehavior;
import it.pietroterracciano.kudos.Interfaces.IDialog;
import it.pietroterracciano.kudos.Kudos;

public class BottomSheetDialog
    extends com.google.android.material.bottomsheet.BottomSheetDialog
    implements IDialog
{
    @NonNull
    private DialogBaseBehavior _dlgBehavior;

    @NonNull
    public DialogBaseBehavior getBaseBehavior()
    {
        return _dlgBehavior;
    }
    public BottomSheetDialog()
    {
        this(Kudos.getContext());
    }

    public BottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public BottomSheetDialog(@NonNull Context context, @StyleRes @NonNull int theme) {
        super(context, theme);
    }

    protected BottomSheetDialog(@NonNull Context context, @NonNull boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onContentChanged()
    {
        _dlgBehavior = DialogBaseBehavior.from(this);
    }
}
