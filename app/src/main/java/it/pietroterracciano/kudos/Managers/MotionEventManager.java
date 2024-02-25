package it.pietroterracciano.kudos.Managers;

import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Constants.CClass;
import it.pietroterracciano.kudos.Enums.ETEAction;
import it.pietroterracciano.kudos.Models.MotionEventModel;
import it.pietroterracciano.kudos.Models.TouchEventModel;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public class MotionEventManager
{
    @Nullable
    private static Constructor<TouchEventModel>
        _cnsTouchEventModel = ConstructorUtils.getDeclared(TouchEventModel.class, CClass.MotionEventModel, CClass.MotionEventModel, ETEAction.class, CClass.Int);

    @Nullable
    private static Constructor<MotionEventModel>
        _cnsMotionEventModel = ConstructorUtils.getDeclared(MotionEventModel.class, MotionEvent.class);

    @Nullable
    private MotionEventModel
        _mem0,
        _mem1,
        _mem2;

    private int
        _iTapsCount;

    private boolean
        _bAreCoordinatesFixed;

    @Nullable
    private TouchEventModel
        _tem;

    @Nullable
    private ETEAction
        _etea0,
        _etea1;

    public void collect(@Nullable MotionEvent me)
    {
        if(me == null)
            return;

        MotionEventModel mem = ConstructorUtils.newInstance(_cnsMotionEventModel, me);

        if(mem == null)
            return;

        switch (mem.Action)
        {
            case MotionEvent.ACTION_DOWN:
                _mem0 = _mem1 = mem;
                _etea0 = ETEAction.Down;
                _bAreCoordinatesFixed = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(_mem0 == null) _mem0 = mem;
                _mem1 = mem;
                _etea0 = ETEAction.Move;
                if(!_bAreCoordinatesFixed) break;
                _bAreCoordinatesFixed = _mem0.X == _mem1.X && _mem0.Y == _mem1.Y;
                break;
            case MotionEvent.ACTION_UP:
                if(_mem0 == null) _mem0 = mem;
                _mem1 = mem;

                if(_bAreCoordinatesFixed)
                {
                    long l = _mem1.EventTime - _mem0.EventTime;

                    if( l > ViewConfiguration.getLongPressTimeout() )
                    {
                        _mem2 = null;
                        _etea0 = ETEAction.LongPress;
                        _iTapsCount = 1;
                    }
                    else if( l <= ViewConfiguration.getTapTimeout() )
                    {
                        _etea0 = ETEAction.Tap;


                        if(
                            _mem2 != null
                            && (_mem1.EventTime - _mem2.EventTime) <= ViewConfiguration.getDoubleTapTimeout()
                        )
                            _iTapsCount++;
                        else
                            _iTapsCount = 1;

                        _mem2 = _mem1;
                    }
                    else
                    {
                        _iTapsCount = 0;
                        _etea0 = ETEAction.Up;
                    }
                }
                else if( (_mem1.EventTime - _mem0.EventTime) <= ViewConfiguration.getDoubleTapTimeout()  )
                {
                    _iTapsCount = 0;
                    _etea0 = ETEAction.Push;
                }
                else
                {
                    _iTapsCount = 0;
                    _etea0 = ETEAction.Up;
                }

                break;
        }
    }

    @Nullable
    public TouchEventModel buildTouch()
    {
        return ConstructorUtils.newInstance(_cnsTouchEventModel, _mem0, _mem1, _etea0, _iTapsCount);
    }

    private static ETEAction parseAction2TEAction(int i)
    {
        switch (i)
        {
            case MotionEvent.ACTION_DOWN:
                return ETEAction.Down;
            case MotionEvent.ACTION_MOVE:
                return ETEAction.Move;
            case MotionEvent.ACTION_UP:
                return ETEAction.Up;
            case MotionEvent.ACTION_CANCEL:
                return ETEAction.Cancel;
            default:
                return ETEAction.Unknown;
        }
    }
}
