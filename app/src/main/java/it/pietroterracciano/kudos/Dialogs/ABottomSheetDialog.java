package it.pietroterracciano.kudos.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Behaviors.BottomSheetDialogBehavior;
import it.pietroterracciano.kudos.Behaviors.DialogBehaviour;
import it.pietroterracciano.kudos.Interfaces.IDialog;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public abstract class ABottomSheetDialog extends BottomSheetDialog
{
    @NonNull
    private static final Constructor<BottomSheetDialogBehavior>
        __cnsBottomSheetDialogBehaviour;

    static
    {
        __cnsBottomSheetDialogBehaviour = ConstructorUtils.getDeclared(BottomSheetDialogBehavior.class, BottomSheetDialog.class);
    }

    @NonNull
    private BottomSheetDialogBehavior _bsdb;

    @NonNull
    public final BottomSheetDialogBehavior getBottomSheetDialogBehaviour()
    {
        return _bsdb;
    }

    public ABottomSheetDialog()
    {
        this(Kudos.getContext());
    }
    public ABottomSheetDialog(@NonNull Context cnt)
    {
        super(cnt);
        _init();
    }
    public ABottomSheetDialog(@StyleRes @NonNull int i)
    {
        this(Kudos.getContext(), i);
    }
    public ABottomSheetDialog(@NonNull Context cnt, @StyleRes @NonNull int i)
    {
        super(cnt, i);
        _init();
    }
    protected ABottomSheetDialog(@NonNull Context cnt, boolean bCancelable, @Nullable OnCancelListener lstOnCancel)
    {
        super(cnt, bCancelable, lstOnCancel);
        _init();
    }
    private void _init()
    {
        _bsdb = ConstructorUtils.createInstance(__cnsBottomSheetDialogBehaviour, this);
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
        _bsdb.onContentChange();
    }
}
