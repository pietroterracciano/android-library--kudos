package it.pietroterracciano.kudos.Utils;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Modules.JSONingModule.JSONing;

public final class SharedPreferencesEditorUtils
{
    @NonNull
    public static boolean commit(@Nullable SharedPreferences.Editor spe)
    {
        if(spe != null)
            try { return spe.commit(); } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean apply(@Nullable SharedPreferences.Editor spe)
    {
        if(spe != null)
            try {spe.apply(); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean putString(@Nullable SharedPreferences.Editor spe, @Nullable String sk, @Nullable String sv)
    {
        if(spe != null && sk != null)
            try { spe.putString(sk, sv); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean putInt(@Nullable SharedPreferences.Editor spe, @Nullable String sk, @NonNull int iv)
    {
        if(spe != null && sk != null)
            try { spe.putInt(sk, iv); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean putBoolean(@Nullable SharedPreferences.Editor spe, @Nullable String sk, @NonNull boolean bv)
    {
        if(spe != null && sk != null)
            try { spe.putBoolean(sk, bv); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean putFloat(@Nullable SharedPreferences.Editor spe, @Nullable String sk, @NonNull float fv)
    {
        if(spe != null && sk != null)
            try { spe.putFloat(sk, fv); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean putLong(@Nullable SharedPreferences.Editor spe, @Nullable String sk, @NonNull long lv)
    {
        if(spe != null && sk != null)
            try { spe.putLong(sk, lv); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean remove(@Nullable SharedPreferences.Editor spe, @Nullable String sk)
    {
        if(spe != null && sk != null)
            try { spe.remove(sk); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean clear(@Nullable SharedPreferences.Editor spe)
    {
        if(spe != null)
            try { spe.clear(); return true; } catch (Exception ignored) {}

        return false;
    }
}
