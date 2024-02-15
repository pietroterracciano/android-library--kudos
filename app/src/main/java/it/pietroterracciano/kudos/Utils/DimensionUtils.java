package it.pietroterracciano.kudos.Utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Kudos;

public abstract class DimensionUtils
{
    private static final HashMap<Integer, HashMap<Float, Float>>
            _hmTValues2Values2Pixels = new HashMap<>();

    @Nullable
    public static Float parse2Pixels(float f, int i)
    {
        return parse2Pixels(Kudos.getDisplayMetrics(), f, i);
    }
    @Nullable
    public static Float parse2Pixels(DisplayMetrics dm, float f, int i)
    {
        if(dm == null)
            return null;

        HashMap<Float, Float>
            hmValues2Pixels = _hmTValues2Values2Pixels.computeIfAbsent(i, j -> new HashMap<>());

        Float
            f2Pixels = hmValues2Pixels.get(f);

        if(f2Pixels == null)
        {
            try
            {
                f2Pixels =
                    TypedValue.applyDimension
                    (
                        i,
                        f,
                        dm
                    );
            }
            catch(Exception ignored)
            {
            }

            if(f2Pixels != null)
                hmValues2Pixels.put(f, f2Pixels);
        }

        return f2Pixels;
    }
}
