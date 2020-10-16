
package com.inflectra.spiratest.addons.junitextension.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TestRun_RecordAutomated1Result" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteAutomatedTestRun" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "testRunRecordAutomated1Result"
})
@XmlRootElement(name = "TestRun_RecordAutomated1Response")
public class TestRunRecordAutomated1Response {

    @XmlElementRef(name = "TestRun_RecordAutomated1Result", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<RemoteAutomatedTestRun> testRunRecordAutomated1Result;

    /**
     * Gets the value of the testRunRecordAutomated1Result property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteAutomatedTestRun }{@code >}
     *     
     */
    public JAXBElement<RemoteAutomatedTestRun> getTestRunRecordAutomated1Result() {
        return testRunRecordAutomated1Result;
    }

    /**
     * Sets the value of the testRunRecordAutomated1Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteAutomatedTestRun }{@code >}
     *     
     */
    public void setTestRunRecordAutomated1Result(JAXBElement<RemoteAutomatedTestRun> value) {
        this.testRunRecordAutomated1Result = ((JAXBElement<RemoteAutomatedTestRun> ) value);
    }

}
