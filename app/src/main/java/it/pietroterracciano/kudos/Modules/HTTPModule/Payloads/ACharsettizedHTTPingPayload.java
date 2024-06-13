package it.pietroterracciano.kudos.Modules.HTTPModule.Payloads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;

public abstract class
    ACharsettizedHTTPingPayload
    <
        PayloadType extends ACharsettizedHTTPingPayload<PayloadType>
    >
extends
        AHTTPingPayload<PayloadType>
{
    @Nullable
    private Charset _ec;

    public ACharsettizedHTTPingPayload(@NonNull EHTTPContentType ehttpct) { super(ehttpct); }
    @NonNull
    public final PayloadType setCharset(@Nullable Charset ec) { _ec = ec; return (PayloadType)this; }

    @Override
    @Nullable
    protected final byte[] _onWrite() { return _onWrite(_ec != null ? _ec : StandardCharsets.UTF_8); }
    @Nullable
    protected abstract byte[] _onWrite(@NonNull Charset ec);
}