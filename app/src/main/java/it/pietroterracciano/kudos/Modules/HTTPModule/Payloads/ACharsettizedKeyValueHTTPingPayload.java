package it.pietroterracciano.kudos.Modules.HTTPModule.Payloads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;

public abstract class
    ACharsettizedKeyValueHTTPingPayload
    <
        PayloadType extends ACharsettizedKeyValueHTTPingPayload<PayloadType, ObjectType>,
        ObjectType
    >
extends
        AKeyValueHTTPingPayload<PayloadType, ObjectType>
{
    @Nullable
    private Charset _ec;

    public ACharsettizedKeyValueHTTPingPayload(@NonNull EHTTPContentType ehttpct) { super(ehttpct); }

    @NonNull
    public final PayloadType setCharset(@Nullable Charset ec) { _ec = ec; return (PayloadType)this; }

    @Override
    @Nullable
    protected final byte[] _onWrite(@Nullable String sk, @Nullable ObjectType ov)
    {
        return _onWrite(sk, ov, _ec != null ? _ec : StandardCharsets.UTF_8);
    }
    @Nullable
    protected abstract byte[] _onWrite(@Nullable String sk, @Nullable ObjectType ov, @NonNull Charset ec);
}