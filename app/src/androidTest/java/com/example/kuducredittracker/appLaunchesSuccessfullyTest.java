package com.example.kuducredittracker;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.kuducredittracker.AppEntrance.login;
import com.example.kuducredittracker.Resources.UserAccount;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class appLaunchesSuccessfullyTest {
    @Test
    public void appLaunchesSuccessfully(){
        ActivityScenario.launch(login.class);
    }

    @Rule
    public final ActivityTestRule<login> rule = new ActivityTestRule<>(login.class, true, false);

    @Test
    public void doLoginTest(){
        Intents.init();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String[] info = {"1234","123"};
        UserAccount userAccount = new UserAccount(info,appContext);
        userAccount.login(info[0],info[1]);
        rule.launchActivity(new Intent());
        intended(hasComponent(login.class.getName()));
        Intents.release();
    }

    @Test
    public void doRegisterTest(){
        Intents.init();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String[] info = {"1234","123","name","sur","022","email"};
        UserAccount userAccount = new UserAccount(info,appContext);
        userAccount.register(info);
        rule.launchActivity(new Intent());
        intended(hasComponent(login.class.getName()));
        Intents.release();
    }

}
