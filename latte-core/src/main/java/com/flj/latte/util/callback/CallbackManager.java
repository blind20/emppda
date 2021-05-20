package com.flj.latte.util.callback;

import java.util.WeakHashMap;

/**
 * Created by
 */

public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();
    private String PARAM;

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }

    public CallbackManager setParam(String param) {
        PARAM = param;
        return this;
    }
    public String getPARAM() {
        return PARAM;
    }
}
