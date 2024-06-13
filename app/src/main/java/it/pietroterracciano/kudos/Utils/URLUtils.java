package it.pietroterracciano.kudos.Utils;

import androidx.annotation.Nullable;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import it.pietroterracciano.kudos.Utils.BaseTypes.StringUtils;

public final class URLUtils
{
    public static URL convert(URI uri)
    {
        if(uri != null)
            try { return uri.toURL(); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static URL convert(String s)
    {
        if(!StringUtils.isBlank(s))
            try { return new URL(s); } catch (Exception ignored) {}

        return null;
    }
    @Nullable
    public static String encode(@Nullable String s) { return encode(s, StandardCharsets.UTF_8); }
    @Nullable
    public static String encode(@Nullable String s, @Nullable Charset ec)
    {
        if(s != null && ec != null)
            try { return URLEncoder.encode(s, ec.displayName()); }
            catch (Exception ignored) { }

        return null;
    }
}
