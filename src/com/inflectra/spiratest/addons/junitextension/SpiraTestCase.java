package com.inflectra.spiratest.addons.junitextension;

import java.lang.annotation.*;

/**
 * This defines the 'SpiraTestCase' annotation used to specify the
 * SpiraTest test case that the JUnit test maps to
 * 
 * @author		Inflectra Corporation
 * @version		4.0.0
 *
 */
@Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value=java.lang.annotation.ElementType.METHOD)
public @interface SpiraTestCase
{
	int testCaseId ();
}