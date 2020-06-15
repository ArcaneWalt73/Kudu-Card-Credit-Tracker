package com.example.kuducredittracker.Resources;


import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
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
    }

}