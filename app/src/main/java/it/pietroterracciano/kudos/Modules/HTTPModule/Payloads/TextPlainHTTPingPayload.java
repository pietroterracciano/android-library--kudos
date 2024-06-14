package it.pietroterracciano.kudos.Modules.HTTPModule.Payloads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;
import it.pietroterracciano.kudos.Utils.Collections.Primitives.bytesUtils;

public final class
    TextPlainHTTPingPayload
extends
    ACharsettizedHTTPingPayload<TextPlainHTTPingPayload>
{
    @Nullable
    private final String _sv;

    public TextPlainHTTPingPayload(@Nullable String sv)
    {
        this(sv, StandardCharsets.UTF_8);
    }
    public TextPlainHTTPingPayload(@Nullable String sv, @Nullable Charset ec)
    {
        super(ec, EHTTPContentType.TextPlain);
        _sv = sv;
    }

    @Override
    @Nullable
    protected final byte[] _onWrite(@Nullable Charset ec)
    {
        return bytesUtils.convert(_sv, ec);
    }
}