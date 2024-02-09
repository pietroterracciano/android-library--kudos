package it.pietroterracciano.kudos.Views.RecyclersView.ViewHolders;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Method;
import java.util.HashMap;

import it.pietroterracciano.kudos.Constants.CClass;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.MethodUtils;
import it.pietroterracciano.kudos.Utils.ObjectUtils;
import it.pietroterracciano.kudos.Views.RecyclersView.Adapters.ARVAdapter;
import it.pietroterracciano.kudos.Views.RecyclersView.Enums.ERVEvent;
import it.pietroterracciano.kudos.Views.RecyclersView.Interfaces.IRVOnItemEventListener;

public final class RVViewHolder extends RecyclerView.ViewHolder
{
    @NonNull
    private static final Class<ARVAdapter> __clsAAdapter = ARVAdapter.class;
    @NonNull
    private static final Method
        __mthADPinvalidateItemX = MethodUtils.getDeclared(__clsAAdapter, "invalidateItemX", CClass.View, CClass.Object, CClass.Int);

    @NonNull
    public final ARVAdapter<?> Adapter;
    @NonNull
    public final View View;
    @NonNull
    public final int TransientID;

    private RVViewHolder(@NonNull ARVAdapter<?> adp, @NonNull View v)
    {
        super(v);
        Adapter = adp;
        View = v;
        TransientID = Kudos.newTransientID();
    }

    @NonNull
    private void injectItem(@NonNull Object itm)
    {
        MethodUtils.invoke(Adapter, __mthADPinvalidateItemX, View, itm, getItemViewType());
    }

    @NonNull
    private void injectOnItemEventListeners(@NonNull HashMap<Integer, HashMap<ERVEvent, IRVOnItemEventListener>> hm)
    {
        /*for(Map.Entry<Integer, HashMap<ERVEvent, IRVOnItemEventListener<ItemType>>> me : hm.entrySet())
        {
            if(me == null) continue;

            Integer ki = me.getKey();

            if(ki == null) continue;

            HashMap<ERVEvent, IRVOnItemEventListener<ItemType>>
                hmi = me.getValue();

            if(hmi == null) continue;

            View v = View.findViewById(ki);

            if(v == null) continue;

            for(Map.Entry<ERVEvent, IRVOnItemEventListener<ItemType>> mei : hmi.entrySet())
            {
                IRVOnItemEventListener<ItemType>
                    lstOnEventj = mei.getValue();

                switch (mei.getKey())
                {
                    case OnClick:
                        IRVOnItemClickListener<ItemType>
                            lstOnClickj = ObjectUtils.cast(lstOnEventj);

                        v.setOnClickListener(
                            lstOnClickj != null
                            ?
                                new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(android.view.View v)
                                    {
                                        lstOnClickj.onClick(_oItem);
                                        invalidateX(_oItem);
                                    }
                                }
                            :
                                null
                        );

                        break;

                    case OnTouch:
                        IRVOnItemTouchListener<ItemType>
                            lstOnTouchj = ObjectUtils.cast(lstOnEventj);

                        v.setOnTouchListener(
                            lstOnTouchj != null
                            ?
                                new View.OnTouchListener()
                                {
                                    @Override
                                    public boolean onTouch(android.view.View v, MotionEvent event)
                                    {
                                        boolean b = lstOnTouchj.onTouch(event, _oItem);
                                        invalidateX(_oItem);
                                        return b;
                                    }
                                }
                            :
                                null
                        );

                        break;
                }
            }
        }*/
    }

    @Override
    public boolean equals(Object o)
    {
        return
            (
                o instanceof RVViewHolder
                && ((RVViewHolder)o).TransientID == TransientID
            )
            || super.equals(o);
    }
}