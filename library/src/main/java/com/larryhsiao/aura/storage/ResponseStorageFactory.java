package com.larryhsiao.aura.storage;

import android.content.Context;

/**
 * Created by mikes on 12/23/2017.
 */

public class ResponseStorageFactory {
    private final Context context;

    public ResponseStorageFactory(Context context) {
        this.context = context;
    }

    public ResponseStorage inMemory(){
        return InMemoryStorage.getInstance();
    }

    public ResponseStorage sharedPreference(){
        return new SharedPreferenceResponseStorage(context);
    }
}
