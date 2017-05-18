package com.example.a90678.lkx_common_17_05_17_16_45.common.view.itemTouchHelper;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/12/19 10:28 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/12/19 10:28 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public interface ItemTouchHelperViewHolder {

    /**
     * Called when the {@link ItemTouchHelper} first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();


    /**
     * Called when the {@link ItemTouchHelper} has completed the move or swipe, and the active item
     * state should be cleared.
     */
    void onItemClear();

}
