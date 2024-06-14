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
    private final Charset _ec;

    public ACharsettizedHTTPingPayload
    (
        @NonNull EHTTPContentType ehttpct
    )
    {
        this(StandardCharsets.UTF_8, ehttpct);
    }

    public ACharsettizedHTTPingPayload
    (
        @Nullable Charset ec,
        @NonNull EHTTPContentType ehttpct
    )
    {
        super(ehttpct);
        _ec = ec;
    }

    @Override
    @Nullable
    protected final byte[] _onWrite() { return _onWrite(_ec); }
    @Nullable
    protected abstract byte[] _onWrite(@Nullable Charset ec);
}