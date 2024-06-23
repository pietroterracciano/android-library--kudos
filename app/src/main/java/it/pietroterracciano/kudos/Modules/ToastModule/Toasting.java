package it.pietroterracciano.kudos.Modules.ToastModule;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Modules.ToastModule.Builders.ToastingBuilder;
import it.pietroterracciano.kudos.Modules.ToastModule.Descriptors.ToastingDescriptor;
import it.pietroterracciano.kudos.Modules.ToastModule.Enums.EToastDuration;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public final class Toasting
{
    @NonNull
    private static final List<Toasting>
        __l;
    @NonNull
    private static final Constructor<ToastingBuilder>
        __cnsToastingBuilder;

    static
    {
        __l = new ArrayList<Toasting>();
        __cnsToastingBuilder = ConstructorUtils.getDeclared(ToastingBuilder.class);
    }

    @NonNull
    public static ToastingBuilder requireBuilder()
    {
        return ConstructorUtils.createInstance(__cnsToastingBuilder);
    }

    public static void cancelAll()
    {
        synchronized (__l)
        {
            int j = __l.size();
            for(int i=0; i<j; i++) __l.get(i).cancel();
            __l.clear();
        }
    }

    @Nullable
    private final Toast _tst;

    private Toasting(@NonNull ToastingDescriptor td)
    {
        Context cnt = td.Context;
        if(cnt == null) cnt = Kudos.getContext();

        Toast tst;

        if(cnt != null)
            try { tst = new Toast(cnt); }
            catch (Exception ignored) { tst = null; }
        else
            tst = null;

        _tst = tst;

        if(_tst == null)
            return;

        if(td.Duration != null)
            try { _tst.setDuration(td.Duration == EToastDuration.Long ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT); } catch (Exception ignored) {}

        if(td.Text != null)
            try { _tst.setText(td.Text); } catch (Exception ignored) {}

        if(td.View != null)
            try { _tst.setView(td.View); } catch (Exception ignored) {}

        synchronized (__l)
        {
            __l.add(this);
        }
    }

    @NonNull
    public final boolean show()
    {
        if(_tst != null)
            try { _tst.show(); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public final boolean cancel()
    {
        if(_tst != null)
            try { _tst.cancel(); return true; } catch (Exception ignored) {}

        return false;
    }
}
