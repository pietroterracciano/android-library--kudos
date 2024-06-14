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
    private final String _sk;
    @Nullable
    private final ObjectType _ov;

    public AKeyValueHTTPingPayload
    (
        @Nullable String sk,
        @Nullable ObjectType ov,
        @NonNull EHTTPContentType ehttpct
    )
    {
        super(ehttpct);
        _sk = sk;
        _ov = ov;
    }

    @Override
    @Nullable
    protected final byte[] _onWrite() { return _onWrite(_sk, _ov); }
    @Nullable
    protected abstract byte[] _onWrite(@Nullable String sk, @Nullable ObjectType ov);
}