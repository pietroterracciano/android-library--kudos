package it.pietroterracciano.kudos.Modules.HTTPModule.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Modules.HTTPModule.Constants.CHTTPConnection;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPConnection;

public final class HTTPingConnectionUtils
{
    @NonNull
    private static final HashMap<String, EHTTPConnection>
        _hmS2E;
    @NonNull
    private static final HashMap<EHTTPConnection, String>
        _hmE2S;

    static
    {
        _hmS2E = new HashMap<>(2);
        _hmS2E.put(CHTTPConnection.Close, EHTTPConnection.Close);
        _hmS2E.put(CHTTPConnection.KeepAlive, EHTTPConnection.KeepAlive);
        _hmE2S = new HashMap<>(2);
        _hmE2S.put(EHTTPConnection.Close, CHTTPConnection.Close);
        _hmE2S.put(EHTTPConnection.KeepAlive, CHTTPConnection.KeepAlive);
    }

    @Nullable
    public final static EHTTPConnection convert(String s) { return _hmS2E.get(s); }
    @Nullable
    public final static String convert(EHTTPConnection e) { return _hmE2S.get(e); }
}
