
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
 *         &lt;element name="TestCase_AddParameterResult" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteTestCaseParameter" minOccurs="0"/>
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
    "testCaseAddParameterResult"
})
@XmlRootElement(name = "TestCase_AddParameterResponse")
public class TestCaseAddParameterResponse {

    @XmlElementRef(name = "TestCase_AddParameterResult", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<RemoteTestCaseParameter> testCaseAddParameterResult;

    /**
     * Gets the value of the testCaseAddParameterResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteTestCaseParameter }{@code >}
     *     
     */
    public JAXBElement<RemoteTestCaseParameter> getTestCaseAddParameterResult() {
        return testCaseAddParameterResult;
    }

    /**
     * Sets the value of the testCaseAddParameterResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteTestCaseParameter }{@code >}
     *     
     */
    public void setTestCaseAddParameterResult(JAXBElement<RemoteTestCaseParameter> value) {
        this.testCaseAddParameterResult = ((JAXBElement<RemoteTestCaseParameter> ) value);
    }

}
