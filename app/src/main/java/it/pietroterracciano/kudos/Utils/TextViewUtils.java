package it.pietroterracciano.kudos.Utils;

import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.EVProperty;

public abstract class TextViewUtils extends BaseViewUtils
{
    public static boolean setText(TextView tv, Object o)
    {
        return o != null && setText(tv, o.toString());
    }
    public static boolean setText(TextView tv, String s)
    {
        if(tv == null) return false;
        tv.setText(s);
        return true;
    }
}
