package it.pietroterracciano.kudos.Utils.Connections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.HttpURLConnection;
import java.net.URLConnection;

import it.pietroterracciano.kudos.Utils.BaseTypes.StringUtils;

public final class HttpURLConnectionUtils
{
    @Nullable
    public static HttpURLConnection convert(@Nullable URLConnection urlc)
    {
        if(urlc != null)
            try {return (HttpURLConnection) urlc;} catch (Exception ignored) {}

        return null;
    }
    @NonNull
    public static boolean setRequestMethod(@Nullable HttpURLConnection httpurlc, @Nullable String s)
    {
        if(httpurlc != null && !StringUtils.isBlank(s))
            try { httpurlc.setRequestMethod(s); return  true; }catch (Exception ignored) {}

        return false;
    }
}
