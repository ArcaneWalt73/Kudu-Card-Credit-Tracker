package com.example.kuducredittracker.Resources;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserAccount.class)
public class UserAccountTest {

    @Test
    public void login() {
        UserAccount userAccount = Mockito.mock(UserAccount.class);
        Mockito.when(userAccount.login(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
    }

    @Test
    public void register() {
        //UserAccount userAccount = Mockito.mock(UserAccount.class);
        //Mockito.when(userAccount.register(Mockito.anyList())).thenReturn(true);
    }

    @Test
    public void emailChecker() {
        assertTrue(UserAccount.emailChecker("chester@gmail.com"));
        assertTrue(UserAccount.emailChecker("chester@students.wits.ac.za"));
        assertFalse(UserAccount.emailChecker("chester"));
        assertFalse(UserAccount.emailChecker("chester@"));
        assertFalse(UserAccount.emailChecker("@gmail.com"));
        assertFalse(UserAccount.emailChecker("chester@.com"));
        assertFalse(UserAccount.emailChecker("chester@gmail.ac.uk"));
        assertFalse(UserAccount.emailChecker(""));

        PowerMockito.spy(UserAccount.class);
        Mockito.when(UserAccount.emailChecker("test@gmail.com")).thenReturn(true);

        Boolean test = UserAccount.emailChecker("test@gmail.com");
        assertEquals(true, test);

        PowerMockito.verifyStatic();
        UserAccount.emailChecker("test@gmail.com");
    }

}