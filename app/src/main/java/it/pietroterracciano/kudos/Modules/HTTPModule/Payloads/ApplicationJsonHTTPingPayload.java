package it.pietroterracciano.kudos.Modules.HTTPModule.Payloads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;
import it.pietroterracciano.kudos.Modules.JSONingModule.JSONing;
import it.pietroterracciano.kudos.Utils.Collections.Primitives.bytesUtils;

public final class
    ApplicationJsonHTTPingPayload
extends
    AHTTPingPayload<ApplicationJsonHTTPingPayload>
{
    @Nullable
    private Object _o;
    @Nullable
    private JSONing _jsoning;

    public ApplicationJsonHTTPingPayload() { super(EHTTPContentType.ApplicationJson); }
    @NonNull
    public ApplicationJsonHTTPingPayload setValue(@Nullable Object o) { _o = o; return this; }
    @NonNull
    public ApplicationJsonHTTPingPayload setJSONing(@Nullable JSONing jsoning) { _jsoning = jsoning; return this; }

    @Override
    @Nullable
    protected byte[] _onWrite()
    {
        if(_jsoning == null)
            _jsoning = JSONing.Default;

        return
            bytesUtils.convert
            (
                _jsoning.serialize(_o)
            );
    }
}
