package com.example.joseluis.registraralunmo;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Created by JOSE LUIS on 4/04/2018.
 */

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener=listener;
    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foreView =((Adapter_alumno.viewholder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(foreView);
        }
    }
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foreView=((Adapter_alumno.viewholder) viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver(c,recyclerView,foreView,dX,dY,actionState,isCurrentlyActive);
    }


    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foreView=((Adapter_alumno.viewholder)viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foreView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foreView=((Adapter_alumno.viewholder)viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver(c,recyclerView,foreView,dX,dY,actionState,isCurrentlyActive);
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiper(viewHolder,direction,viewHolder.getAdapterPosition());
    }
    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }
    public interface RecyclerItemTouchHelperListener
    {
        void onSwiper(RecyclerView.ViewHolder viewHolder,int direction,int position);
    }
}
