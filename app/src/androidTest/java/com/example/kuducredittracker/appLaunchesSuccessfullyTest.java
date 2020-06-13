package com.example.kuducredittracker;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.kuducredittracker.AppEntrance.login;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class appLaunchesSuccessfullyTest {
    @Rule
    public ActivityTestRule<login> activityRule = new ActivityTestRule(login.class);

    @Test
    public void appLaunchesSuccessfully(){
        ActivityScenario.launch(login.class);
    }
}
