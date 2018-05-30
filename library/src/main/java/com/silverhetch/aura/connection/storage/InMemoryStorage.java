package com.silverhetch.aura.connection.storage;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by mikes on 10/22/2017.
 */

class InMemoryStorage implements ResponseStorage {
    private static final ResponseStorage INSTANCE = new InMemoryStorage();
    private final Map<String, StoredResponse> responsePool;

    public static ResponseStorage getInstance() {
        return INSTANCE;
    }

    private InMemoryStorage() {
        this.responsePool = new LinkedHashMap<>();
    }

    @Override
    public void store(String url, String response) {
        responsePool.put(url, new StoredResponseImpl(System.currentTimeMillis(), response));
    }

    @Override
    public void clear() {
        responsePool.clear();
    }

    @Override
    public StoredResponse response(String url) {
        return responsePool.get(url);
    }

    @Override
    public Collection<StoredResponse> responses() {
        return responsePool.values();
    }
}
