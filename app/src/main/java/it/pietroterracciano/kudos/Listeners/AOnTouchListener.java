package it.pietroterracciano.kudos.Listeners;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Constants.CClass;
import it.pietroterracciano.kudos.Managers.MotionEventManager;
import it.pietroterracciano.kudos.Models.MotionEventModel;
import it.pietroterracciano.kudos.Models.TouchEventModel;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public abstract class AOnTouchListener implements View.OnTouchListener
{
    @NonNull
    private MotionEventManager
        _mem;

    @Nullable
    private TouchEventModel
        _tem;

    public AOnTouchListener()
    {
        _mem = new MotionEventManager();
    }

    @Override
    public final boolean onTouch(View v, MotionEvent event)
    {
        _mem.collect(event);
        _tem = _mem.buildTouch();
        return _tem != null && onTouch(v, _tem);
    }

    public abstract boolean onTouch(View v, TouchEventModel tem);
}
