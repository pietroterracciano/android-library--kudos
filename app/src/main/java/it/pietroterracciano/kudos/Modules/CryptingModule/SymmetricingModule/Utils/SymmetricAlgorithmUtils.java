package it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Constants.CSymmetricAlgorithm;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricAlgorithm;

public final class SymmetricAlgorithmUtils
{
    @NonNull
    private static final HashMap<String, ESymmetricAlgorithm>
            _hmS2E;
    @NonNull
    private static final HashMap<ESymmetricAlgorithm, String>
            _hmE2S;

    static
    {
        _hmS2E = new HashMap<>(9);
        _hmS2E.put(CSymmetricAlgorithm.AES, ESymmetricAlgorithm.AES);
        _hmS2E.put(CSymmetricAlgorithm.AES128, ESymmetricAlgorithm.AES128);
        _hmS2E.put(CSymmetricAlgorithm.AES256, ESymmetricAlgorithm.AES256);
        _hmS2E.put(CSymmetricAlgorithm.ARC4, ESymmetricAlgorithm.ARC4);
        _hmS2E.put(CSymmetricAlgorithm.BLOWFISH, ESymmetricAlgorithm.BLOWFISH);
        _hmS2E.put(CSymmetricAlgorithm.ChaCha20, ESymmetricAlgorithm.ChaCha20);
        _hmS2E.put(CSymmetricAlgorithm.DES, ESymmetricAlgorithm.DES);
        _hmS2E.put(CSymmetricAlgorithm.DESede, ESymmetricAlgorithm.DESede);
        _hmS2E.put(CSymmetricAlgorithm.RSA, ESymmetricAlgorithm.RSA);
        _hmE2S = new HashMap<>(9);
        _hmE2S.put(ESymmetricAlgorithm.AES, CSymmetricAlgorithm.AES);
        _hmE2S.put(ESymmetricAlgorithm.AES128, CSymmetricAlgorithm.AES128);
        _hmE2S.put(ESymmetricAlgorithm.AES256, CSymmetricAlgorithm.AES256);
        _hmE2S.put(ESymmetricAlgorithm.ARC4, CSymmetricAlgorithm.ARC4);
        _hmE2S.put(ESymmetricAlgorithm.BLOWFISH, CSymmetricAlgorithm.BLOWFISH);
        _hmE2S.put(ESymmetricAlgorithm.ChaCha20, CSymmetricAlgorithm.ChaCha20);
        _hmE2S.put(ESymmetricAlgorithm.DES, CSymmetricAlgorithm.DES);
        _hmE2S.put(ESymmetricAlgorithm.DESede, CSymmetricAlgorithm.DESede);
        _hmE2S.put(ESymmetricAlgorithm.RSA, CSymmetricAlgorithm.RSA);
    }

    @Nullable
    public final static ESymmetricAlgorithm convert(String s) { return _hmS2E.get(s); }
    @Nullable
    public final static String convert(ESymmetricAlgorithm e) { return _hmE2S.get(e); }
}