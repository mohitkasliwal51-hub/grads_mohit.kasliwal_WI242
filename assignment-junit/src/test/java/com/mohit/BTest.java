package com.mohit;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class BTest {
    @Test
    void testInterfaceMethodCall() {

        I mockI = mock(I.class); // No real implementation used

        B b = new B(mockI);

        b.perform();

        verify(mockI).abc(); // Verify method was called
    }

    @Test
    void testVoidMethod() {

        I mockI = mock(I.class);

        doNothing().when(mockI).abc();
        // doThrow(new RuntimeException()).when(mockI).abc();

        B b = new B(mockI);
        b.perform();

        verify(mockI).abc();
    }

    @Test
    void testMethodCalledMultipleTimes() {

        I mockI = mock(I.class);

        B b = new B(mockI);

        b.performMultipleTimes();

        verify(mockI, times(3)).abc();
    }
    // verify(mockI, atLeast(1)).abc();
    // verify(mockI, atMost(5)).abc();
    // verify(mockI, never()).abc();
}
