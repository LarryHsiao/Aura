package com.silverhetch.aura.connection.storage;

import java.util.Collection;

/**
 * Created by mikes on 10/22/2017.
 */

public interface ResponseStorage {
    void store(String url, String response);

    StoredResponse response(String url);

    Collection<StoredResponse> responses();

    void clear();
}
