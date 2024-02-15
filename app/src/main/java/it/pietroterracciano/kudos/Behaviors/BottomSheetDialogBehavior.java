package it.pietroterracciano.kudos.Behaviors;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.security.Key;

import it.pietroterracciano.kudos.Enums.EVLayoutParam;
import it.pietroterracciano.kudos.Utils.KeyboardUtils;
import it.pietroterracciano.kudos.Utils.ScreenUtils;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public class BottomSheetDialogBehavior extends ADialogBehavior<BottomSheetDialogBehavior>
{
    @NonNull
    private final Rect _r;
    @NonNull
    private boolean _bIsAttached2Bottom;
    @Nullable
    private final View x_vRoot, x_vInflated;
    @NonNull
    private final BottomSheetBehavior<FrameLayout> _bsb;

    public BottomSheetDialogBehavior isAttached2Bottom(@NonNull boolean bIsAttached2Bottom)
    {
        _bIsAttached2Bottom = bIsAttached2Bottom;
        return this;
    }

    protected BottomSheetDialogBehavior(@NonNull BottomSheetDialog bsd)
    {
        super(bsd);

        _bsb = bsd.getBehavior();
        _r = new Rect();
        x_vRoot = getRootView();
        x_vInflated = getInflatedView();

        if(
            x_vRoot == null
            || x_vInflated == null
        )
            return;

        x_vRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @NonNull
            private final Rect _r = new Rect();
            @NonNull
            private int _i, _j;

            @Override
            public void onGlobalLayout()
            {
                x_vRoot.getWindowVisibleDisplayFrame(_r);
                _i = ScreenUtils.getHeight();
                _j = _i - _r.height();

                if (_j > 0.20d * _i)
                {
                    _bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
                    invalidateX();
                }
                else
                    invalidateX();
            }
        });


        x_vRoot.post(new Runnable()
        {
            @Override
            public void run()
            {
                invalidateX();
            }
        });

        _bsb.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()
        {
            @NonNull
            private float _f;

            @Override
            public void onStateChanged(@NonNull View v, @NonNull int i)
            {
                switch (i)
                {
                    case BottomSheetBehavior.STATE_EXPANDED:
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                    default:
                        KeyboardUtils.hide(x_vInflated);
                        break;
                }

                _f = Float.MAX_VALUE;
                invalidateX();
            }

            @Override
            public void onSlide(@NonNull View v, float f)
            {
                if(f < 0.0d && _f > f)
                {
                    _f = f;
                    return;
                }

                invalidateX();
            }
        });
    }

    private void invalidateX()
    {
        if(!_bIsAttached2Bottom) return;
        x_vRoot.getLocalVisibleRect(_r);
        ViewUtils.setLayoutParam(x_vInflated, EVLayoutParam.Height, _r.height());
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