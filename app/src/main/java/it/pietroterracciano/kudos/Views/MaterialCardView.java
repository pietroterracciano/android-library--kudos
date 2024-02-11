package it.pietroterracciano.kudos.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.shape.CornerFamily;

import it.pietroterracciano.kudos.R;

public class MaterialCardView extends com.google.android.material.card.MaterialCardView
{
    public MaterialCardView(@NonNull Context context)
    {
        super(context);
        _this(context, null, 0, 0);
    }

    public MaterialCardView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        _this(context, attrs, 0, 0);
    }

    public MaterialCardView(@NonNull Context context, @Nullable AttributeSet attrs, @NonNull int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        _this(context, attrs, defStyleAttr, 0);
    }

    public MaterialCardView setTopLeftRadius(@NonNull float f)
    {
        setShapeAppearanceModel(getShapeAppearanceModel().toBuilder().setTopLeftCorner(CornerFamily.ROUNDED, f).build());
        return this;
    }

    public MaterialCardView setTopRightRadius(@NonNull float f)
    {
        setShapeAppearanceModel(getShapeAppearanceModel().toBuilder().setTopRightCorner(CornerFamily.ROUNDED, f).build());
        return this;
    }

    public MaterialCardView setBottomLeftRadius(@NonNull float f)
    {
        setShapeAppearanceModel(getShapeAppearanceModel().toBuilder().setBottomLeftCorner(CornerFamily.ROUNDED, f).build());
        return this;
    }

    public MaterialCardView setBottomRightRadius(@NonNull float f)
    {
        setShapeAppearanceModel(getShapeAppearanceModel().toBuilder().setBottomRightCorner(CornerFamily.ROUNDED, f).build());
        return this;
    }

    private void _this(@Nullable Context context, @Nullable AttributeSet attrs, @NonNull int defStyleAttr, @NonNull int defStyleRes)
    {
        if(context == null)
            return;

        TypedArray
            oTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CardView, defStyleAttr, defStyleRes);

        if(oTypedArray.hasValue(R.styleable.CardView_cardCornerTopLeftRadius))
            setTopLeftRadius(oTypedArray.getDimension(R.styleable.CardView_cardCornerTopLeftRadius, 0.0f));

        if(oTypedArray.hasValue(R.styleable.CardView_cardCornerTopRighRadius))
            setTopRightRadius(oTypedArray.getDimension(R.styleable.CardView_cardCornerTopRighRadius, 0.0f));

        if(oTypedArray.hasValue(R.styleable.CardView_cardCornerBottomLeftRadius))
            setBottomLeftRadius(oTypedArray.getDimension(R.styleable.CardView_cardCornerBottomLeftRadius, 0.0f));

        if(oTypedArray.hasValue(R.styleable.CardView_cardCornerBottomRightRadius))
            setBottomRightRadius(oTypedArray.getDimension(R.styleable.CardView_cardCornerBottomRightRadius, 0.0f));

        oTypedArray.recycle();
    }
}
