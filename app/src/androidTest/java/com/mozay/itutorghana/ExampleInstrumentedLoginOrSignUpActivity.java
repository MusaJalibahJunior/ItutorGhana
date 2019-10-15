package com.mozay.itutorghana;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


/**
 * Instrumented activity_main, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedLoginOrSignUpActivity {
    @Test
    public void useAppContext() {
        // Context of the app under activity_main.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.mozay.itutorghana", appContext.getPackageName());
    }
}
