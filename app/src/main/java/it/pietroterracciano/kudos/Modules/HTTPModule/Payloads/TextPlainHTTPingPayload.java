package it.pietroterracciano.kudos.Modules.HTTPModule.Payloads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.OutputStream;
import java.nio.charset.Charset;

import it.pietroterracciano.kudos.Modules.HTTPModule.Constants.CHTTPContentType;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;
import it.pietroterracciano.kudos.Utils.Collections.BytesUtils;
import it.pietroterracciano.kudos.Utils.StreamUtils;

public final class
    TextPlainHTTPingPayload
extends
    ACharsettizedHTTPingPayload<TextPlainHTTPingPayload>
{
    @Nullable
    private String _sv;

    public TextPlainHTTPingPayload() { super(EHTTPContentType.TextPlain); }

    @NonNull
    public final TextPlainHTTPingPayload setValue(@Nullable String sv) { _sv = sv; return this; }

    @Override
    @Nullable
    protected final byte[] _onWrite(@NonNull Charset ec)
    {
        return BytesUtils.convert(_sv, ec);
    }
}