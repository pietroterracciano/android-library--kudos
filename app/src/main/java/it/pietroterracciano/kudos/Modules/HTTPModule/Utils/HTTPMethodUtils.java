package it.pietroterracciano.kudos.Modules.HTTPModule.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Modules.HTTPModule.Constants.CHTTPMethod;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPMethod;

public final class HTTPMethodUtils
{
    @NonNull
    private static final HashMap<String, EHTTPMethod>
            _hmS2E;
    @NonNull
    private static final HashMap<EHTTPMethod, String>
            _hmE2S;

    static
    {
        _hmS2E = new HashMap<>(6);
        _hmS2E.put(CHTTPMethod.Get, EHTTPMethod.Get);
        _hmS2E.put(CHTTPMethod.Post, EHTTPMethod.Post);
        _hmS2E.put(CHTTPMethod.Delete, EHTTPMethod.Delete);
        _hmS2E.put(CHTTPMethod.Put, EHTTPMethod.Put);
        _hmS2E.put(CHTTPMethod.Options, EHTTPMethod.Options);
        _hmS2E.put(CHTTPMethod.Head, EHTTPMethod.Head);
        _hmE2S = new HashMap<>(6);
        _hmE2S.put(EHTTPMethod.Get, CHTTPMethod.Get);
        _hmE2S.put(EHTTPMethod.Post, CHTTPMethod.Post);
        _hmE2S.put(EHTTPMethod.Delete, CHTTPMethod.Delete);
        _hmE2S.put(EHTTPMethod.Put, CHTTPMethod.Put);
        _hmE2S.put(EHTTPMethod.Options, CHTTPMethod.Options);
        _hmE2S.put(EHTTPMethod.Head, CHTTPMethod.Head);
    }

    @Nullable
    public final static EHTTPMethod convert(String s) { return _hmS2E.get(s); }
    @Nullable
    public final static String convert(EHTTPMethod e) { return _hmE2S.get(e); }
}