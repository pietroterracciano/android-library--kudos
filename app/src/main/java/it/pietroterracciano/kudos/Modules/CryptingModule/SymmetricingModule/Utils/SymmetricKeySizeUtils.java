package it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Constants.CSymmetricKeySize;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricKeySize;

public final class SymmetricKeySizeUtils
{
    @NonNull
    private static final HashMap<Integer, ESymmetricKeySize>
            _hmI2E;
    @NonNull
    private static final HashMap<ESymmetricKeySize, Integer>
            _hmE2I;

    static
    {
        _hmI2E = new HashMap<>(5);
        _hmI2E.put(CSymmetricKeySize._128bit, ESymmetricKeySize._128bit);
        _hmI2E.put(CSymmetricKeySize._160bit, ESymmetricKeySize._160bit);
        _hmI2E.put(CSymmetricKeySize._192bit, ESymmetricKeySize._192bit);
        _hmI2E.put(CSymmetricKeySize._224bit, ESymmetricKeySize._224bit);
        _hmI2E.put(CSymmetricKeySize._256bit, ESymmetricKeySize._256bit);
        _hmE2I = new HashMap<>(5);
        _hmE2I.put(ESymmetricKeySize._128bit, CSymmetricKeySize._128bit);
        _hmE2I.put(ESymmetricKeySize._160bit, CSymmetricKeySize._160bit);
        _hmE2I.put(ESymmetricKeySize._192bit, CSymmetricKeySize._192bit);
        _hmE2I.put(ESymmetricKeySize._224bit, CSymmetricKeySize._224bit);
        _hmE2I.put(ESymmetricKeySize._256bit, CSymmetricKeySize._256bit);
    }

    @Nullable
    public final static ESymmetricKeySize convert(Integer i) { return _hmI2E.get(i); }
    @Nullable
    public final static Integer convert(ESymmetricKeySize e) { return _hmE2I.get(e); }
}