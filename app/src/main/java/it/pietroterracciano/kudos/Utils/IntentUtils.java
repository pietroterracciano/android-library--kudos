package it.pietroterracciano.kudos.Utils;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Modules.JSONingModule.JSONing;
import it.pietroterracciano.kudos.Utils.DataTypes.StringUtils;

public final class IntentUtils
{
    @Nullable
    public static boolean putExtra(@Nullable Intent ntt, @Nullable String sk, @Nullable Object ov)
    {
        return putExtra(ntt, sk, JSONing.Default.serialize(ov));
    }

    @Nullable
    public static boolean putExtra(@Nullable Intent ntt, @Nullable String sk, @Nullable String sv)
    {
        if(ntt != null)
            try { ntt.putExtra(sk, sv); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putExtra(@Nullable Intent ntt, @Nullable String sk, @NonNull int iv)
    {
        if(ntt != null)
            try { ntt.putExtra(sk, iv); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putExtra(@Nullable Intent ntt, @Nullable String sk, @NonNull boolean bv)
    {
        if(ntt != null)
            try { ntt.putExtra(sk, bv); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putExtra(@Nullable Intent ntt, @Nullable String sk, @NonNull short shrv)
    {
        if(ntt != null)
            try { ntt.putExtra(sk, shrv); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putExtra(@Nullable Intent ntt, @Nullable String sk, @NonNull double dv)
    {
        if(ntt != null)
            try { ntt.putExtra(sk, dv); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean putExtra(@Nullable Intent ntt, @Nullable String sk, @NonNull float fv)
    {
        if(ntt != null)
            try { ntt.putExtra(sk, fv); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static <T> T getObjectExtra(@Nullable Intent ntt, @Nullable String sk, @Nullable Class<T> cls)
    {
        return ntt != null
            ? BundleUtils.getObject(ntt.getExtras(), sk, cls)
            : null;
    }

    @Nullable
    public static String getStringExtra(@Nullable Intent ntt, @Nullable String sk)
    {
        return ntt != null
            ? BundleUtils.getString(ntt.getExtras(), sk)
            : null;
    }

    @Nullable
    public static Integer getIntegerExtra(@Nullable Intent ntt, @Nullable String sk)
    {
        return ntt != null
            ? BundleUtils.getInteger(ntt.getExtras(), sk)
            : null;
    }

    @Nullable
    public static Boolean getBooleanExtra(@Nullable Intent ntt, @Nullable String sk)
    {
        return ntt != null
            ? BundleUtils.getBoolean(ntt.getExtras(), sk)
            : null;
    }

    @Nullable
    public static Short getShortExtra(@Nullable Intent ntt, @Nullable String sk)
    {
        return ntt != null
            ? BundleUtils.getShort(ntt.getExtras(), sk)
            : null;
    }

    @Nullable
    public static Double getDoubleExtra(@Nullable Intent ntt, @Nullable String sk)
    {
        return ntt != null
            ? BundleUtils.getDouble(ntt.getExtras(), sk)
            : null;
    }

    @Nullable
    public static Float getFloatExtra(@Nullable Intent ntt, @Nullable String sk)
    {
        return ntt != null
            ? BundleUtils.getFloat(ntt.getExtras(), sk)
            : null;
    }

    @Nullable
    public static String getAction(@Nullable Intent ntt)
    {
        return ntt != null
            ? ntt.getAction()
            : null;
    }
}