package com.inflectra.spiratest.addons.junitextension;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.*;

import com.inflectra.spiratest.addons.junitextension.soap.IImportExport;
import com.inflectra.spiratest.addons.junitextension.soap.IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage;
import com.inflectra.spiratest.addons.junitextension.soap.IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage;
import com.inflectra.spiratest.addons.junitextension.soap.IImportExportTestRunRecordAutomated1ServiceFaultMessageFaultFaultMessage;
import com.inflectra.spiratest.addons.junitextension.soap.ImportExport;
import com.inflectra.spiratest.addons.junitextension.soap.RemoteAutomatedTestRun;
import com.sun.xml.internal.ws.client.ClientTransportException;
import com.sun.xml.internal.ws.wsdl.parser.InaccessibleWSDLException;

/**
 * This defines the 'SpiraTestExecute' class that provides the Java facade for
 * calling the SOAP web service exposed by SpiraTest
 *
 * @author Inflectra Corporation
 * @version 4.0.0
 */
public class SpiraTestExecute {
    private static final String WEB_SERVICE_SUFFIX = "/Services/v3_0/ImportExport.svc?WSDL";
    private static final String WEB_SERVICE_NAMESPACE = "{http://www.inflectra.com/SpiraTest/Services/v3.0/}ImportExport";
    private static final String WEB_SERVICE_NAMESPACE_DATA_OBJECTS = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects";

    private static final String SPIRA_PLUG_IN_NAME = "JUnit Extension";

    public String url;
    public String userName;
    public String password;
    public int projectId;

    private ImportExport service;
    private IImportExport soap = null;
    private IImportExport soap1 = null;


    /**
     * Records a test run
     *
     * @param testCaseId        The test case being executed
     * @param releaseId         The release being executed against (optional)
     * @param testSetId         The test set being executed against (optional)
     * @param executionStatusId The status of the test run (pass/fail/not run)
     * @param runnerName        The name of the automated testing tool
     * @param runnerTestName    The name of the test as stored in JUnit
     * @param runnerAssertCount The number of assertions
     * @param runnerMessage     The failure message (if appropriate)
     * @param runnerStackTrace  The error stack trace (if any)
     * @param endDate           When the test run ended
     * @param startDate         When the test run started
     * @return ID of the new test run
     */
    public int recordTestRun(int testCaseId, Integer releaseId, Integer testSetId, Date startDate,
                             Date endDate, int executionStatusId, String runnerName, String runnerTestName, int runnerAssertCount,
                             String runnerMessage, String runnerStackTrace) {
        String response = "";

        // Instantiate the soap proxy
        try {


            //connect to server if the first time
            if (service == null) {
                URL serviceUrl = new URL(this.url + WEB_SERVICE_SUFFIX);
                setupVars(serviceUrl);
            }

            // Now record the test result
            RemoteAutomatedTestRun remoteTestRun = new RemoteAutomatedTestRun();
            remoteTestRun.setTestCaseId(testCaseId);
            if (releaseId != null) {
                remoteTestRun.setReleaseId(CreateJAXBInteger("ReleaseId", releaseId));
            }
            if (testSetId != null) {
                remoteTestRun.setTestSetId(CreateJAXBInteger("TestSetId", testSetId));
            }
            remoteTestRun.setStartDate(convertDatesJava2Xml(startDate));
            remoteTestRun.setEndDate(CreateJAXBXMLGregorianCalendar("EndDate", convertDatesJava2Xml(endDate)));
            remoteTestRun.setExecutionStatusId(executionStatusId);
            remoteTestRun.setRunnerName(CreateJAXBString("RunnerName", runnerName));
            remoteTestRun.setRunnerTestName(CreateJAXBString("RunnerTestName", runnerTestName));
            remoteTestRun.setRunnerAssertCount(CreateJAXBInteger("RunnerAssertCount", runnerAssertCount));
            remoteTestRun.setRunnerMessage(CreateJAXBString("RunnerMessage", runnerMessage));
            remoteTestRun.setRunnerStackTrace(CreateJAXBString("RunnerStackTrace", runnerStackTrace));

            remoteTestRun = soap.testRunRecordAutomated1(remoteTestRun);
            int testRunId = remoteTestRun.getTestRunId().getValue();
            return testRunId;
        } catch (WebServiceException exception) {
            // Display the error
            System.out.print("Error sending results to SpiraTest server (" + exception.getMessage() + ")\n\n");
            return -1;
        } catch (IImportExportTestRunRecordAutomated1ServiceFaultMessageFaultFaultMessage exception) {
            System.out.print("Error sending results to SpiraTest server (" + exception.getMessage() + ")\n\n");
            return -1;
        }  catch (MalformedURLException exception) {
            System.out.print(
                    "Error creating URL for connecting to SpiraTest server (" + exception.getMessage() + ")\n\n");
            return -1;
        }
    }

    /**
     * Setup the ImportExport service, and the two soap proxies
     */
    private void setupVars(URL serviceUrl) {
        try {
            // Trust all SSL certificates
            SSLUtilities.trustAllHttpsCertificates();

            service = new ImportExport(serviceUrl, QName.valueOf(WEB_SERVICE_NAMESPACE));
            // Try both the HTTP and HTTPS ports
            soap = null;
            soap1 = null;
            try {
                soap = service.getBasicHttpBindingIImportExport();
            } catch (WebServiceException ex) {
                // Ignore as the port will be left as null
            }
            try {
                soap1 = service.getBasicHttpBindingIImportExport1();
            } catch (WebServiceException ex) {
                // Ignore as the port will be left as null
            }

            // If one is NULL, simply set to the same as the other to avoid having to add
            // NULL checks in the subsequent code
            if (soap == null) {
                soap = soap1;
            } else {
                soap1 = soap;
            }

            // If both are NULL, throw exception
            if (soap == null && soap1 == null) {
                // Display the error
                System.out.print(
                        "Unable to connect with either the SpiraTest HTTP or HTTPS APIs. Please check the URL and try again\n\n");
            }

            // Make sure that session is maintained
            Map<String, Object> requestContext = ((BindingProvider) soap).getRequestContext();
            requestContext.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
            Map<String, Object> requestContext1 = ((BindingProvider) soap1).getRequestContext();
            requestContext1.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

            // Authenticate
            boolean success = false;
            try {
                success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
            } catch (InaccessibleWSDLException ex) {
                // Try using the second binding
                soap = soap1;
                success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
            } catch (ClientTransportException ex) {
                // Try using the second binding
                soap = soap1;
                success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
            }
            if (!success) {
                // Display the error
                System.out.print(
                        "Unable to authenticate with SpiraTest server. Please check the username/password and try again\n\n");
            }

            // Connect to the project
            success = soap.connectionConnectToProject(projectId);
            if (!success) {
                System.out.print("Unable to connect to SpiraTest project " + projectId
                        + ", please check that the user is a member of this project\n\n");
            }
        }
        catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception) {
            System.out.print("Error connecting to SpiraTest project (" + exception.getMessage() + ")\n\n");
        } catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception) {
            System.out.print("Error authenticating with SpiraTest server (" + exception.getMessage() + ")\n\n");
        }
    }

    /**
     * Send a test run to Spira from the info in the given test run
     *
     * @return ID of the new test run
     */
    public int recordTestRun(TestRun testRun) {
        Date now = new Date();
        return recordTestRun(testRun.testCaseId, testRun.releaseId == -1 ? null : testRun.releaseId,
                testRun.testSetId == -1 ? null : testRun.testSetId, now, now, testRun.executionStatusId,
                "JUnit", testRun.testName, 1, testRun.message, testRun.stackTrace);
    }

    public static XMLGregorianCalendar convertDatesJava2Xml(Date date) {
        if (date == null) {
            return null;
        }
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            XMLGregorianCalendar xmlCal = datatypeFactory.newXMLGregorianCalendar(calendar);
            // We need to unset the timezone because SpiraTeam is not expected it
            // and it will break concurrency
            xmlCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            return xmlCal;
        } catch (DatatypeConfigurationException ex) {
            return null;
        }
    }

    /***
     * Creates a JAXB web service string element from a Java string
     *
     * @param value
     * @return
     */
    public static JAXBElement<String> CreateJAXBString(String fieldName, String value) {
        JAXBElement<String> jaxString = new JAXBElement<String>(
                new QName(WEB_SERVICE_NAMESPACE_DATA_OBJECTS, fieldName), String.class, value);
        if (value == null) {
            jaxString.setNil(true);
        }
        return jaxString;
    }

    /***
     * Creates a JAXB web service integer element from a Java integer
     *
     * @param value
     * @return
     */
    public static JAXBElement<Integer> CreateJAXBInteger(String fieldName, Integer value) {
        JAXBElement<Integer> jaxInteger = new JAXBElement<Integer>(
                new QName(WEB_SERVICE_NAMESPACE_DATA_OBJECTS, fieldName), Integer.class, value);
        if (value == null) {
            jaxInteger.setNil(true);
        }
        return jaxInteger;
    }

    /***
     * Creates a JAXB web service XMLGregorianCalendar element from a Java
     * XMLGregorianCalendar object
     *
     * @param value
     * @return
     */
    public static JAXBElement<XMLGregorianCalendar> CreateJAXBXMLGregorianCalendar(String fieldName,
                                                                                   XMLGregorianCalendar value) {
        JAXBElement<XMLGregorianCalendar> jaxXMLGregorianCalendar = new JAXBElement<XMLGregorianCalendar>(
                new QName(WEB_SERVICE_NAMESPACE_DATA_OBJECTS, fieldName), XMLGregorianCalendar.class, value);
        if (value == null) {
            jaxXMLGregorianCalendar.setNil(true);
        }
        return jaxXMLGregorianCalendar;
    }
}