package it.pietroterracciano.kudos.Modules.JSONingModule;

import static java.lang.reflect.Modifier.TRANSIENT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public final class JSONing
{
    @NonNull
    public static final JSONing
        Default;

    static
    {
        Default =
            new JSONing
            (
                new GsonBuilder()
                    .excludeFieldsWithModifiers(TRANSIENT)
                    .create()
            );
    }

    @NonNull
    private final Gson _gson;

    @Nullable
    public static JSONing build(@Nullable GsonBuilder gb)
    {
        if(gb != null)
            try { return new JSONing(gb.create()); } catch (Exception ignored) {}

        return null;
    }

    private JSONing(@NonNull Gson gson)
    {
        _gson = gson;
    }

    @Nullable
    public String serialize(@Nullable Object o)
    {
        if(o != null)
            try { return _gson.toJson(o); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public <T> T deserialize(@Nullable TypeToken<T> tt, @Nullable String s)
    {
        if(s != null && tt != null)
            try {return _gson.fromJson(s, tt); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public <T> T deserialize(@Nullable Class<T> cls, @Nullable String s)
    {
        if(s != null && cls != null)
            try {return _gson.fromJson(s, cls); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public <T> T deserialize(@Nullable Type t, @Nullable String s)
    {
        if(s != null && t != null)
            try {return _gson.fromJson(s, t); }
            catch (Exception ignored) {}

        return null;
    }
}
