package it.pietroterracciano.kudos.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Constants.CCharacter;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Modules.JSONingModule.JSONing;
import it.pietroterracciano.kudos.Utils.DataTypes.StringUtils;
import it.pietroterracciano.kudos.Utils.SharedPreferencesEditorUtils;
import it.pietroterracciano.kudos.Utils.SharedPreferencesUtils;

public final class SharedPreferencesController
{
    @Nullable
    private final transient SharedPreferences _sp;
    @Nullable
    private final transient SharedPreferences.Editor _spe;
    @NonNull
    public final boolean IsInitialized;

    public SharedPreferencesController()
    {
        this(Kudos.getContext(), null);
    }
    public SharedPreferencesController(@Nullable String sp) { this(Kudos.getContext(), sp); }
    public SharedPreferencesController(@Nullable Context cnx)
    {
        this(cnx, null);
    }
    public SharedPreferencesController(@Nullable Context cnx, @Nullable String sn)
    {
        if( cnx == null )
        {
            _sp = null;
            _spe = null;
            IsInitialized = false;
            return;
        }

        StringBuilder
                sb = new StringBuilder();

        sb
            .append("pn")
            .append(CCharacter.DoubleDot)
            .append(cnx.getPackageName())
            .append(CCharacter.Pipe)

            .append("cnx")
            .append(CCharacter.DoubleDot)
            .append("sharedPreferences");

        if(!StringUtils.isBlank(sn))
            sb
                .append("spn")
                .append(CCharacter.DoubleDot)
                .append(sn);

        SharedPreferences sp;
        try { sp = cnx.getSharedPreferences ( sb.toString(), Context.MODE_PRIVATE ); }
        catch (Exception ignored) { sp = null ; }

        SharedPreferences.Editor spe;
        if(sp != null) try { spe = sp.edit(); } catch (Exception ignored) {spe = null;}
        else spe  = null;

        _sp = sp;
        _spe = spe;
        IsInitialized = _sp != null && _spe != null;
    }

    @NonNull
    public SharedPreferencesController putObject(@Nullable String sk, @Nullable Object ov)
    {
        return putString(sk, JSONing.Default.serialize(ov));
    }

    @NonNull
    public SharedPreferencesController putString(@Nullable String sk, @Nullable String sv)
    {
        SharedPreferencesEditorUtils.putString(_spe, sk, sv);
        return this;
    }

    @NonNull
    public SharedPreferencesController putFloat(@Nullable String sk, @NonNull float fv)
    {
        SharedPreferencesEditorUtils.putFloat(_spe, sk, fv);
        return this;
    }

    @NonNull
    public SharedPreferencesController putBoolean(@Nullable String sk, @NonNull boolean bv)
    {
        SharedPreferencesEditorUtils.putBoolean(_spe, sk, bv);
        return this;
    }

    @NonNull
    public SharedPreferencesController putInt(@Nullable String sk, @NonNull int iv)
    {
        SharedPreferencesEditorUtils.putInt(_spe, sk, iv);
        return this;
    }

    @NonNull
    public SharedPreferencesController putLong(@Nullable String sk, @NonNull long lv)
    {
        SharedPreferencesEditorUtils.putLong(_spe, sk, lv);
        return this;
    }

    @NonNull
    public boolean commit() { return SharedPreferencesEditorUtils.commit(_spe); }
    @NonNull
    public boolean apply() { return SharedPreferencesEditorUtils.apply(_spe); }

    @Nullable
    public Integer getInteger(@Nullable String sk) { return SharedPreferencesUtils.getInteger(_sp, sk); }

    @Nullable
    public String getString(@Nullable String sk) { return SharedPreferencesUtils.getString(_sp, sk); }

    @Nullable
    public Long getLong(@Nullable String sk) { return SharedPreferencesUtils.getLong(_sp, sk); }

    @Nullable
    public Float getFloat(@Nullable String sk) { return SharedPreferencesUtils.getFloat(_sp, sk); }

    @Nullable
    public Boolean getBoolean(@Nullable String sk) { return SharedPreferencesUtils.getBoolean(_sp, sk); }

    @Nullable
    public <T> T getObject(@Nullable String sk, @Nullable Class<T> cls) { return SharedPreferencesUtils.getObject(_sp, sk, cls); }

    @NonNull
    public SharedPreferencesController remove(@Nullable String sk)
    {
        SharedPreferencesEditorUtils.remove(_spe, sk);
        return this;
    }

    @NonNull
    public SharedPreferencesController clear()
    {
        SharedPreferencesEditorUtils.clear(_spe);;
        return this;
    }
}