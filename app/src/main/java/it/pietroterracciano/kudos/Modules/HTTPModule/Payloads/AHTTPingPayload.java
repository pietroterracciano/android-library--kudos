package it.pietroterracciano.kudos.Modules.HTTPModule.Payloads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.OutputStream;

import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;
import it.pietroterracciano.kudos.Utils.StreamUtils;

public abstract class AHTTPingPayload<PayloadType extends AHTTPingPayload<PayloadType>>
{
    public final EHTTPContentType ContentType;
    public AHTTPingPayload(@NonNull EHTTPContentType ehttpct) { ContentType = ehttpct;}
    @NonNull
    protected final boolean _write(@Nullable OutputStream os) { return StreamUtils.write(os, _onWrite()); }
    @Nullable
    protected abstract byte[] _onWrite();
}