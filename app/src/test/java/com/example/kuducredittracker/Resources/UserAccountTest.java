package com.example.kuducredittracker.Resources;


import org.junit.Test;

import static org.junit.Assert.*;
public class UserAccountTest {

    @Test
    public void login() {
    }

    @Test
    public void register() {
    }

    @Test
    public void Encyptor() {

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