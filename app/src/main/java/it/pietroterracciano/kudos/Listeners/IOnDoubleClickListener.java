package it.pietroterracciano.kudos.Listeners;

import android.view.View;
import android.view.ViewConfiguration;

public abstract class IOnDoubleClickListener implements View.OnClickListener
{
    private static final long DOUBLE_CLICK_TIME_DELTA = ViewConfiguration.getDoubleTapTimeout();

    long lastClickTime = 0;
    @Override
    public void onClick(View v)
    {
        long clickTime = System.currentTimeMillis();
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA)
            onDoubleClick(v);
        else
            onSingleClick(v);

        lastClickTime = clickTime;
    }

    protected abstract void onDoubleClick(View v);
    protected abstract void onSingleClick(View v);


}
