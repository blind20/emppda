package com.tmri.enforcement.app.delegate;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tmri.enforcement.app.R;

import java.util.List;



public class SimpleListAdapter extends BaseMultiItemQuickAdapter<SimpleListBean, BaseViewHolder> {

    public SimpleListAdapter(List<SimpleListBean> data,int itemType) {
        super(data);
        if (itemType == ItemType.ITEM_SIMPLE_LIST_2){
            addItemType(ItemType.ITEM_SIMPLE_LIST_2, R.layout.item_simple_list_2);
        }else if(itemType == ItemType.ITEM_NARROW_LIST_2){
            addItemType(ItemType.ITEM_NARROW_LIST_2, R.layout.item_narrow_list_2);
        }

    }

    @Override
    protected void convert(BaseViewHolder helper, SimpleListBean item) {
        switch (helper.getItemViewType()) {
            case ItemType.ITEM_SIMPLE_LIST_2:
            case ItemType.ITEM_NARROW_LIST_2:
                helper.setText(R.id.tv_item_name, item.getItemName());
                helper.setText(R.id.tv_item_value, item.getItemValue());
                break;
            default:
                break;
        }
    }
}
