package it.pietroterracciano.kudos.Utils.Layouts;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.balysv.materialripple.MaterialRippleLayout;

import java.lang.reflect.Field;

import it.pietroterracciano.kudos.Constants.CClass;
import it.pietroterracciano.kudos.Utils.DataTypes.Primitives.intUtils;
import it.pietroterracciano.kudos.Utils.DataTypes.Primitives.booleanUtils;
import it.pietroterracciano.kudos.Utils.FieldUtils;

public abstract class MaterialRippleLayoutUtils
{
    @NonNull
    private static final Field
        _fldMaterialRippleLayout_rippleDuration,
        _fldMaterialRippleLayout_rippleDelayClick;

    @NonNull
    private static final Object
        _lck;

    static
    {
        _fldMaterialRippleLayout_rippleDuration = FieldUtils.get(CClass.MaterialRippleLayout, "rippleDuration");
        _fldMaterialRippleLayout_rippleDelayClick = FieldUtils.get(CClass.MaterialRippleLayout, "rippleDelayClick");
        _lck = new Object();
    }

    public static boolean setOnClickListener
    (
        @Nullable MaterialRippleLayout mrl,
        @Nullable View.OnClickListener lst
    )
    {
        if (mrl == null) return false;

        Boolean
            b = FieldUtils.getValue(mrl, _fldMaterialRippleLayout_rippleDelayClick);

        Integer
            i = FieldUtils.getValue(mrl, _fldMaterialRippleLayout_rippleDuration);

        final boolean
            fb = booleanUtils.convert(b);

        final int
            fi = intUtils.convert(i);

        mrl.setClickable(true);
        mrl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!mrl.isClickable())
                    return;

                mrl.setClickable(false);

                if (lst != null)
                    lst.onClick(v);

                if(!fb || fi < 1)
                {
                    mrl.setClickable(true);
                    return;
                }


                mrl.postDelayed
                (
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                mrl.setClickable(true);
                            }
                        },
                        fi
                );
            }
        });

        return true;
    }
}