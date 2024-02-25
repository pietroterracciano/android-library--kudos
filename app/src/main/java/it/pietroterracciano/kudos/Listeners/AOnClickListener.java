package it.pietroterracciano.kudos.Listeners;

import android.view.View;
import android.view.ViewConfiguration;

public abstract class AOnClickListener implements View.OnClickListener
{
    private long _l0 = 0;
    @Override
    public final void onClick(View v)
    {
        long l1 = System.currentTimeMillis();
        if ( (l1 - _l0) < ViewConfiguration.getDoubleTapTimeout())
            onDoubleClick(v);
        else
            onSingleClick(v);

        _l0 = l1;
    }

    protected abstract void onDoubleClick(View v);
    protected abstract void onSingleClick(View v);
}
