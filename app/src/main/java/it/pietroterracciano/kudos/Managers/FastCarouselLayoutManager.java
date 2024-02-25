package it.pietroterracciano.kudos.Managers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.EVLayoutParam;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public final class FastCarouselLayoutManager extends LinearLayout
{
    @NonNull
    private int _iSpanCount;
    @NonNull
    private float _fWeight;

    public FastCarouselLayoutManager(Context context)
    {
        super(context);
    }

    public FastCarouselLayoutManager(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FastCarouselLayoutManager(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public FastCarouselLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setSpanCount(int i)
    {
        if (_iSpanCount == i) return;
        _iSpanCount = i;
        _fWeight = 1.0f / _iSpanCount;
        requestLayout();
    }

    @Override
    public void onViewAdded(@NonNull View v)
    {
        if(getOrientation() == HORIZONTAL)
        {
            ViewUtils.setLayoutParam(v, EVLayoutParam.Width, 0);
            ViewUtils.setLayoutParam(v, EVLayoutParam.Weight, _fWeight);
        }
        else
        {
            ViewUtils.setLayoutParam(v, EVLayoutParam.Height, 0);
            ViewUtils.setLayoutParam(v, EVLayoutParam.Weight, _fWeight);
        }

        super.onViewAdded(v);
    }
}
