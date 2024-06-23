package it.pietroterracciano.kudos.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Behaviors.ADialogBehavior;
import it.pietroterracciano.kudos.Behaviors.BottomSheetDialogBehavior;
import it.pietroterracciano.kudos.Behaviors.DialogBehaviour;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public abstract class ADialog extends Dialog
{
    @NonNull
    private static final Constructor<DialogBehaviour>
        __cnsDialogBehaviour;

    static
    {
        __cnsDialogBehaviour = ConstructorUtils.getDeclared(DialogBehaviour.class, Dialog.class);
    }

    @NonNull
    private DialogBehaviour _db;

    @NonNull
    public final DialogBehaviour getDialogBehaviour()
    {
        return _db;
    }

    public ADialog()
    {
        this(Kudos.getContext());
    }
    public ADialog(@NonNull Context cnt)
    {
        super(cnt);
        _init();
    }
    public ADialog(@StyleRes @NonNull int i)
    {
        this(Kudos.getContext(), i);
    }
    public ADialog(@NonNull Context cnt, @StyleRes @NonNull int i)
    {
        super(cnt, i);
        _init();
    }
    protected ADialog(@NonNull Context cnt, boolean bCancelable, @Nullable OnCancelListener lstOnCancel)
    {
        super(cnt, bCancelable, lstOnCancel);
        _init();
    }
    private void _init()
    {
        _db = ConstructorUtils.createInstance(__cnsDialogBehaviour, this);
    }

    @Override
    protected final void onCreate(Bundle bnd)
    {
        super.onCreate(bnd);
        _onCreateReceive(bnd);
    }

    protected abstract void _onCreateReceive(Bundle bnd);

    @Override
    protected final void onStart()
    {
        super.onStart();
        _onStartReceive();
    }

    protected abstract void _onStartReceive();

    @Override
    protected final void onStop()
    {
        super.onStop();
        _onStopReceive();
    }

    protected abstract void _onStopReceive();

    @Override
    public final void onContentChanged()
    {
        super.onContentChanged();
        _db.onContentChange();
    }
}
