package com.flj.latte.ui.recycler;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;
    private Context mContext;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            return null;
//            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

    public Context getContext() {
        return mContext;
    }

    public DataConverter setContext(Context context) {
        this.mContext = context;
        return this;
    }

    public void clearData(){
        ENTITIES.clear();
    }
}
