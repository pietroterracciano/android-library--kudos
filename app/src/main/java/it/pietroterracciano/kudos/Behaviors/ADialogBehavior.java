package it.pietroterracciano.kudos.Behaviors;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import it.pietroterracciano.kudos.Enums.EVLayoutParam;
import it.pietroterracciano.kudos.Enums.EWLayoutParam;
import it.pietroterracciano.kudos.Utils.DataTypes.Primitives.intUtils;
import it.pietroterracciano.kudos.Utils.Dialogs.DialogUtils;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;
import it.pietroterracciano.kudos.Utils.Views.WindowUtils;

public abstract class ADialogBehavior<DialogType extends ADialogBehavior<DialogType>>
{
    @NonNull
    private int
        _iWindowGravity, _iWindowAnimations, _iWindowX, _iWindowY,
        _iRootWidth, _iRootHeight;
    @NonNull
    private boolean
        _bHasWindowGravity, _bHasWindowAnimations, _bHasWindowX, _bHasWindowY,
        _bHasRootWidth, _bHasRootHeight,
        _bIsWindowDimBehindEnabled,
        _bHasWindowDimBehindEnabled;
    @Nullable
    private View _x_vRoot;
    @Nullable
    private View _x_vInflated;
    @Nullable
    private Window _x_wnd;
    @Nullable
    private final Dialog _dlg;

    @Nullable
    public View getRootView()
    {
        return _x_vRoot;
    }
    @Nullable
    public View getInflatedView()
    {
        return _x_vInflated;
    }

    @NonNull
    public DialogType setGravity(@GravityInt @NonNull int i)
    {
        _iWindowGravity = i;
        _bHasWindowGravity = true;
        _invalidateWindowGravity();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setAnimations(@NonNull @StyleRes int i)
    {
        _iWindowAnimations = i;
        _bHasWindowAnimations = true;
        _invalidateWindowAnimations();
        return (DialogType)this;
    }

    @NonNull
    public DialogType isDimBehindEnabled(@NonNull boolean b)
    {
        _bIsWindowDimBehindEnabled = b;
        _bHasWindowDimBehindEnabled = true;
        _invalidateWindowDimBehind();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setX(@NonNull int i)
    {
        _iWindowX = i;
        _bHasWindowX = true;
        _invalidateWindowX();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setY(@NonNull int i)
    {
        _iWindowY = i;
        _bHasWindowY = true;
        _invalidateWindowY();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setWidth(@NonNull int i)
    {
        _iRootWidth = i;
        _bHasRootWidth = true;
        _invalidateWidth();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setHeight(@NonNull int i)
    {
        _iRootHeight = i;
        _bHasRootHeight = true;
        _invalidateHeight();
        return (DialogType)this;
    }

    protected ADialogBehavior(@Nullable Dialog dlg)
    {
        _dlg = dlg;
    }

    public void onContentChange()
    {
        _invalidateRootView();
        _invalidateInflatedView();
        _invalidateWindow();

        _onContentChangeReceive();

        _invalidateWindowGravity();
        _invalidateWindowAnimations();
        _invalidateWindowDimBehind();
        _invalidateWindowX();
        _invalidateWindowY();

        _invalidateWidth();
        _invalidateHeight();
    }

    protected abstract void _onContentChangeReceive();

    private void _invalidateRootView()
    {
        _x_vRoot = _onFindRootView();
    }

    private void _invalidateInflatedView()
    {
        _x_vInflated = _onFindInflatedView();

        if(!_bHasRootWidth)
            _iRootWidth = intUtils.convert(ViewUtils.getLayoutParamInt(_x_vInflated, EVLayoutParam.Width));

        if(!_bHasRootHeight)
            _iRootHeight = intUtils.convert(ViewUtils.getLayoutParamInt(_x_vInflated, EVLayoutParam.Height));
    }

    private void _invalidateWindow()
    {
        _x_wnd = DialogUtils.getWindow(_dlg);

        if(!_bHasWindowX)
            _iWindowX = intUtils.convert(WindowUtils.getLayoutParam(_x_wnd, EWLayoutParam.X));

        if(!_bHasWindowY)
            _iWindowY = intUtils.convert(WindowUtils.getLayoutParam(_x_wnd, EWLayoutParam.Y));

        if(!_bHasWindowGravity)
            _iWindowGravity = intUtils.convert(WindowUtils.getLayoutParam(_x_wnd, EWLayoutParam.Gravity));

        if(!_bHasWindowAnimations)
            _iWindowAnimations = intUtils.convert(WindowUtils.getLayoutParam(_x_wnd, EWLayoutParam.Animations));

        if(!_bHasWindowDimBehindEnabled)
            _bIsWindowDimBehindEnabled = true;
    }

    private void _invalidateWindowGravity()
    {
        WindowUtils.setLayoutParam(_x_wnd, EWLayoutParam.Gravity, _iWindowGravity);
    }

    private void _invalidateWindowAnimations()
    {
        WindowUtils.setLayoutParam(_x_wnd, EWLayoutParam.Animations, _iWindowAnimations);
    }

    private void _invalidateWindowDimBehind()
    {
        if(_bIsWindowDimBehindEnabled)
            WindowUtils.addFlags(_x_wnd, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        else
            WindowUtils.clearFlags(_x_wnd, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    private void _invalidateWindowX()
    {
        WindowUtils.setLayoutParam(_x_wnd, EWLayoutParam.X, _iWindowX);
    }

    private void _invalidateWindowY()
    {
        WindowUtils.setLayoutParam(_x_wnd, EWLayoutParam.Y, _iWindowY);
    }

    private void _invalidateWidth()
    {
        ViewUtils.setLayoutParam(_x_vRoot, EVLayoutParam.Width, _iRootWidth);
        WindowUtils.setLayout(_x_wnd, _iRootWidth, _iRootHeight);
    }

    private void _invalidateHeight()
    {
        ViewUtils.setLayoutParam(_x_vRoot, EVLayoutParam.Height, _iRootHeight);
        WindowUtils.setLayout(_x_wnd, _iRootWidth, _iRootHeight);
    }

    @Nullable
    protected abstract View _onFindRootView();
    @Nullable
    protected abstract View _onFindInflatedView();
}