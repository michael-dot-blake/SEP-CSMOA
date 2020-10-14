
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
 *         &lt;element name="TestRun_CreateFromTestCasesResult" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}ArrayOfRemoteManualTestRun" minOccurs="0"/>
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
    "testRunCreateFromTestCasesResult"
})
@XmlRootElement(name = "TestRun_CreateFromTestCasesResponse")
public class TestRunCreateFromTestCasesResponse {

    @XmlElementRef(name = "TestRun_CreateFromTestCasesResult", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfRemoteManualTestRun> testRunCreateFromTestCasesResult;

    /**
     * Gets the value of the testRunCreateFromTestCasesResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRemoteManualTestRun }{@code >}
     *     
     */
    public JAXBElement<ArrayOfRemoteManualTestRun> getTestRunCreateFromTestCasesResult() {
        return testRunCreateFromTestCasesResult;
    }

    /**
     * Sets the value of the testRunCreateFromTestCasesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRemoteManualTestRun }{@code >}
     *     
     */
    public void setTestRunCreateFromTestCasesResult(JAXBElement<ArrayOfRemoteManualTestRun> value) {
        this.testRunCreateFromTestCasesResult = ((JAXBElement<ArrayOfRemoteManualTestRun> ) value);
    }

}
