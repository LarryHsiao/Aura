package com.larryhsiao.aura.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mikes on 11/17/2017.
 */

class SharedPreferenceResponseStorage implements ResponseStorage {
    private static final String STORAGE_NAME = "SERVER_DATA_STORAGE";
    private final SharedPreferences preferences;

    SharedPreferenceResponseStorage(Context context) {
        this.preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void store(String url, String response) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("time", System.currentTimeMillis());
        jsonObject.addProperty("response", response);
        preferences.edit().putString(url, jsonObject.toString()).apply();
    }

    @Override
    public StoredResponse response(String url) {
        String jsonWithTime = preferences.getString(url, "");
        return obtainResponseObject(jsonWithTime);
    }

    private StoredResponse obtainResponseObject(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        return new StoredResponseImpl(jsonObject.get("time").getAsLong(), jsonObject.get("response").getAsString());
    }

    @Override
    public Collection<StoredResponse> responses() {
        List<StoredResponse> responses = new ArrayList<>();
        for (Object response : preferences.getAll().values()) {
            responses.add(obtainResponseObject((String) response));
        }
        return responses;
    }

    @Override
    public void clear() {
        preferences.edit().clear().apply();
    }
}
