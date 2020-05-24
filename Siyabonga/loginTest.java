

import android.content.Context;

import org.junit.Test;

import static org.junit.Assert.*;

public class loginTest {
   


    @Test
    public void readStringFromContext_LocalizedString() {

        login myObjectUnderTest = new login();

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.Output_From_PHP;

        // ...then the result should be the expected one.
        assertEquals(result, "12345");//Assuming 12345 is the correcet output return from php
    }
}