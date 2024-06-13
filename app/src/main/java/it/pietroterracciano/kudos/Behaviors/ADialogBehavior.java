package it.pietroterracciano.kudos.Behaviors;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.GravityInt;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import it.pietroterracciano.kudos.Enums.EVLayoutParam;
import it.pietroterracciano.kudos.Enums.EWLayoutParam;
import it.pietroterracciano.kudos.Utils.BaseTypes.NumericUtils.intUtils;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;
import it.pietroterracciano.kudos.Utils.Views.WindowUtils;

public abstract class ADialogBehavior<DialogType extends ADialogBehavior<DialogType>>
{
    @NonNull
    private int
        _iWGravity, _iWWindowAnimations, _iWX, _iWY,
        _iRWidth, _iRHeight;
    @NonNull
    private boolean
        _bHasWGravity, _bHasWWindowAnimations, _bHasWX, _bHasWY,
        _bHasRWidth, _bHasRHeight,
        _bIsWDimBehindEnabled,
        _bHasWDimBehindEnabled;
    @Nullable
    private View x_vRoot, x_vInflated;
    @Nullable
    private Window x_oWindow;
    @NonNull
    private Dialog x_oDialog;

    @Nullable
    public View getRootView()
    {
        return x_vRoot;
    }
    @Nullable
    public View getInflatedView()
    {
        return x_vInflated;
    }

    @NonNull
    public DialogType setGravity(@GravityInt @NonNull int i)
    {
        _iWGravity = i;
        _bHasWGravity = true;
        invalidateWGravity();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setWindowAnimations(@NonNull @StyleRes int i)
    {
        _iWWindowAnimations = i;
        _bHasWWindowAnimations = true;
        invalidateWWindowAnimations();
        return (DialogType)this;
    }

    @NonNull
    public DialogType isDimBehindEnabled(@NonNull boolean b)
    {
        _bIsWDimBehindEnabled = b;
        _bHasWDimBehindEnabled = true;
        invalidateWDimBehind();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setX(@NonNull int i)
    {
        _iWX = i;
        _bHasWX = true;
        invalidateWX();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setY(@NonNull int i)
    {
        _iWY = i;
        _bHasWY = true;
        invalidateWY();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setWidth(@NonNull int i)
    {
        _iRWidth = i;
        _bHasRWidth = true;
        invalidateWidth();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setHeight(@NonNull int i)
    {
        _iRHeight = i;
        _bHasRHeight = true;
        invalidateHeight();
        return (DialogType)this;
    }

    protected ADialogBehavior(@NonNull Dialog dlg)
    {
        x_oDialog = dlg;

        invalidateWidth();
        invalidateHeight();
    }

    private void invalidateWGravity()
    {
        invalidateWReference();
        WindowUtils.setLayoutParam(x_oWindow, EWLayoutParam.Gravity, _iWGravity);
    }

    private void invalidateWWindowAnimations()
    {
        invalidateWReference();
        WindowUtils.setLayoutParam(x_oWindow, EWLayoutParam.Animations, _iWWindowAnimations);
    }

    private void invalidateWDimBehind()
    {
        invalidateWReference();

        if(_bIsWDimBehindEnabled)
            WindowUtils.addFlags(x_oWindow, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        else
            WindowUtils.clearFlags(x_oWindow, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    private void invalidateWX()
    {
        invalidateWReference();
        WindowUtils.setLayoutParam(x_oWindow, EWLayoutParam.X, _iWX);
    }

    private void invalidateWY()
    {
        invalidateWReference();
        WindowUtils.setLayoutParam(x_oWindow, EWLayoutParam.Y, _iWY);
    }

    private void invalidateWidth()
    {
        invalidateRReference();
        invalidateWReference();
        ViewUtils.setLayoutParam(x_vRoot, EVLayoutParam.Width, _iRWidth);
        WindowUtils.setLayout(x_oWindow, _iRWidth, _iRHeight);
    }

    private void invalidateHeight()
    {
        invalidateRReference();
        invalidateWReference();
        ViewUtils.setLayoutParam(x_vRoot, EVLayoutParam.Height, _iRHeight);
        WindowUtils.setLayout(x_oWindow, _iRWidth, _iRHeight);
    }

    private void invalidateRReference()
    {
        if(x_vRoot != null) return;

        ViewGroup
            x_oViewGroup = onFindRootViewGroup();

        if(x_oViewGroup == null)
            return;

        x_vRoot = onFindRootView(x_oViewGroup);
        x_vInflated = onFindInflatedView(x_oViewGroup);

        if(!_bHasRWidth)
            _iRWidth = intUtils.convert(ViewUtils.getLayoutParamInt(x_vInflated, EVLayoutParam.Width));

        if(!_bHasRHeight)
            _iRHeight = intUtils.convert(ViewUtils.getLayoutParamInt(x_vInflated, EVLayoutParam.Height));
    }

    @Nullable
    protected <ViewType extends View> ViewType findViewById(@IdRes @NonNull int i)
    {
        return x_oDialog.findViewById(i);
    }

    @Nullable
    protected abstract ViewGroup onFindRootViewGroup();
    @Nullable
    protected abstract View onFindRootView(@NonNull ViewGroup x_oViewGroup);
    @Nullable
    protected abstract View onFindInflatedView(@NonNull ViewGroup x_oViewGroup);

    private void invalidateWReference()
    {
        if(x_oWindow != null) return;

        x_oWindow = x_oDialog.getWindow();

        if(!_bHasWX)
            _iWX = intUtils.convert(WindowUtils.getLayoutParam(x_oWindow, EWLayoutParam.X));

        if(!_bHasWY)
            _iWY = intUtils.convert(WindowUtils.getLayoutParam(x_oWindow, EWLayoutParam.Y));

        if(!_bHasWGravity)
            _iWGravity = intUtils.convert(WindowUtils.getLayoutParam(x_oWindow, EWLayoutParam.Gravity));

        if(!_bHasWWindowAnimations)
            _iWWindowAnimations = intUtils.convert(WindowUtils.getLayoutParam(x_oWindow, EWLayoutParam.Animations));

        if(!_bHasWDimBehindEnabled)
            _bIsWDimBehindEnabled = true;
    }
}
