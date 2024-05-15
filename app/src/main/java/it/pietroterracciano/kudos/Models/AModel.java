package it.pietroterracciano.kudos.Models;

import androidx.annotation.NonNull;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ObjectUtils;

public abstract class AModel<ModelType extends AModel<ModelType>>
{
    @NonNull
    public final int TransientID;

    public AModel()
    {
        TransientID = Kudos.newTransientID();
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        else if(!(o instanceof AModel))
            return false;

        AModel<ModelType>
            m = ObjectUtils.cast(o);

        return
            (
                m != null
                && m.TransientID == TransientID
            )
            || super.equals(o);
    }
}
