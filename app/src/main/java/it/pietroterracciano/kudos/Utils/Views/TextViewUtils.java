package it.pietroterracciano.kudos.Utils.Views;

import android.widget.TextView;

import it.pietroterracciano.kudos.Utils.LayoutParams.LayoutParamsUtils;

public abstract class TextViewUtils extends LayoutParamsUtils
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
