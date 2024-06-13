package it.pietroterracciano.kudos.Modules.HTTPModule.Payloads;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.charset.Charset;

import it.pietroterracciano.kudos.Constants.CCharacter;
import it.pietroterracciano.kudos.Modules.HTTPModule.Constants.CHTTPContentType;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;
import it.pietroterracciano.kudos.Utils.Collections.BytesUtils;
import it.pietroterracciano.kudos.Utils.URLUtils;

public final class
    ApplicationXWwwUrlEncodedHTTPingPayload
extends
    ACharsettizedKeyValueHTTPingPayload<ApplicationXWwwUrlEncodedHTTPingPayload, String>
{
    public ApplicationXWwwUrlEncodedHTTPingPayload() {  super(EHTTPContentType.ApplicationXWwwFormUrlEncoded); }

    @Override
    @Nullable
    protected byte[] _onWrite
    (
        @Nullable String sk,
        @Nullable String sv,
        @NonNull Charset ec
    )
    {
        if(sk == null)
            return null;

        sv = URLUtils.encode(sv, ec);

        if(sv == null)
            return null;

        return
            BytesUtils.convert
            (
             sk + CCharacter.Equal + sv,
                ec
            );
    }
}
