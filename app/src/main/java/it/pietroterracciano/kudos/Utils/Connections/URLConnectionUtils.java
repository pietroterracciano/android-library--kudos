package it.pietroterracciano.kudos.Utils.Connections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import it.pietroterracciano.kudos.Utils.DataTypes.StringUtils;

public final class URLConnectionUtils
{
    @Nullable
    public static URLConnection openConnection(@Nullable URL url)
    {
        return openConnection(url, Proxy.NO_PROXY);
    }
    @Nullable
    public static URLConnection openConnection(@Nullable URL url, @Nullable Proxy prx)
    {
        if(url != null && prx != null)
            try { return url.openConnection(prx); }
            catch (Exception ignored) { }

        return null;
    }

    @NonNull
    public static boolean setRequestProperty(@Nullable URLConnection urlc, @Nullable String sk, @Nullable String sv)
    {
        if(urlc != null && !StringUtils.isBlank(sk))
            try { urlc.setRequestProperty(sk, sv); return true; }
            catch (Exception ignored) {}

        return false;
    }
    @NonNull
    public static boolean setUseCaches(@Nullable URLConnection urlc, @NonNull boolean b)
    {
        if(urlc != null)
            try { urlc.setUseCaches(b); return true; }
            catch (Exception ignored) {}

        return false;
    }
    @NonNull
    public static boolean setDefaultUseCaches(@Nullable URLConnection urlc, @NonNull boolean b)
    {
        if(urlc != null)
            try { urlc.setDefaultUseCaches(b); return true; }
            catch (Exception ignored) {}

        return false;
    }
    @NonNull
    public static boolean setConnectTimeout(@Nullable URLConnection urlc, @NonNull int i)
    {
        if(urlc != null && i > 0)
            try { urlc.setConnectTimeout(i); return true; }
            catch (Exception ignored) {}

        return false;
    }
    @NonNull
    public static boolean setReadTimeout(@Nullable URLConnection urlc, @NonNull int i)
    {
        if(urlc != null && i > -1)
            try { urlc.setReadTimeout(i); return true; }
            catch (Exception ignored) {}

        return false;
    }
    @NonNull
    public static boolean setDoInput(@Nullable URLConnection urlc, @NonNull boolean b)
    {
        if(urlc != null )
            try { urlc.setDoInput(b); return true; }
            catch (Exception ignored) {}

        return false;
    }
    @NonNull
    public static boolean setDoOutput(@Nullable URLConnection urlc, @NonNull boolean b)
    {
        if(urlc != null )
            try { urlc.setDoOutput(b); return true; }
            catch (Exception ignored) {}

        return false;
    }
}