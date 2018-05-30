package com.silverhetch.aura.connection.storage;

import com.silverhetch.aura.connection.storage.InMemoryStorage;
import com.silverhetch.aura.connection.storage.ResponseStorage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mikes on 12/14/2017.
 */
public class InMemoryStorageTest {
    @Before
    public void setUp() throws Exception {
        InMemoryStorage.getInstance().clear();
    }

    @Test
    public void initial() throws Exception {
        ResponseStorage inMemoryStorage =  InMemoryStorage.getInstance();
        assertEquals(0,inMemoryStorage.responses().size());
    }

    @Test
    public void store_checkSize() throws Exception {
        ResponseStorage storage = InMemoryStorage.getInstance();
        storage.store("url","Response");
        assertEquals(1, storage.responses().size());
    }

    @Test
    public void store_checkContent() throws Exception {
        ResponseStorage storage = InMemoryStorage.getInstance();
        storage.store("url", "Response");
    }
}