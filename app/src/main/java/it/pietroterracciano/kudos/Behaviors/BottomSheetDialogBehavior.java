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
import it.pietroterracciano.kudos.R;
import it.pietroterracciano.kudos.Utils.Dialogs.BottomSheetDialogUtils;
import it.pietroterracciano.kudos.Utils.KeyboardUtils;
import it.pietroterracciano.kudos.Utils.ScreenUtils;
import it.pietroterracciano.kudos.Utils.Views.ViewGroupUtils;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public class BottomSheetDialogBehavior extends ADialogBehavior<BottomSheetDialogBehavior>
{
    @Nullable
    private View _x_vSticky;
    @Nullable
    private ViewGroup _x_vgContainer;
    @NonNull
    private final BottomSheetBehavior<FrameLayout> _bsb;
    @NonNull
    private final BottomSheetDialog _bsd;

    @Nullable
    public ViewGroup getContainerViewGroup()
    {
        return _x_vgContainer;
    }

    public BottomSheetDialogBehavior setStickyView(@Nullable View v)
    {
        _x_vSticky = v;
        _invalidateContainerStickyView();
        return this;
    }

    private BottomSheetDialogBehavior(@NonNull  BottomSheetDialog bsd)
    {
        super(bsd);

        _bsd = bsd;
        _bsb = bsd.getBehavior();
    }

    @Override
    protected final void _onContentChangeReceive()
    {
        _invalidateContainerView();
        _invalidateContainerStickyView();
    }

    private void _invalidateContainerView()
    {
        _x_vgContainer = BottomSheetDialogUtils.findViewById(_bsd, com.google.android.material.R.id.container);
    }

    private void _invalidateContainerStickyView()
    {
        if(_x_vSticky == null) return;
        ViewGroupUtils.addView(_x_vgContainer, _x_vSticky);
    }

    @Override
    @Nullable
    protected View _onFindRootView()
    {
        return BottomSheetDialogUtils.getRootView(_bsd);
    }

    @Override
    @Nullable
    protected View _onFindInflatedView()
    {
        return BottomSheetDialogUtils.getInflatedView(_bsd);
    }
}