package it.pietroterracciano.kudos.Handlers;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Utils.ObjectUtils;

public class FastViewHandler
{
    @NonNull
    private final HashMap<Integer, View> _hmIDs2Views;

    public FastViewHandler(@Nullable View oView)
    {
        _hmIDs2Views = new HashMap<>();
        addView2Hash(oView);
    }
    
    private void addView2Hash(@Nullable View oView)
    {
        if(oView == null)
            return;

        int iVID = oView.getId();
        if(iVID != View.NO_ID) _hmIDs2Views.put(iVID, oView);

        if(!(oView instanceof ViewGroup))
            return;

        ViewGroup oViewGroup = (ViewGroup) oView;

        int j = oViewGroup.getChildCount();

        for(int i=0; i<j; i++)
            addView2Hash(oViewGroup.getChildAt(i));
    }

    public <T extends View> T getViewByID(@NonNull @IdRes int iID)
    {
        return ObjectUtils.cast(_hmIDs2Views.get(iID));
    }
}
