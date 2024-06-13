package it.pietroterracciano.kudos.Modules.HTTPModule.Types;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import it.pietroterracciano.kudos.Utils.BaseTypes.StringUtils;

public final class HTTPingResponse
{
    @NonNull
    private HttpURLConnection
        _httpurlc;

    @Nullable
    private Integer
        _isc;

    @Nullable
    private InputStream
        _is;

    private HTTPingResponse(@NonNull HttpURLConnection httpurlc)
    {
        _httpurlc = httpurlc;
    }

    @Nullable
    public Map<String, List<String>> getHeaders()
    {
        try { return _httpurlc.getHeaderFields(); }
        catch (Exception ignored) { return null; }
    }

    @Nullable
    public String getHeader(String s)
    {
        if(!StringUtils.isBlank(s))
            try { return _httpurlc.getHeaderField(s); }
            catch (Exception ignored) { }

        return null;
    }

    @Nullable
    public String getHeader(int i)
    {
        if(i > -1)
            try { return _httpurlc.getHeaderField(i); }
            catch (Exception ignored) { }

        return null;
    }

    @Nullable
    public InputStream getStream() { return getStream(true); }
    @Nullable
    public InputStream getStream(boolean bRequireStreamQuickly)
    {
        if(_is != null)
            return _is;

        if(!bRequireStreamQuickly)
        {
            int isc = getStatusCode();

            try
            {
                return isc == 200
                    ? (_is = _httpurlc.getInputStream())
                    : (_is  = _httpurlc.getErrorStream());
            }
            catch (Exception oException)
            {
            }

            return null;
        }

        try { _is = _httpurlc.getInputStream(); }
        catch (Exception ignored) {}

        if(_is != null)
            return _is;

        try { _is = _httpurlc.getErrorStream(); }
        catch (Exception ignored) {}

        return _is;
    }

    public int getContentLength()
    {
        try { return _httpurlc.getContentLength(); }
        catch (Exception ignored) { return -1; }
    }

    public int getStatusCode()
    {
        try { return (_isc = _httpurlc.getResponseCode()); }
        catch (Exception ignored) { return _isc = -1; }
    }

    public boolean disconnect()
    {
        try { _httpurlc.disconnect(); return true; }
        catch (Exception ignored) { return false ;}
    }
}
