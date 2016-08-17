package com.allegrowatcher;

import com.allegrowatcher.controllers.AllegroController;
import com.allegrowatcher.db.DataManager;
import com.allegrowatcher.model.Item;
import com.allegrowatcher.model.Summary;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by daba on 2016-08-17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class AllegroControllerTest {

    private DataManager dataManager;
    private Action1<Summary> summaryListener;

    @Before
    public void prepare() {
        dataManager = new DataManager();
        summaryListener = mock(Action1.class);
    }

    @Test
    public void load_summary() {
        dataManager.addAllegroId(RuntimeEnvironment.application, 1001);
        dataManager.addAllegroId(RuntimeEnvironment.application, 1002);
        dataManager.addAllegroId(RuntimeEnvironment.application, 1003);
        dataManager.addAllegroId(RuntimeEnvironment.application, 1004);

        new AllegroControllerMock()
                .loadSummary(RuntimeEnvironment.application, 1, 10, 20)
                .subscribe(summaryListener);

        ArgumentCaptor<Summary> argument = ArgumentCaptor.forClass(Summary.class);
        verify(summaryListener, times(1)).call(argument.capture());

        assertEquals(argument.getValue().itemsCount, 6);
        assertEquals(argument.getValue().newItemsCount, 2);
        assertEquals(argument.getValue().items.get(0).isNew, false);
        assertEquals(argument.getValue().items.get(3).isNew, false);
        assertEquals(argument.getValue().items.get(4).isNew, true);
        assertEquals(argument.getValue().items.get(5).isNew, true);
    }

    public class AllegroControllerMock extends AllegroController {

        @Override
        public List<Item> loadItems(int categoryId, int priceMin, int priceMax) {
            List<Item> items = new ArrayList<>(6);
            items.add(new Item(1001, "Piasta"));
            items.add(new Item(1002, "Kaseta"));
            items.add(new Item(1003, "Kierownica"));
            items.add(new Item(1004, "Widelec"));
            items.add(new Item(1005, "Rama"));
            items.add(new Item(1006, "Korby"));
            return items;
        }
    }
}
