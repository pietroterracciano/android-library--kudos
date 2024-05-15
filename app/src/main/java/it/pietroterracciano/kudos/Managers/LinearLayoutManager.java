package it.pietroterracciano.kudos.Managers;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

public class LinearLayoutManager extends androidx.recyclerview.widget.LinearLayoutManager
{
    @NonNull
    private boolean
        _bCanScrollHorizontally,
        _bCanScrollVertically;

    public LinearLayoutManager canScrollVertically(boolean b)
    {
        _bCanScrollVertically = b;
        return this;
    }

    public LinearLayoutManager canScrollHorizontally(boolean b)
    {
        _bCanScrollHorizontally = b;
        return this;
    }

    public LinearLayoutManager(Context context) {
        super(context);
        _this();
    }

    public LinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        _this();
    }

    public LinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        _this();
    }

    private void _this()
    {
        _bCanScrollHorizontally = _bCanScrollVertically = true;
    }

    @Override
    public boolean canScrollHorizontally()
    {
        return _bCanScrollHorizontally && super.canScrollHorizontally();
    }

    @Override
    public boolean canScrollVertically()
    {
        return _bCanScrollVertically && super.canScrollVertically();
    }
}