package it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Constants.CSymmetricPaddingMode;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricPaddingMode;

public final class SymmetricPaddingModeUtils
{
    @NonNull
    private static final HashMap<String, ESymmetricPaddingMode>
            _hmS2E;
    @NonNull
    private static final HashMap<ESymmetricPaddingMode, String>
            _hmE2S;

    static
    {
        _hmS2E = new HashMap<>(10);
        _hmS2E.put(CSymmetricPaddingMode.None, ESymmetricPaddingMode.None);
        _hmS2E.put(CSymmetricPaddingMode.PKCS1, ESymmetricPaddingMode.PKCS1);
        _hmS2E.put(CSymmetricPaddingMode.PKCS5, ESymmetricPaddingMode.PKCS5);
        _hmS2E.put(CSymmetricPaddingMode.ISO10126, ESymmetricPaddingMode.ISO10126);
        _hmS2E.put(CSymmetricPaddingMode.OAEP, ESymmetricPaddingMode.OAEP);
        _hmS2E.put(CSymmetricPaddingMode.OAEP_SHA1_MGF1, ESymmetricPaddingMode.OAEP_SHA1_MGF1);
        _hmS2E.put(CSymmetricPaddingMode.OAEP_SHA224_MGF1, ESymmetricPaddingMode.OAEP_SHA224_MGF1);
        _hmS2E.put(CSymmetricPaddingMode.OAEP_SHA256_MGF1, ESymmetricPaddingMode.OAEP_SHA256_MGF1);
        _hmS2E.put(CSymmetricPaddingMode.OAEP_SHA384_MGF1, ESymmetricPaddingMode.OAEP_SHA384_MGF1);
        _hmS2E.put(CSymmetricPaddingMode.OAEP_SHA512_MGF1, ESymmetricPaddingMode.OAEP_SHA512_MGF1);
        _hmE2S = new HashMap<>(9);
        _hmE2S.put(ESymmetricPaddingMode.None, CSymmetricPaddingMode.None);
        _hmE2S.put(ESymmetricPaddingMode.PKCS1, CSymmetricPaddingMode.PKCS1);
        _hmE2S.put(ESymmetricPaddingMode.PKCS5, CSymmetricPaddingMode.PKCS5);
        _hmE2S.put(ESymmetricPaddingMode.ISO10126, CSymmetricPaddingMode.ISO10126);
        _hmE2S.put(ESymmetricPaddingMode.OAEP, CSymmetricPaddingMode.OAEP);
        _hmE2S.put(ESymmetricPaddingMode.OAEP_SHA1_MGF1, CSymmetricPaddingMode.OAEP_SHA1_MGF1);
        _hmE2S.put(ESymmetricPaddingMode.OAEP_SHA224_MGF1, CSymmetricPaddingMode.OAEP_SHA224_MGF1);
        _hmE2S.put(ESymmetricPaddingMode.OAEP_SHA256_MGF1, CSymmetricPaddingMode.OAEP_SHA256_MGF1);
        _hmE2S.put(ESymmetricPaddingMode.OAEP_SHA384_MGF1, CSymmetricPaddingMode.OAEP_SHA384_MGF1);
        _hmE2S.put(ESymmetricPaddingMode.OAEP_SHA512_MGF1, CSymmetricPaddingMode.OAEP_SHA512_MGF1);
    }

    @Nullable
    public final static ESymmetricPaddingMode convert(String s) { return _hmS2E.get(s); }
    @Nullable
    public final static String convert(ESymmetricPaddingMode e) { return _hmE2S.get(e); }
}