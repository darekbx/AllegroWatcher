package com.allegrowatcher;

import com.allegrowatcher.db.DataManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DataManagerTest {

    private DataManager dataManager;

    @Before
    public void prepare() {
        dataManager = new DataManager();
    }

    @Test
    public void add_allegro_id() {
        dataManager.addAllegroId(RuntimeEnvironment.application, 1001);
        dataManager.addAllegroId(RuntimeEnvironment.application, Long.MAX_VALUE);
        dataManager.addAllegroId(RuntimeEnvironment.application, Long.MIN_VALUE);

        List<Long> allegroIds = dataManager.getStoredIds(RuntimeEnvironment.application);

        assertEquals(allegroIds.size(), 3);
        assertEquals((long)allegroIds.get(0), 1001);
        assertEquals((long)allegroIds.get(1), Long.MAX_VALUE);
        assertEquals((long)allegroIds.get(2), Long.MIN_VALUE);
    }
}