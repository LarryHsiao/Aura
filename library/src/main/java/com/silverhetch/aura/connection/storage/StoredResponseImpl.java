package com.silverhetch.aura.connection.storage;

/**
 * Created by larryhsiao on 2017/11/8.
 */

class StoredResponseImpl implements StoredResponse {
    private final long timeStamp;
    private final String response;

    StoredResponseImpl(long timeStamp, String response) {
        this.timeStamp = timeStamp;
        this.response = response;
    }

    @Override
    public long timestamp() {
        return timeStamp;
    }

    @Override
    public String responseBody() {
        return response;
    }
}
