package com.mohit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class ITest {
    @Test
    void testInterfaceMethodCall() {

        I mockI = mock(I.class); // No real implementation used

        // Call method
        mockI.abc();

        // Verify it was called once
        verify(mockI).abc();
    }

    @Test
    void testMethodCallMultipleTimes() {

        I mockI = mock(I.class);

        mockI.abc();
        mockI.abc();
        mockI.abc();

        verify(mockI, times(3)).abc();
    }

    @Test
    void testVoidMethodThrowsException() {

        I mockI = mock(I.class);

        doThrow(new RuntimeException())
                .when(mockI).abc();

        assertThrows(RuntimeException.class, () -> {
            mockI.abc();
        });
    }

    // verify(mockI, atLeast(1)).abc();
    // verify(mockI, atMost(5)).abc();
    // verify(mockI, never()).abc();
}
