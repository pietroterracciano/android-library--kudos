package it.pietroterracciano.kudos.Modules.HTTPModule.Payloads;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.charset.Charset;

import it.pietroterracciano.kudos.Constants.CCharacter;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;
import it.pietroterracciano.kudos.Utils.Collections.Primitives.bytesUtils;
import it.pietroterracciano.kudos.Utils.URLUtils;

public final class
    ApplicationXWwwUrlEncodedHTTPingPayload
extends
    ACharsettizedKeyValueHTTPingPayload<ApplicationXWwwUrlEncodedHTTPingPayload, String>
{
    public ApplicationXWwwUrlEncodedHTTPingPayload(@Nullable String sk, @Nullable String sv, @Nullable Charset ec)
    {
        super(sk, sv, ec, EHTTPContentType.ApplicationXWwwFormUrlEncoded);
    }

    @Override
    @Nullable
    protected byte[] _onWrite
    (
        @Nullable String sk,
        @Nullable String sv,
        @Nullable Charset ec
    )
    {
        if(sk == null)
            return null;

        sv = URLUtils.encode(sv, ec);

        if(sv == null)
            return null;

        return
            bytesUtils.convert
            (
             sk + CCharacter.Equal + sv,
                ec
            );
    }
}
