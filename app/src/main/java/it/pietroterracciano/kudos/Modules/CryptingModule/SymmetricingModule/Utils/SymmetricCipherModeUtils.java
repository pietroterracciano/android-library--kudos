package it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Constants.CSymmetricCipherMode;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricCipherMode;

public final class SymmetricCipherModeUtils
{
    @NonNull
    private static final HashMap<String, ESymmetricCipherMode>
            _hmS2E;
    @NonNull
    private static final HashMap<ESymmetricCipherMode, String>
            _hmE2S;

    static
    {
        _hmS2E = new HashMap<>(9);
        _hmS2E.put(CSymmetricCipherMode.CBC, ESymmetricCipherMode.CBC);
        _hmS2E.put(CSymmetricCipherMode.CFB, ESymmetricCipherMode.CFB);
        _hmS2E.put(CSymmetricCipherMode.CTS, ESymmetricCipherMode.CTS);
        _hmS2E.put(CSymmetricCipherMode.CTR, ESymmetricCipherMode.CTR);
        _hmS2E.put(CSymmetricCipherMode.ECB, ESymmetricCipherMode.ECB);
        _hmS2E.put(CSymmetricCipherMode.GCM, ESymmetricCipherMode.GCM);
        _hmS2E.put(CSymmetricCipherMode.GCM_SIV, ESymmetricCipherMode.GCM_SIV);
        _hmS2E.put(CSymmetricCipherMode.OFB, ESymmetricCipherMode.OFB);
        _hmS2E.put(CSymmetricCipherMode.Poly1305, ESymmetricCipherMode.Poly1305);
        _hmE2S = new HashMap<>(9);
        _hmE2S.put(ESymmetricCipherMode.CBC, CSymmetricCipherMode.CBC);
        _hmE2S.put(ESymmetricCipherMode.CFB, CSymmetricCipherMode.CFB);
        _hmE2S.put(ESymmetricCipherMode.CTS, CSymmetricCipherMode.CTS);
        _hmE2S.put(ESymmetricCipherMode.CTR, CSymmetricCipherMode.CTR);
        _hmE2S.put(ESymmetricCipherMode.ECB, CSymmetricCipherMode.ECB);
        _hmE2S.put(ESymmetricCipherMode.GCM, CSymmetricCipherMode.GCM);
        _hmE2S.put(ESymmetricCipherMode.GCM_SIV, CSymmetricCipherMode.GCM_SIV);
        _hmE2S.put(ESymmetricCipherMode.OFB, CSymmetricCipherMode.OFB);
        _hmE2S.put(ESymmetricCipherMode.Poly1305, CSymmetricCipherMode.Poly1305);
    }

    @Nullable
    public final static ESymmetricCipherMode convert(String s) { return _hmS2E.get(s); }
    @Nullable
    public final static String convert(ESymmetricCipherMode e) { return _hmE2S.get(e); }
}