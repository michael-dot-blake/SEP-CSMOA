package com.inflectra.spiratest.addons.junitextension;

import org.junit.jupiter.api.extension.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Main class of the extension
 */
public class SpiraExtension implements Extension, BeforeAllCallback, AfterEachCallback {
    private static final int EXECUTION_SUCCESS = 2;
    private static final int EXECUTION_FAILED = 1;
    /**
     * Used to send information to Spira
     */
    private static SpiraTestExecute spiraTestExecute;
    /**
     * All of the test runs we need to post to the server. They are posted when the last unit test finishes
     */
    private static List<TestRun> testRuns;
    /**
     * The total number of tests that will be run
     */
    private static int numTests;

    /**
     * Called before any of the test cases are run. This method sets up the instance
     * @param extensionContext
     * @throws Exception
     */
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        //get all of the methods in the class
        Class clazz = extensionContext.getTestClass().get();
        Method[] methods = clazz.getMethods();

        numTests = 0;

        //increment numTests for every method that has the Spira annotation
        for(Method m : methods) {
            if(m.isAnnotationPresent(SpiraTestCase.class)) {
                numTests++;
            }
        }
        //initialize the array list
        testRuns = new ArrayList<>(numTests);


    }

    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Set<String> tags = extensionContext.getTags();

        //Spira test run we are creating
        TestRun testRun = new TestRun();

        //get the method being tested
        Method method = extensionContext.getTestMethod().get();
        //get the class being tested
        Class clazz = extensionContext.getTestClass().get();

        //only do stuff if there is a spira annotation on the method
        if (method.isAnnotationPresent(SpiraTestCase.class)) {
            //get the annotation and retrieve the testCaseId from it
            SpiraTestCase testCase = method.getAnnotation(SpiraTestCase.class);
            int testCaseId = testCase.testCaseId();
            testRun.testCaseId = testCaseId;

            //only get authentication credentials if they have been marked
            if(clazz.isAnnotationPresent(SpiraTestConfiguration.class)) {
                //get the spira configuration annotation
                Annotation spiraAnnotation = clazz.getAnnotation(SpiraTestConfiguration.class);
                SpiraTestConfiguration testConfiguration = (SpiraTestConfiguration) spiraAnnotation;

                //now populate the credentials in testRun
                testRun.url       = testConfiguration.url();
                testRun.userName  = testConfiguration.login();
                testRun.password  = testConfiguration.password();
                testRun.projectId = testConfiguration.projectId();
                testRun.releaseId = testConfiguration.releaseId();
                testRun.testSetId = testConfiguration.testSetId();
                testRun.testName  = extensionContext.getDisplayName();

                Optional<Throwable> throwable = extensionContext.getExecutionException();
                if(throwable.isPresent()) {
                    //turn stack trace into something readable
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    throwable.get().printStackTrace(pw);
                    testRun.stackTrace = sw.toString();
                    testRun.executionStatusId = EXECUTION_FAILED;
                    testRun.message = "Test Failed";
                }
                else {
                    testRun.stackTrace = "Test Succeeded";
                    testRun.executionStatusId = EXECUTION_SUCCESS;
                }

            }
            //get a list of fields we are missing
            List<String> missing = missingRequiredFields(testRun);
            //if we are good to go
            if(missing.size() == 0) {
                //only need to initialize once
                if(spiraTestExecute == null) {
                    //initialize spiraTestExecute
                    spiraTestExecute = new SpiraTestExecute();
                    spiraTestExecute.password  = testRun.password;
                    spiraTestExecute.userName  = testRun.userName;
                    spiraTestExecute.url       = testRun.url;
                    spiraTestExecute.projectId = testRun.projectId;
                }
                testRuns.add(testRun);

                if(testRuns.size() == numTests) {
                    for(TestRun r : testRuns) {
                        spiraTestExecute.recordTestRun(r);
                    }
                }
            }
            else {
                System.out.println("You need to specify the following to connect to Spira: " + missing.toString());
            }
        }
    }

    /**
     *
     * @return A list with all of the required fields that are missing
     */
    private static List<String> missingRequiredFields(TestRun testRun) {
        List<String> missing = new ArrayList<>(4);
        //Make sure we have the required fields: URL, username, password, and project ID
        if(testRun.url == null || testRun.url.isEmpty()) {
            missing.add("URL");
        }
        if(testRun.userName == null || testRun.userName.isEmpty()) {
            missing.add("Username");
        }
        if(testRun.password == null || testRun.password.isEmpty()) {
            missing.add("Password");
        }
        if(testRun.projectId == -1) {
            missing.add("Project ID");
        }
        return missing;
    }

}
