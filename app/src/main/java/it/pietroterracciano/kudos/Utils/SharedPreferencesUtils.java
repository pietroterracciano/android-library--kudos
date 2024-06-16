package it.pietroterracciano.kudos.Utils;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Modules.JSONingModule.JSONing;

public final class SharedPreferencesUtils
{
    @Nullable
    public static Integer getInteger(@Nullable SharedPreferences sp, @Nullable String sk)
    {
        if(sp != null && sk != null)
            try { return sp.getInt(sk, 0); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Long getLong(@Nullable SharedPreferences sp, @Nullable String sk)
    {
        if(sp != null && sk != null)
            try { return sp.getLong(sk, 0L); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Boolean getBoolean(@Nullable SharedPreferences sp, @Nullable String sk)
    {
        if(sp != null && sk != null)
            try { return sp.getBoolean(sk, false); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Float getFloat(@Nullable SharedPreferences sp, @Nullable String sk)
    {
        if(sp != null && sk != null)
            try { return sp.getFloat(sk, 0f); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static String getString(@Nullable SharedPreferences sp, @Nullable String sk)
    {
        if(sp != null && sk != null)
            try { return sp.getString(sk, null); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static <T> T getObject(@Nullable SharedPreferences sp, @Nullable String sk, @Nullable Class<T> cls)
    {
        return JSONing.Default.deserialize(cls, getString(sp, sk));
    }
}
