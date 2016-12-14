package com.silverhetch.util.connection.storage;

/**
 * Created by larryhsiao on 2017/11/8.
 */

public interface StoredResponse {
    long timestamp();
    String responseBody();
}
