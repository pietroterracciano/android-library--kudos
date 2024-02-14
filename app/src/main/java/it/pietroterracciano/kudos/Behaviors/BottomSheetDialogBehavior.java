package it.pietroterracciano.kudos.Behaviors;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import it.pietroterracciano.kudos.Enums.EVLayoutParam;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public class BottomSheetDialogBehavior extends ADialogBehavior<BottomSheetDialogBehavior>
{
    @NonNull
    private boolean _bIsAttached2Bottom;

    public BottomSheetDialogBehavior isAttached2Bottom(@NonNull boolean bIsAttached2Bottom)
    {
        _bIsAttached2Bottom = bIsAttached2Bottom;
        return this;
    }

    protected BottomSheetDialogBehavior(@NonNull BottomSheetDialog bsd)
    {
        super(bsd);

        View x_vInflated = getInflatedView();

        if(x_vInflated == null)
            return;

        BottomSheetBehavior<FrameLayout> bsb = bsd.getBehavior();
        bsb.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()
        {
            @NonNull
            private boolean _bCanSlide;
            @NonNull
            private int _iVIY, _iVIHeight;

            @Override
            public void onStateChanged(@NonNull View v, @NonNull int i)
            {
                switch (i)
                {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        _bCanSlide = _bIsAttached2Bottom;

                        if(!_bCanSlide) return;

                        int[] a = new int[2];
                        x_vInflated.getLocationOnScreen(a);

                        _iVIY = a[1];
                        _iVIHeight = x_vInflated.getMeasuredHeight();

                        Log.d("pietroterracciano95", "x_vInflated.getMeasuredHeight() "+_iVIHeight);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        ViewUtils.setLayoutParam(x_vInflated, EVLayoutParam.Height, bsb.getPeekHeight());
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        ViewUtils.setLayoutParam(x_vInflated, EVLayoutParam.Height, bsb.getMaxHeight());
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View v, float f)
            {
                if(!_bCanSlide || f < 0.0f) return;
                int[] a = new int[2];
                x_vInflated.getLocationOnScreen(a);
                ViewUtils.setLayoutParam(x_vInflated, EVLayoutParam.Height, _iVIHeight - a[1] + _iVIY);
            }
        });
    }
    @Nullable
    public static BottomSheetDialogBehavior from(@Nullable BottomSheetDialog bsd)
    {
        return bsd != null ? new BottomSheetDialogBehavior(bsd) : null;
    }

    @Override
    @Nullable
    protected ViewGroup onFindRootViewGroup()
    {
        return findViewById(com.google.android.material.R.id.design_bottom_sheet);
    }

    @Override
    @Nullable
    protected View onFindRootView(@NonNull ViewGroup x_vgRoot)
    {
        return x_vgRoot;
    }

    @Override
    @Nullable
    protected View onFindInflatedView(@NonNull ViewGroup x_vgRoot)
    {
        return x_vgRoot.getChildAt(0);
    }
}