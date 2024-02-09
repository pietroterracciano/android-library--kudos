package it.pietroterracciano.kudos.Views.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.TypesUtils.NumericUtils.intUtils;
import it.pietroterracciano.kudos.Utils.ViewUtils;
import it.pietroterracciano.kudos.Utils.WindowUtils;

public abstract class ADialog<DialogType extends ADialog<DialogType>> extends Dialog
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
    @NonNull
    private View x_vRoot;
    @Nullable
    private Window x_oWindow;

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
        return (DialogType) this;
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
        invalidateRWidth();
        return (DialogType)this;
    }

    @NonNull
    public DialogType setHeight(@NonNull int i)
    {
        _iRHeight = i;
        _bHasRHeight = true;
        invalidateRHeight();
        return (DialogType)this;
    }

    public ADialog()
    {
        this(Kudos.getContext());
    }
    public ADialog(@NonNull Context context)
    {
        super(context);
    }
    public ADialog(@StyleRes int iStyleResourceID)
    {
        this(Kudos.getContext(), iStyleResourceID);
    }
    public ADialog(@NonNull Context context, @StyleRes int themeResId)
    {
        super(context, themeResId);
    }

    protected ADialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener)
    {
        super(context, cancelable, cancelListener);
    }


    @Override
    public void onContentChanged()
    {
        super.onContentChanged();

        ViewGroup x_vgContent = findViewById(android.R.id.content);

        if(x_vgContent != null)
        {
            x_vRoot = x_vgContent.getChildAt(0);

            if(!_bHasRWidth)
                _iRWidth = intUtils.from(ViewUtils.getWidth(x_vRoot));

            if(!_bHasRHeight)
                _iRHeight = intUtils.from(ViewUtils.getHeight(x_vRoot));
        }

        x_oWindow = getWindow();

        if(!_bHasWX)
            _iWX = intUtils.from(WindowUtils.getX(x_oWindow));

        if(!_bHasWY)
            _iWY = intUtils.from(WindowUtils.getY(x_oWindow));

        if(!_bHasWGravity)
            _iWGravity = intUtils.from(WindowUtils.getGravity(x_oWindow));

        if(!_bHasWWindowAnimations)
            _iWWindowAnimations = intUtils.from(WindowUtils.getWindowAnimations(x_oWindow));

        invalidateRWidth();
        invalidateRHeight();

        invalidateWX();
        invalidateWY();
        invalidateWGravity();
        invalidateWWindowAnimations();
        invalidateWDimBehind();
    }



    private void invalidateWGravity()
    {
        WindowUtils.setGravity(x_oWindow, _iWGravity);
    }

    private void invalidateWWindowAnimations()
    {
        WindowUtils.setWindowAnimations(x_oWindow, _iWWindowAnimations);
    }

    private void invalidateWDimBehind()
    {
        if(_bIsWDimBehindEnabled)
            WindowUtils.addFlags(x_oWindow, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        else
            WindowUtils.clearFlags(x_oWindow, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    private void invalidateWX()
    {
        WindowUtils.setX(x_oWindow, _iWX);
    }

    private void invalidateWY()
    {
        WindowUtils.setY(x_oWindow, _iWY);
    }

    private void invalidateRWidth()
    {
       // invalidateRWidthHeight();
        ViewUtils.setWidth(x_vRoot, _iRWidth);
        WindowUtils.setLayout(x_oWindow, _iRWidth, _iRHeight);
    }

    private void invalidateRHeight()
    {
        // invalidateRWidthHeight();
        ViewUtils.setHeight(x_vRoot, _iRHeight);
        WindowUtils.setLayout(x_oWindow, _iRWidth, _iRHeight);
    }

    /*private void invalidateRWidthHeight()
    {
        _iRWidth = ViewUtils.normalizeWidthHeight(_iRWidth);
        _iRHeight = ViewUtils.normalizeWidthHeight(_iRHeight);

        if(
            _iRWidth == WindowManager.LayoutParams.MATCH_PARENT
            && _iRHeight == WindowManager.LayoutParams.MATCH_PARENT
        )
            setCanceledOnTouchOutside(false);
    }*/
}
