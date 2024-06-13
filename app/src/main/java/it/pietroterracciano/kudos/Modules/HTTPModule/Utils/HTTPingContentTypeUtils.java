package it.pietroterracciano.kudos.Modules.HTTPModule.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Modules.HTTPModule.Constants.CHTTPConnection;
import it.pietroterracciano.kudos.Modules.HTTPModule.Constants.CHTTPContentType;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPConnection;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;


public final class HTTPingContentTypeUtils
{
    @NonNull
    private static final HashMap<String, EHTTPContentType>
            _hmS2E;
    @NonNull
    private static final HashMap<EHTTPContentType, String>
            _hmE2S;

    static
    {
        _hmS2E = new HashMap<>(2);
        _hmS2E.put(CHTTPContentType.TextPlain, EHTTPContentType.TextPlain);
        _hmS2E.put(CHTTPContentType.ApplicationJson, EHTTPContentType.ApplicationJson);
        _hmS2E.put(CHTTPContentType.ApplicationXWwwFormUrlEncoded, EHTTPContentType.ApplicationXWwwFormUrlEncoded);
        _hmS2E.put(CHTTPContentType.MultipartFormData, EHTTPContentType.MultipartFormData);
        _hmE2S = new HashMap<>(2);
        _hmE2S.put(EHTTPContentType.TextPlain, CHTTPContentType.TextPlain);
        _hmE2S.put(EHTTPContentType.ApplicationJson, CHTTPContentType.ApplicationJson);
        _hmE2S.put(EHTTPContentType.ApplicationXWwwFormUrlEncoded, CHTTPContentType.ApplicationXWwwFormUrlEncoded);
        _hmE2S.put(EHTTPContentType.MultipartFormData, CHTTPContentType.MultipartFormData);
    }

    @Nullable
    public final static EHTTPContentType convert(String s) { return _hmS2E.get(s); }
    @Nullable
    public final static String convert(EHTTPContentType e) { return _hmE2S.get(e); }
}
