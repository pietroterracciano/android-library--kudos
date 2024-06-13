package it.pietroterracciano.kudos.Modules.HTTPModule.Payloads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.OutputStream;

import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;

public abstract class
    AKeyValueHTTPingPayload
    <
        PayloadType extends AKeyValueHTTPingPayload<PayloadType, ObjectType>,
        ObjectType
    >
extends
        AHTTPingPayload<PayloadType>
{
    @Nullable
    private String _sk;
    @Nullable
    private ObjectType _ov;

    public AKeyValueHTTPingPayload(@NonNull EHTTPContentType ehttpct) { super(ehttpct); }

    @NonNull
    public final PayloadType setKey(@Nullable String sk) {_sk = sk; return (PayloadType)this; }
    @NonNull
    public final PayloadType setValue(@Nullable ObjectType ov) { _ov = ov; return (PayloadType)this; }

    @Override
    @Nullable
    protected final byte[] _onWrite() { return _onWrite(_sk, _ov); }
    @Nullable
    protected abstract byte[] _onWrite(@Nullable String sk, @Nullable ObjectType ov);
}