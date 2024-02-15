package it.pietroterracciano.kudos.Utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import it.pietroterracciano.kudos.Kudos;

public abstract class KeyboardUtils
{
    private static InputMethodManager
            _srvInputMethodManager;

    public static boolean hide(View v)
    {
        return hide(Kudos.getContext(), v);
    }

    public static boolean hide(Context cnt, View v)
    {
        if(cnt == null || v == null)
            return false;

        v.post(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        v.clearFocus();
                        _srvInputMethodManager = (InputMethodManager)cnt.getSystemService(Context.INPUT_METHOD_SERVICE);
                        _srvInputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
        );

        return true;
    }
}
