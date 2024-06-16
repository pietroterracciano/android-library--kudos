package it.pietroterracciano.kudos.Utils;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Modules.JSONingModule.JSONing;

public final class BundleUtils
{
    @Nullable
    public static boolean putObject(@Nullable Bundle bnd, @Nullable String sk, @Nullable Object ov)
    {
        return
            putString(bnd, sk, JSONing.Default.serialize(ov));
    }

    @Nullable
    public static boolean putString(@Nullable Bundle bnd, @Nullable String sk, @Nullable String sv)
    {
        if(bnd != null)
            try { bnd.putString(sk, sv); return true; }
            catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putInt(@Nullable Bundle bnd, @Nullable String sk, @NonNull int iv)
    {
        if(bnd != null)
            try { bnd.putInt(sk, iv); return true; }
            catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putBoolean(@Nullable Bundle bnd, @Nullable String sk, @NonNull boolean bv)
    {
        if(bnd != null)
            try { bnd.putBoolean(sk, bv); return true; }
            catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putShort(@Nullable Bundle bnd, @Nullable String sk, @NonNull short shrv)
    {
        if(bnd != null)
            try { bnd.putShort(sk, shrv); return true; }
            catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putDouble(@Nullable Bundle bnd, @Nullable String sk, @NonNull double dv)
    {
        if(bnd != null)
            try { bnd.putDouble(sk, dv); return true; }
            catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putFloat(@Nullable Bundle bnd, @Nullable String sk, @NonNull float fv)
    {
        if(bnd != null)
            try { bnd.putFloat(sk, fv); return true; }
            catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static <T> T getObject(@Nullable Bundle bnd, @Nullable String sk, @Nullable Class<T> cls)
    {
        return
            bnd != null
                ? JSONing.Default.deserialize(cls, getString(bnd, sk))
                : null;
    }

    @Nullable
    public static String getString(@Nullable Bundle bnd, @Nullable String sk)
    {
        if(bnd != null)
            try { return bnd.getString(sk); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Integer getInteger(@Nullable Bundle bnd, @Nullable String sk)
    {
        if(bnd != null)
            try { return bnd.getInt(sk, 0); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Boolean getBoolean(@Nullable Bundle bnd, @Nullable String sk)
    {
        if(bnd != null)
            try { return bnd.getBoolean(sk, false); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Short getShort(@Nullable Bundle bnd, @Nullable String sk)
    {
        if(bnd != null)
            try { return bnd.getShort(sk, (short)0); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Double getDouble(@Nullable Bundle bnd, @Nullable String sk)
    {
        if(bnd != null)
            try { return bnd.getDouble(sk, 0d); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Float getFloat(@Nullable Bundle bnd, @Nullable String sk)
    {
        if(bnd != null)
            try { return bnd.getFloat(sk, 0f); }
            catch (Exception ignored) {}

        return null;
    }
}
