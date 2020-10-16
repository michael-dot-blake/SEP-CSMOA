package com.inflectra.spiratest.addons.junitextension.samples;

import com.inflectra.spiratest.addons.junitextension.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Some simple tests using the ability to return results back to SpiraTest
 *
 * @author Inflectra Corporation
 * @version 4.0.0
 */
@SpiraTestConfiguration(
        //following are REQUIRED
        url = "https://demo.spiraservice.net/peter-inflectra",
        login = "fredbloggs",
        password = "PleaseChange",
        projectId = 1,
        //following are OPTIONAL
        releaseId = 7,
        testSetId = 1
)

public class SimpleTest {
    protected int fValue1;
    protected int fValue2;

    /**
     * Sets up the unit test
     */
    @BeforeEach
    public void setUp() {
        fValue1 = 2;
        fValue2 = 3;
    }

    /**
     * Tests the addition of the two values
     */
    @Test
    @SpiraTestCase(testCaseId = 22)
    public void testAdd() {
        double result = fValue1 + fValue2;

        // forced failure result == 5
        assertTrue(result == 6);
    }

    /**
     * Tests division by zero
     */
    @Test
    @SpiraTestCase(testCaseId = 22)
    public void testDivideByZero() {
        int zero = 0;
        int result = 8 / zero;
    }

    /**
     * Tests two equal values
     */
    @Test
    @SpiraTestCase(testCaseId = 22)
    public void testEquals() {
        assertEquals(12, 12);
        assertEquals(12L, 12L);
        assertEquals(new Long(12), new Long(12));

        assertEquals(12, 13, "Size");
        assertEquals(12.0, 11.99, 0.0, "Capacity");
    }

    /**
     * Tests success
     */
    @Test
    @SpiraTestCase(testCaseId = 22)
    public void testSuccess() {
        //Successful test
        assertEquals(12, 12);
    }
}