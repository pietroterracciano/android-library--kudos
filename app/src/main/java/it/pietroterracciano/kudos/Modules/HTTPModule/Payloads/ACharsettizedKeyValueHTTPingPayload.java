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
    private final Charset _ec;

    public ACharsettizedKeyValueHTTPingPayload
    (
        @Nullable String sk,
        @Nullable ObjectType ov,
        @NonNull EHTTPContentType ehttpct
    )
    {
        this(sk, ov, StandardCharsets.UTF_8, ehttpct);
    }
    public ACharsettizedKeyValueHTTPingPayload
    (
        @Nullable String sk,
        @Nullable ObjectType ov,
        @Nullable Charset ec,
        @NonNull EHTTPContentType ehttpct
    )
    {
        super(sk, ov, ehttpct);
        _ec = ec;
    }

    @Override
    @Nullable
    protected final byte[] _onWrite(@Nullable String sk, @Nullable ObjectType ov)
    {
        return _onWrite(sk, ov, _ec);
    }
    @Nullable
    protected abstract byte[] _onWrite(@Nullable String sk, @Nullable ObjectType ov, @Nullable Charset ec);
}