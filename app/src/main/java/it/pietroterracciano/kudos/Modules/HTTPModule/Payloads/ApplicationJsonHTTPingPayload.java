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
    private final Object _o;
    @Nullable
    private final JSONing _jsoning;

    public ApplicationJsonHTTPingPayload(@Nullable Object o)
    {
        this(o, JSONing.Default);
    }
    public ApplicationJsonHTTPingPayload(@Nullable Object o, @Nullable JSONing jsoning)
    {
        super(EHTTPContentType.ApplicationJson);
        _o = o;
        _jsoning = jsoning;
    }

    @Override
    @Nullable
    protected byte[] _onWrite()
    {
        return
            _jsoning != null
            ?
                bytesUtils.convert
                (
                    _jsoning.serialize(_o)
                )
            :
                null;
    }
}
