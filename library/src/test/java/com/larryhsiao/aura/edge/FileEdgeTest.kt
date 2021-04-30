package com.larryhsiao.aura.edge

import org.junit.Assert
import org.junit.Test
import java.io.File

/**
 * Some edge/learning test for [File]
 */
class FileEdgeTest {
    /**
     * Constructor test, to find out if files is the same.
     */
    @Test
    fun constructorPath() {
        Assert.assertEquals(
            File("/path/to/target"),
            File(File("/"), "path/to/target")
        )
    }
}