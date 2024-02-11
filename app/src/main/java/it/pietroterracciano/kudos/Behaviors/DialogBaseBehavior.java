package it.pietroterracciano.kudos.Behaviors;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import it.pietroterracciano.kudos.Dialogs.BottomSheetDialog;
import it.pietroterracciano.kudos.Utils.TypesUtils.NumericUtils.intUtils;
import it.pietroterracciano.kudos.Utils.ViewUtils;
import it.pietroterracciano.kudos.Utils.WindowUtils;

public class DialogBaseBehavior
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
    private View x_vRoot;
    @Nullable
    private Window x_oWindow;
    @NonNull
    private Dialog x_oDialog;

    @NonNull
    public DialogBaseBehavior setGravity(@GravityInt @NonNull int i)
    {
        _iWGravity = i;
        _bHasWGravity = true;
        invalidateWGravity();
        return this;
    }

    @NonNull
    public DialogBaseBehavior setWindowAnimations(@NonNull @StyleRes int i)
    {
        _iWWindowAnimations = i;
        _bHasWWindowAnimations = true;
        invalidateWWindowAnimations();
        return this;
    }

    @NonNull
    public DialogBaseBehavior isDimBehindEnabled(@NonNull boolean b)
    {
        _bIsWDimBehindEnabled = b;
        _bHasWDimBehindEnabled = true;
        invalidateWDimBehind();
        return  this;
    }

    @NonNull
    public DialogBaseBehavior setX(@NonNull int i)
    {
        _iWX = i;
        _bHasWX = true;
        invalidateWX();
        return this;
    }

    @NonNull
    public DialogBaseBehavior setY(@NonNull int i)
    {
        _iWY = i;
        _bHasWY = true;
        invalidateWY();
        return this;
    }

    @NonNull
    public DialogBaseBehavior setWidth(@NonNull int i)
    {
        _iRWidth = i;
        _bHasRWidth = true;
        invalidateWidth();
        return this;
    }

    @NonNull
    public DialogBaseBehavior setHeight(@NonNull int i)
    {
        _iRHeight = i;
        _bHasRHeight = true;
        invalidateHeight();
        return this;
    }

    private DialogBaseBehavior(@NonNull Dialog dlg)
    {
        x_oDialog = dlg;

        invalidateWidth();
        invalidateHeight();
    }

    private void invalidateWGravity()
    {
        invalidateWReference();
        WindowUtils.setGravity(x_oWindow, _iWGravity);
    }

    private void invalidateWWindowAnimations()
    {
        invalidateWReference();
        WindowUtils.setWindowAnimations(x_oWindow, _iWWindowAnimations);
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
        WindowUtils.setX(x_oWindow, _iWX);
    }

    private void invalidateWY()
    {
        invalidateWReference();
        WindowUtils.setY(x_oWindow, _iWY);
    }

    private void invalidateWidth()
    {
        invalidateRReference();
        invalidateWReference();
        ViewUtils.setHeight(x_vRoot, _iRWidth);
        WindowUtils.setLayout(x_oWindow, _iRWidth, _iRHeight);
    }

    private void invalidateHeight()
    {
        invalidateRReference();
        invalidateWReference();
        ViewUtils.setHeight(x_vRoot, _iRHeight);
        WindowUtils.setLayout(x_oWindow, _iRWidth, _iRHeight);
    }

    private void invalidateRReference()
    {
        if(x_vRoot != null) return;

        ViewGroup
            x_oViewGroup = x_oDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);

        if(x_oViewGroup != null)
            x_vRoot = x_oViewGroup;
        else
        {
            x_oViewGroup = x_oDialog.findViewById(android.R.id.content);
            if(x_oViewGroup == null) return;
        }

        View
            x_oView = x_oViewGroup.getChildAt(0);

        if(x_vRoot == null)
            x_vRoot = x_oView;

        if(!_bHasRWidth)
            _iRWidth = intUtils.from(ViewUtils.getWidth(x_oView));

        if(!_bHasRHeight)
            _iRHeight = intUtils.from(ViewUtils.getHeight(x_oView));
    }

    private void invalidateWReference()
    {
        if(x_oWindow != null) return;

        x_oWindow = x_oDialog.getWindow();

        if(!_bHasWX)
            _iWX = intUtils.from(WindowUtils.getX(x_oWindow));

        if(!_bHasWY)
            _iWY = intUtils.from(WindowUtils.getY(x_oWindow));

        if(!_bHasWGravity)
            _iWGravity = intUtils.from(WindowUtils.getGravity(x_oWindow));

        if(!_bHasWWindowAnimations)
            _iWWindowAnimations = intUtils.from(WindowUtils.getWindowAnimations(x_oWindow));

        if(!_bHasWDimBehindEnabled)
            _bIsWDimBehindEnabled = true;
    }

    @Nullable
    public static DialogBaseBehavior from(@Nullable Dialog dlg)
    {
        return dlg != null ? new DialogBaseBehavior(dlg) : null;
    }
}
