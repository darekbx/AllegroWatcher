package com.allegrowatcher;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-25.
 */
@RunWith(AndroidJUnit4.class)
public class SummaryActivityTest {

    @Rule
    public ActivityTestRule<SummaryActivity> testRule = new ActivityTestRule<>(
            SummaryActivity.class,
            false,
            false);

    @Test
    public void start_activity() {
        Intent intent = new Intent();

        testRule.launchActivity(intent);

        assertNotNull(testRule.getActivity());
    }

    @Test
    public void show_add_dialog() throws Throwable {
        testRule.launchActivity(new Intent());

        testRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                testRule.getActivity().showAddDialog();
            }
        });
    }
}
